package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaCriteriaSomatorioPorContaETipo {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<BigDecimal> criteria = builder
				.createQuery(BigDecimal.class);

		Root<Movimentacao> root = criteria.from(Movimentacao.class);

		Conta conta = new Conta();
		conta.setId(1);

		Path<Conta> caminhoAteConta = root.<Conta> get("conta");
		Path<TipoMovimentacao> caminhoAteTipoMovimentacao = root
				.<TipoMovimentacao> get("tipoMovimentacao");

		Path<BigDecimal> caminhoAteValor = root.<BigDecimal> get("valor");
		List<Predicate> predicados = new ArrayList<Predicate>();

		Predicate contaIgual = builder.equal(caminhoAteConta, conta);
		predicados.add(contaIgual);

		Predicate tipoMovimentacaoIgual = builder.equal(
				caminhoAteTipoMovimentacao, TipoMovimentacao.ENTRADA);
		predicados.add(tipoMovimentacaoIgual);

		criteria.where(predicados.toArray(new Predicate[0]));
		Expression<BigDecimal> somatorio = builder.sum(caminhoAteValor);
		criteria.select(somatorio);

		System.out.println(em.createQuery(criteria).getSingleResult());

	}

}
