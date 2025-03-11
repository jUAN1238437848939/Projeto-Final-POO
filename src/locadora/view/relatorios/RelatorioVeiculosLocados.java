package locadora.view.relatorios;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import locadora.dao.LocacaoDAO;
import locadora.model.Locacao;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class RelatorioVeiculosLocados {
    
    public void gerarRelatorio() {
        try {
             // Verificando se a pasta "relatorios" existe, se não, criando-a
             File pastaRelatorios = new File("relatorios");
             if (!pastaRelatorios.exists()) {
                 pastaRelatorios.mkdir(); // Cria a pasta "relatorios"
             }
             
             // Definindo o caminho onde o PDF será salvo
            String caminhoRelatorio = "relatorios/relatorio_veiculoslocados.pdf";
            // Criando o documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(caminhoRelatorio));
            document.open();
            
            // Título
            document.add(new Paragraph("Relatório de Veículos Locados no Mês"));
            document.add(Chunk.NEWLINE);
            
            // Adicionando uma tabela
            PdfPTable table = new PdfPTable(4); // 4 colunas: Veículo, Cliente, Data Locação, Data Devolução
            table.addCell("Veículo");
            table.addCell("Cliente");
            table.addCell("Data de Locação");
            table.addCell("Data de Devolução");

            LocacaoDAO locacaoDAO = new LocacaoDAO();
            List<Locacao> locacoes = locacaoDAO.carregar("locacoes.json"); // Carregar as locações do JSON

            for (Locacao locacao : locacoes) {
                // Preenchendo a tabela com os dados de cada locação
                table.addCell(locacao.getVeiculo().getModelo());
                table.addCell(locacao.getCliente().getNome());
                table.addCell(locacao.getDataRetirada().toString());
                table.addCell(locacao.getDataDevolucaoReal().toString());
            }

            document.add(table);
            document.close();
            
            System.out.println("Relatório de veículos locados gerado com sucesso! Salvo em: " + caminhoRelatorio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
