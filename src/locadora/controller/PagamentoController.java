package locadora.controller;

import exception.PagamentoJaRegistradoException;
import locadora.model.Pagamento;
import locadora.model.Locacao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagamentoController {
    private List<Pagamento> pagamentos;

    public PagamentoController() {
        this.pagamentos = new ArrayList<>();
    }

    // Método para registrar um pagamento
    public Pagamento registrarPagamento(Locacao locacao, double valorPago, String metodoPagamento) throws PagamentoJaRegistradoException {
        // Verificar se a locação já foi paga
        if (verificarPagamento(locacao)) {
            throw new PagamentoJaRegistradoException("Erro: Esta locação já foi paga.");
        }

        // Criar pagamento e adicionar à lista
        Pagamento pagamento = new Pagamento(locacao, valorPago, LocalDate.now(), metodoPagamento);
        pagamentos.add(pagamento);
        return pagamento;
    }

    // Verificar se a locação já foi paga
    private boolean verificarPagamento(Locacao locacao) {
        for (Pagamento pagamento : pagamentos) {
            // Corrigido: utilizar getLocacao() para acessar o id da locação associada ao pagamento
            if (pagamento.getIdLocacao() == locacao.getIdLocacao()) {
                return true; 
            }
        }
        return false; // locação ainda não foi paga
    }

    // Método para listar todos os pagamentos
    public List<Pagamento> listarPagamentos() {
        return pagamentos;
    }

    // Método para buscar pagamentos por locação
    public List<Pagamento> buscarPagamentosPorLocacao(int idLocacao) {
        List<Pagamento> result = new ArrayList<>();
        for (Pagamento pagamento : pagamentos) {
            // Corrigido: utilizar getLocacao() para acessar o id da locação associada ao pagamento
            if (pagamento.getIdLocacao() == idLocacao) {
                result.add(pagamento);
            }
        }
        return result;
    }
}
