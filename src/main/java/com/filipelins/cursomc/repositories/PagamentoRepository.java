package com.filipelins.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filipelins.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
