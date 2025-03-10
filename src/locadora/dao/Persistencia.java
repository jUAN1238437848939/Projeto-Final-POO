package locadora.dao;
import java.util.List;

public interface Persistencia<T> {
    // Método para salvar uma lista de objetos. <T> Tipo de objeto a ser manipulado.
    void salvar(List<T> lista, String arquivo);
    // Método para carregar dados de um arquivo
    List<T> carregar(String arquivo);
    // Método para gerar relatório de pagamento
    void gerarRelatorioPagamento(T objeto);
}
