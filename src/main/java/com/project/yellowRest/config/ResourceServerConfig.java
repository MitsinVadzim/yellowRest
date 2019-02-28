package com.project.yellowRest.config;

import com.project.yellowRest.config.oauth.*;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public ResourceServerConfig(UserService userService){
        this.userService = userService;
    }

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
//        http.addFilterAfter(
//                new SaveDatabaseFilter(), BasicAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/", "/v2/api-docs", "/google/login",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        ;

    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
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
        GoogleAccessTokenValidator accessTokenValidator = new GoogleAccessTokenValidator();
        accessTokenValidator.setClientId(google().getClientId());
        accessTokenValidator.setCheckTokenUrl(googleResource().getTokenInfoUri());
        return accessTokenValidator;
    }


//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
//        return map -> {
//            Long id = (Long)map.get("id");
//
//            User user = userRepository.findById(id).orElseGet(() -> {
//                User newUser = new User();
//
//                newUser.setId(id);
//                newUser.setUsername((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
//                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
//                newUser.setActive(true);
//                newUser.setRoles(Collections.singleton(Role.USER));
//                return newUser;
//            });
//
//            user.setLastVisit(LocalDateTime.now());
//
//            return userRepository.save(user);
//        };
//    }
}
