package com.filipelins.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.filipelins.cursomc.domain.Categoria;
import com.filipelins.cursomc.domain.Cidade;
import com.filipelins.cursomc.domain.Cliente;
import com.filipelins.cursomc.domain.Endereco;
import com.filipelins.cursomc.domain.Estado;
import com.filipelins.cursomc.domain.ItemPedido;
import com.filipelins.cursomc.domain.Pagamento;
import com.filipelins.cursomc.domain.PagamentoComBoleto;
import com.filipelins.cursomc.domain.PagamentoComCartao;
import com.filipelins.cursomc.domain.Pedido;
import com.filipelins.cursomc.domain.Produto;
import com.filipelins.cursomc.domain.enums.EstadoPagamento;
import com.filipelins.cursomc.domain.enums.TipoCliente;
import com.filipelins.cursomc.repositories.CategoriaRepository;
import com.filipelins.cursomc.repositories.CidadeRepository;
import com.filipelins.cursomc.repositories.ClienteRepository;
import com.filipelins.cursomc.repositories.EnderecoRepository;
import com.filipelins.cursomc.repositories.EstadoRepository;
import com.filipelins.cursomc.repositories.ItemPedidoRepository;
import com.filipelins.cursomc.repositories.PagamentoRepository;
import com.filipelins.cursomc.repositories.PedidoRepository;
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

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");
		Categoria cat3 = new Categoria("Cozinha");
		Categoria cat4 = new Categoria("Eletrônicos");
		Categoria cat5 = new Categoria("Casa");
		Categoria cat6 = new Categoria("Jardim");
		Categoria cat7 = new Categoria("Decoração");
		Categoria cat8 = new Categoria("Perfumaria");

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
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

		Cliente cli1 = new Cliente("Joaquim Silva", "jsilva@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco("Rua Flores", "300", "Apto 203", "Jardim", "40000000", cli1, cit1);
		Endereco e2 = new Endereco("Av. Santos Dumont", "1000", "Sala 800", "Aldeota", "60000000", cli1, cit2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(sdf.parse("10/10/2019 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().add(ip3);
		
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
