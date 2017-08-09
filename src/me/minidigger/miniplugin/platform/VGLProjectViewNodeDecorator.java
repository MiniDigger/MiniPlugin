package me.minidigger.miniplugin.platform;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packageDependencies.ui.PackageDependenciesNode;
import com.intellij.psi.PsiDirectory;
import com.intellij.ui.ColoredTreeCellRenderer;

import java.util.Set;

import me.minidigger.miniplugin.facet.VGLFacet;

public class VGLProjectViewNodeDecorator implements ProjectViewNodeDecorator {
    @Override
    public void decorate(ProjectViewNode node, PresentationData data) {
        Project project = node.getProject();
        if (project == null) return;
        if (!(node instanceof PsiDirectoryNode)) return;

        PsiDirectory directory = ((PsiDirectoryNode) node).getValue();
        Module module = ModuleUtilCore.findModuleForPsiElement(directory);
        if (module == null) return;

        ModuleRootManager rootManager = ModuleRootManager.getInstance(module);
        // Make sure there is at least a root to go off of
        if (rootManager.getContentRoots().length == 0) return;

        // Get the root and compare it to the node
        VirtualFile root = rootManager.getContentRoots()[0];
        if (!root.equals(node.getVirtualFile())) return;

        Set<VGLFacet> children = VGLFacet.getChildInstances(module);
        if (children.size() == 0) return;

        ModuleManager manager = ModuleManager.getInstance(project);
        String[] path = manager.getModuleGroupPath(module);
        if (path == null) {
            data.setIcon(children.iterator().next().getIcon());
            return;
        }

        Module testModule = manager.findModuleByName(path[path.length - 1]);
        if (!module.equals(testModule)) {
            return;
        }

        data.setIcon(children.iterator().next().getIcon());
    }

    @Override
    public void decorate(PackageDependenciesNode node, ColoredTreeCellRenderer cellRenderer) {
        //empty
    }
}
