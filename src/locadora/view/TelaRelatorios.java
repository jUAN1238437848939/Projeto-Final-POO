package locadora.view;

import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.Cliente;
import locadora.model.Locacao;
import locadora.model.Pagamento;
import locadora.model.Veiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRelatorios extends JFrame {

    private JComboBox<String> tipoRelatorioComboBox;
    private JButton gerarRelatorioButton;
    private JTextField idField;

    public TelaRelatorios() {
        setTitle("Tela de Relatórios");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componente para escolher o tipo de relatório
        tipoRelatorioComboBox = new JComboBox<>(new String[]{"Relatório de Cliente", "Relatório de Locação", "Relatório de Pagamento", "Relatório de Veículo"});
        tipoRelatorioComboBox.setPreferredSize(new Dimension(300, 30));
        add(tipoRelatorioComboBox);

        // Campo para digitar o ID do objeto (Cliente, Locação, Pagamento ou Veículo)
        idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(300, 30));
        add(idField);

        // Botão para gerar o relatório
        gerarRelatorioButton = new JButton("Gerar Relatório");
        add(gerarRelatorioButton);

        // Ação do botão para gerar o relatório
        gerarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
    }

    private void gerarRelatorio() {
        String tipoRelatorio = (String) tipoRelatorioComboBox.getSelectedItem();
        String idTexto = idField.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Obter o ID informado
            int id = Integer.parseInt(idTexto);

            // Gerar o relatório de acordo com a seleção
            switch (tipoRelatorio) {
                case "Relatório de Cliente":
                    gerarRelatorioCliente(id);
                    break;
                case "Relatório de Locação":
                    gerarRelatorioLocacao(id);
                    break;
                case "Relatório de Pagamento":
                    gerarRelatorioPagamento(id);
                    break;
                case "Relatório de Veículo":
                    gerarRelatorioVeiculo(id);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Tipo de relatório inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorioCliente(int id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.carregar("clientes.json").stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (cliente != null) {
            clienteDAO.gerarRelatorioPagamento(cliente);
            JOptionPane.showMessageDialog(this, "Relatório de cliente gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorioLocacao(int id) {
        LocacaoDAO locacaoDAO = new LocacaoDAO();
        Locacao locacao = locacaoDAO.carregar("locacoes.json").stream().filter(l -> l.getIdLocacao() == id).findFirst().orElse(null);

        if (locacao != null) {
            locacaoDAO.gerarRelatorioPagamento(locacao);
            JOptionPane.showMessageDialog(this, "Relatório de locação gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Locação não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorioPagamento(int id) {
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        Pagamento pagamento = pagamentoDAO.carregar("pagamentos.json").stream().filter(p -> p.getIdPagamento() == id).findFirst().orElse(null);

        if (pagamento != null) {
            pagamentoDAO.gerarRelatorioPagamento(pagamento);
            JOptionPane.showMessageDialog(this, "Relatório de pagamento gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Pagamento não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorioVeiculo(int id) {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Veiculo veiculo = veiculoDAO.carregar("veiculos.json").stream().filter(v -> v.getId() == id).findFirst().orElse(null);

        if (veiculo != null) {
            veiculoDAO.gerarRelatorioPagamento(veiculo);
            JOptionPane.showMessageDialog(this, "Relatório de veículo gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Veículo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaRelatorios().setVisible(true);
            }
        });
    }
}
