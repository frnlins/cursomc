package com.filipelins.cursomc.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Integer id;
	private final String nome;
}
