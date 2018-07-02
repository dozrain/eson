package com.rain.config.filter;


import org.apache.catalina.ssi.SSIFilter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import javax.servlet.MultipartConfigElement;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ron on 15/6/27.
 */
@Configuration
public class WebConfig {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(BeanFactory beanFactory) {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
                mappings.add("shtml","text/x-server-parsed-html");
                container.setMimeMappings(mappings);
            }
        };
    }

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("redirect:/login.shtml");
            }
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(amroInterceptor());
            }
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                AmroContextResolver resolver = new AmroContextResolver();
                argumentResolvers.add(resolver);
            }

        };
    }
    @Bean
    public AogInterceptor amroInterceptor(){
        AogInterceptor amroInterceptor = new AogInterceptor();
        return amroInterceptor;
    }
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".shtml");
        return resolver;
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("500MB");
        return factory.createMultipartConfig();
    }
    @Bean
    public FilterRegistrationBean ssiFilterReg() {
        SSIFilter ssiFilter = new SSIFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(ssiFilter);
        registrationBean.addInitParameter("isVirtualWebappRelative", "true");
        List<String> urlPatterns = Arrays.asList(new String[]{"*.shtml"});
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

}
