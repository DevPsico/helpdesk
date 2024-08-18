package com.ericson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericson.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
