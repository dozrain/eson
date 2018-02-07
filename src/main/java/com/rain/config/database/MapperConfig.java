package com.rain.config.database;

import com.rain.config.context.ConfigConstants;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * Created by tpeng on 2016/7/21.
 */
@Configuration
@AutoConfigureAfter(DataBaseConfig.class)
public class MapperConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(ConfigConstants.PRIMARY_SESSION_FACTORY);
        mapperScannerConfigurer.setBasePackage(ConfigConstants.SCAN_PACKAGE_PATH + ".**.dao.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", Mapper.class.getName());
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
