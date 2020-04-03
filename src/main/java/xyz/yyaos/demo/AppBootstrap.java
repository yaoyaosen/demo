package xyz.yyaos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("xyz.yyaos.demo.filter")
public class AppBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(AppBootstrap.class, args);
	}
}
