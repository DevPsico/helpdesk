package com.ericson.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
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

import com.ericson.helpdesk.domain.Cliente;
import com.ericson.helpdesk.dtos.ClienteDTO;
import com.ericson.helpdesk.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {

		Cliente cliente = clienteService.findById(id);
		ClienteDTO clienteDTO = new ClienteDTO(cliente);

		return ResponseEntity.ok().body(clienteDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> listCliente = clienteService.findAll();
		List<ClienteDTO> listClienteDTO = listCliente.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listClienteDTO);

	}

	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {

		Cliente cliente = clienteService.create(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {

		Cliente cliente = clienteService.update(id, clienteDTO);

		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {

		clienteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
