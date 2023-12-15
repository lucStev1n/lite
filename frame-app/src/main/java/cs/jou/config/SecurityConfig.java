package cs.jou.config;

import cs.jou.auth.FrameRememberMeService;
import cs.jou.support.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.RememberMeServices;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    RememberMeServices rememberMeServices,
                                    AuthenticationEntryPoint entryPoint,
                                    AccessDeniedHandler deniedHandler) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin();
        http.rememberMe().rememberMeServices(rememberMeServices);

        http.authorizeHttpRequests().antMatchers("/api/**").authenticated().anyRequest().permitAll();

        http.csrf().disable();

        http.exceptionHandling().accessDeniedHandler(deniedHandler).authenticationEntryPoint(entryPoint);

        return http.build();
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService, ApplicationProperties properties) {
        FrameRememberMeService rememberMeService = new FrameRememberMeService(properties.getRememberMeHashKey(),
                                                                              userDetailsService);

        rememberMeService.key(properties.getRememberMeHashKey()).setCookieName(properties.getCookieName());
        rememberMeService.setParameter(properties.getRememberMeParam());
        rememberMeService.setAlwaysRemember(properties.isAlwaysRememberMe());

        return rememberMeService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
