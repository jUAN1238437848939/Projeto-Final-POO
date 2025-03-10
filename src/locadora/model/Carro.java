package locadora.model;

public class Carro extends Veiculo {
    // Construtor original
    public Carro(int id, String placa, String modelo, int ano, String status, Cliente cliente, double valorDiaria) {
        super(id, placa, modelo, ano, status, cliente, valorDiaria); // Passando o valor da diária
    }

    // Novo construtor compatível com o que a tela está usando
    public Carro(String placa, String modelo, int ano, double valorDiaria, Cliente cliente) {
        super(placa, modelo, ano, valorDiaria, cliente); // Passando o valor da diária corretamente
    }

    @Override
    public String getTipo() {
        return "Carro"; 
    }

    @Override
    public double calcularCustoLocacao(int dias) {
        return dias * 120; // Cálculo do custo de locação (120 por dia)
    }
}
