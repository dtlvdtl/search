package com.ygb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication// 声明当前类为sprinboot的入口类
//public class WebApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(WebApplication.class, args);
//	}
//}
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@EnableCaching
public class WebApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WebApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}