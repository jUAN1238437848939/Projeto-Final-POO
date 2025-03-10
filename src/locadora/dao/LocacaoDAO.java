package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import locadora.model.Locacao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO implements Persistencia<Locacao> {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void salvar(List<Locacao> lista, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            // Melhorar o tratamento de exceção lançando uma nova exceção.
            System.err.println("Erro ao salvar os dados de locações no arquivo: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar os dados de locação", e);
        }
    }

    @Override
    public List<Locacao> carregar(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type listType = new TypeToken<ArrayList<Locacao>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            // Adicionando uma mensagem de erro para facilitar a depuração.
            System.err.println("Erro ao carregar os dados de locações do arquivo: " + e.getMessage());
            return new ArrayList<>();  // Retorna lista vazia em caso de falha, mas podemos logar o erro.
        }
    }

    @Override
    public void gerarRelatorioPagamento(Locacao locacao) {
        Document document = new Document();

        try {
            // Caminho para salvar o relatório PDF
            String caminhoArquivoRelatorio = "RelatorioLocacao_" + locacao.getIdLocacao() + ".pdf";

            // Vai verificar o diretório onde o PDF será salvo existe, caso contrário, criá-lo.

            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivoRelatorio));

            document.open();

            // Título do PDF
            Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Relatório de Locação", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Adiciona espaçamento
            document.add(new Paragraph("\n"));

            // Adiciona informações da locação no PDF
            Font fontNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            document.add(new Paragraph("ID da Locação: " + locacao.getIdLocacao(), fontNormal));
            document.add(new Paragraph("ID do Veículo: " + locacao.getIdVeiculo(), fontNormal));
            document.add(new Paragraph("ID do Cliente: " + locacao.getIdCliente(), fontNormal));
            document.add(new Paragraph("Data de Retirada: " + locacao.getDataRetirada().toString(), fontNormal));
            document.add(new Paragraph("Data de Devolução: " + locacao.getDataDevolucaoPrevista().toString(), fontNormal));

            // Fechar o documento PDF
            document.close();

            System.out.println("Relatório de locação gerado com sucesso: " + caminhoArquivoRelatorio);

        } catch (DocumentException | IOException e) {
            // Melhorar o tratamento de exceção para fornecer uma mensagem mais clara
            System.err.println("Erro ao gerar o relatório de locação: " + e.getMessage());
            throw new RuntimeException("Falha ao gerar relatório de locação", e);
        }
    }
}
