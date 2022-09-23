package com.swa.proj3commonmodule.security.enums;

public enum UserRole {
    ROLE_CANDIDATE("ROLE_CANDIDATE"), ROLE_RECRUITER("ROLE_RECRUITER");
    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
