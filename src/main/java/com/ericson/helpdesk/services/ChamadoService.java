package com.ericson.helpdesk.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ericson.helpdesk.domain.Chamado;
import com.ericson.helpdesk.repositories.ChamadoRepository;
import com.ericson.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado findById(Integer id) {
		
		Optional<Chamado> obj = chamadoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

}
