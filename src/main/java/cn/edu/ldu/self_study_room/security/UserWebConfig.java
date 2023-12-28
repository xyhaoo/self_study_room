package cn.edu.ldu.self_study_room.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class UserWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(new UserLoginFilter())
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/self_study_room/login")// 排除登录页面
                .excludePathPatterns("/self_study_room/loading");
    }
}
