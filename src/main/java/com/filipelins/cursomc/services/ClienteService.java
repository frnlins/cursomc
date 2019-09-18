package com.filipelins.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.filipelins.cursomc.domain.Cliente;
import com.filipelins.cursomc.dto.ClienteDTO;
import com.filipelins.cursomc.repositories.ClienteRepository;
import com.filipelins.cursomc.services.exceptions.DataIntegrityException;
import com.filipelins.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente update(Cliente cliente) {
		Cliente currentCliente = findById(cliente.getId());
		updateData(currentCliente, cliente);
		return repo.save(currentCliente);
	}

	public void deleteById(Integer id) {
		findById(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());
	}

	private void updateData(Cliente currentCliente, Cliente cliente) {
		currentCliente.setNome(cliente.getNome());
		currentCliente.setEmail(cliente.getEmail());
	}
}
