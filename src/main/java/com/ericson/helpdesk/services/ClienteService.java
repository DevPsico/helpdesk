package com.ericson.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ericson.helpdesk.domain.Cliente;
import com.ericson.helpdesk.domain.Pessoa;
import com.ericson.helpdesk.dtos.ClienteDTO;
import com.ericson.helpdesk.repositories.ClienteRepository;
import com.ericson.helpdesk.repositories.PessoaRepository;
import com.ericson.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.ericson.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Cliente findById(Integer id) {
		// TODO Auto-generated method stub

		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado !"));
	}

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	public Cliente create(@Valid ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub

		clienteDTO.setId(null);
		clienteDTO.setSenha(bCryptPasswordEncoder.encode(clienteDTO.getSenha()));
		validaPorCpfEEmail(clienteDTO);

		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}

	public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub

		clienteDTO.setId(id);

		Cliente clienteURI = findById(id);
		validaPorCpfEEmail(clienteDTO);

		clienteURI = new Cliente(clienteDTO);

		return clienteRepository.save(clienteURI);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

		Cliente cliente = findById(id);

		if (cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException(
					"O cliente possui ORDENS DE SERVIÇOS e não pode ser deletado !!!");
		} else {
			clienteRepository.deleteById(id);
		}
	}

	private void validaPorCpfEEmail(ClienteDTO clienteDTO) {
		// TODO Auto-generated method stub

		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());

		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}

		pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());

		if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}
	}
}