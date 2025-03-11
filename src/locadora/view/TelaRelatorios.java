package locadora.view;

import javax.swing.*;
import java.awt.*;

public class TelaRelatorios extends JFrame {

    public TelaRelatorios() {
        // Configuração da janela de relatórios
        setTitle("Selecione o Relatório");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Layout da tela de relatórios
        setLayout(new BorderLayout());

        // Título da tela
        JLabel label = new JLabel("Escolha o Relatório para Gerar", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH); // Coloca o título na parte superior da tela

        // Painel de botões para os relatórios
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 1, 10, 10)); // Organiza os botões em colunas
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona espaçamento ao redor

        // Botão para gerar o relatório de clientes e locações
        JButton btnRelatorioClientesLocacoes = new JButton("Clientes e Locações");
        btnRelatorioClientesLocacoes.addActionListener(e -> gerarRelatorioClientesLocacoes());
        painelBotoes.add(btnRelatorioClientesLocacoes);

        // Botão para gerar outro relatório, por exemplo, de pagamentos
        JButton btnRelatorioPagamentos = new JButton("Relatório de Pagamentos");
        btnRelatorioPagamentos.addActionListener(e -> gerarRelatorioPagamentos());
        painelBotoes.add(btnRelatorioPagamentos);

        // Botão para gerar outro relatório
        JButton btnRelatorioVeiculos = new JButton("Relatório de Veículos");
        btnRelatorioVeiculos.addActionListener(e -> gerarRelatorioVeiculos());
        painelBotoes.add(btnRelatorioVeiculos);

        // Botão de sair
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> sair());
        painelBotoes.add(btnSair);

        // Adiciona o painel de botões na tela
        add(painelBotoes, BorderLayout.CENTER);

        // Exibe a tela
        setVisible(true);
    }

    // Métodos para gerar os relatórios
    private void gerarRelatorioClientesLocacoes() {
        // Chama a geração do relatório de clientes e locações
        new locadora.view.relatorios.RelatorioClientesLocacoes().gerarRelatorio();
        dispose(); // Fecha a tela de relatórios
    }

    private void gerarRelatorioPagamentos() {
        // Aqui você deve adicionar a lógica para gerar o relatório de pagamentos
        System.out.println("Gerando relatório de pagamentos...");
        dispose(); // Fecha a tela de relatórios
    }

    private void gerarRelatorioVeiculos() {
        // Aqui você deve adicionar a lógica para gerar o relatório de veículos
        System.out.println("Gerando relatório de veículos...");
        dispose(); // Fecha a tela de relatórios
    }

    // Método para fechar a tela de relatórios
    private void sair() {
        dispose(); // Fecha a tela de relatórios
    }
}

