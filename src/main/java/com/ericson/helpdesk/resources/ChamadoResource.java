package com.ericson.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ericson.helpdesk.domain.Chamado;
import com.ericson.helpdesk.dtos.ChamadoDTO;
import com.ericson.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		
		Chamado chamado = chamadoService.findById(id);
		
		
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
		
	}

}
