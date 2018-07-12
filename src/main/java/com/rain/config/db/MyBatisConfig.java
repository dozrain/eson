package com.rain.config.db;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * Created by tpeng on 2016/7/21.
 */
//@Configuration
//@AutoConfigureAfter(DBConfig.class)
public class MyBatisConfig {

    @Bean
    public MapperScannerConfigurer oracleScannerConfig() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("SqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.rain.aog.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", Mapper.class.getName());
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer mysqlScannerConfig() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sapdataSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.rain.aog.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", Mapper.class.getName());
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
