package com.filipelins.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null)
			return null;

		for (EstadoPagamento ep : EstadoPagamento.values()) {
			if (ep.getCodigo().equals(codigo)) {
				return ep;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
