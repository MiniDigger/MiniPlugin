package dev.benndorf.miniplugin.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;

import javax.swing.*;

public class VGLModuleWizardMetadataStep extends ModuleWizardStep {

    private VGLProjectCreator creator;

    private JPanel panel;
    private JTextField groupId;
    private JTextField artifactId;
    private JTextField version;
    private JComboBox<String> buildSystem;

    public VGLModuleWizardMetadataStep(VGLProjectCreator creator) {
        this.creator = creator;
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public void updateDataModel() {
        creator.setGroupId(groupId.getText());
        creator.setArtifactId(artifactId.getText());
        creator.setVersion(version.getText());
        creator.setBuildSystem(
                buildSystem.getSelectedItem().equals("Gradle") ? BuildSystemType.GRADLE : BuildSystemType.MAVEN);
    }

    @Override
    public boolean isStepVisible() {
        return creator.getProjectType().equals(VGLProjectType.ADDON);
    }

    @Override
    public boolean validate() throws ConfigurationException {
        if (buildSystem.getSelectedItem() == null) {
            return showError(buildSystem, "Please select a build system");
        }
        if (groupId.getText().isEmpty()) {
            return showError(groupId, "The group id cannot be empty");
        }
        if (artifactId.getText().isEmpty()) {
            return showError(artifactId, "The artifact id cannot be empty");
        }
        if (version.getText().isEmpty()) {
            return showError(version, "The version cannot be empty");
        }
        if (!groupId.getText().matches("\\S+")) {
            return showError(groupId, "The group id cannot contain any whitespace");
        }
        if (!artifactId.getText().matches("\\S+")) {
            return showError(artifactId, "The artifact id cannot contain any whitespace");
        }

        return true;
    }

    private boolean showError(JComponent element, String text) {
        JBPopupFactory.getInstance().createHtmlTextBalloonBuilder(text, MessageType.ERROR, null)
                .setFadeoutTime(2000)
                .createBalloon()
                .show(RelativePoint.getSouthWestOf(element), Balloon.Position.below);
        return false;
    }
}
