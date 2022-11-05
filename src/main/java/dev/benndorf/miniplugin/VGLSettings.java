package dev.benndorf.miniplugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;

import org.jetbrains.annotations.Nullable;

@State(name = "VGLSettings", storages = @Storage("vgl.xml"))
public class VGLSettings implements PersistentStateComponent<VGLSettings.State> {

    public State state;

    @Nullable
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(State state) {
        this.state = state;
    }

    class State {

        boolean testSetting = true;
    }

    public static VGLSettings get() {
        return ServiceManager.getService(VGLSettings.class);
    }
}
