package me.minidigger.miniplugin.facet;

import com.intellij.facet.ui.FacetEditorTab;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class VGLFacetEditorTab extends FacetEditorTab {

    private VGLFacetConfig config;

    public VGLFacetEditorTab(VGLFacetConfig config) {
        this.config = config;
    }

    @NotNull
    @Override
    public JComponent createComponent() {
        return null;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "VGL Module Settings";
    }
}
