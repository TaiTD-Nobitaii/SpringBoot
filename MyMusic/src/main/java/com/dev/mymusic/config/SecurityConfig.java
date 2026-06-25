package com.dev.mymusic.config;

import com.dev.mymusic.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // 1. Khai báo Bean mã hóa mật khẩu bằng thuật toán BCrypt bảo mật cao
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Khai báo Bean AuthenticationManager để quản lý xác thực đăng nhập
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 3. Cấu hình bảo mật chính (SecurityFilterChain) theo chuẩn Spring Security 6 mới nhất
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Vô hiệu hóa CSRF vì REST API dùng JWT (không dùng Cookie/Session truyền thống)
                .csrf(AbstractHttpConfigurer::disable)

                // Cấu hình không lưu trữ trạng thái phiên làm việc (STATELESS Session)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Phân quyền cho các Endpoint
                .authorizeHttpRequests(authorize -> authorize
                        // Cho phép tất cả mọi người truy cập các API Authentication (Đăng ký, Đăng nhập)
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/songs/**").permitAll()
                        .requestMatchers("/api/playlist/**").permitAll()

                        // Cho phép truy cập tài liệu API Swagger UI công khai
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Các Request khác bắt buộc phải được xác thực (Đăng nhập và có JWT hợp lệ)
                        .anyRequest().authenticated()
                );

        // Thêm bộ lọc JWT vào trước bộ lọc UsernamePasswordAuthenticationFilter mặc định của Spring
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
