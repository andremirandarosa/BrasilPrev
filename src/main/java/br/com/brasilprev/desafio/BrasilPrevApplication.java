package br.com.brasilprev.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BrasilPrevApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrasilPrevApplication.class, args);
	}

}
