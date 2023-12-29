package cn.edu.ldu.self_study_room.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserLoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("userid"+session.getAttribute("user_id"));
        // 获取请求的URI
        String uri = request.getRequestURI();

        // 检查用户是否已登录，这里可以根据自己的业务逻辑进行判断
        if ( uri.startsWith("/self_study_room/user") && session.getAttribute("user_id") == null) {
            // 用户未登录，重定向到登录页面
            response.sendRedirect("/self_study_room/login");
            return false; // 阻止请求继续处理
        }



        return true; // 允许请求继续处理
    }
}