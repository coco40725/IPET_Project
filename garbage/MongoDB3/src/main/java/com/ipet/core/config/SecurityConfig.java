package com.ipet.core.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:12
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider()));
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager auth){
        try {
            // 登出
            http.logout()
                    .logoutSuccessUrl("/ipet-back/login")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/ipet-back/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true);



            // 自定義登入頁面
            http.formLogin()
                    .loginPage("/ipet-back/login").permitAll()
                    .loginProcessingUrl("/ipet-back/login").permitAll()
                    // 登入成功後，redirect到此路徑
                    .defaultSuccessUrl("/ipet-back/home")
                    .failureForwardUrl("/ipet-back/login").permitAll()

                    // 設定哪些 ROLE 可以使用哪些 request
                    .and().authorizeRequests()
                    .antMatchers("/backstage/js/**", "/backstage/css/**", "/backstage/img/**", "/backstage/plugins/**").permitAll()
                    .anyRequest().authenticated();


            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
