package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import locadora.model.Pagamento;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PagamentoDAO implements Persistencia<Pagamento> {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String DIRETORIO_PDF = "relatorios/";

    @Override
    public void salvar(List<Pagamento> lista, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os pagamentos: " + e.getMessage());
        }
    }

    @Override
    public List<Pagamento> carregar(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type listType = new TypeToken<ArrayList<Pagamento>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Erro ao carregar os pagamentos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void gerarRelatorioPagamento(Pagamento pagamento) {
        // Cria o diretório caso não exista
        criarDiretorio(DIRETORIO_PDF);

        // Caminho do arquivo PDF
        String caminhoRelatorio = DIRETORIO_PDF + "RelatorioPagamento_" + pagamento.getIdPagamento() + ".pdf";
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(caminhoRelatorio));

            // Inicia o documento
            document.open();

            // Adiciona título ao relatório
            adicionarTextoAoRelatorio(document, "Relatório de Pagamento", Font.BOLD, 18, Element.ALIGN_CENTER);

            // Adiciona as informações de pagamento no PDF
            adicionarTextoAoRelatorio(document, "ID do Pagamento: " + pagamento.getIdPagamento());
            adicionarTextoAoRelatorio(document, "ID da Locação: " + pagamento.getIdLocacao());
            adicionarTextoAoRelatorio(document, "Valor Pago: R$ " + pagamento.getValorPago());
            adicionarTextoAoRelatorio(document, "Data de Pagamento: " + pagamento.getDataPagamento().toString());
            adicionarTextoAoRelatorio(document, "Método de Pagamento: " + pagamento.getMetodoPagamento());

            // Fecha o documento
            document.close();

            System.out.println("Relatório de pagamento gerado com sucesso em: " + caminhoRelatorio);
        } catch (DocumentException | IOException e) {
            System.err.println("Erro ao gerar o relatório de pagamento: " + e.getMessage());
        }
    }

    // Método para adicionar texto ao relatório com formatação
    private void adicionarTextoAoRelatorio(Document document, String texto) throws DocumentException {
        Font fontNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        document.add(new Paragraph(texto, fontNormal));
    }

    // Método para adicionar título ao relatório com formatação
    private void adicionarTextoAoRelatorio(Document document, String texto, int estilo, int tamanho, int alinhamento) throws DocumentException {
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, tamanho, estilo);
        Paragraph title = new Paragraph(texto, fontTitle);
        title.setAlignment(alinhamento);
        document.add(title);
    }

    // Método para criar diretório caso não exista
    private void criarDiretorio(String diretorio) {
        Path path = Paths.get(diretorio);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("Erro ao criar o diretório: " + e.getMessage());
            }
        }
    }
}
