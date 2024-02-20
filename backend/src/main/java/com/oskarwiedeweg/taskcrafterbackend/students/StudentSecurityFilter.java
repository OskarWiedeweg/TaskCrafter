package com.oskarwiedeweg.taskcrafterbackend.students;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Data
@Component
public class StudentSecurityFilter extends OncePerRequestFilter {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String studentId = request.getHeader("X-Student-Id");
        String studentSecret = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (studentId == null || studentSecret == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String encryptedStudentSecret = studentService.getStudentSecret(studentId);

        if (!passwordEncoder.matches(studentSecret, encryptedStudentSecret)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid secret!");
        }

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(studentId, studentSecret, List.of(new SimpleGrantedAuthority("ROLE_STUDENT"))));
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }
}
