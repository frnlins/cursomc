package com.filipelins.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.filipelins.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamento, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(cal.getTime());
	}
}
