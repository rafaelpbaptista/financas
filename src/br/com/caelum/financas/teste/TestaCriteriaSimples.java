package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaCriteriaSimples {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Movimentacao> criteria = builder
				.createQuery(Movimentacao.class);

		criteria.from(Movimentacao.class);

		List<Movimentacao> movimentacoes = em.createQuery(criteria)
				.getResultList();

		System.out.println(movimentacoes);
		for (Movimentacao m : movimentacoes) {
			System.out.println("\nDescricao ..: " + m.getDescricao());
			System.out.println("Valor ......: R$ " + m.getValor());
			System.out.println("Tipo .......: " + m.getTipoMovimentacao());

		}

	}

}
