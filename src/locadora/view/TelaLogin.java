package locadora.view;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton btnLogin;

    public TelaLogin() {
        setTitle("Login de Usuário");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Painel para o formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridLayout(3, 2, 10, 10)); // Grid com 3 linhas e 2 colunas
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Campos de entrada
        painelFormulario.add(new JLabel("Usuário:"));
        campoUsuario = new JTextField();
        painelFormulario.add(campoUsuario);

        painelFormulario.add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        painelFormulario.add(campoSenha);

        // Botão de login
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> realizarLogin());
        painelFormulario.add(btnLogin);

        // Adicionando painel de formulário ao centro da janela
        add(painelFormulario, BorderLayout.CENTER);

        // Tornando a janela visível
        setVisible(true);
    }

    private void realizarLogin() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        // Validação de login para Administrador, Gerente e Atendente
        if (usuario.equals("admin") && senha.equals("admin123")) {
            new locadora.view.administrador.TelaAdministrador();
            dispose(); // Fecha a tela de login
        } else if (usuario.equals("gerente") && senha.equals("gerente123")) {
            new locadora.view.gerente.TelaGerente(); // Tela do Gerente
            dispose(); // Fecha a tela de login
        } else if (usuario.equals("atendente") && senha.equals("atendente123")) {
            new locadora.view.atendente.TelaAtendente(); // Tela do Atendente
            dispose(); // Fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(this, "Credenciais inválidas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Executa a tela de login
        SwingUtilities.invokeLater(() -> new TelaLogin());
    }
}
