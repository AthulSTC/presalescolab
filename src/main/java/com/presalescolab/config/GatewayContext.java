package com.presalescolab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/**/presales-gateway-context.xml")
public class GatewayContext {
}
