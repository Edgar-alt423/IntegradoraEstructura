package com.example.IntegradoraEstructura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class IntegradoraEstructuraApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradoraEstructuraApplication.class, args);
	}

}
