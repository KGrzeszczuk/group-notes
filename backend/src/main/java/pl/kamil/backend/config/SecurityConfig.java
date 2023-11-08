package pl.kamil.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // AuthenticationManager authenticationManager(AuthenticationConfiguration
    // configuration) throws Exception {
    // return configuration.getAuthenticationManager();
    // }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowCredentials(true)
                        .exposedHeaders("X-Total-Count");
            }
        };
    }

    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(mvc.pattern("/public/**")).permitAll()
                .requestMatchers(mvc.pattern("/private/**")).authenticated()
                .requestMatchers(mvc.pattern("/color")).permitAll()
                .requestMatchers(mvc.pattern("/login")).permitAll()
                .anyRequest().authenticated())
                .cors(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable());

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.exceptionHandling(
                exeption -> exeption.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

        return http.build();
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    

    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider());
        return authenticationManagerBuilder.build();
    }

    // @Bean
    // public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    //     // Authentication authentication;
    //     // authentication. 
    //     return http.getSharedObject(AuthenticationManagerBuilder.class)
    //             .authenticationProvider(authProvider())
    //             .build();
    // }

    // @Bean
    // AuthenticationManager authenticationManager(AuthenticationConfiguration
    // configuration) throws Exception {
    // return configuration.getAuthenticationManager();
    // }

    // @Bean
    // UserDetailsService userDetailsService() {
    // UserDetails user = User.withDefaultPasswordEncoder()
    // .username("userr")
    // .password("password")
    // .roles("USER")
    // .build();
    // UserDetails admin = User.withDefaultPasswordEncoder()
    // .username("adminn")
    // .password("password")
    // .roles("ADMIN", "USER")
    // .build();
    // return new InMemoryUserDetailsManager(user, admin);
    // }

}
