package dev.benndorf.miniplugin.module;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import dev.benndorf.miniplugin.assets.Assets;

public class VGLModuleType extends ModuleType<VGLModuleBuilder> {

    private static final String ID = "VGL_MODULE_TYPE";
    public static String NAME = "VoxelGamesLib";

    public VGLModuleType() {
        super(ID);
    }

    public static VGLModuleType getInstance() {
        return (VGLModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    @NotNull
    @Override
    public VGLModuleBuilder createModuleBuilder() {
        return new VGLModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "VoxelGamesLib Project";
    }

    @Override
    public Icon getNodeIcon(boolean b) {
        return Assets.VGL_ICON;
    }

    @Override
    public Icon getIcon() {
        return Assets.VGL_ICON;
    }
}
