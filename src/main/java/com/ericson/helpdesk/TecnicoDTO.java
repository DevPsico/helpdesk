package com.ericson.helpdesk;

import com.ericson.helpdesk.domain.Pessoa;
import com.ericson.helpdesk.domain.Tecnico;

public class TecnicoDTO extends Pessoa{
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TecnicoDTO(Tecnico tecnico) {
		this.id = tecnico.getId();
		this.cpf = tecnico.getCpf();
		this.email = tecnico.getEmail();
		this.nome = tecnico.getNome();
		this.senha = tecnico.getSenha();
	}

	public TecnicoDTO(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		// TODO Auto-generated constructor stub
	}
}