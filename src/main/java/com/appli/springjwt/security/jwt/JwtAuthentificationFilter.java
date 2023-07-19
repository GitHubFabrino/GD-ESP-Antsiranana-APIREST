package com.appli.springjwt.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
public class JwtAuthentificationFilter extends OncePerRequestFilter {
    private static final List<String> AUTH_WHITELIST = Arrays.asList(
      "/api/files/{filename:=+}"
    );
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String path = request.getRequestURI();
        if(isPathInAuthWhitelist(path)){
            filterChain.doFilter(request, response);
            return;
        }

    }
    private boolean isPathInAuthWhitelist(String path){
        return AUTH_WHITELIST.stream().anyMatch(path::startsWith);
    }
}
