package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaRelacionamento {
	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		Conta conta = new Conta();

		conta.setBanco("Santander");
		conta.setNumero("99999-9");
		conta.setAgencia("9999");
		conta.setTitular("Maria");

		// persistindo a conta
		manager.persist(conta);

		Movimentacao movimentacao = new Movimentacao();

		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Conta de luz - ABRIL/2012");
		movimentacao.setValor(new BigDecimal("135"));
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);

		movimentacao.setConta(conta);

		manager.persist(movimentacao);

		manager.getTransaction().commit();

		manager.close();
	}

}
