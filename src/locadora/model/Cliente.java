package locadora.model;

import java.util.List;

public class Cliente {
    private static int idCounter = 1; // Contador para gerar IDs automáticos
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private List<Locacao> locacoes;  // Lista de locações associadas ao cliente

    // Construtor
    public Cliente(String nome, String cpf, String telefone, String email) {
        this.id = idCounter++;  // Gera um ID único automaticamente
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método para calcular o total pago em todas as locações (exemplo de funcionalidade adicional)
    public double calcularTotalPago() {
        double totalPago = 0;
        if (locacoes != null) {
            for (Locacao locacao : locacoes) {
                totalPago += locacao.getValorTotal();
            }
        }
        return totalPago;
    }

    // Método para adicionar locação (caso haja alguma locação associada ao cliente)
    public void adicionarLocacao(Locacao locacao) {
        if (this.locacoes != null) {
            this.locacoes.add(locacao);
        }
    }

    // Método toString para imprimir as informações do cliente
    @Override
    public String toString() {
        return "Cliente: " + nome + " | CPF: " + cpf + " | Telefone: " + telefone + " | Email: " + email;
    }
}
