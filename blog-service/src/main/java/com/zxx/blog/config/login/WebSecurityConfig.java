package com.zxx.blog.config.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginExceptionHandler loginExceptionHandler;
    
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    
    @Autowired
    private LoginFailHandler loginFailHandler;
//    
//    @Autowired
//    private LoginAuthenticationProvider loginAuthenticationProvider;
    
    @Autowired
    private JwtAuthenticationTokenFilter jwtFilter;
    
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    	http.addFilterAt(loginAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/upload/**").permitAll()
                .anyRequest().authenticated()
                //登录处理
                .and().formLogin().permitAll()
                //访问异常处理
                .and().exceptionHandling()
                .authenticationEntryPoint(loginExceptionHandler);
    }
    
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public authTokenFilter loginAuthenticationFilter() throws Exception {
    	authTokenFilter filter = new authTokenFilter();
    	filter.setAuthenticationManager(authenticationManager());
    	filter.setAuthenticationSuccessHandler(loginSuccessHandler);
    	filter.setAuthenticationFailureHandler(loginFailHandler);
    	return filter;
    }
}
