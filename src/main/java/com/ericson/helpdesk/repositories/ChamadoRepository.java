package com.ericson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericson.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
