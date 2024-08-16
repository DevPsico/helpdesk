package com.ericson.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ericson.helpdesk.domain.Chamado;
import com.ericson.helpdesk.domain.Cliente;
import com.ericson.helpdesk.domain.Tecnico;
import com.ericson.helpdesk.domain.enums.Perfil;
import com.ericson.helpdesk.domain.enums.Prioridade;
import com.ericson.helpdesk.domain.enums.Status;
import com.ericson.helpdesk.repositories.ChamadoRepository;
import com.ericson.helpdesk.repositories.ClienteRepository;
import com.ericson.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	TecnicoRepository tecnicoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Tecnico tec1 = new Tecnico(null, "Ericson Sérgio", "00912971410", "ericson@algo.com.br", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Cliente ericson", "00312345670", "cliente@algo.com.br", "122");

		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Sou o titulo chamado 01", "obs 1 chamado",
				tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(cha1));

	}
}