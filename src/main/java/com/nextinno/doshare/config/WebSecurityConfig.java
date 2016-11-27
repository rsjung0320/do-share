package com.nextinno.doshare.config;

import com.nextinno.doshare.common.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = DoShareConfig.DEFAULT_BASE_PACKAGE)
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtFilter authenticationTokenFilterBean() throws Exception {
        return new JwtFilter();
    }

    // 여기에 path를 두는 것은 어느 path던 통과가 된다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/fonts/**", "/styles/**", "/login/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .httpBasic().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // to-do 좀 더 알아 볼 것. 이곳에 ROLE도 추가할 수 있음.
//                .antMatchers("/admin/api/**").access("hasRole('admin')");
//                .anyRequest().hasRole("admin");
        

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//        httpSecurity
//        .addFilter(authenticationTokenFilterBean());

        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}
