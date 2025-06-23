package org.evgeny.hairok.Config;


import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.Filters.SessionAttributeFilterAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Qualifier("clientService")
    private final UserDetailsService clientService;

    @Qualifier("masterService")
    private final UserDetailsService masterService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider clientProvider = new DaoAuthenticationProvider();
        clientProvider.setUserDetailsService(clientService);
        clientProvider.setPasswordEncoder(passwordEncoder());


        DaoAuthenticationProvider masterProvider = new DaoAuthenticationProvider();
        masterProvider.setUserDetailsService(masterService);
        masterProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(clientProvider, masterProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationManager(authenticationManager(http))
                .authorizeHttpRequests(
                        auth -> auth
                .requestMatchers("/admin/**").hasRole("admin")
                .requestMatchers("/login","/loginMaster", "/register/**", "/register/master-personal-info",
                        "/login/loginMaster").permitAll()
                .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new SessionAttributeFilterAccount(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard/client-dashboard")
                        .failureUrl("/login?error")
                        .usernameParameter("phone")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


}
