package me.minidigger.miniplugin;

import com.intellij.codeInspection.reference.EntryPoint;
import com.intellij.codeInspection.reference.RefElement;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.psi.PsiElement;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlatformAnnotationEntryPoint extends EntryPoint {

    @NotNull
    @Override
    public String getDisplayName() {
        return "MiniPlugin Entry Point";
    }

    @Nullable
    @Override
    public String[] getIgnoreAnnotations() {
        return new String[]{"com.voxelgameslib.voxelgameslib.event.GameEvent"};
    }

    @Override
    public boolean isEntryPoint(@NotNull RefElement refElement, @NotNull PsiElement psiElement) {
        return false;
    }

    @Override
    public boolean isEntryPoint(@NotNull PsiElement psiElement) {
        return false;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public void setSelected(boolean selected) {

    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {

    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {

    }
}