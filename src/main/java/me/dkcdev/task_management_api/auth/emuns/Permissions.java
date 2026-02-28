package me.dkcdev.task_management_api.auth.emuns;

public enum Permissions {
    
    TASK_CREATE("task:create"),
    TASK_READ("task:read"),
    TASK_UPDATE("task:update"),
    TASK_DELETE("task:delete"),
    USER_CREATE("user:create"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete");

    private final String permission;
    
    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
