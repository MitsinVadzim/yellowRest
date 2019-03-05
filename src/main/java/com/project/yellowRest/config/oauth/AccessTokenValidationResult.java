package com.project.yellowRest.config.oauth;

import java.util.Map;

public class AccessTokenValidationResult {
    private final boolean valid;
    private final Map<String, ?> tokenInfo;

    public AccessTokenValidationResult(boolean valid, Map<String, ?> tokenInfo) {
        this.valid = valid;
        this.tokenInfo = tokenInfo;
    }

    public boolean isValid() {
        return valid;
    }

    public Map<String, ?> getTokenInfo() {
        return tokenInfo;
    }
}

