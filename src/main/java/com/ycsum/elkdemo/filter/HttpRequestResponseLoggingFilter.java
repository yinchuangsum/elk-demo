package com.ycsum.elkdemo.filter;

import com.ycsum.elkdemo.data.logging.Request;
import com.ycsum.elkdemo.data.logging.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class HttpRequestResponseLoggingFilter extends AbstractRequestLoggingFilter {
    Logger logger = LoggerFactory.getLogger(HttpRequestResponseLoggingFilter.class);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        Request req = new Request(request.getRequestURL().toString(),
                request.getRemoteHost(),
                request.getRemoteAddr(),
                request.getQueryString(),
                request.getRequestURI(),
                getRequestBody(request));
        logger.info(req.toString());
    }

    private String getRequestBody(HttpServletRequest request) {
        try {
            return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception ignored) {}
        return "";
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }

    private void afterRequest(HttpServletResponse response) throws IOException {
        String responseBody = new String(((ContentCachingResponseWrapper) response).getContentAsByteArray(), response.getCharacterEncoding());
        Response res = new Response(response.getStatus(), response.getContentType(), responseBody);
        logger.info(res.toString());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isFirstRequest = !this.isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;
        if (this.isIncludePayload() && isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request, this.getMaxPayloadLength());
        }

        boolean shouldLog = this.shouldLog(requestToUse);
        if (shouldLog && isFirstRequest) {
            this.beforeRequest(requestToUse, "");
        }

        try {
            filterChain.doFilter(requestToUse, response);
        } finally {
            if (shouldLog && !this.isAsyncStarted(requestToUse)) {
                this.afterRequest(response);
            }

        }

    }
}
