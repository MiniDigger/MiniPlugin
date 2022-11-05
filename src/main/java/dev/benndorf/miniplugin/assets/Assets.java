package dev.benndorf.miniplugin.assets;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class Assets {

    private static Icon loadIcon(String path) {
        return IconLoader.getIcon(path, Assets.class);
    }

    public static Icon VGL_ICON = loadIcon("/assets/icons/VGL.png");
}
