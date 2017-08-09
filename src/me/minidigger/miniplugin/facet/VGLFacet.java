package me.minidigger.miniplugin.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetTypeId;
import com.intellij.facet.FacetTypeRegistry;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

import me.minidigger.miniplugin.assets.Assets;

public class VGLFacet extends Facet<VGLFacetConfig> {

    public static FacetTypeId<VGLFacet> ID = new FacetTypeId<>(VGLFacetType.TYPE_ID);

    public VGLFacet(@NotNull Module module, @NotNull String name, @NotNull VGLFacetConfig configuration, Facet underlyingFacet) {
        super(FacetTypeRegistry.getInstance().findFacetType(ID), module, name, configuration, underlyingFacet);
    }

    public static Set<VGLFacet> getChildInstances(Module module) {
        VGLFacet instance = getInstance(module);
        if (instance != null) {
            return new HashSet<VGLFacet>() {
                {
                    add(instance);
                }
            };
        }

        ModuleManager manager = ModuleManager.getInstance(module.getProject());
        Set<VGLFacet> result = new HashSet<>();

        for (Module m : manager.getModules()) {
            String[] path = manager.getModuleGroupPath(m);
            if (path == null) continue;
            Module namedModule = manager.findModuleByName(path[path.length - 1]);
            if (namedModule == null) continue;
            if (!namedModule.equals(module)) continue;
            VGLFacet facet = getInstance(m);
            if (facet == null) continue;
            result.add(facet);
        }
        return result;
    }

    public static VGLFacet getInstance(Module module) {
        return FacetManager.getInstance(module).getFacetByType(ID);
    }

    public static <T extends VGLFacet> T getInstance(Module module, VGLFacetType type) {
        VGLFacet instance = getInstance(module);
        return null;//TODO IDK
        // = getInstance(module)?.getModuleOfType(type);
    }

    public Icon getIcon() {
        return Assets.VGL_ICON;//TODO fixeme
    }
}
