package locadora.view.administrador;

import locadora.view.TelaCadastroCliente;
import locadora.view.TelaCadastroVeiculo;
import locadora.view.TelaRelatorios;

import javax.swing.*;
import java.awt.*;

public class TelaAdministrador extends JFrame {

    public TelaAdministrador() {
        // Configuração da janela principal
        setTitle("Tela do Administrador");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Layout principal da janela
        setLayout(new BorderLayout());

        // Título da tela
        JLabel label = new JLabel("Bem-vindo, Administrador!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Define o estilo da fonte
        add(label, BorderLayout.NORTH); // Coloca o título na parte superior da tela

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 1, 10, 10)); // Organiza os botões em colunas
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona um espaçamento ao redor

        // Botão para cadastrar usuários
        JButton btnCadastrarUsuarios = new JButton("Cadastrar Usuários");
        btnCadastrarUsuarios.addActionListener(e -> cadastrarUsuarios()); // Ação de cadastro de usuários
        painelBotoes.add(btnCadastrarUsuarios);

        // Botão para cadastrar veículos
        JButton btnCadastrarVeiculos = new JButton("Cadastrar Veículos");
        btnCadastrarVeiculos.addActionListener(e -> cadastrarVeiculos()); // Ação de cadastro de veículos
        painelBotoes.add(btnCadastrarVeiculos);

        // Botão para gerar relatórios
        JButton btnRelatorios = new JButton("Gerar Relatórios");
        btnRelatorios.addActionListener(e -> gerarRelatorios()); // Ação de geração de relatórios
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

    // Método para abrir a tela de cadastro de usuários
    private void cadastrarUsuarios() {
        new TelaCadastroCliente(); 
    }

    // Método para abrir a tela de cadastro de veículos
    private void cadastrarVeiculos() {
        new TelaCadastroVeiculo(); 
    }

    // Método para abrir a tela de relatórios
    private void gerarRelatorios() {
        new TelaRelatorios(); // Abre a tela de relatórios
    }

    // Método para simular a ação de sair (volta para a tela de login)
    private void sair() {
        dispose(); // Fecha a tela atual
        new locadora.view.TelaLogin(); // Abre a tela de login
    }

    public static void main(String[] args) {
        new TelaAdministrador();
    }
}
