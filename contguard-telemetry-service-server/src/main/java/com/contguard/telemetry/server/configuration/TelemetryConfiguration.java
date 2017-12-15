package com.contguard.telemetry.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class TelemetryConfiguration {
    private DispatcherServlet dispatcherServlet;

    @Value("${app.base-url-prefix}")
    private String _baseUrlPrefix;

    @Bean
    public DispatcherServlet dispatcherServlet() {
        if (dispatcherServlet == null) {
            dispatcherServlet = new DispatcherServlet();
        }
        return dispatcherServlet;
    }

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        String urlPrefix = _baseUrlPrefix + "/*";
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet(), urlPrefix);
        servletRegistrationBean.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return servletRegistrationBean;
    }
}
