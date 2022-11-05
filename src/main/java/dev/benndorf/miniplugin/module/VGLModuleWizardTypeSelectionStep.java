package dev.benndorf.miniplugin.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.ui.IdeBorderFactory;

import javax.swing.*;

public class VGLModuleWizardTypeSelectionStep extends ModuleWizardStep {

    private VGLProjectCreator creator;

    private JPanel panel;
    private JPanel chooserPanel;
    private JPanel infoPanel;
    private JEditorPane infoPane;
    private JRadioButton checkOutVGlButton;
    private JRadioButton newAddonButton;
    private JCheckBox addonIncludeTestServer;
    private JCheckBox vglIncludeTestServer;
    private JCheckBox vglInclude1vs1;
    private JCheckBox vglIncludeDeathmatch;

    public VGLModuleWizardTypeSelectionStep(VGLProjectCreator creator) {
        this.creator = creator;

        addonIncludeTestServer.setEnabled(false);
        vglIncludeTestServer.setEnabled(false);
        vglInclude1vs1.setEnabled(false);
        vglIncludeDeathmatch.setEnabled(false);
    }

    @Override
    public JComponent getComponent() {
        chooserPanel.setBorder(IdeBorderFactory.createBorder());
        infoPanel.setBorder(IdeBorderFactory.createBorder());

        checkOutVGlButton.addActionListener((e) -> toggle(true));
        newAddonButton.addActionListener((e) -> toggle(false));

        addonIncludeTestServer.addActionListener((e) -> refreshInfoPane());
        vglIncludeTestServer.addActionListener((e) -> refreshInfoPane());
        vglInclude1vs1.addActionListener((e) -> refreshInfoPane());
        vglIncludeDeathmatch.addActionListener((e) -> refreshInfoPane());

        return panel;
    }

    private void toggle(boolean enableVGL) {
        addonIncludeTestServer.setEnabled(!enableVGL);

        vglIncludeTestServer.setEnabled(enableVGL);
        vglInclude1vs1.setEnabled(enableVGL);
        vglIncludeDeathmatch.setEnabled(enableVGL);

        refreshInfoPane();
    }

    private void refreshInfoPane() {
        StringBuilder text = new StringBuilder("");
        if (checkOutVGlButton.isSelected()) {
            text.append(
                    "The VoxelGamesLib framework project will be checked out and setup so that you are able to develop on vgl itself. \n");
            if (vglIncludeTestServer.isSelected()) {
                text.append(
                        "Additionally, the testserver will be checked out and setup so that you can easily test your changes. \n");
            }
            if (vglIncludeDeathmatch.isSelected() || vglInclude1vs1.isSelected()) {
                text.append("Additonally, the following gamemodes will be installed: \n");
                if (vglInclude1vs1.isSelected()) {
                    text.append("1vs1 \n");
                }
                if (vglIncludeDeathmatch.isSelected()) {
                    text.append("Deathmatch \n");
                }
            }
        } else if (newAddonButton.isSelected()) {
            text.append(
                    "A new addon project will be created, so that you can get started developing gamemodes using VoxelGamesLib. \n");
            if (addonIncludeTestServer.isSelected()) {
                text.append(
                        "Additionally, the testserver will be checked out and setup so that you can easily test your new gamemode. \n");
            }
        }
        infoPane.setText(text.toString());
    }

    @Override
    public void updateDataModel() {
        creator.setProjectType(checkOutVGlButton.isSelected() ? VGLProjectType.FRAMEWORK : VGLProjectType.ADDON);
        if (addonIncludeTestServer.isSelected() || vglIncludeTestServer.isSelected()) {
            creator.addAdditionalModule("testserver");
        }
        if (vglInclude1vs1.isSelected()) {
            creator.addAdditionalModule("1vs1");
        }
        if (vglIncludeDeathmatch.isSelected()) {
            creator.addAdditionalModule("Deathmatch");
        }
    }

    @Override
    public boolean validate() throws ConfigurationException {
        return checkOutVGlButton.isSelected() || newAddonButton.isSelected();
    }
}
