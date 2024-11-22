package com.api.phantshopping.domain.dto.response;

import java.util.UUID;

public record AutenticaUserResponse(UUID userId, String name, boolean success, String message)
{

    public AutenticaUserResponse of(UUID userId, String name, boolean success, String message)
    {
        return new AutenticaUserResponse(userId, name, success, message);
    }

    public AutenticaUserResponse of(UUID userId, String name, boolean success)
    {
        return new AutenticaUserResponse(userId, name, success, null);
    }

    public AutenticaUserResponse of(UUID userId, String name)
    {
        return new AutenticaUserResponse(userId, name, true, null);
    }
}
