package com.filipelins.cursomc.dto;

import java.io.Serializable;

import com.filipelins.cursomc.domain.Produto;

import lombok.Data;

@Data
public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double preco;

	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
	}
}
