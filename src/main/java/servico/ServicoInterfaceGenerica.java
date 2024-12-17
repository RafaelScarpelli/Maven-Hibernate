package servico;

import dao.DAOGenerico;

public interface ServicoInterfaceGenerica<T> {

	DAOGenerico<T> getDao();

	default T inserir(T objeto) {
		return getDao().inserir(objeto);
	}

	default T alterar(T objeto) {
		return getDao().alterar(objeto);
	}

	default void excluir(Long id) {
		getDao().excluir(id);
	}
}
