package com.blog.api.filters;

import com.blog.api.entity.User;
import com.blog.api.service.impl.IUserService;
import com.blog.api.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final IUserService iUserService;
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(Objects.nonNull(header) && header.startsWith("Bearer ")) {
            String jwt = header.substring(7);

            String username = jwtUtil.getUsernameFromToken(jwt);
            if (Objects.nonNull(username)
                    && Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
                User user = iUserService.getUserByUsername(username);
                if (jwtUtil.validateToken(jwt, user)) {
                    UsernamePasswordAuthenticationToken UPAT = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    UPAT.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(UPAT);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
