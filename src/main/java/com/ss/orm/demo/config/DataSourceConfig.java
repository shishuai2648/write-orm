package com.ss.orm.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DataSourceConfig {

    @Bean(name = "readDataSource")
    @ConfigurationProperties("spring.datasource.druid.read")
    public DataSource readDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "writeDataSource")
    @ConfigurationProperties("spring.datasource.druid.write")
    public DataSource writeDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource readDataSource,DataSource writeDataSource){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDataSourceEntry(new DynamicDataSourceEntry());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("read",readDataSource);
        map.put("write",writeDataSource);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(readDataSource);
        return dynamicDataSource;
    }

}
