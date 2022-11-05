package dev.benndorf.miniplugin;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class AddJSR305AnnotationQuickFix extends BaseIntentionAction {

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "MiniPlugin";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {

    }
}
