package com.filipelins.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filipelins.cursomc.domain.Categoria;
import com.filipelins.cursomc.dto.CategoriaDTO;
import com.filipelins.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categorias = service.findAll();
		List<CategoriaDTO> list = categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> categorias = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> list = categorias.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria categoria = service.findById(id);
		return ResponseEntity.ok(categoria);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id) {
		objDTO.setId(id);
		Categoria categoria = service.fromDTO(objDTO);
		categoria = service.update(categoria);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
