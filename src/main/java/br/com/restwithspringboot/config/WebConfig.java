package br.com.restwithspringboot.config;

import br.com.restwithspringboot.converter.YamlJackson2HttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

import static org.springframework.http.MediaType.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String JSON = "json";
    private static final String XML = "xml";
    private static final String X_YAML = "x-yaml";
    private static final MediaType APPLICATION_X_YAML = valueOf("application/x-yaml");

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {
        contentNegotiationConfigurer
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(APPLICATION_JSON)
                .mediaType(JSON, APPLICATION_JSON)
                .mediaType(X_YAML, APPLICATION_X_YAML)
                .mediaType(XML, APPLICATION_XML);
    }
}