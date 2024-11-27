package com.api.phantshopping.domain.dto;

import java.util.UUID;

public record AuthResponseDto(int status, String message, String token, UUID id, String nome)
{
}
