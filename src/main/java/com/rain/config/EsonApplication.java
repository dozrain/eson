package com.rain.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * Created by rain on 2017/11/26.
 * 项目启动入口，配置包根路径
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.rain")
public class EsonApplication {
	public static void main(String[] args) {
		SpringApplication.run(EsonApplication.class, args);
	}
}
