package locadora.view.relatorios;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import locadora.dao.PagamentoDAO;
import locadora.dao.LocacaoDAO;
import locadora.model.Pagamento;
import locadora.model.Locacao;
import locadora.model.Cliente;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class RelatorioFaturamentoMensal {

    public void gerarRelatorio() {
        try {
            // Verificando se a pasta "relatorios" existe, se não, criando-a
            File pastaRelatorios = new File("relatorios");
            if (!pastaRelatorios.exists()) {
                pastaRelatorios.mkdir(); // Cria a pasta "relatorios"
            }

            // Definindo o caminho onde o PDF será salvo
            String caminhoRelatorio = "relatorios/relatorio_faturamento_mensal.pdf";

            // Criando o documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(caminhoRelatorio));

            document.open();
            
            // Título do relatório
            document.add(new Paragraph("Relatório de Faturamento Mensal"));
            document.add(Chunk.NEWLINE); // Linha em branco

            // Criando a tabela com 3 colunas: Pagamento, Cliente, Valor
            PdfPTable table = new PdfPTable(3);
            table.addCell("Pagamento");
            table.addCell("Cliente");
            table.addCell("Valor");

            // Carregando os pagamentos do arquivo JSON
            PagamentoDAO pagamentoDAO = new PagamentoDAO();
            List<Pagamento> pagamentos = pagamentoDAO.carregar("pagamentos.json");

            double totalFaturamento = 0;

            // Preenchendo a tabela com os dados dos pagamentos
            for (Pagamento pagamento : pagamentos) {
                // Buscando a locação associada ao pagamento
                LocacaoDAO locacaoDAO = new LocacaoDAO();
                Locacao locacao = locacaoDAO.carregar("locacoes.json").stream()
                        .filter(l -> l.getIdLocacao() == pagamento.getIdLocacao()) // Supondo que o Pagamento tem idLocacao
                        .findFirst()
                        .orElse(null);

                // Buscando o cliente associado à locação
                Cliente cliente = locacao != null ? locacao.getCliente() : null;

                // Preenchendo a tabela com as informações de cada pagamento
                table.addCell(String.valueOf(pagamento.getIdPagamento()));
                table.addCell(cliente != null ? cliente.getNome() : "Cliente Não Encontrado");
                table.addCell(String.format("R$ %.2f", pagamento.getValorPago()));

                totalFaturamento += pagamento.getValorPago(); // Somando os valores pagos
            }

            // Adicionando a tabela ao documento
            document.add(table);

            // Adicionando o total faturado ao final do relatório
            document.add(Chunk.NEWLINE); // Linha em branco
            document.add(new Paragraph("Total Faturado no Mês: R$ " + String.format("%.2f", totalFaturamento)));

            // Fechando o documento
            document.close();
            
            // Mensagem de sucesso
            System.out.println("Relatório de faturamento gerado com sucesso! Salvo em: " + caminhoRelatorio);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório.");
        }
    }

    public static void main(String[] args) {
        new RelatorioFaturamentoMensal().gerarRelatorio();
    }
}
