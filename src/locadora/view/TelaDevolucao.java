package locadora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TelaDevolucao extends JFrame {

    private JTextField txtDataDevolucao;
    private JTextField txtValorDevolucao;

    public TelaDevolucao() {
        setTitle("Registrar Devolução");
        setSize(400, 250);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout());

        // Título da tela
        JLabel titulo = new JLabel("Registrar Devolução", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campo para data de devolução
        painelFormulario.add(new JLabel("Data Devolução (dd/mm/aaaa):"));
        txtDataDevolucao = new JTextField();
        painelFormulario.add(txtDataDevolucao);

        // Campo para valor da devolução
        painelFormulario.add(new JLabel("Valor da Devolução (R$):"));
        txtValorDevolucao = new JTextField();
        painelFormulario.add(txtValorDevolucao);

        add(painelFormulario, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        // Evento para o botão "Confirmar"
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarDevolucao();
            }
        });

        // Evento para o botão "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para validar a data inserida
    private boolean validarData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(data); // Apenas tenta fazer o parse para verificar se é uma data válida
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    

    // Método para validar o valor inserido
    private boolean validarValor(String valor) {
        try {
            double valorConvertido = Double.parseDouble(valor);
            return valorConvertido >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para confirmar a devolução
    private void confirmarDevolucao() {
        String dataDevolucao = txtDataDevolucao.getText();
        String valorDevolucao = txtValorDevolucao.getText();

        // Validação dos campos antes de registrar a devolução
        if (dataDevolucao.isEmpty() || valorDevolucao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validarData(dataDevolucao)) {
            JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/mm/aaaa.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validarValor(valorDevolucao)) {
            JOptionPane.showMessageDialog(this, "Valor inválido! Insira um número positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Exibindo a devolução registrada no console (pode ser salvo no banco de dados)
        System.out.println("Devolução registrada!");
        System.out.println("Data Devolução: " + dataDevolucao);
        System.out.println("Valor da Devolução: R$ " + valorDevolucao);

        JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Limpar campos após registrar a devolução
        limparCampos();
    }

    // Método para limpar os campos após a devolução
    private void limparCampos() {
        txtDataDevolucao.setText("");
        txtValorDevolucao.setText("");
    }

    public static void main(String[] args) {
        new TelaDevolucao();
    }
}
