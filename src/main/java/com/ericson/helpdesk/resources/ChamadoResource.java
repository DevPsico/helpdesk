package com.ericson.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ericson.helpdesk.domain.Chamado;
import com.ericson.helpdesk.dtos.ChamadoDTO;
import com.ericson.helpdesk.services.ChamadoService;

import jakarta.validation.Valid;

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
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		
		List<Chamado> listaChamado = chamadoService.findAll();
		List<ChamadoDTO> listaChamadoDTO = listaChamado.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaChamadoDTO);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> create (@Valid @RequestBody ChamadoDTO chamadoDTO){
		
		Chamado chamado = chamadoService.create(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(chamado.getId()).toUri();
		
		
		
		return ResponseEntity.created(uri).build();
	}

}
