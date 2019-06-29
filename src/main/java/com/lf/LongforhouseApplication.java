package com.lf;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan({"com.lf.background.*.mapper","com.lf.background.house.*.mapper","com.lf.foreground.*.mapper"})  //扫描mapper接口
@EnableAutoConfiguration   //开启自动解析配置文件(如欢迎页的配置)
@SpringBootApplication
public class LongforhouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LongforhouseApplication.class, args);
	}

}
