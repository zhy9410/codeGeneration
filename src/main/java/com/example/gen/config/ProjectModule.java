package com.example.gen.config;

public class ProjectModule {
    private String module;

    private String pomName;

    private String includes;

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPomName() {
        return pomName;
    }

    public void setPomName(String pomName) {
        this.pomName = pomName;
    }
}
