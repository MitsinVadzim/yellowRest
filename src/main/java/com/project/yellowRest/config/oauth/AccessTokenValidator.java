package com.project.yellowRest.config.oauth;

public interface AccessTokenValidator {
    AccessTokenValidationResult validate(String accessToken);
}
