package com.lzs.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

@Component
@Order(1)
public class RequestResonseLogFilter implements Filter {

    private final static Logger logger = LogManager.getLogger(RequestResonseLogFilter.class);

    private static final String TRACE_ID = "traceID";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //先生成traceId
        String traceId = UUID.randomUUID().toString().replace("-", "");
        ThreadContext.put(TRACE_ID, traceId);

        MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) servletRequest);

        Map<String,String> headers = Maps.newHashMap();
        for (Enumeration<?> e = multiReadRequest.getHeaderNames(); e.hasMoreElements();) {
            String nextHeaderName = (String) e.nextElement();
            String headerValue = multiReadRequest.getHeader(nextHeaderName);
            headers.put(nextHeaderName, headerValue);
        }
        logger.info("client ip: {}", multiReadRequest.getRemoteAddr());
        logger.info("request url : {}", multiReadRequest.getRequestURL());
        logger.info("request headers: {}", JSONObject.toJSONString(headers));

        String requestContent = CharStreams.toString(new InputStreamReader(multiReadRequest.getInputStream(), StandardCharsets.UTF_8));
        logger.info("request query string: {}, request params: {}", multiReadRequest.getQueryString(), requestContent);
        filterChain.doFilter(multiReadRequest, servletResponse);
        ((HttpServletResponse) servletResponse).addHeader(TRACE_ID, traceId);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        ThreadContext.clearAll();
    }
}
