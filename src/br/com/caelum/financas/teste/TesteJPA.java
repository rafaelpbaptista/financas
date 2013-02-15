package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {

		Conta conta = new Conta();

		conta.setBanco("HSBC");
		conta.setAgencia("321");
		conta.setNumero("123456");
		conta.setTitular("Joao Ferreira");

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		manager.persist(conta);

		manager.getTransaction().commit();

		manager.close();
	}
}
