package edu.ben.template.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new LocaleInterceptor());
		// registry.addInterceptor(new
		// ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
		// registry.addInterceptor(new
		// SecurityInterceptor()).addPathPatterns("/secure/*");
//		registry.addInterceptor(userInterceptor());
		//registry.addInterceptor(devEnvironmentInterceptor());
	}

//	@Bean
//	UserInterceptor userInterceptor() {
//		return new UserInterceptor();
//	}

//	@Bean
//	DevEnvironmentInterceptor devEnvironmentInterceptor() {
//		return new DevEnvironmentInterceptor();
//	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// registry.addFormatterForFieldAnnotation(new
		// DurationFormatterFactory());
		// registry.addFormatterForFieldAnnotation(new
		// SqlDateFormatterFactory());
		// registry.addFormatterForFieldAnnotation(new
		// SqlTimeFormatterFactory());
		// registry.addFormatterForFieldAnnotation(new
		// LocalDateFormatterFactory());
	}

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
		registry.addResourceHandler("/content/**").addResourceLocations("/content/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/content/favicon.ico");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		List<MediaType> types = new ArrayList<>();
		types.add(new MediaType("application", "json"));
		converter.setSupportedMediaTypes(types);
		// create new object mapper
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new AfterburnerModule());
		mapper.registerModule(new JodaModule());

		converter.setObjectMapper(mapper);
		converters.add(converter);
	}
}