package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOGenerico<T> {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
	private final Class<T> classeEntidade;
	
	public DAOGenerico(Class<T> classeEntidade) {
		this.classeEntidade = classeEntidade;
	}
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public T inserir(T objeto) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(objeto);
			em.getTransaction().commit();
			return objeto;
		}catch (Exception e) {
			em.getTransaction().rollback();
            em.close();
            return null;
		}
	}
	
	public T alterar(T objeto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T objetoAlterado = em.merge(objeto);
            em.getTransaction().commit();
            em.close();
            return objetoAlterado;
        } catch (Exception e) {
        	em.getTransaction().rollback();
            em.close();
            return null;
        }
    }

    public T excluir(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T objeto = em.find(classeEntidade, id);
            if (objeto != null) {
                em.remove(objeto);
            }
            em.getTransaction().commit();
            em.close();
            return objeto;
        } catch (Exception e) {
        	em.getTransaction().rollback();
            em.close();
            return null;
        }
    }
}