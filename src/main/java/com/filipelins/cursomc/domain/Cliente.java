package com.filipelins.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filipelins.cursomc.domain.enums.Perfil;
import com.filipelins.cursomc.domain.enums.TipoCliente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;
	private String cpfOuCnpj;
	private Integer tipoCliente;

	@JsonIgnore
	private String senha;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente.getCodigo();
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(this.tipoCliente);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCodigo();
	}

	public Set<Perfil> getPerfis() {
		return this.perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public Cliente(Integer id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
}
