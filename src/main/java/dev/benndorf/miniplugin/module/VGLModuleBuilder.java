package dev.benndorf.miniplugin.module;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import dev.benndorf.miniplugin.assets.Assets;

public class VGLModuleBuilder extends JavaModuleBuilder {

    private VGLProjectCreator creator = new VGLProjectCreator();

    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {

    }

    @Override
    public ModuleType getModuleType() {
        return JavaModuleType.getModuleType();
    }

    @Override
    public String getParentGroup() {
        return VGLModuleType.getInstance().getName();
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
            @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{
                new VGLModuleWizardMetadataStep(creator)
        };
    }

    @Nullable
    @Override
    public ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
        return new VGLModuleWizardTypeSelectionStep(creator);
    }

    @Override
    public boolean validate(Project current, Project dest) {
        return true;
    }

    @Override
    public String getPresentableName() {
        return VGLModuleType.NAME;
    }

    @Override
    public String getGroupName() {
        return VGLModuleType.NAME;
    }

    @Override
    public int getWeight() {
        return JavaModuleBuilder.BUILD_SYSTEM_WEIGHT - 1;
    }

    @Nullable
    @Override
    public String getBuilderId() {
        return "VGL_MODULE";
    }

    @Override
    public Icon getNodeIcon() {
        return Assets.VGL_ICON;
    }

    @Override
    public boolean isSuitableSdkType(SdkTypeId sdkType) {
        return sdkType.equals(JavaSdk.getInstance());
    }
}
