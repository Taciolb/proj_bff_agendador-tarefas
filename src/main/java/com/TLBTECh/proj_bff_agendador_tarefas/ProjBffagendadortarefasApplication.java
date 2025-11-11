package com.TLBTECh.proj_bff_agendador_tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjBffagendadortarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjBffagendadortarefasApplication.class, args);
	}

}
