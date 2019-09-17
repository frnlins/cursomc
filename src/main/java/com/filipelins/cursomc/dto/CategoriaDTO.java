package com.filipelins.cursomc.dto;

import java.io.Serializable;

import com.filipelins.cursomc.domain.Categoria;

import lombok.Data;

@Data
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
}
