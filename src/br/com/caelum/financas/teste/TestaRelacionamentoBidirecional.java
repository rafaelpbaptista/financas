package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestaRelacionamentoBidirecional {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager
				.createQuery("select distinct c from Conta c join fetch c.movimentacoes");

		List<Conta> contas = query.getResultList();

		for (Conta c : contas) {
			System.out.println("\nTitular .............: " + c.getTitular());
			System.out.println("No. movimentacoes ...: "
					+ c.getMovimentacoes().size());
		}

	}

}
