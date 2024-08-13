package com.ericson.helpdesk.domain;

import java.time.LocalDate;
import com.ericson.helpdesk.domain.enums.Prioridade;
import com.ericson.helpdesk.domain.enums.Status;

import lombok.Data;

@Data
public class Chamado {

	private Integer id;
	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento;
	private Prioridade prioridade;
	private Status status;
	private String titulo;
	private String observacoes;
	
	private Tecnico tecnico;
	private Cliente cliente;

}