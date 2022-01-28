package com.ycsum.elkdemo.filter;

import com.ycsum.elkdemo.component.CachedBodyHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CachedRequestResponseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request = new CachedBodyHttpServletRequest(request);
        ContentCachingResponseWrapper cachedResponse = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(request, cachedResponse);
        cachedResponse.copyBodyToResponse();
    }
}
