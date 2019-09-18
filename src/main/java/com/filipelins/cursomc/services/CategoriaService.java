package com.filipelins.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.filipelins.cursomc.domain.Categoria;
import com.filipelins.cursomc.dto.CategoriaDTO;
import com.filipelins.cursomc.repositories.CategoriaRepository;
import com.filipelins.cursomc.services.exceptions.DataIntegrityException;
import com.filipelins.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria insert(Categoria categoria) {
		return repo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		Categoria currentCategoria= findById(categoria.getId());
		updateData(currentCategoria, categoria);
		return repo.save(currentCategoria);
	}

	public void deleteById(Integer id) {
		findById(id);

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}

	private void updateData(Categoria currentCategoria, Categoria categoria) {
		currentCategoria.setNome(categoria.getNome());
	}
}
