package com.yunmu;

import com.yunmu.core.config.FileStorageProperties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yunmu.back","com.yunmu.bapp","com.yunmu.core"})
@MapperScan(basePackages = {"com.yunmu.core.dao"})
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableScheduling
public class YunmuInternalApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunmuInternalApplication.class, args);
	}

}
