package com.nextinno.doshare.main;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.nextinno.doshare.config.DoShareConfig;

@EnableJpaRepositories(DoShareConfig.DEFAULT_BASE_PACKAGE)
@EntityScan(DoShareConfig.DEFAULT_BASE_PACKAGE)
@ComponentScan(basePackages = DoShareConfig.DEFAULT_BASE_PACKAGE)
@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        
//        Ejb3Configuration cfg = new Ejb3Configuration();
//        HashMap<String,String> props = new HashMap<String,String>();
//        props.put("hibernate.format_sql","true");
//        Ejb3Configuration configured = cfg.configure("nextinno.doshare", props);
//        SchemaExport se = new SchemaExport(configured.getHibernateConfiguration());
//        se.setDelimiter(";");
//        se.create(true, false);
        logger.info("main start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    // server 단을 UTF-8로 변환시켜주는 함수
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    // server 단을 UTF-8로 변환시켜주는 함수
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
