package com.nextinno.doshare.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextinno.doshare.config.DoShareConfig;

@Controller
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceTransactionManagerAutoConfiguration.class,
        DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = DoShareConfig.DEFAULT_BASE_PACKAGE)
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * @return
     */
//    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/login")
    // 나는 json으로만 데이터를 주고 받을 것이니 @ResponseBody는 없도록 한다.
    @ResponseBody
    String home() {
        logger.info("home API Request!");
        return String.format("hello workd");
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
        logger.info("main start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String LogLevel = DoShareConfig.getProperty("LogLevel");
        logger.info("LogLevel : " + LogLevel);
        logger.debug("main??????");
    }
}