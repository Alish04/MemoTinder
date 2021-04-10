package com.company.akeninbaev.model;

import io.javalin.core.security.Role;

public enum UserRole implements Role {
    ADMIN,
    COMMON;
}
