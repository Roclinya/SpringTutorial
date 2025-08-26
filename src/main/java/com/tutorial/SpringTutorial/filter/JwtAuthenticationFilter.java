package com.tutorial.SpringTutorial.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

        long startTime = System.currentTimeMillis();
        System.out.println("OncePerRequestFilter processing...");
        filterChain.doFilter(wrappedRequest, response);
        long processTime = System.currentTimeMillis() - startTime;
        System.out.println("processTime : "+processTime + " ms");
    }
}
