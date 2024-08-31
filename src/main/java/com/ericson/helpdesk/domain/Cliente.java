package com.ericson.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ericson.helpdesk.domain.enums.Perfil;
import com.ericson.helpdesk.dtos.ClienteDTO;
import com.ericson.helpdesk.dtos.TecnicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);

	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(ClienteDTO clienteDTO) {
		super();
		this.id = clienteDTO.getId();
		this.nome =clienteDTO.getNome();
		this.cpf =clienteDTO.getCpf();
		this.email =clienteDTO.getEmail();
		this.senha =clienteDTO.getSenha();
		this.perfis =clienteDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao =clienteDTO.getDataCriacao();
	}

	
	
	
}
