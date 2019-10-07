package com.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.forex")
@EnableEurekaClient
public class CurrencyconversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyconversionApplication.class, args);
	}
}
