package com.rain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by rain on 2017/11/26.
 * 项目启动入口，配置包根路径
 */
@SpringBootApplication
@MapperScan({"com.rain.mapper",})
public class EsonApplication{
	public static void main(String[] args) {
		SpringApplication.run(EsonApplication.class, args);
	}
}
