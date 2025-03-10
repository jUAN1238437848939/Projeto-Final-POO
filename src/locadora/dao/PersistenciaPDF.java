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

public class PersistenciaPDF implements Persistencia<Pagamento> {

    // Gson para converter objetos para JSON e vice-versa
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void salvar(List<Pagamento> lista, String arquivo) {
        try (FileWriter writer = new FileWriter(arquivo)) {
            // Serializa a lista de pagamentos para o formato JSON e salva no arquivo
            gson.toJson(lista, writer);
            System.out.println("Lista de pagamentos salva com sucesso em: " + arquivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar a lista de pagamentos.");
        }
    }

    @Override
    public List<Pagamento> carregar(String arquivo) {
        List<Pagamento> listaPagamentos = new ArrayList<>();
        try (FileReader reader = new FileReader(arquivo)) {
            // Definindo o tipo de dados esperado (List<Pagamento>) para o Gson
            Type listType = new TypeToken<List<Pagamento>>(){}.getType();
            // Desserializa o conteúdo do arquivo JSON em uma lista de pagamentos
            listaPagamentos = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados do arquivo: " + arquivo);
        }
        return listaPagamentos;
    }

    @Override
    public void gerarRelatorioPagamento(Pagamento pagamento) {
        Document document = new Document();

        try {
            // Caminho do arquivo PDF onde o relatório será gerado
            PdfWriter.getInstance(document, new FileOutputStream("RelatorioPagamento_" + pagamento.getIdPagamento() + ".pdf"));

            // Inicia o documento PDF
            document.open();

            // Título do relatório
            Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Relatório de Pagamento", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Adiciona um espaçamento entre o título e as informações
            document.add(new Paragraph("\n"));

            // Adiciona as informações de pagamento ao PDF
            Font fontNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
            document.add(new Paragraph("ID do Pagamento: " + pagamento.getIdPagamento(), fontNormal));
            document.add(new Paragraph("ID da Locação: " + pagamento.getIdLocacao(), fontNormal));
            document.add(new Paragraph("Valor Pago: R$ " + pagamento.getValorPago(), fontNormal));
            document.add(new Paragraph("Data de Pagamento: " + pagamento.getDataPagamento().toString(), fontNormal));
            document.add(new Paragraph("Método de Pagamento: " + pagamento.getMetodoPagamento(), fontNormal));

            // Fecha o documento PDF
            document.close();

            System.out.println("Relatório de pagamento gerado com sucesso.");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar o relatório de pagamento.");
        }
    }
}
