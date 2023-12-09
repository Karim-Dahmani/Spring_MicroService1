package org.sid.inventoryservice;

import org.sid.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.sid.inventoryservice.entities.Product;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			//restConfiguration.exposeIdsFor(Product.class);
			productRepository.saveAll(
					List.of(
							Product.builder().name("computer").price(12000).quantity(100).build(),
							Product.builder().name("ps5").price(9000).quantity(200).build(),
							Product.builder().name("steam Deck").price(7000).quantity(40).build()
					));
		};
	}
}
