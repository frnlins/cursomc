package com.filipelins.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.filipelins.cursomc.domain.Categoria;
import com.filipelins.cursomc.domain.Cidade;
import com.filipelins.cursomc.domain.Estado;
import com.filipelins.cursomc.domain.Produto;
import com.filipelins.cursomc.repositories.CategoriaRepository;
import com.filipelins.cursomc.repositories.CidadeRepository;
import com.filipelins.cursomc.repositories.EstadoRepository;
import com.filipelins.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado("Bahia");
		Estado est2 = new Estado("Ceará");

		Cidade cit1 = new Cidade("Salvador", est1);
		Cidade cit2 = new Cidade("Fortaleza", est2);
		Cidade cit3 = new Cidade("Jericoacoara", est2);

		est1.getCidades().add(cit1);
		est2.getCidades().addAll(Arrays.asList(cit2, cit3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
	}

}
