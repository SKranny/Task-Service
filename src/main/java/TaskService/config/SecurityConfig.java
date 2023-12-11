package TaskService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    @Primary
    public HttpSecurity httpSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(conf -> {
                    conf.requestMatchers("/api/v1/task/**").authenticated();
                    conf.requestMatchers("/v3/api-docs/**").permitAll();
                    conf.requestMatchers("/docs/**").permitAll();
                    conf.anyRequest().authenticated();
                });
    }
}
