package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidade.Cliente;
import entidade.Conta;
import entidade.Movimentacao;

public class ContaDAO extends DAOGenerico<Conta>{

	public ContaDAO() {
		super(Conta.class);
	}

	public List<Conta> buscarPorIdCliente(Conta conta) {
		EntityManager em = getEntityManager();
		Cliente cliente = conta.getCliente();
		long id = cliente.getId();
		List<Conta> contas = null;
		try {
			contas = em.createQuery("from Conta where id_cliente='" + id + "'", Conta.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return contas;
	}
	
	public Conta buscarPorId(Long id) {
		EntityManager em = getEntityManager();
		try {
			Conta conta = em.createQuery("from Conta where id = '"+id+"'", Conta.class).getSingleResult();
			em.close();
			return conta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
    }
}