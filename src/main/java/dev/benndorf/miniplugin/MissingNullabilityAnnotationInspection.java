package dev.benndorf.miniplugin;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiPrimitiveType;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class MissingNullabilityAnnotationInspection extends BaseInspection {

    private static final Set<String> acceptedNames = Set.of(
            "javax.annotation.Nonnull", "javax.annotation.Nullable", // jsr-305
            "org.jetbrains.annotations.NotNull", "org.jetbrains.annotations.Nullable", // intellij
            "org.checkerframework.checker.nullness.qual.Nullable", "org.checkerframework.checker.nullness.qual.NonNull"
            // checker framework
    );

    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        return "Missing nullability annotation";
    }

    public String dum() {
        return "";
    }

    @NotNull
    @Override
    protected String buildErrorString(Object... infos) {
        Type type = (Type) infos[0];
        switch (type) {
            case RETURN_TYPE:
                return "Missing nullability annotation on return type";
            case PARAM:
                return "Missing nullability annotation on parameter " + infos[1];
        }

        return "Missing nullability annotation";
    }

    @Override
    public BaseInspectionVisitor buildVisitor() {
        return new BaseInspectionVisitor() {
            @Override
            public void visitMethod(PsiMethod method) {
                if (method.getReturnType() != null && !(method.getReturnType() instanceof PsiPrimitiveType)) {
                    if (Arrays.stream(method.getModifierList().getAnnotations())
                            .map(PsiAnnotation::getQualifiedName)
                            .noneMatch(acceptedNames::contains)) {
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
                                        .noneMatch(acceptedNames::contains)) {
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
