package com.project.yellowRest.config.google;

import com.project.yellowRest.config.oauth.AccessTokenValidationResult;
import com.project.yellowRest.config.oauth.AccessTokenValidator;
import com.project.yellowRest.service.UserServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.util.Collections.singleton;

public class GoogleTokenServices implements ResourceServerTokenServices {

    private final UserServiceImpl userService;
    private final AccessTokenValidator tokenValidator;
    private final AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
    private final RestTemplate restTemplate = new RestTemplate();
    private String userInfoUrl;


    public GoogleTokenServices(AccessTokenValidator tokenValidator, UserServiceImpl userService) {
        this.tokenValidator = tokenValidator;
        this.userService = userService;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        AccessTokenValidationResult validationResult = tokenValidator.validate(accessToken);
        if (!validationResult.isValid()) {
            throw new UnapprovedClientAuthenticationException("The token is not intended to be used for this application.");
        }
        userService.save(getUserInfo(accessToken));
        Map<String, ?> tokenInfo = validationResult.getTokenInfo();
        return getAuthentication(tokenInfo, accessToken);

    }

    private OAuth2Authentication getAuthentication(Map<String, ?> tokenInfo, String accessToken) {
        OAuth2Request request = tokenConverter.extractAuthentication(tokenInfo).getOAuth2Request();
        Authentication authentication = getAuthenticationToken(accessToken);
        return new OAuth2Authentication(request, authentication);
    }

    private Authentication getAuthenticationToken(String accessToken) {
        GooglePrincipal googlePrincipal = getUserInfo(accessToken);
        return new UsernamePasswordAuthenticationToken(googlePrincipal, null, singleton(new SimpleGrantedAuthority("USER")));
    }

    private HttpHeaders getHttpHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }

    private GooglePrincipal getUserInfo(String accessToken) {
        HttpHeaders headers = getHttpHeaders(accessToken);
        return restTemplate.exchange(userInfoUrl, HttpMethod.GET, new HttpEntity<>(headers), GooglePrincipal.class).getBody();
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }
}
