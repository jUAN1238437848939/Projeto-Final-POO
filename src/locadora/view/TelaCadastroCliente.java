package locadora.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import locadora.controller.ClienteController;
import locadora.model.Cliente;

public class TelaCadastroCliente extends JFrame {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private ClienteController clienteController;

    public TelaCadastroCliente() {
        clienteController = new ClienteController();

        setTitle("Cadastro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(criarTitulo(), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
        add(criarBotoes(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel criarTitulo() {
        JLabel titulo = new JLabel("Cadastro de Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        return titulo;
    }

    private JPanel criarFormulario() {
        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painelFormulario.add(txtCpf);

        painelFormulario.add(new JLabel("E-mail:"));
        txtEmail = new JTextField();
        painelFormulario.add(txtEmail);

        painelFormulario.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelFormulario.add(txtTelefone);

        return painelFormulario;
    }

    private JPanel criarBotoes() {
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(this::salvarCliente);
        btnCancelar.addActionListener(e -> dispose());

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        return painelBotoes;
    }

    private void salvarCliente(ActionEvent e) {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String email = txtEmail.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validarCpf(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validarEmail(email)) {
            JOptionPane.showMessageDialog(this, "E-mail inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, cpf, telefone, email);
        try {
            clienteController.cadastrarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
    }

    private boolean validarCpf(String cpf) {
        if (!cpf.matches("\\d{11}")) return false;
        return !cpf.matches("(\\d)\\1{10}");
    }

    private boolean validarEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static void main(String[] args) {
        new TelaCadastroCliente();
    }
}
