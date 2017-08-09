package me.minidigger.miniplugin.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import me.minidigger.miniplugin.assets.Assets;

public class VGLFacetType extends FacetType<VGLFacet, VGLFacetConfig> {

    public static String TYPE_ID = "vgl";

    public VGLFacetType() {
        super(VGLFacet.ID, TYPE_ID, "VoxelGamesLib");
    }

    @Override
    public VGLFacetConfig createDefaultConfiguration() {
        return new VGLFacetConfig();
    }

    @Override
    public VGLFacet createFacet(@NotNull Module module, String name, @NotNull VGLFacetConfig configuration, @Nullable Facet underlyingFacet) {
        return new VGLFacet(module, name, configuration, underlyingFacet);
    }

    @Override
    public boolean isSuitableModuleType(ModuleType moduleType) {
        return moduleType instanceof JavaModuleType;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return Assets.VGL_ICON;
    }
}
