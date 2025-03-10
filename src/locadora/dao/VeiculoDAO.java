package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import locadora.model.Veiculo;

import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements Persistencia<Veiculo> {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void salvar(List<Veiculo> lista, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(lista, writer);
            System.out.println("Lista de veículos salva com sucesso no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de veículos: " + e.getMessage());
        }
    }

    @Override
    public List<Veiculo> carregar(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type listType = new TypeToken<ArrayList<Veiculo>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Erro ao carregar os veículos do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void gerarRelatorioPagamento(Veiculo veiculo) {
        
        System.out.println("Gerando relatório de pagamento para o veículo: " + veiculo.getModelo() + " | Placa: " + veiculo.getPlaca());

        // Gerar PDF do relatório de pagamento
        gerarRelatorioPDF(veiculo);
    }

    // Método separado para gerar relatório PDF
    private void gerarRelatorioPDF(Veiculo veiculo) {
        Document document = new Document();
        try {
            // Caminho para salvar o relatório PDF
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("relatorio_veiculo_" + veiculo.getPlaca() + ".pdf"));
            document.open();

            // Título e informações no relatório
            document.add(new Paragraph("Relatório de Pagamento - Veículo: " + veiculo.getModelo() + " | Placa: " + veiculo.getPlaca()));
            document.add(new Paragraph("----------------------------------------------------------------"));
            document.add(new Paragraph("Modelo: " + veiculo.getModelo()));
            document.add(new Paragraph("Placa: " + veiculo.getPlaca()));
            document.add(new Paragraph("Status: " + veiculo.getStatus()));

            // Fechar o documento e salvar
            document.close();
            writer.close();

            System.out.println("Relatório gerado com sucesso em PDF.");
        } catch (Exception e) {
            System.err.println("Erro ao gerar o relatório em PDF: " + e.getMessage());
        }
    }
}
