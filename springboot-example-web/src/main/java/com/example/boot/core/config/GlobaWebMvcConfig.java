package com.example.boot.core.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot.core.converter.ResResultConverter;
import com.example.boot.core.interceptor.LogInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class GlobaWebMvcConfig implements WebMvcConfigurer{
	
	@Override
	// 重写父类提供的跨域请求处理的接口
	public void addCorsMappings(CorsRegistry registry) {
		// 添加映射路径
		registry.addMapping("/**")
				// 放行哪些原始域
				.allowedOrigins("*")
				// 是否发送Cookie信息
				.allowCredentials(true)
				// 放行哪些原始域(请求方式)
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				// 放行哪些原始域(头部信息)
				.allowedHeaders("*")
				// 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
				.exposedHeaders("Server", "Date", "Content-Length", "Set-Cookie2", "Set-Cookie", "addCookie",
						"cookie", "JSESSIONID", "Access-Token", "Access-Control-Allow-Origin",
						"Access-Control-Allow-Credentials");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/web/**").addResourceLocations("classpath:/web/");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.removeIf(httpMessageConverter -> MappingJackson2HttpMessageConverter.class.isAssignableFrom(httpMessageConverter.getClass()));
        converters.add(0, getMappingJackson2HttpMessageConverter());
        log.info("■ 消息转换器列表：" + converters);
	}
	
	private MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ResResultConverter converter = new ResResultConverter();
        converter.setObjectMapper(objectMapper);
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        return converter;
    }
}
