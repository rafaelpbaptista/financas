package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaConsulta {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setId(1);

		// Consulta com concatenação de Strings
		//
		// Query query = manager
		// .createQuery("select m from Movimentacao m where m.conta.id="
		// + conta.getId());

		// Consulta com Positional Parameter Notation
		//
		// Query query = manager
		// .createQuery("select m from Movimentacao m where m.conta=?1");
		// query.setParameter(1, conta);

		// Consulta com Named Parameter Notation
		//
		// Query query = manager
		// .createQuery("select m from Movimentacao m where m.conta=:pConta");

		// Consulta com Named Parameter Notation e Ordenação
		//
		// Query query = manager
		// .createQuery("select m from Movimentacao m where m.conta = :pConta "
		// + " order by m.valor desc");

		Query query = manager
				.createQuery("select m from Movimentacao m where m.conta = :pConta"
						+ " and m.tipoMovimentacao = :pTipo"
						+ " order by m.valor desc");

		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		List<Movimentacao> movimentacoes = query.getResultList();

		for (Movimentacao m : movimentacoes) {
			System.out.println("\nDescricao ..: " + m.getDescricao());
			System.out.println("Valor ......: R$ " + m.getValor());
		}

	}
}
