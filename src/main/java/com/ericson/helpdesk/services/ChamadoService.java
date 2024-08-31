package com.ericson.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ericson.helpdesk.domain.Chamado;
import com.ericson.helpdesk.domain.Cliente;
import com.ericson.helpdesk.domain.Tecnico;
import com.ericson.helpdesk.domain.enums.Prioridade;
import com.ericson.helpdesk.domain.enums.Status;
import com.ericson.helpdesk.dtos.ChamadoDTO;
import com.ericson.helpdesk.repositories.ChamadoRepository;
import com.ericson.helpdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {

		Optional<Chamado> obj = chamadoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

	public List<Chamado> findAll() {

		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {

		return chamadoRepository.save(newChamado(chamadoDTO));
	}

	public Chamado update(Integer id, @Valid ChamadoDTO chamadoDTO) {
		// TODO Auto-generated method stub

		chamadoDTO.setId(id);
		Chamado oldChamado = findById(id);

		oldChamado = newChamado(chamadoDTO);

		return chamadoRepository.save(oldChamado);

	}

	private Chamado newChamado(ChamadoDTO chamadoDTO) {

		Tecnico tecnico = tecnicoService.findById(chamadoDTO.getCodTecnico());
		Cliente cliente = clienteService.findById(chamadoDTO.getCodCliente());

		Chamado chamado = new Chamado();

		if (chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		
		if (chamadoDTO.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
	

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacoes(chamadoDTO.getObservacoes());

		return chamado;
	}
}