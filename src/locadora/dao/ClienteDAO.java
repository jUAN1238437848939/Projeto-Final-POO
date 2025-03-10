package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.model.Cliente;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements Persistencia<Cliente> {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void salvar(List<Cliente> lista, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista de clientes: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar dados dos clientes.", e);
        }
    }

    @Override
    public List<Cliente> carregar(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type listType = new TypeToken<ArrayList<Cliente>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void gerarRelatorioPagamento(Cliente cliente) {
        Document document = new Document();

        try {
            String nomeArquivoRelatorio = "relatorio_pagamento_" + cliente.getId() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivoRelatorio));

            document.open();

            document.add(new Paragraph("Relatório de Pagamento - Cliente: " + cliente.getNome()));
            document.add(new Paragraph("\n")); 

            document.add(new Paragraph("ID Cliente: " + cliente.getId()));
            document.add(new Paragraph("Nome: " + cliente.getNome()));
            document.add(new Paragraph("Email: " + cliente.getEmail()));

            document.add(new Paragraph("Total Pago: R$ " + cliente.calcularTotalPago()));

            document.close();

            System.out.println("Relatório de pagamento gerado para o cliente: " + cliente.getNome());

        } catch (DocumentException | IOException e) {
            System.err.println("Erro ao gerar o relatório de pagamento: " + e.getMessage());
            throw new RuntimeException("Falha ao gerar relatório de pagamento", e);
        }
    }
}
