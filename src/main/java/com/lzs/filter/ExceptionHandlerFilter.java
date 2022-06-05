package com.lzs.filter;

import com.alibaba.fastjson.JSONObject;
import com.lzs.common.ErrorStatus;
import com.lzs.common.Result;
import com.lzs.common.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final static Logger logger = LogManager.getLogger(ExceptionHandlerFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception exception) {

            Throwable e = exception.getCause();
            logger.error("exception: ", e);
            Object result = null;
            if (e instanceof ServiceException) {
                ServiceException serviceException = (ServiceException) e;
                result = new Result<>(serviceException.getStatus(), serviceException.getMessage(), new Object());
            } else {
                result = new Result<>(ErrorStatus.NETWORK_IS_BUSY, e.getMessage(), new Object());
            }

            String s = JSONObject.toJSONString(result);
            logger.info("reponse: {}", s);
            response.setContentType("application/json");
            response.getOutputStream().write(JSONObject.toJSONString(result).getBytes("UTF-8"));
        }
    }
}
