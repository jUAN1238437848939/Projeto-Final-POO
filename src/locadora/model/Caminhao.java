package locadora.model;

public class Caminhao extends Veiculo {

    // Construtor original
    public Caminhao(int id, String placa, String modelo, int ano, String status, Cliente cliente, double valorDiaria) {
        super(id, placa, modelo, ano, status, cliente, valorDiaria); 
    }

    // Construtor compatível com a tela
    public Caminhao(String placa, String modelo, int ano, double valorDiaria, Cliente cliente) {
        super(placa, modelo, ano, valorDiaria, cliente);
    }
    @Override
	public String getTipo() {
        return "Caminhao"; 
    }
        
    @Override
    public double calcularCustoLocacao(int dias) {
        return dias * 300;
    }
}

