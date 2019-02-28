package com.project.yellowRest.config;

import com.project.yellowRest.domain.Role;
import com.project.yellowRest.domain.User;
import com.project.yellowRest.repository.UserRepository;
import com.project.yellowRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


////    private final UserService userDetailsService;
////
////    private final PasswordEncoder passwordEncoder;
////
////    @Autowired
////    public WebSecurityConfig(UserService userDetailsService, PasswordEncoder passwordEncoder) {
////        this.userDetailsService = userDetailsService;
////        this.passwordEncoder = passwordEncoder;
////    }
////
////    @Autowired
////    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .userDetailsService(userDetailsService)
////                .passwordEncoder(passwordEncoder);
////    }
////
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .antMatchers("/oauth/token","/registration").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll()
////                .and()
////                .httpBasic()
////                .and()
////                .csrf().disable();
////    }
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//
//    @Resource(name = "userService")
//    private UserDetailsService userDetailsService;
//
//    private final ClientDetailsService clientDetailsService;
//
//    @Autowired
//    public WebSecurityConfig(ClientDetailsService clientDetailsService) {
//        this.clientDetailsService = clientDetailsService;
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/api-docs/**", "/configuration/ui",
//                        "/swagger-resources",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**").permitAll();
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }
//
//    @Bean
//    @Autowired
//    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
//        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
//        handler.setTokenStore(tokenStore);
//        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//        handler.setClientDetailsService(clientDetailsService);
//        return handler;
//    }
//
//    @Bean
//    @Autowired
//    public ApprovalStore approvalStore(TokenStore tokenStore) {
//        TokenApprovalStore store = new TokenApprovalStore();
//        store.setTokenStore(tokenStore);
//        return store;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**");
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
//                .and().formLogin().loginProcessingUrl("/google/login")
//                .successHandler(myAuthenticationSuccessHandler())
                .and()
                .csrf().disable();

    }





//    @Bean
//    ApplicationListener<AuthenticationSuccessEvent> doSomething() {
//        return new ApplicationListener<AuthenticationSuccessEvent>() {
//            @Override
//            public void onApplicationEvent(AuthenticationSuccessEvent event){
//                OAuth2Authentication authentication = (OAuth2Authentication) event.getAuthentication();
//                Principal principal = (Principal) authentication.getPrincipal();
//                System.out.println("fdas");
//                // get required details from OAuth2Authentication instance and proceed further
//            }
//        };
//    }

}
