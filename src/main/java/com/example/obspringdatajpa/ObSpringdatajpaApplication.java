package com.example.obspringdatajpa;

import com.example.obspringdatajpa.entities.LaptopEntity;
import com.example.obspringdatajpa.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObSpringdatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObSpringdatajpaApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);


		LaptopEntity laptop1 = new LaptopEntity(null,"ASUS","bf4517", 505.84, LocalDate.of(2020, 01, 15));
		LaptopEntity laptop2 = new LaptopEntity(null,"hp","jhy8453", 1200.20, LocalDate.of(2022, 12, 01));


		repository.save(laptop1);
		repository.save(laptop2);
	}


}
