package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.model.Veiculo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private static final String FILE_PATH = "data/veiculos.json"; // Caminho fixo para o arquivo
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Método para carregar veículos do arquivo JSON
    public List<Veiculo> carregar(String arquivo) {
        try (FileReader reader = new FileReader(arquivo)) { // Usando o parâmetro arquivo
            Type listType = new TypeToken<ArrayList<Veiculo>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Erro ao carregar veículos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para salvar a lista de veículos no JSON
    public void salvar(List<Veiculo> lista) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(lista, writer);
            System.out.println("Veículos atualizados no JSON.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }

    // Método para adicionar um relatório ao histórico do veículo e salvar no JSON
    public void gerarRelatorioPagamento(Veiculo veiculo, String detalhesPagamento) {
        System.out.println("Registrando pagamento no JSON para o veículo: " + veiculo.getModelo() + " | Placa: " + veiculo.getPlaca());

        // Carrega os veículos existentes a partir do arquivo JSON
        List<Veiculo> veiculos = carregar(FILE_PATH); // Passando o caminho do arquivo

        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(veiculo.getPlaca())) {
                v.adicionarHistoricoPagamento(detalhesPagamento);
                break;
            }
        }

        // Salva os veículos atualizados no JSON
        salvar(veiculos);
    }
}
