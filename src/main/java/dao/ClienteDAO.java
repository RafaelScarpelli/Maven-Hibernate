package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidade.Cliente;
import entidade.Movimentacao;

public class ClienteDAO extends DAOGenerico<Cliente>{

    public ClienteDAO() {
		super(Cliente.class);
	}

    public Cliente buscarPorId(Long id) {
		EntityManager em = getEntityManager();
		Cliente cliente = em.find(Cliente.class, id);
		em.close();
		return cliente;
	}

    public List<Cliente> buscarPorCpf(String cpf) {
        EntityManager em = getEntityManager();
        List<Cliente> clientes = null;
        try {
            clientes = em.createQuery("from Cliente where cpf='" + cpf + "'", Cliente.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return clientes;
    }
}
