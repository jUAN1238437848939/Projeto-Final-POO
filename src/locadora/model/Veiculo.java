package locadora.model;

public abstract class Veiculo {

    protected int id; // Identificador único do veículo
    protected String placa;
    protected String modelo;
    protected int ano;
    protected String status;
    protected double valorDiaria; // Valor da diária do veículo
    protected Cliente cliente; // Cliente associado ao veículo

    // Construtor completo
    public Veiculo(int id, String placa, String modelo, int ano, String status, Cliente cliente, double valorDiaria) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.status = status != null ? status : "Disponível"; // Garantir que o status não seja nulo
        this.cliente = cliente; // Associando o cliente
        this.valorDiaria = valorDiaria; // Inicializando o valor da diária
    }

    // Novo construtor sem ID (para cadastro de veículos)
    public Veiculo(String placa, String modelo, int ano, double valorDiaria, Cliente cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.status = "Disponível"; // Define status padrão
        this.valorDiaria = valorDiaria; // Define o valor da diária
        this.cliente = cliente; // Associando o cliente
    }

    // Métodos getters e setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public abstract String getTipo();

    public abstract double calcularCustoLocacao(int dias); 

    @Override
    public String toString() {
        return modelo + " - Placa: " + placa + " - Ano: " + ano + " - Status: " + status + " - Valor Diária: R$" + valorDiaria;
    }
}
