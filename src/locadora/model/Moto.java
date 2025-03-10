package locadora.model;

public class Moto extends Veiculo {
	// Construtor original
	public Moto(int id, String placa, String modelo, int ano, String status, Cliente cliente, double valorDiaria) {
		super(id,placa,modelo,ano,status,cliente, valorDiaria);
	}
    // Novo construtor compatível com o que a tela está usando
	public Moto(String placa, String modelo, int ano, double valorDiaria, Cliente cliente) {
        super(placa, modelo, ano, valorDiaria, cliente);
    }
	@Override
	public String getTipo() {
        return "Moto"; 
    }

	@Override
	public double calcularCustoLocacao(int dias) {
		return dias * 50; 
	}

}