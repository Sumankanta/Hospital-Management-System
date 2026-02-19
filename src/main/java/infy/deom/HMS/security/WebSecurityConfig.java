package infy.deom.HMS.security;

import infy.deom.HMS.entity.Type.PermissionType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static infy.deom.HMS.entity.Type.PermissionType.*;
import static infy.deom.HMS.entity.Type.RoleType.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableMethodSecurity
public class WebSecurityConfig {

//    private final PasswordEncoder passwordEncoder;

    private final JwtAuthFiler jwtAuthFiler;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;

    //To achieve Stateless
    //1. Disable the form login
    //2. Disable the CSRF
    //3. Disable the Session Manager

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // This is use to disable the CSRF // with lambada csrfConfig -> csrfConfig.disable()
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/auth/**").permitAll() // Publicly available any one can access
//                        .requestMatchers("/admin/**").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE, "/admin/**").hasAnyAuthority(APPOINTMENT_DELETE.name(), USER_MANAGE.name())
                        .requestMatchers("/doctors/**").hasAnyRole(DOCTOR.name(), ADMIN.name())
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFiler, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oAuth2 -> oAuth2
                                .failureHandler((request, response, exception) -> {
                                    log.error("OAuth2 error: {}", exception.getMessage());
                                    handlerExceptionResolver.resolveException(request, response, null, exception);
                                })
                                .successHandler(oAuth2SuccessHandler)
                        )
                .exceptionHandling(exceptionHandlingConfigurer ->
                        exceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                            handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
                        }));
//                .formLogin(Customizer.withDefaults()); // Here we disable the default form login page
        return httpSecurity.build();
    }


}
