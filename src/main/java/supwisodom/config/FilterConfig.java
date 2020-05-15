package supwisodom.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import supwisodom.filter.AuthFilter;

@Configuration
public class FilterConfig {

	@Bean
    public FilterRegistrationBean<AuthFilter> registFilter() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<AuthFilter>();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/index/**");
        registration.setName("AuthFilter");
        registration.setOrder(1);
        return registration;
    }
}
