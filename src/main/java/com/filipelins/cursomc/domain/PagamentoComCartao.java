package com.filipelins.cursomc.domain;

import javax.persistence.Entity;

import com.filipelins.cursomc.domain.enums.EstadoPagamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoComCartao(EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(estadoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
	}
}
