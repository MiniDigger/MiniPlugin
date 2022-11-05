package dev.benndorf.miniplugin.module;

import java.util.HashSet;
import java.util.Set;

public class VGLProjectCreator {

    private VGLProjectType projectType;
    private Set<String> additionalModules;

    private String groupId;
    private String artifactId;
    private String version;

    private BuildSystemType buildSystemType;

    public VGLProjectCreator() {
        reset();
    }

    public void reset() {
        projectType = VGLProjectType.ADDON;
        additionalModules = new HashSet<>();

        groupId = "";
        artifactId = "";
        version = "";

        buildSystemType = BuildSystemType.MAVEN;
    }

    public void setProjectType(VGLProjectType projectType) {
        this.projectType = projectType;
    }

    public VGLProjectType getProjectType() {
        return projectType;
    }

    public void addAdditionalModule(String module) {
        additionalModules.add(module);
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBuildSystem(BuildSystemType buildSystem) {
        this.buildSystemType = buildSystem;
    }
}
