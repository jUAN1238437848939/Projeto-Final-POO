package locadora.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Locacao {
    private int idLocacao;
    private int idVeiculo;
    private int idCliente;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double valorTotal;
    private List<Pagamento> pagamentos;

    // Construtor
    public Locacao(int idVeiculo, Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, int diasPrevistos, double valorTotal, List<Pagamento> pagamentos) {
        this.idVeiculo = idVeiculo;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucaoPrevista = dataRetirada.plusDays(diasPrevistos);
        this.dataDevolucaoReal = null;
        this.valorTotal = valorTotal;
        veiculo.setStatus("Locado"); // Alteração de status do veículo
        this.pagamentos = pagamentos;
    }
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

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    // Método para registrar o pagamento no momento da devolução
    public void registrarPagamento(double valorPago, String metodoPagamento) {
        // Cria um novo pagamento com os dados fornecidos
        Pagamento pagamento = new Pagamento(this, valorPago, LocalDate.now(), metodoPagamento);
        
        // Adiciona o pagamento à lista de pagamentos da locação
        this.pagamentos.add(pagamento);
        
        // Verifica se o valor pago é suficiente para cobrir o valor total da locação
        if (valorPago >= valorTotal) {
            System.out.println("Pagamento realizado com sucesso. Locação paga!");
        } else {
            System.out.println("Pagamento realizado. Ainda falta pagar: R$ " + (valorTotal - valorPago));
        }
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

        veiculo.setStatus("Disponível"); }

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
