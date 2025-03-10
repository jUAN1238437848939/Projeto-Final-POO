package locadora.controller;

import locadora.dao.VeiculoDAO;
import locadora.model.Pagamento;
import locadora.model.Locacao;
import locadora.model.Veiculo;

import java.util.List;

public class RelatorioController {

    // Método para gerar relatório de pagamento (invoca o método na classe VeiculoDAO)
    public void gerarRelatorioPagamento(Pagamento pagamento) {
        // Obtém o veículo associado à locação do pagamento
        Veiculo veiculo = pagamento.getLocacao().getVeiculo();  // Acessando o veículo da locação

        // Verificando se o veículo foi encontrado
        if (veiculo != null) {
            // Cria o VeiculoDAO e chama o método de geração de relatório
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.gerarRelatorioPagamento(veiculo);  // Passa o veículo para gerar o relatório
        } else {
            System.out.println("Erro: Veículo não encontrado na locação.");
        }
    }

    // Método para gerar relatório de locações ativas
    public void gerarRelatorioLocacoesAtivas(List<Locacao> locacoesAtivas) {
        VeiculoDAO veiculoDAO = new VeiculoDAO();  // Cria uma instância do VeiculoDAO

        for (Locacao locacao : locacoesAtivas) {
            // Acessando o veículo da locação ativa
            Veiculo veiculo = locacao.getVeiculo();
            
            if (veiculo != null) {
                // Chama o método para gerar o relatório em PDF de locações ativas
                veiculoDAO.gerarRelatorioPagamento(veiculo);  // Aqui você pode usar o método de gerar relatório já existente
            } else {
                System.out.println("Erro: Veículo não encontrado para a locação ID: " + locacao.getIdLocacao());
            }
        }
    }
}



