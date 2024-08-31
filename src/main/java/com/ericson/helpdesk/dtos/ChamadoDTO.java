package com.ericson.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import com.ericson.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	private Integer prioridade;
	private Integer status;
	private String titulo;
	private String observacoes;
	private String nomeTecnico;
	private String nomeCliente;

	private Integer codTecnico;
	private Integer codCliente;

	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Integer id, LocalDate dataAbertura, LocalDate dataFechamento, Integer prioridade, Integer status,
			String titulo, String observacoes, String nomeTecnico, String nomeCliente, Integer tecnico,
			Integer cliente) {
		super();
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.nomeTecnico = nomeTecnico;
		this.nomeCliente = nomeCliente;
		this.codTecnico = tecnico;
		this.codCliente = cliente;
	}

	public ChamadoDTO(Chamado chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.prioridade = chamado.getPrioridade().getCodigo();
		this.status = chamado.getStatus().getCodigo();
		this.titulo = chamado.getTitulo();
		this.observacoes = chamado.getObservacoes();
		this.nomeTecnico = chamado.getTecnico().getNome();
		this.nomeCliente = chamado.getCliente().getNome();
		this.codTecnico = chamado.getTecnico().getId();
		this.codCliente = chamado.getCliente().getId();
	}

}
