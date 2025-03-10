package locadora.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Locacao {
    private static final Random random = new Random(); // Tornar o random constante
    private int idLocacao;
    private int idVeiculo;
    private int idCliente;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double valorTotal;

    // Construtor
    public Locacao(int idVeiculo, int idCliente, Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, int diasPrevistos, double valorTotal) {
        this.idLocacao = 10000000 + random.nextInt(90000000); // Geração de ID aleatório
        this.idVeiculo = idVeiculo;
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucaoPrevista = dataRetirada.plusDays(diasPrevistos);
        this.dataDevolucaoReal = null;
        this.valorTotal = valorTotal;
        veiculo.setStatus("Locado"); // Alteração de status do veículo
    }

    // Getters e Setters
    public int getIdLocacao() {
        return this.idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    // Método para devolver o veículo e aplicar multa por atraso
    public void devolverVeiculo(LocalDate dataDevolucaoReal, double multaPorDia) throws exception.DevolucaoAtrasadaException {
        // Verifica se a devolução já foi registrada
        if (this.dataDevolucaoReal != null) {
            System.out.println("Erro: O veículo já foi devolvido.");
            return;
        }

        this.dataDevolucaoReal = dataDevolucaoReal;
        long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        if (diasAtraso > 0) {
            valorTotal += diasAtraso * multaPorDia; // Aplica a multa por atraso
            throw new exception.DevolucaoAtrasadaException("A devolução foi realizada com " + diasAtraso + " dias de atraso. Multa aplicada.");
        }

        veiculo.setStatus("Disponível"); // Atualiza o status do veículo
    }

    // Representação em String da Locação
    @Override
    public String toString() {
        return "Locação - ID Locação: " + getIdLocacao() +
                ", Cliente: " + cliente.getNome() +
                ", Veículo: " + veiculo.getModelo() + " (" + veiculo.getPlaca() + ")" +
                ", Retirada: " + dataRetirada +
                ", Devolução Prevista: " + dataDevolucaoPrevista +
                ", Devolução Real: " + (dataDevolucaoReal != null ? dataDevolucaoReal : "Não devolvido") +
                ", Valor Total: R$ " + valorTotal;
    }
}
