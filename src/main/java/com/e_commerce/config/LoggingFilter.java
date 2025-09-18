package com.e_commerce.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("🔎 Incoming request: " + req.getMethod() + " " + req.getRequestURI());
        chain.doFilter(request, response);
    }
}
