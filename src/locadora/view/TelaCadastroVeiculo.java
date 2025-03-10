package locadora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import locadora.model.Carro;
import locadora.model.Cliente;
import locadora.model.Moto;
import locadora.model.Caminhao;
import locadora.model.Veiculo;
import locadora.controller.VeiculoController;

public class TelaCadastroVeiculo extends JFrame {

    private JTextField txtModelo;
    private JTextField txtPlaca;
    private JTextField txtAno;
    private JTextField txtTipo;
    private JTextField txtValorDiaria;
    private JTextField txtNomeCliente; // Novo campo para o nome do cliente
    private VeiculoController veiculoController; // Controlador para gerenciar os veículos

    public TelaCadastroVeiculo() {
        setTitle("Cadastro de Veículo");
        setSize(400, 350); // Aumentando o tamanho para incluir os campos do cliente
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializa o controlador
        veiculoController = new VeiculoController();

        JLabel titulo = new JLabel("Cadastro de Veículo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Painel do formulário
        JPanel painelFormulario = new JPanel(new GridLayout(7, 2, 5, 5)); // Agora com 7 linhas
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        painelFormulario.add(txtModelo);

        painelFormulario.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        painelFormulario.add(txtPlaca);

        painelFormulario.add(new JLabel("Ano:"));
        txtAno = new JTextField();
        painelFormulario.add(txtAno);

        painelFormulario.add(new JLabel("Tipo (Carro/Moto/Caminhão):"));
        txtTipo = new JTextField();
        painelFormulario.add(txtTipo);

        painelFormulario.add(new JLabel("Valor da Diária:"));
        txtValorDiaria = new JTextField();
        painelFormulario.add(txtValorDiaria);

        painelFormulario.add(new JLabel("Nome do Cliente:")); // Campo para o nome do cliente
        txtNomeCliente = new JTextField();
        painelFormulario.add(txtNomeCliente);

        add(painelFormulario, BorderLayout.CENTER);

        // Painel dos botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarVeiculo();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void salvarVeiculo() {
        String modelo = txtModelo.getText().trim();
        String placa = txtPlaca.getText().trim();
        String anoText = txtAno.getText().trim();
        String tipo = txtTipo.getText().trim().toLowerCase();
        String valorDiariaText = txtValorDiaria.getText().trim();
        String nomeCliente = txtNomeCliente.getText().trim(); // Pegando o nome do cliente

        // Verifica se os campos estão preenchidos
        if (modelo.isEmpty() || placa.isEmpty() || anoText.isEmpty() || tipo.isEmpty() || valorDiariaText.isEmpty() || nomeCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ano;
        double valorDiaria;
        
        // Tenta converter os valores de ano e diária, tratando erros de conversão
        try {
            ano = Integer.parseInt(anoText);
            valorDiaria = Double.parseDouble(valorDiariaText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ano e Valor da Diária devem ser valores numéricos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria um objeto Cliente
        Cliente cliente = new Cliente(nomeCliente, "000.000.000-00", "0000-0000", "email@exemplo.com"); 
        // Cria um objeto Veiculo apropriado conforme o tipo informado
        Veiculo veiculo;
        switch (tipo) {
            case "carro":
                veiculo = new Carro(placa, modelo, ano, valorDiaria, cliente);
                break;
            case "moto":
                veiculo = new Moto(placa, modelo, ano, valorDiaria, cliente);
                break;
            case "caminhao":
                veiculo = new Caminhao(placa, modelo, ano, valorDiaria, cliente);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Tipo de veículo inválido! Escolha entre Carro, Moto ou Caminhão.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Salva o veículo utilizando o controlador
        try {
            veiculoController.adicionarVeiculo(veiculo);
            JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar veículo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtModelo.setText("");
        txtPlaca.setText("");
        txtAno.setText("");
        txtTipo.setText("");
        txtValorDiaria.setText("");
        txtNomeCliente.setText(""); // Limpar o campo de cliente
    }

    public static void main(String[] args) {
        new TelaCadastroVeiculo();
    }
}
