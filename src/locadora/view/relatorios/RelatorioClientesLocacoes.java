package locadora.view.relatorios;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.model.Cliente;
import locadora.model.Locacao;
import locadora.model.Veiculo;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class RelatorioClientesLocacoes {

    public void gerarRelatorio() {
        try {
            // Verificando se a pasta "relatorios" existe, se não, criando-a
            File pastaRelatorios = new File("relatorios");
            if (!pastaRelatorios.exists()) {
                pastaRelatorios.mkdir(); // Cria a pasta "relatorios"
            }
            // Definindo o caminho onde o PDF será salvo
            String caminhoRelatorio = "relatorios/relatorio_clientes_locacoes.pdf";
            // Criando o documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(caminhoRelatorio));

            document.open();

            // Título
            document.add(new Paragraph("Relatório de Clientes e Suas Locações"));
            document.add(Chunk.NEWLINE);

            // Adicionando uma tabela
            PdfPTable table = new PdfPTable(4); // 4 colunas: Cliente, ID Locação, Veículo, Data de Locação
            table.addCell("Cliente");
            table.addCell("ID Locação");
            table.addCell("Veículo");
            table.addCell("Data de Locação");

            // Carregar todos os clientes
            ClienteDAO clienteDAO = new ClienteDAO();
            List<Cliente> clientes = clienteDAO.carregar("clientes.json");

            LocacaoDAO locacaoDAO = new LocacaoDAO();

            // Para cada cliente, buscar as locações associadas
            for (Cliente cliente : clientes) {
                // Buscar as locações desse cliente
                List<Locacao> locacoes = locacaoDAO.carregar("locacoes.json").stream()
                        .filter(l -> l.getCliente().getId() == cliente.getId())
                        .toList();

                for (Locacao locacao : locacoes) {
                    Veiculo veiculo = locacao.getVeiculo(); // Veículo locado na locação

                    // Preenchendo a tabela com os dados de cada locação
                    table.addCell(cliente.getNome());
                    table.addCell(String.valueOf(locacao.getIdLocacao()));
                    table.addCell(veiculo != null ? veiculo.getModelo() : "Veículo Não Encontrado");
                    table.addCell(locacao.getDataRetirada().toString());
                }
            }

            // Adicionando a tabela ao documento
            document.add(table);

            // Finalizando o documento
            document.close();

            System.out.println("Relatório de clientes e suas locações gerado com sucesso! Salvo em: " + caminhoRelatorio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Criando uma instância do relatório de clientes e locações
        RelatorioClientesLocacoes relatorioClientesLocacoes = new RelatorioClientesLocacoes();

        // Gerando o relatório
        relatorioClientesLocacoes.gerarRelatorio();
    }
}
