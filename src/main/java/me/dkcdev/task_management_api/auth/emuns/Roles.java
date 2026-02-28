package me.dkcdev.task_management_api.auth.emuns;

public enum Roles {
    OWNER("OWNER"),
    ADMIN("ADMIN"),
    VIEWER("VIEWER");
    
    private final String name;
    
    Roles(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
