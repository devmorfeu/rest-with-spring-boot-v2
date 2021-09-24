package br.com.restwithspringboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class OpenApiConfig extends WebMvcConfigurationSupport {

    @Bean
    public OpenAPI customApi() {
        return new OpenAPI().info(
                new Info()
                        .title("Rest API example")
                        .description("")
                        .license(new License().name("apache"))
                        .version("1.0")
                        .termsOfService(""));

    }
}