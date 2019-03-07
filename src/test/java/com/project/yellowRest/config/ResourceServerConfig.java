package com.project.yellowRest.config;

import com.project.yellowRest.config.google.GoogleAccessTokenValidator;
import com.project.yellowRest.config.google.GoogleTokenServices;
import com.project.yellowRest.config.oauth.AccessTokenValidator;
import com.project.yellowRest.service.UserService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;


@Configuration
@Profile("test")
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Bean
//    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
//    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .anyRequest().permitAll()
        ;

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId(google().getClientId());
    }

    @Bean
    public ResourceServerTokenServices tokenServices(AccessTokenValidator tokenValidator, UserService userService) {
        GoogleTokenServices googleTokenServices = new GoogleTokenServices(tokenValidator, userService);
        googleTokenServices.setUserInfoUrl(googleResource().getUserInfoUri());
        return googleTokenServices;
    }

    @Bean
    public AccessTokenValidator tokenValidator() {
        GoogleAccessTokenValidator accessTokenValidator = new GoogleAccessTokenValidator(getRestTemplate());
        accessTokenValidator.setClientId(google().getClientId());
        accessTokenValidator.setCheckTokenUrl(googleResource().getTokenInfoUri());
        return accessTokenValidator;
    }
}
