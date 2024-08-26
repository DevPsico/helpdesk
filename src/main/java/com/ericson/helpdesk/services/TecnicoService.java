package com.ericson.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericson.helpdesk.domain.Tecnico;
import com.ericson.helpdesk.dtos.TecnicoDTO;
import com.ericson.helpdesk.repositories.TecnicoRepository;
import com.ericson.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id));
		
	}
	
	public List<Tecnico> findAll(){
		
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		
		tecnicoDTO.setId(null);
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		
		
		return tecnicoRepository.save(tecnico);
	}
}