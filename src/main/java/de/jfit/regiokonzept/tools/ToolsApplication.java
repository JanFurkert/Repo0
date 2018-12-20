package de.jfit.regiokonzept.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication steht für
// @Configuration -> ist eine vollwertige Konfiguration
// @ComponentScan -> alles in diesem pakage und sub-packages wird gescannt nach @Component
// @EnableAutoConfiguration -> enabled Suche der 'application.properties'
public class ToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsApplication.class, args);
    }

}
