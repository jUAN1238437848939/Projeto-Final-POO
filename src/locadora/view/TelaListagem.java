package locadora.view;

import locadora.dao.VeiculoDAO;
import locadora.model.Veiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaListagem extends JFrame {

    private JTable tabelaVeiculos;
    private DefaultTableModel modeloTabela;
    private JTextField campoFiltroStatus;
    private VeiculoDAO veiculoDAO;
    private List<Veiculo> veiculos; // Lista armazenada para evitar múltiplas leituras do arquivo

    public TelaListagem() {
        setTitle("Listagem de Veículos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        veiculoDAO = new VeiculoDAO();
        veiculos = veiculoDAO.carregar("veiculos.json"); // Carrega apenas uma vez

        JLabel titulo = new JLabel("Listagem de Veículos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        String[] colunas = {"Modelo", "Placa", "Ano", "Tipo", "Valor da Diária", "Status", "Cliente"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaVeiculos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaVeiculos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelFiltro = new JPanel();
        painelFiltro.add(new JLabel("Filtrar por Status (Disponível/Locado):"));
        campoFiltroStatus = new JTextField(15);
        painelFiltro.add(campoFiltroStatus);

        JButton btnAplicarFiltro = new JButton("Aplicar Filtro");
        btnAplicarFiltro.addActionListener(e -> aplicarFiltro());
        painelFiltro.add(btnAplicarFiltro);

        add(painelFiltro, BorderLayout.SOUTH); // Move o filtro para o rodapé

        carregarVeiculos();
        setVisible(true);
    }

    private void carregarVeiculos() {
        modeloTabela.setRowCount(0);
        for (Veiculo veiculo : veiculos) {
            modeloTabela.addRow(new Object[]{
                veiculo.getModelo(),
                veiculo.getPlaca(),
                veiculo.getAno(),
                veiculo.getTipo(),
                veiculo.getValorDiaria(),
                veiculo.getStatus(),
                veiculo.getCliente() != null ? veiculo.getCliente().getNome() : "N/A"
            });
        }
    }

    private void aplicarFiltro() {
        String filtro = campoFiltroStatus.getText().trim().toLowerCase();
        modeloTabela.setRowCount(0);

        for (Veiculo veiculo : veiculos) {
            String status = veiculo.getStatus().toLowerCase();
            if (filtro.isEmpty() || status.equals("disponível") || status.equals("locado")) {
                modeloTabela.addRow(new Object[]{
                    veiculo.getModelo(),
                    veiculo.getPlaca(),
                    veiculo.getAno(),
                    veiculo.getTipo(),
                    veiculo.getValorDiaria(),
                    veiculo.getStatus(),
                    veiculo.getCliente() != null ? veiculo.getCliente().getNome() : "N/A"
                });
            }
        }
    }

    public static void main(String[] args) {
        new TelaListagem();
    }
}
