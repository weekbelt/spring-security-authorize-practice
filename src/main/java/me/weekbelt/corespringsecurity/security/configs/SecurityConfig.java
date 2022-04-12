package me.weekbelt.corespringsecurity.security.configs;

import lombok.extern.slf4j.Slf4j;
import me.weekbelt.corespringsecurity.security.common.FormWebAuthenticationDetailsSource;
import me.weekbelt.corespringsecurity.security.handler.FormAccessDeniedHandler;
import me.weekbelt.corespringsecurity.security.provider.FormAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormWebAuthenticationDetailsSource formWebAuthenticationDetailsSource;
    @Autowired
    private AuthenticationSuccessHandler formAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler formAuthenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/mypage").hasRole("USER")
            .antMatchers("/messages").hasRole("MANAGER")
            .antMatchers("/config").hasRole("ADMIN")
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .authenticationDetailsSource(formWebAuthenticationDetailsSource)
            .successHandler(formAuthenticationSuccessHandler)
            .failureHandler(formAuthenticationFailureHandler)
            .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/denied")
            .accessDeniedHandler(accessDeniedHandler());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new FormAuthenticationProvider(passwordEncoder());
    }

    public AccessDeniedHandler accessDeniedHandler() {
        FormAccessDeniedHandler commonAccessDeniedHandler = new FormAccessDeniedHandler();
        commonAccessDeniedHandler.setErrorPage("/denied");
        return commonAccessDeniedHandler;
    }

}
