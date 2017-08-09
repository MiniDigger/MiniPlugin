package me.minidigger.miniplugin.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;

import org.jdom.Element;
import org.jetbrains.annotations.Nullable;

public class VGLFacetConfig implements FacetConfiguration, PersistentStateComponent<VGLFacetConfigData> {

    private VGLFacet facet = null;
    private VGLFacetConfigData state = new VGLFacetConfigData();

    @Override
    public FacetEditorTab[] createEditorTabs(FacetEditorContext editorContext, FacetValidatorsManager validatorsManager) {
        return new FacetEditorTab[]{new VGLFacetEditorTab(this)};
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {
        //empty
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
        // empty
    }

    @Nullable
    @Override
    public VGLFacetConfigData getState() {
        return state;
    }

    @Override
    public void loadState(VGLFacetConfigData state) {
        this.state = state;
    }
}
