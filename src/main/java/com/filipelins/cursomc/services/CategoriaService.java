package com.filipelins.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipelins.cursomc.domain.Categoria;
import com.filipelins.cursomc.repositories.CategoriaRepository;
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

	public Categoria insert(Categoria categoria) {
		return repo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		findById(categoria.getId());
		return repo.save(categoria);
	}
}
