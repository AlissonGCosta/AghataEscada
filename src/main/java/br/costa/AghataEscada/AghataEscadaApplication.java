package br.costa.AghataEscada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class AghataEscadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AghataEscadaApplication.class, args);
	}

}
