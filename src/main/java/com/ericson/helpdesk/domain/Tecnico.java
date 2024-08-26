package com.ericson.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ericson.helpdesk.domain.enums.Perfil;
import com.ericson.helpdesk.dtos.TecnicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.TECNICO);

	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.TECNICO);
		// TODO Auto-generated constructor stub
	}

	public Tecnico(TecnicoDTO tecnicoDTO) {
		super();
		this.id = tecnicoDTO.getId();
		this.nome = tecnicoDTO.getNome();
		this.cpf = tecnicoDTO.getCpf();
		this.email = tecnicoDTO.getEmail();
		this.senha = tecnicoDTO.getSenha();
		this.perfis = tecnicoDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = tecnicoDTO.getDataCriacao();
	}
	
	
}