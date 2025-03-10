package locadora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPagamentos extends JFrame {
    private JTextField txtCliente;
    private JTextField txtVeiculo;
    private JTextField txtDataLocacao;
    private JTextField txtValorDiaria;
    private JTextField txtTotalPagar;
    private JTextField txtDiasLocacao;  // Campo para número de dias de locação

    public TelaPagamentos() {
        setTitle("Tela de Pagamento");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Processamento de Pagamento", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 5, 5)); // Agora com 6 linhas
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos do formulário
        painelFormulario.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        painelFormulario.add(txtCliente);

        painelFormulario.add(new JLabel("Veículo:"));
        txtVeiculo = new JTextField();
        painelFormulario.add(txtVeiculo);

        painelFormulario.add(new JLabel("Data de Locação:"));
        txtDataLocacao = new JTextField();
        painelFormulario.add(txtDataLocacao);

        painelFormulario.add(new JLabel("Valor da Diária:"));
        txtValorDiaria = new JTextField();
        painelFormulario.add(txtValorDiaria);

        painelFormulario.add(new JLabel("Dias de Locação:"));
        txtDiasLocacao = new JTextField();
        painelFormulario.add(txtDiasLocacao);

        painelFormulario.add(new JLabel("Total a Pagar:"));
        txtTotalPagar = new JTextField();
        txtTotalPagar.setEditable(false); // Não pode ser editado diretamente
        painelFormulario.add(txtTotalPagar);

        add(painelFormulario, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnEfetuarPagamento = new JButton("Efetuar Pagamento");
        JButton btnCancelar = new JButton("Cancelar");

        // Ação para o botão "Efetuar Pagamento"
        btnEfetuarPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulando o pagamento
                String cliente = txtCliente.getText();
                String veiculo = txtVeiculo.getText();
                String valorDiaria = txtValorDiaria.getText();
                String diasLocacaoStr = txtDiasLocacao.getText();
                
                // Se todos os campos estiverem preenchidos, simula o pagamento
                if (!cliente.isEmpty() && !veiculo.isEmpty() && !valorDiaria.isEmpty() && !diasLocacaoStr.isEmpty()) {
                    try {
                        double valor = Double.parseDouble(valorDiaria);
                        int diasLocacao = Integer.parseInt(diasLocacaoStr);
                        
                        if (valor <= 0 || diasLocacao <= 0) {
                            throw new NumberFormatException("Valor da diária e dias de locação devem ser maiores que zero.");
                        }

                        // Calcula o total a pagar (diária * dias de locação)
                        double totalPagar = calcularTotalPagar(valor, diasLocacao);

                        // Exibe a informação de pagamento
                        txtTotalPagar.setText(String.format("%.2f", totalPagar));
                        JOptionPane.showMessageDialog(TelaPagamentos.this, "Pagamento realizado com sucesso!\nTotal: R$ " + totalPagar, "Pagamento", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(TelaPagamentos.this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaPagamentos.this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação para o botão "Cancelar"
        btnCancelar.addActionListener(e -> dispose()); // Fecha a tela de pagamento

        painelBotoes.add(btnEfetuarPagamento);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para calcular o total a pagar
    private double calcularTotalPagar(double valorDiaria, int diasLocacao) {
        return valorDiaria * diasLocacao;
    }

    // Método para preencher os campos com os dados da locação
    public void preencherCamposComLocacao(String cliente, String veiculo, String dataLocacao, double valorDiaria, int diasLocacao) {
        txtCliente.setText(cliente);
        txtVeiculo.setText(veiculo);
        txtDataLocacao.setText(dataLocacao);
        txtValorDiaria.setText(String.valueOf(valorDiaria));
        txtDiasLocacao.setText(String.valueOf(diasLocacao));
    }

    public static void main(String[] args) {
        new TelaPagamentos(); 
    }
}
