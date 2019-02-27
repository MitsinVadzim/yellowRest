package com.project.yellowRest.config;

import com.project.yellowRest.config.oauth.AccessTokenValidator;
import com.project.yellowRest.config.oauth.GoogleAccessTokenValidator;
import com.project.yellowRest.config.oauth.GoogleTokenServices;
import com.project.yellowRest.domain.Role;
import com.project.yellowRest.domain.User;
import com.project.yellowRest.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.time.LocalDateTime;
import java.util.Collections;


@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/", "/google/login", "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();

    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(google().getClientId());
    }


    @Bean
    public ResourceServerTokenServices tokenServices(AccessTokenValidator tokenValidator) {
        GoogleTokenServices googleTokenServices = new GoogleTokenServices(tokenValidator);
        googleTokenServices.setUserInfoUrl(googleResource().getUserInfoUri());
        return googleTokenServices;
    }

    @Bean
    public AccessTokenValidator tokenValidator() {
        GoogleAccessTokenValidator accessTokenValidator = new GoogleAccessTokenValidator();
        accessTokenValidator.setClientId(google().getClientId());
        accessTokenValidator.setCheckTokenUrl(googleResource().getTokenInfoUri());
        return accessTokenValidator;
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            Long id = (Long)map.get("id");

            User user = userRepository.findById(id).orElseGet(() -> {
                User newUser = new User();

                newUser.setId(id);
                newUser.setUsername((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setUserpic((String) map.get("picture"));
                newUser.setActive(true);
                newUser.setRoles(Collections.singleton(Role.USER));
                return newUser;
            });

            user.setLastVisit(LocalDateTime.now());

            return userRepository.save(user);
        };
    }
}
