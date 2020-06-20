package com.ouchen;

import com.ouchen.core.config.FileStorageProperties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ouchen.back", "com.ouchen.bapp", "com.ouchen.core"})
@MapperScan(basePackages = {"com.ouchen.core.dao"})
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableScheduling
public class YunmuInternalApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunmuInternalApplication.class, args);
	}

}
