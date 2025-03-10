package locadora.controller;

import exception.DevolucaoAtrasadaException;
import exception.VeiculoIndisponivelException;
import locadora.model.Veiculo;
import locadora.model.Cliente;
import locadora.model.Locacao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocacaoController {

    private List<Locacao> locacoes;
    private List<Cliente> clientes; // Lista de clientes
    private List<Veiculo> veiculos; // Lista de veículos

    // Construtor
    public LocacaoController(List<Cliente> clientes, List<Veiculo> veiculos) {
        this.locacoes = new ArrayList<>();
        this.clientes = clientes;
        this.veiculos = veiculos;
    }

    // Método para registrar a locação de um veículo
    public Locacao registrarLocacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, int diasPrevistos) throws VeiculoIndisponivelException {
        // Verifica se o veículo está disponível para locação
        if (!veiculo.getStatus().equals("Disponível")) {  // A comparação continua sendo com String
            throw new VeiculoIndisponivelException("Erro: Veículo " + veiculo.getModelo() + "(Placa: " + veiculo.getPlaca() + ") não está disponível.");
        }

        // Calcula o valor total da locação com base no número de dias e tipo de veículo
        double valorTotal = veiculo.calcularCustoLocacao(diasPrevistos);

        // Cria a nova locação
        Locacao novaLocacao = new Locacao(veiculo.getId(), cliente.getId(), cliente, veiculo, dataRetirada, diasPrevistos, valorTotal);

        // Registra a locação
        locacoes.add(novaLocacao);

        // Atualiza o status do veículo para "Locado"
        veiculo.setStatus("Locado");

        return novaLocacao;
    }

    // Método para registrar a devolução do veículo
    public void registrarDevolucao(Locacao locacao, LocalDate dataDevolucao, double multaPorDia) {
        try {
            locacao.devolverVeiculo(dataDevolucao, multaPorDia);
            
            // Após devolução, atualiza o status do veículo para "Disponível"
            locacao.getVeiculo().setStatus("Disponível");
        } catch (DevolucaoAtrasadaException e) {
            System.out.println(e.getMessage()); // Lida com a exceção de devolução atrasada
        }
    }

    // Método para listar locações ativas (não devolvidas)
    public List<Locacao> listarLocacoesAtivas() {
        List<Locacao> ativas = new ArrayList<>();
        for (Locacao locacao : locacoes) {
            if (locacao.getDataDevolucaoReal() == null) {
                ativas.add(locacao);
            }
        }
        return ativas;
    }

    // Método para listar todas as locações (ativas e finalizadas)
    public List<Locacao> listarTodasLocacoes() {
        return locacoes;
    }

    // Método para verificar se um cliente já possui locação ativa
    public boolean clienteTemLocacaoAtiva(Cliente cliente) {
        for (Locacao locacao : locacoes) {
            if (locacao.getCliente().equals(cliente) && locacao.getDataDevolucaoReal() == null) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar locação ativa
    public Locacao buscarLocacaoAtiva(Cliente cliente, Veiculo veiculo) {
        for (Locacao locacao : locacoes) {
            if (locacao.getCliente().equals(cliente) && locacao.getVeiculo().equals(veiculo) && locacao.getDataDevolucaoReal() == null) {
                return locacao;
            }
        }
        return null;
    }

    // Método para verificar se um veículo está alugado
    public boolean veiculoEstaAlugado(Veiculo veiculo) {
        for (Locacao locacao : locacoes) {
            if (locacao.getVeiculo().equals(veiculo) && locacao.getDataDevolucaoReal() == null) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar cliente por nome
    public Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(nome)) {
                return cliente;
            }
        }
        return null;  // Retorna null se o cliente não for encontrado
    }

    // Método para buscar veículo por placa
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;  // Retorna null se o veículo não for encontrado
    }

    // Métodos para acessar as listas de clientes e veículos
    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
