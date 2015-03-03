package com.presalescolab.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(value = "com.presalescolab")
@PropertySource(value = {"classpath:/app.properties","file:${bp4it.prop.path}/bp4it-admin-ui.properties"},ignoreResourceNotFound = true)
public class RootContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer prop = new PropertySourcesPlaceholderConfigurer();
        prop.setIgnoreUnresolvablePlaceholders(true);
        prop.setIgnoreResourceNotFound(true);
        return prop;
        //return new PropertySourcesPlaceholderConfigurer();
    }

}
