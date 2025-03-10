package locadora.controller;

import locadora.model.Veiculo;
import java.util.ArrayList;
import java.util.List;

public class VeiculoController {
    private List<Veiculo> veiculos;

    public VeiculoController() {
        this.veiculos = new ArrayList<>();
    }
    
    // Método para adicionar um veículo à lista
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    // Método para buscar um veículo pelo número da placa
    public Veiculo buscarPorPlaca(String placa) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(placa)) {
                return veiculos.get(i);
            }
        }
        return null; // Retorna null caso o veículo não seja encontrado
    }
    
    // Método para listar todos os veículos disponíveis
    public List<Veiculo> listarDisponiveis() {
        List<Veiculo> disponiveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getStatus().equalsIgnoreCase("Disponível")) { // Verifica se o status é "Disponível"
                disponiveis.add(veiculo);
            }
        }
        return disponiveis;
    }
    
    // Método para listar todos os veículos cadastrados
    public List<Veiculo> listarTodos() {
        return veiculos;
    }
}
