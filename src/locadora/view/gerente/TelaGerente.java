package locadora.view.gerente;

import locadora.view.TelaCadastroCliente;
import locadora.view.TelaCadastroVeiculo;
import locadora.view.TelaRelatorios;

import javax.swing.*;
import java.awt.*;

public class TelaGerente extends JFrame {

    public TelaGerente() {
        // Configuração da janela principal
        setTitle("Tela do Gerente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Layout principal da janela
        setLayout(new BorderLayout());

        // Título da tela
        JLabel label = new JLabel("Bem-vindo, Gerente!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Define o estilo da fonte
        add(label, BorderLayout.NORTH); // Coloca o título na parte superior da tela

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 1, 10, 10)); // Organiza os botões em colunas
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona um espaçamento ao redor

        // Botão para cadastrar veículos
        JButton btnCadastrarVeiculos = new JButton("Cadastrar Veículos");
        btnCadastrarVeiculos.addActionListener(e -> cadastrarVeiculos()); // Ação para cadastrar veículos
        painelBotoes.add(btnCadastrarVeiculos);

        // Botão para cadastrar clientes
        JButton btnCadastrarClientes = new JButton("Cadastrar Clientes");
        btnCadastrarClientes.addActionListener(e -> cadastrarClientes()); // Ação para cadastrar clientes
        painelBotoes.add(btnCadastrarClientes);

        // Botão para gerar relatórios
        JButton btnRelatorios = new JButton("Gerar Relatórios");
        btnRelatorios.addActionListener(e -> gerarRelatorios()); // Ação para gerar relatórios
        painelBotoes.add(btnRelatorios);


        // Botão de sair (volta para a tela de login)
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> sair()); // Ação de sair da tela
        painelBotoes.add(btnSair);

        // Adiciona o painel de botões na tela
        add(painelBotoes, BorderLayout.CENTER);

        // Exibe a tela
        setVisible(true);
    }

    // Método para abrir a tela de cadastro de veículos
    private void cadastrarVeiculos() {
        new TelaCadastroVeiculo(); // Abre a tela de cadastro de veículos
    }

    // Método para abrir a tela de cadastro de clientes
    private void cadastrarClientes() {
        new TelaCadastroCliente(); // Abre a tela de cadastro de clientes
    }

    // Método para abrir a tela de relatórios
    private void gerarRelatorios() {
        new TelaRelatorios();
    }

    // Método para simular a ação de sair (volta para a tela de login)
    private void sair() {
        dispose(); // Fecha a tela atual
        new locadora.view.TelaLogin(); // Abre a tela de login
    }

    public static void main(String[] args) {
        new TelaGerente(); // Executa a tela de gerente
    }
}


