package io.github.mshivaeifar.driverservice.unit.Infrastructure.observability.tracing;

import io.github.mshivaeifar.common.CommonConstants;
import io.github.mshivaeifar.driverservice.infrastructure.observability.tracing.CorrelationIdFilter;
import io.github.mshivaeifar.driverservice.infrastructure.observability.tracing.ObservabilityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CorrelationIdFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    CorrelationIdFilter correlationIdFilter;

    @Test
    public void given_correlation_id_header_when_filter_invoked_then_should_propagate_it() throws ServletException, IOException {
        //Given
        String correlationId = UUID.randomUUID().toString();
        when(request.getHeader(ObservabilityConstants.CORRELATION_ID_HEADER_NAME))
                .thenReturn(correlationId);
        //When
        correlationIdFilter.doFilter(request, response, filterChain);

        //Then
        verify(response)
                .addHeader(ObservabilityConstants.CORRELATION_ID_HEADER_NAME, correlationId);

        verify(filterChain).doFilter(request, response);

        assertThat(MDC.get(CommonConstants.CORRELATION_ID)).isNull();

    }

    @Test
    void given_no_correlation_id_header_when_filter_invoked_then_should_generate_one()
            throws Exception {
        //Given
        when(request.getHeader(ObservabilityConstants.CORRELATION_ID_HEADER_NAME))
                .thenReturn(null);

        //When
        correlationIdFilter.doFilter(request, response, filterChain);

        //Then
        ArgumentCaptor<String> captor =
                ArgumentCaptor.forClass(String.class);
        verify(response)
                .addHeader(eq(ObservabilityConstants.CORRELATION_ID_HEADER_NAME), captor.capture());
        String generatedId = captor.getValue();

        assertThat(generatedId)
                .isNotBlank();

        assertThatCode(() -> UUID.fromString(generatedId))
                .doesNotThrowAnyException();

        assertThat(MDC.get(CommonConstants.CORRELATION_ID))
                .isNull();


    }

    @Test
    void given_exception_when_filter_invoked_then_mdc_should_be_cleared()
            throws Exception {
        //Given
        when(request.getHeader(ObservabilityConstants.CORRELATION_ID_HEADER_NAME))
                .thenReturn("core_id");
        doThrow(new RuntimeException("boom"))
                .when(filterChain)
                .doFilter(request, response);

        //When
        assertThatThrownBy(() -> correlationIdFilter.doFilter(request, response, filterChain))


                //Then
                .isInstanceOf(RuntimeException.class);

        assertThat(MDC.get(CommonConstants.CORRELATION_ID)).isNull();

    }

}
