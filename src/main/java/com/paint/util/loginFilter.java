package com.paint.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Object user = request.getSession().getAttribute("user");
        if (null != user) {
            return true;
        } else {
            try {
                response.sendRedirect(request.getContextPath() + "initLogin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
