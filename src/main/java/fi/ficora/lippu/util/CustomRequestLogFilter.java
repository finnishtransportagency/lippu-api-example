package fi.ficora.lippu.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Custom requests logger to log every request. This
 * will log the relevant headers, x-transaction-id,
 * x-message-id, x-initiator and the endpoint.
 */
@Configuration
public class CustomRequestLogFilter extends CommonsRequestLoggingFilter {
    @Bean
    public CommonsRequestLoggingFilter logFilter(){
        CommonsRequestLoggingFilter filter=new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(false);
        filter.setIncludeHeaders(true);
        filter.setMaxPayloadLength(100);
        return filter;
    }
}