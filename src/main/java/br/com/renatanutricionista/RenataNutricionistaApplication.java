package br.com.renatanutricionista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RenataNutricionistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenataNutricionistaApplication.class, args);
	}
}
