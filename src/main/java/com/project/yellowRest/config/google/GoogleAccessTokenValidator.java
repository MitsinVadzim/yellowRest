package com.project.yellowRest.config.google;

import com.google.common.collect.ImmutableMap;
import com.project.yellowRest.config.oauth.AccessTokenValidationResult;
import com.project.yellowRest.config.oauth.AccessTokenValidator;
import lombok.Data;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Data
public class GoogleAccessTokenValidator implements AccessTokenValidator {

    private final RestTemplate restTemplate;
    private String checkTokenUrl;
    private String clientId;

    public GoogleAccessTokenValidator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() == 400) {
                    throw new InvalidTokenException("The provided token is invalid");
                }
            }
        });
    }

    @Override
    public AccessTokenValidationResult validate(String accessToken) {
        Map<String, ?> response = getGoogleResponse(accessToken);
        boolean validationResult = validateResponse(response);
        return new AccessTokenValidationResult(validationResult, response);
    }

    private boolean validateResponse(Map<String, ?> response) {
        String aud = (String) response.get("aud");
        return StringUtils.equals(aud, clientId);
    }

    private Map<String, ?> getGoogleResponse(String accessToken) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(new HttpHeaders());
        Map<String, String> variables = ImmutableMap.of("accessToken", accessToken);
        Map map = restTemplate.exchange(checkTokenUrl, HttpMethod.GET, requestEntity, Map.class, variables).getBody();
        return (Map<String, Object>) map;
    }
}
