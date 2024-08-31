package com.ericson.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericson.helpdesk.domain.Pessoa;
import com.ericson.helpdesk.domain.Tecnico;
import com.ericson.helpdesk.dtos.TecnicoDTO;
import com.ericson.helpdesk.repositories.PessoaRepository;
import com.ericson.helpdesk.repositories.TecnicoRepository;
import com.ericson.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.ericson.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {

		Optional<Tecnico> obj = tecnicoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id));

	}

	public List<Tecnico> findAll() {

		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {

		tecnicoDTO.setId(null);

		validaPorCpfEEmail(tecnicoDTO);
		Tecnico tecnico = new Tecnico(tecnicoDTO);

		return tecnicoRepository.save(tecnico);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {
		// TODO Auto-generated method stub

		tecnicoDTO.setId(id);
		Tecnico tecnicoURI = findById(id);
		validaPorCpfEEmail(tecnicoDTO);

		tecnicoURI = new Tecnico(tecnicoDTO);

		return tecnicoRepository.save(tecnicoURI);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

		Tecnico tecnico = findById(id);

		if (tecnico.getChamados().size() > 0) {

			throw new DataIntegrityViolationException(
					"O Técnico possui ORDENS DE SERVIÇOS e não pode ser deletado !!!");

		} else {
			tecnicoRepository.deleteById(id);
		}
	}

	private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
		// TODO Auto-generated method stub

		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());

		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}

		pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());

		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}
	}

}