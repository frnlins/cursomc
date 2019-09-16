package com.filipelins.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filipelins.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
