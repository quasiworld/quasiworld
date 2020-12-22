package com.qwplus.quasiworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/*@EntityScan("com.qwplus.quasiworld.model")
@EnableJpaRepositories(basePackages= {"com.qwplus.quasiworld.business.*.dao"})*/
public class QuasiWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuasiWorldApplication.class, args);
	}

}
