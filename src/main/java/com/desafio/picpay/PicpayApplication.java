package com.desafio.picpay;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PicpayApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplicationBuilder(PicpayApplication.class).build();

		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}
