package com.example.qpid;

import com.example.qpid.global.security.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class QpidApplication {
	public static void main(String[] args) {
		SpringApplication.run(QpidApplication.class, args);
	}
}
