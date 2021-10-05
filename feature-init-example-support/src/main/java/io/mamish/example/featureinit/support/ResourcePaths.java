package io.mamish.example.featureinit.support;

public enum ResourcePaths {
    TABLE_SCHEMAS_LIST("META-INF/admiralbot/table-schemas-init.list");

    private final String path;

    private ResourcePaths(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
