package com.filipelins.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.filipelins.cursomc.domain.Categoria;
import com.filipelins.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/**
	 * O spring cria a consulta apenas pelo nome do método, a query correspondente
	 * seria:
	 *
	 * @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat
	 * WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	 * 
	 * Os parametros do método tbm teriam que estar anotados com @Param("...")
	 * 
	 * @param nome
	 * @param categorias
	 * @param pageRequest
	 * @return
	 */
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
			Pageable pageRequest);

}
