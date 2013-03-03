package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaCriteriaPesquisandoPorConta {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Movimentacao> criteria = builder
				.createQuery(Movimentacao.class);

		// criteria.from(Movimentacao.class);

		Root<Movimentacao> root = criteria.from(Movimentacao.class);

		Path<Conta> caminhoAteConta = root.<Conta> get("conta");

		Conta conta = new Conta();
		conta.setId(1);

		Predicate contaIgual = builder.equal(caminhoAteConta, conta);

		criteria.where(contaIgual);

		List<Movimentacao> movimentacoes = em.createQuery(criteria)
				.getResultList();

		for (Movimentacao m : movimentacoes) {
			System.out.println("\nDescricao ..: " + m.getDescricao());
			System.out.println("Valor ......: R$ " + m.getValor());
			System.out.println("Tipo .......: " + m.getTipoMovimentacao());
		}

	}

}
