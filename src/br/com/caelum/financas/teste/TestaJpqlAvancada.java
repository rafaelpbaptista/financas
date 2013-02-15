package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaJpqlAvancada {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setId(3);

		// String jpql =
		// "select sum(m.valor) from Movimentacao m where m.conta=:pConta "
		// + "and m.tipoMovimentacao=:pTipo";

		String jpql = "select avg(m.valor) from Movimentacao m where m.conta=:pConta "
				+ "and m.tipoMovimentacao=:pTipo";

		// Query query = manager.createQuery(jpql);

		// TypedQuery<BigDecimal> query = manager.createQuery(jpql,
		// BigDecimal.class);

		TypedQuery<Double> query = manager.createQuery(jpql, Double.class);

		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		// BigDecimal resultado = query.getSingleResult();

		Double resultado = query.getSingleResult();

		System.out.println("Total movimentado ..: R$ " + resultado);

	}

}
