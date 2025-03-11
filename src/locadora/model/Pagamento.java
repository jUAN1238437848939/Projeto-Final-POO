package locadora.model;

import java.time.LocalDate;

public class Pagamento {
    private int idLocacao;
    private int idPagamento;
    private double valorPago;
    private LocalDate dataPagamento;
    private String metodoPagamento;
    private Locacao locacao;  // Adicionei o campo Locacao

    // Construtor
    public Pagamento(Locacao locacao, double valorPago, LocalDate dataPagamento, String metodoPagamento) {
        this.locacao = locacao; 
        this.idLocacao = locacao.getIdLocacao();
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.metodoPagamento = metodoPagamento;
    }

    // Métodos getters
    public int getIdPagamento() {
        return idPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public Locacao getLocacao() {  // Adicionando o método getLocacao()
        return locacao;
    }
    public int getIdLocacao() {
        return locacao.getIdLocacao();  
    }

    // Representação em String da classe Pagamento
    @Override
    public String toString() {
        return "Pagamento [ID: " + idPagamento + "] - Locação ID: " + idLocacao +
               ", Valor Pago: R$ " + valorPago + ", Data: " + dataPagamento +
               ", Método: " + metodoPagamento;
    }

}
