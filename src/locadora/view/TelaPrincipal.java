package locadora.view;

import locadora.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal(Usuario usuario) {
        setTitle("Locadora de Veículos - " + usuario.getPerfil());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Locadora de Veículos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout());

        if (usuario.getPerfil().equals("Administrador")) {
            painelBotoes.add(new JButton("Cadastrar Usuários"));
        }
        if (usuario.getPerfil().equals("Administrador") || usuario.getPerfil().equals("Gerente")) {
            painelBotoes.add(new JButton("Cadastro de Veículo"));
            painelBotoes.add(new JButton("Cadastro de Cliente"));
            painelBotoes.add(new JButton("Relatórios"));
        }
        if (usuario.getPerfil().equals("Administrador") || usuario.getPerfil().equals("Gerente") || usuario.getPerfil().equals("Atendente")) {
            painelBotoes.add(new JButton("Realizar Locação"));
            painelBotoes.add(new JButton("Registrar Devolução"));
            painelBotoes.add(new JButton("Pagamentos"));
        }

        add(painelBotoes, BorderLayout.CENTER);
        setVisible(true);
    }
}