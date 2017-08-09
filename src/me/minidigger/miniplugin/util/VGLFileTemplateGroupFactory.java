package me.minidigger.miniplugin.util;

import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;

import me.minidigger.miniplugin.assets.Assets;

public class VGLFileTemplateGroupFactory implements FileTemplateGroupDescriptorFactory {
    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor("VGL", Assets.VGL_ICON);
        return null;
    }
}
