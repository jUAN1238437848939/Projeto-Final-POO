package locadora.view.administrador;

import locadora.model.Usuario;
import locadora.dao.UsuarioDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdministrador extends JFrame {

    public TelaAdministrador() {
        // Configuração da janela principal
        setTitle("Tela do Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Layout principal da janela
        setLayout(new BorderLayout());

        // Título da tela
        JLabel label = new JLabel("Cadastrar Novo Usuário", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Define o estilo da fonte
        add(label, BorderLayout.NORTH); // Coloca o título na parte superior da tela

        // Painel de campos e botões
        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(5, 2, 10, 10)); // Organiza os campos e botões
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adiciona um espaçamento ao redor

        // Componente para o nome do usuário
        painelCampos.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        painelCampos.add(nomeField);

        // Componente para o login
        painelCampos.add(new JLabel("Login:"));
        JTextField loginField = new JTextField();
        painelCampos.add(loginField);

        // Componente para a senha
        painelCampos.add(new JLabel("Senha:"));
        JPasswordField senhaField = new JPasswordField();
        painelCampos.add(senhaField);

        // Componente para o cargo
        painelCampos.add(new JLabel("Cargo:"));
        JComboBox<String> cargoComboBox = new JComboBox<>(new String[]{"Administrador", "Gerente", "Atendente"});
        painelCampos.add(cargoComboBox);

        // Botão para cadastrar o usuário
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario(nomeField, loginField, senhaField, cargoComboBox);
            }
        });
        painelCampos.add(btnCadastrar);

        // Botão de sair (volta para a tela de login)
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> sair()); // Ação de sair da tela
        painelCampos.add(btnSair);

        // Adiciona o painel de campos na tela
        add(painelCampos, BorderLayout.CENTER);

        // Exibe a tela
        setVisible(true);
    }

    // Método para cadastrar o usuário
    private void cadastrarUsuario(JTextField nomeField, JTextField loginField, JPasswordField senhaField, JComboBox<String> cargoComboBox) {
        String nome = nomeField.getText().trim();
        String login = loginField.getText().trim();
        String senha = new String(senhaField.getPassword()).trim();
        String cargo = (String) cargoComboBox.getSelectedItem();

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar um objeto de usuário
        Usuario novoUsuario = new Usuario(nome, login, senha, cargo);

        // Salvar o novo usuário no sistema (no arquivo JSON)
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.salvarEmArquivo(novoUsuario); // Usando o método salvarEmArquivo da classe UsuarioDao

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
