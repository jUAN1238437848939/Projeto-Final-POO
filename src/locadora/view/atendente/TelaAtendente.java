package locadora.view.atendente;

import locadora.model.Cliente;
import locadora.model.Veiculo;
import locadora.dao.ClienteDAO;
import locadora.dao.VeiculoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaAtendente extends JFrame {

    private JComboBox<String> comboClientes;
    private JComboBox<String> comboVeiculos;
    private JTextField campoDataLocacao;
    private JTextField campoDataDevolucao;
    private JButton btnRegistrarLocacao;

    private List<Cliente> listaClientes;
    private List<Veiculo> listaVeiculos;

    public TelaAtendente() {
        setTitle("Tela do Atendente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Carregar clientes e veículos
        carregarDados();

        // Layout
        setLayout(new BorderLayout());

        // Label de boas-vindas
        JLabel label = new JLabel("Bem-vindo, Atendente!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Painel central com campos de locação
        JPanel painelForm = new JPanel();
        painelForm.setLayout(new GridLayout(5, 2, 10, 10));

        painelForm.add(new JLabel("Selecione o Cliente:"));
        comboClientes = new JComboBox<>();
        listaClientes.forEach(cliente -> comboClientes.addItem(cliente.getNome()));
        painelForm.add(comboClientes);

        painelForm.add(new JLabel("Selecione o Veículo:"));
        comboVeiculos = new JComboBox<>();
        listaVeiculos.forEach(veiculo -> comboVeiculos.addItem(veiculo.getModelo()));
        painelForm.add(comboVeiculos);

        painelForm.add(new JLabel("Data de Locação:"));
        campoDataLocacao = new JTextField();
        painelForm.add(campoDataLocacao);

        painelForm.add(new JLabel("Data de Devolução:"));
        campoDataDevolucao = new JTextField();
        painelForm.add(campoDataDevolucao);

        add(painelForm, BorderLayout.CENTER);

        // Botão para registrar a locação
        btnRegistrarLocacao = new JButton("Registrar Locação");
        btnRegistrarLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarLocacao();
            }
        });
        add(btnRegistrarLocacao, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void carregarDados() {
        // Carregar lista de clientes e veículos a partir do banco de dados (ou arquivos JSON)
        ClienteDAO clienteDAO = new ClienteDAO();
        listaClientes = clienteDAO.carregar("clientes.json");

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        listaVeiculos = veiculoDAO.carregar("veiculos.json");
    }

    private void registrarLocacao() {
        // Verificar se todos os campos estão preenchidos
        if (comboClientes.getSelectedIndex() == -1 || comboVeiculos.getSelectedIndex() == -1 ||
            campoDataLocacao.getText().trim().isEmpty() || campoDataDevolucao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lógica para registrar a locação
        Cliente clienteSelecionado = listaClientes.get(comboClientes.getSelectedIndex());
        Veiculo veiculoSelecionado = listaVeiculos.get(comboVeiculos.getSelectedIndex());

        String dataLocacao = campoDataLocacao.getText();
        String dataDevolucao = campoDataDevolucao.getText();

        JOptionPane.showMessageDialog(this, "Locação registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new TelaAtendente();
    }
}
