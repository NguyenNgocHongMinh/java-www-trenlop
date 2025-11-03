package com.se.nguyenngochongminh_shopping_thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();

        // Customer người dùng có tài khoản
        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("111"))
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        //GUEST có thể truy cập (không cần đăng nhập)
                        .requestMatchers("/", "/home", "/login", "/css/**", "/js/**", "/images/**", "/uploads/**").permitAll()
                        
                        // 2. GUEST, CUSTOMER, ADMIN được xem danh sách Product
                        .requestMatchers("/product", "/product/{id}").permitAll()
                        
                        // 3. CUSTOMER và ADMIN được xem giỏ hàng và đặt hàng
                        .requestMatchers("/cart/**", "/order/checkout").hasAnyRole("CUSTOMER", "ADMIN")
                        
                        // 4. CUSTOMER và ADMIN chỉ xem đơn hàng
                        .requestMatchers("/order", "/order/{id}").hasAnyRole("CUSTOMER", "ADMIN")
                        
                        // 5. ADMIN quản lý Product
                        .requestMatchers("/product/add", "/product/save", "/product/edit/**", "/product/delete/**").hasRole("ADMIN")
                        
                        // 6. ADMIN quản lý Comment
                        .requestMatchers("/comment/delete/**").hasRole("ADMIN")
                        .requestMatchers("/comment/add/**").hasAnyRole("CUSTOMER", "ADMIN")
                        

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/product", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }
}

