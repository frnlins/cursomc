package com.filipelins.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filipelins.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
