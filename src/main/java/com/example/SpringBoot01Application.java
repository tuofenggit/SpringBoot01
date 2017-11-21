package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

@ServletComponentScan
@SpringBootApplication
public class SpringBoot01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot01Application.class, args);
	}
	
	 
}
