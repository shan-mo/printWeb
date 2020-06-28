package com.paint;

import com.paint.util.loginFilter;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Properties;

@SpringBootApplication
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@MapperScan("com.paint.mapper")
@PropertySource("classpath:jdbc.properties")
public class PaintApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaintApplication.class, args);
    }

    /**
     * 路径拦截器配置
     */
    @Configuration
    public class InterceptorConfig extends WebMvcConfigurationSupport {
        @Override
        protected void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new loginFilter()).addPathPatterns("/**")
                    .excludePathPatterns("/initLogin", "/login", "/gotoregist", "/regist", "/registcheck", "/getcha", "/image/**", "/js/**", "/layui/**", "/css/**");
            super.addInterceptors(registry);
        }

        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
            registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/");
            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
            registry.addResourceHandler("/layui/**").addResourceLocations("classpath:/static/js/layui/");
            registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
            super.addResourceHandlers(registry);
        }
    }


    /**
     * 验证码配置
     *
     * @return
     */
    @Bean
    public DefaultKaptcha captchaProduce() {
        DefaultKaptcha captchaProduce = new DefaultKaptcha();
        Properties properties = new Properties();
        // 是否需要边框
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        // 边框颜色
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "red");
        // 字体颜色
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        // 字体大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");
        // 字体
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        // 文本长度
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // 字符的取值范围
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
        // 图片宽度
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "200");
        // 图片高度
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "50");
        //
        properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, "code");
        Config config = new Config(properties);
        captchaProduce.setConfig(config);
        return captchaProduce;
    }

}
