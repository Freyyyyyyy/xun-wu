package com.cpt202.xunwu;

import com.cpt202.xunwu.property.FileProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

@EnableConfigurationProperties({
	FileProperties.class
})
@EnableJpaAuditing

public class XunWuApplication {

	public static void main(String[] args) {
		SpringApplication.run(XunWuApplication.class, args);
	}

}
