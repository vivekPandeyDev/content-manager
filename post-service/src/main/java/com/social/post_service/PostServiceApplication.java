package com.social.post_service;

import jakarta.servlet.MultipartConfigElement;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@PropertySources({@PropertySource("classpath:config.properties")})
@EnableAsync
public class PostServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("10MB"));
        factory.setMaxRequestSize(DataSize.parse("10MB"));
        return factory.createMultipartConfig();
    }

//    @Bean
//    public Job job(){
//        return new SimpleJob();
//    }

}
