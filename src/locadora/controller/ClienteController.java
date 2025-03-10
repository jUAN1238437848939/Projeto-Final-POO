package locadora.controller;

import exception.ClienteExistenteException;
import locadora.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    // Método para cadastrar um cliente
    public void cadastrarCliente(Cliente cliente) throws ClienteExistenteException {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cliente.getCpf())) {
                throw new ClienteExistenteException("Erro: Já existe um cliente cadastrado com esse CPF.");
            }
        }
        clientes.add(cliente);
    }

    // Método para buscar um cliente pelo CPF
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null; // Retorna null caso não encontre o cliente
    }

    // Método para listar todos os clientes
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes); // Retorna uma cópia para evitar modificações externas
    }
}
