//package com.project.yellowRest.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    //    private final AuthenticationManager authenticationManager;
////
////    private final PasswordEncoder passwordEncoder;
////
////    @Autowired
////    public AuthorizationServerConfig(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
////        this.authenticationManager = authenticationManager;
////        this.passwordEncoder = passwordEncoder;
////    }
////
////    @Override
////    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        endpoints
////                .authenticationManager(authenticationManager);
////    }
////
////    @Override
////    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////        clients
////                .inMemory()
////                .withClient("my-trusted-client")
////                .authorizedGrantTypes("client_credentials", "password")
////                .authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
////                .scopes("read","write","trust")
////                .resourceIds("oauth2-resource")
////                .accessTokenValiditySeconds(5000)
////                .secret(passwordEncoder.encode("secret"));
////    }
////
////    @Override
////    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
////        security
////                .checkTokenAccess("isAuthenticated()");
////    }
//    static final String CLIENT_ID = "hendi-client";
//    static final String CLIENT_SECRET = "hendi-secret";
//    static final String GRANT_TYPE = "password";
//    static final String SCOPE_READ = "read";
//    static final String SCOPE_WRITE = "write";
//    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
//    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
//
//    private final TokenStore tokenStore;
//
//    private final UserApprovalHandler userApprovalHandler;
//
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public AuthorizationServerConfig(TokenStore tokenStore, UserApprovalHandler userApprovalHandler, AuthenticationManager authenticationManager) {
//        this.tokenStore = tokenStore;
//        this.userApprovalHandler = userApprovalHandler;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
//
//        configurer
//                .inMemory()
//                .withClient(CLIENT_ID)
//                .secret(CLIENT_SECRET)
//                .authorizedGrantTypes(GRANT_TYPE)
//                .scopes(SCOPE_READ, SCOPE_WRITE)
//                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
//                refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
//                .authenticationManager(authenticationManager);
//    }
//}
