package com.ericson.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ericson.helpdesk.TecnicoDTO;
import com.ericson.helpdesk.domain.Tecnico;
import com.ericson.helpdesk.services.TecnicoService;

@RestController
@RequestMapping("tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		
		Tecnico obj = tecnicoService.findById(id);
		TecnicoDTO tecnicoDTO = new TecnicoDTO(obj);
		
		return ResponseEntity.ok().body(tecnicoDTO);
		
	}
}