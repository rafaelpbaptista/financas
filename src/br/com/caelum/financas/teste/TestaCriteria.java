package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaCriteria {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setId(3);

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Movimentacao> criteria = builder
				.createQuery(Movimentacao.class);

		Root<Movimentacao> root = criteria.from(Movimentacao.class);

	}

}
