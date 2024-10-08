package com.ericson.helpdesk.services;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ericson.helpdesk.domain.Chamado;
import com.ericson.helpdesk.domain.Cliente;
import com.ericson.helpdesk.domain.Tecnico;
import com.ericson.helpdesk.domain.enums.Perfil;
import com.ericson.helpdesk.domain.enums.Prioridade;
import com.ericson.helpdesk.domain.enums.Status;
import com.ericson.helpdesk.repositories.ChamadoRepository;
import com.ericson.helpdesk.repositories.ClienteRepository;
import com.ericson.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	TecnicoRepository tecnicoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ChamadoRepository chamadoRepository;

	
	public void instanciaDb() {
		
		Tecnico tec1 = new Tecnico(null, "Ericson Sérgio", "00912971410", "ericson@algo.com.br", "1234");
		tec1.addPerfil(Perfil.TECNICO);
		
		Tecnico tec2 = new Tecnico(null, "Mariana", "12345678900", "mariana@algo.com.br", "1234");

		Cliente cli1 = new Cliente(null, "Cliente ericson", "00312345670", "cliente@algo.com.br", "122");

		Cliente cli2 = new Cliente(null, "Cliente Mariana", "04115703407", "mariana@algo2.com.br", "1234");
		
		
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Sou o titulo chamado 01", "obs 1 chamado",
				tec1, cli1);
		
		Chamado cha2 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Sou o chamado 2", "OBS chamado 2", tec2, cli2);

		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		chamadoRepository.saveAll(Arrays.asList(cha1, cha2));
		
	}
}