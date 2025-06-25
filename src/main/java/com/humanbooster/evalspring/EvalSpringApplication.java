package com.humanbooster.evalspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvalSpringApplication {

	public static void main(String[] args) {
		try {
			System.out.println("===== STARTING APP =====\n");
			SpringApplication.run(EvalSpringApplication.class, args);
			System.out.println("===== APP RUNNING =====\n");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
