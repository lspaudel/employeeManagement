package com.example.employeeManagement.config;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter implements Filter {
    private static final String HEADER_REQUEST_ID = "X-Request-ID" ;
    private static final String MDC_REQUEST_ID_KEY = "requestId";
    private static final String MDC_USER_ID_KEY = "userId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestId = httpServletRequest.getHeader(HEADER_REQUEST_ID);
        if (requestId == null || requestId.isBlank()){
            requestId = UUID.randomUUID().toString();
        }
        // Pull the userId from authentication if your app uses it.
        // If there is no authenticated user, generate a fallback ID.
        String userId = UUID.randomUUID().toString();

        MDC.put(MDC_REQUEST_ID_KEY, requestId);
        MDC.put(MDC_USER_ID_KEY, userId);

        try {
            httpServletRequest.setAttribute(MDC_REQUEST_ID_KEY, requestId);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_REQUEST_ID_KEY);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
