package me.minidigger.miniplugin;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiPrimitiveType;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class MissingJSR305Inspection extends BaseInspection {

    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        return "Missing JSR-305 annotation";
    }

    @NotNull
    @Override
    protected String buildErrorString(Object... infos) {
        Type type = (Type) infos[0];
        switch (type) {
            case RETURN_TYPE:
                return "Missing JSR-305 annotation on return type";
            case PARAM:
                return "Missing JSR-305 annotation on parameter " + infos[1];
        }

        return "Missing JSR-305 annotation";
    }

    @Override
    public BaseInspectionVisitor buildVisitor() {
        return new BaseInspectionVisitor() {
            @Override
            public void visitMethod(PsiMethod method) {
                if (method.getReturnType() != null && !(method.getReturnType() instanceof PsiPrimitiveType)) {
                    if (Arrays.stream(method.getModifierList().getAnnotations())
                            .map(PsiAnnotation::getQualifiedName)
                            .noneMatch(name -> name.equals("javax.annotation.Nonnull") || name.equals("javax.annotation.Nullable"))) {
                        registerMethodError(method, Type.RETURN_TYPE);
                    }
                }

                if (method.getParameterList().getParametersCount() != 0) {
                    Arrays.stream(method.getParameterList().getParameters())
                            .filter(psiParameter -> !(psiParameter.getType() instanceof PsiPrimitiveType))
                            .filter(psiParameter -> psiParameter.getModifierList() != null)
                            .forEach(psiParameter -> {
                                if (Arrays.stream(psiParameter.getModifierList().getAnnotations())
                                        .map(PsiAnnotation::getQualifiedName)
                                        .filter(Objects::nonNull)
                                        .noneMatch(name -> name.equals("javax.annotation.Nonnull") || name.equals("javax.annotation.Nullable"))) {
                                    registerError(psiParameter, Type.PARAM, psiParameter.getName());
                                }
                            });
                }
            }
        };
    }

    enum Type {
        RETURN_TYPE,
        PARAM,
    }
}
