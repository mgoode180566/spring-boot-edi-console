package com.sml.mgoode;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

/**
 * Created by michaelgoode on 13/09/2017.
 */

@EnableWebMvc
@Configuration
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("customlogin");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        //"classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

    @Primary
    @Bean(name = "genericEDI")
    @ConfigurationProperties(prefix = "spring.edi")
    public DataSource getGenericDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "genericJdbcTemplate")
    public JdbcTemplate jdbcTemplateGeneric(@Qualifier("genericEDI") DataSource ds) {
        return new JdbcTemplate(ds);
    }


    @Bean(name = "msEDI")
    @ConfigurationProperties(prefix = "spring.ms")
    public DataSource getMSDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "msJdbcTemplate")
    public JdbcTemplate jdbcTemplateMS(@Qualifier("msEDI") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
