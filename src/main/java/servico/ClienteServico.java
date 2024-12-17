package servico;

import java.util.List;

import dao.ClienteDAO;
import dao.DAOGenerico;
import entidade.Cliente;
import util.ValidarCpf;

public class ClienteServico implements ServicoInterfaceGenerica<Cliente>{
    ClienteDAO dao = new ClienteDAO();

    @Override
    public Cliente inserir(Cliente cliente) {
        if (validarOperacao(cliente) == false) {
            System.out.println("Operação inválida");
            return null;
        }
        return dao.inserir(cliente);
    }

    public boolean validarOperacao(Cliente cliente) {
        String cpf = cliente.getCpf();
        if (ValidarCpf.validarCpf(cpf) == false) {
            System.out.println("CPF inválido");
            return false;
        }
        if (buscarPorCpf(cpf) == false) {
            System.out.println("CPF já cadastrado");
            return false;
        }

        return true;
    }

    public boolean buscarPorCpf(String cpf) {
        List<Cliente> clientes = dao.buscarPorCpf(cpf);
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return false;
            }
        }
        return true;
    }

	@Override
	public DAOGenerico<Cliente> getDao() {
		return dao;
	}
}
