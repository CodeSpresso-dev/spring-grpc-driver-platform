package io.github.mshivaeifar.driverservice.infrastructure.observability.tracing;

import io.github.mshivaeifar.common.CommonConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String correlationId =
                Optional.ofNullable(
                                request.getHeader(ObservabilityConstants.CORRELATION_ID_HEADER_NAME)
                        )
                        .orElse(UUID.randomUUID().toString());
        try {

            MDC.put(
                    CommonConstants.CORRELATION_ID,
                    correlationId
            );

            response.addHeader(
                    ObservabilityConstants.CORRELATION_ID_HEADER_NAME,
                    correlationId
            );

            filterChain.doFilter(request, response);

        } finally {
            MDC.remove(CommonConstants.CORRELATION_ID);
        }
    }
}
