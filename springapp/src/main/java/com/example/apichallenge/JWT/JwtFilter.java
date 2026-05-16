package com.example.apichallenge.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        String header=req.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer "))
        {
            String token=header.substring(7);
            if(jwtUtil.validateToken(token)){
                String email=jwtUtil.extractEmail(token);
                var auth=new UsernamePasswordAuthenticationToken(email,null);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }
        filterChain.doFilter(req,response);

    }


}
