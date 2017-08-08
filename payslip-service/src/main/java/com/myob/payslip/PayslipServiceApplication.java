package com.myob.payslip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.myob.payslip")
@EnableAutoConfiguration
//@EnableJpaRepositories
public class PayslipServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayslipServiceApplication.class, args);
	}
}
