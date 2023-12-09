package org.sid.costumerservice;

import org.sid.costumerservice.entities.Customer;
import org.sid.costumerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CostumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostumerServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			//restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(List.of(
					Customer.builder().name("Karim").email("Karim@gmail.com").build(),
					Customer.builder().name("Mohammed").email("Mohammed@gmail.com").build(),
					Customer.builder().name("Youness").email("Youness@gmail.com").build()
			));

			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
