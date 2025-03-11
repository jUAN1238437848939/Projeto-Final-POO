package locadora.controller;

import locadora.dao.PagamentoDAO;
import locadora.model.Pagamento;
import locadora.model.Locacao;
import locadora.model.Veiculo;
import locadora.model.Cliente;
import java.util.List;

public class RelatorioController {

    private PagamentoDAO pagamentoDAO = new PagamentoDAO();

    
    public void gerarRelatorioPagamento(Pagamento pagamento) {
        // Obtém o veículo e o cliente associados à locação do pagamento
        Veiculo veiculo = pagamento.getLocacao().getVeiculo();
        Cliente cliente = pagamento.getLocacao().getCliente();

        // Verificando se o veículo e o cliente foram encontrados
        if (veiculo != null && cliente != null) {
            // Chama o método de geração de relatório de pagamento e armazena os dados no JSON
            pagamentoDAO.gerarRelatorioPagamento(pagamento); // Gera o relatório em PDF
            salvarHistoricoPagamento(pagamento);  // Salva o pagamento no histórico
        } else {
            System.out.println("Erro: Veículo ou Cliente não encontrado na locação.");
        }
    }

    // Método para salvar o histórico de pagamento no arquivo JSON
    private void salvarHistoricoPagamento(Pagamento pagamento) {
        List<Pagamento> pagamentos = pagamentoDAO.carregar("data/pagamentos.json");
        pagamentos.add(pagamento);
        // Salvar novamente no arquivo JSON
        pagamentoDAO.salvar(pagamentos, "data/pagamentos.json");
    }

    // Método para gerar relatório de locações ativas com pagamentos
    public void gerarRelatorioLocacoesAtivas(List<Locacao> locacoesAtivas) {
        for (Locacao locacao : locacoesAtivas) {
            // Acessando o veículo e pagamento da locação ativa
            Veiculo veiculo = locacao.getVeiculo();
            List<Pagamento> pagamentos = locacao.getPagamentos();

            // Gerando o relatório de pagamento para cada locação
            for (Pagamento pagamento : pagamentos) {
                if (veiculo != null) {
                    pagamentoDAO.gerarRelatorioPagamento(pagamento);  // Gera o relatório de pagamento em PDF
                } else {
                    System.out.println("Erro: Veículo não encontrado para a locação ID: " + locacao.getIdLocacao());
                }
            }
        }
    }
}
