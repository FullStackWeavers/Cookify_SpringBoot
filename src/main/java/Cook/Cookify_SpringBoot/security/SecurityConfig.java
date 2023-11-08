package Cook.Cookify_SpringBoot.security;

import Cook.Cookify_SpringBoot.entity.Role;
import Cook.Cookify_SpringBoot.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http.csrf().disable().headers().frameOptions().disable()
                .and()
                    .authorizeRequests().antMatchers("/","/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
         return http.build();
    }

    private AuthenticationSuccessHandler successHandler(){
        return (request, response, authentication) -> {
            response.sendRedirect("/userInfo");
        };
    }
}
