package com.filipelins.cursomc.domain.enums;

public enum Perfil {

	/**
	 * O prefixo ROLE_ vem das especificações do spring security.
	 */
	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer codigo) {
		if (codigo == null)
			return null;

		for (Perfil ep : Perfil.values()) {
			if (ep.getCodigo().equals(codigo)) {
				return ep;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
