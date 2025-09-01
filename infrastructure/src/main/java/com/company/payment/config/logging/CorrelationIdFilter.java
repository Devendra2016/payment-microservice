package com.company.payment.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class CorrelationIdFilter extends OncePerRequestFilter {
    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    public static final String CORRELATION_ID_MDC_KEY = "correlationId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 1. Try to get the correlation ID from the header
            String correlationId = request.getHeader(CORRELATION_ID_HEADER);

            // 2. If not present, generate a new one
            if (correlationId == null || correlationId.isBlank()) {
                correlationId = generateCorrelationId();
            }

            // 3. Store the correlation ID in the MDC
            MDC.put(CORRELATION_ID_MDC_KEY, correlationId);

            // 4. Add the correlation ID to the response headers so the caller gets it back
            response.setHeader(CORRELATION_ID_HEADER, correlationId);

            // Proceed with the request
            filterChain.doFilter(request, response);
        } finally {
            // 5. Clear the MDC after the request is done to prevent memory leak
            MDC.remove(CORRELATION_ID_MDC_KEY);
        }
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
