package com.ericson.helpdesk.resources;

import java.net.URI;
import java.security.Provider.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ericson.helpdesk.domain.Tecnico;
import com.ericson.helpdesk.dtos.TecnicoDTO;
import com.ericson.helpdesk.services.TecnicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

		Tecnico obj = tecnicoService.findById(id);
		TecnicoDTO tecnicoDTO = new TecnicoDTO(obj);

		return ResponseEntity.ok().body(tecnicoDTO);

	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		List<Tecnico> listTecnico = tecnicoService.findAll();
		List<TecnicoDTO> listTecnicoDTO = listTecnico.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listTecnicoDTO);

	}

	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {

		Tecnico tecnico = tecnicoService.create(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO) {

		Tecnico tecnico = tecnicoService.update(id, tecnicoDTO);

		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}
	
	@DeleteMapping(value ="/{id}" )
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		
		tecnicoService.delete(id);
		
		
		return ResponseEntity.noContent().build();
		
	}

}