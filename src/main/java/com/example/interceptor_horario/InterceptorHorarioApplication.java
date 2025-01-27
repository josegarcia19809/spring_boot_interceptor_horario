package com.example.interceptor_horario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterceptorHorarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterceptorHorarioApplication.class, args);
		System.out.println("Servidor en ejecución...");
	}

}
