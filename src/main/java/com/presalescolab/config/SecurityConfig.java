package com.presalescolab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.annotation.Resource;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true,prePostEnabled=true)
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Value("${bp4it.adminui.home.page}")
    private String homepage;

    @Value("${bp4it.adminui.login.page}")
    private String loginpage;

    @Value("${bp4it.adminui.login.path}")
    private String loginpath;

    @Value("${bp4it.adminui.logout.path}")
    private String logoutPath;

    @Value("${bp4it.adminui.loggedout.path}")
    private String loggedoutPath;

    public static final String FILTER_VIEW = "/view/**";

    @Resource(name = "internalAuthProvider")
    private com.presalescolab.security.PresalesInternalAuthenticationProvider presalesInternalAuthenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.authenticationProvider(presalesInternalAuthenticationProvider);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web
           .ignoring()
                .antMatchers("/api/user/login")
                .antMatchers("/api/alive")
                .antMatchers("/password/**")
                .antMatchers("/api/public/**")
                .antMatchers("/api/user/theaters")
                .antMatchers("/api/user/divisions")
                .antMatchers(FILTER_VIEW)
                .antMatchers(loginpath);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);

//        final HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();
//        tokenRepository.setHeaderName("X-XSRF-TOKEN");
//        http.csrf().csrfTokenRepository(tokenRepository);

        http.csrf().disable();
        http.httpBasic().disable();


     // Make authentication required across the board
        http
            .authorizeRequests()
                .antMatchers("/").anonymous()
                .antMatchers("/view").authenticated()
                .antMatchers("/api/**").hasRole("user");


     // Take control of the login experience
        http
            .formLogin()
                .defaultSuccessUrl("/home")
                .loginPage(loginpage)
                .permitAll();

        http.logout().logoutUrl(logoutPath).logoutSuccessUrl(loggedoutPath);

    }

}
