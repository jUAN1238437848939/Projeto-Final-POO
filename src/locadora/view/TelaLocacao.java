package locadora.view;

import locadora.controller.LocacaoController;
import locadora.model.Cliente;
import locadora.model.Veiculo;
import locadora.model.Locacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TelaLocacao extends JFrame {
    private LocacaoController locacaoController;
    private JComboBox<Cliente> cbClientes;
    private JComboBox<Veiculo> cbVeiculos;
    private JSpinner spDiasPrevistos;
    private JTextField tfDataRetirada;
    private JButton btnRegistrarLocacao;
    private JButton btnRegistrarDevolucao;
    private JTextArea taResultado;
    
    public TelaLocacao(LocacaoController locacaoController) {
        this.locacaoController = locacaoController;
        
        setTitle("Tela de Locação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout
        setLayout(new FlowLayout());
        
        // ComboBox para selecionar cliente
        cbClientes = new JComboBox<>();
        for (Cliente cliente : locacaoController.getClientes()) {
            cbClientes.addItem(cliente);
        }
        add(new JLabel("Cliente:"));
        add(cbClientes);
        
        // ComboBox para selecionar veículo
        cbVeiculos = new JComboBox<>();
        for (Veiculo veiculo : locacaoController.getVeiculos()) {
            cbVeiculos.addItem(veiculo);
        }
        add(new JLabel("Veículo:"));
        add(cbVeiculos);
        
        // Campo para data de retirada
        tfDataRetirada = new JTextField(10);
        add(new JLabel("Data de Retirada (yyyy-MM-dd):"));
        add(tfDataRetirada);
        
        // Spinner para selecionar os dias previstos
        spDiasPrevistos = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        add(new JLabel("Dias Previstos:"));
        add(spDiasPrevistos);
        
        // Botão para registrar locação
        btnRegistrarLocacao = new JButton("Registrar Locação");
        btnRegistrarLocacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarLocacao();
            }
        });
        add(btnRegistrarLocacao);
        
        // Botão para registrar devolução
        btnRegistrarDevolucao = new JButton("Registrar Devolução");
        btnRegistrarDevolucao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDevolucao();
            }
        });
        add(btnRegistrarDevolucao);
        
        // Área de resultado
        taResultado = new JTextArea(5, 30);
        taResultado.setEditable(false);
        add(new JScrollPane(taResultado));
    }

    private void registrarLocacao() {
        Cliente cliente = (Cliente) cbClientes.getSelectedItem();
        Veiculo veiculo = (Veiculo) cbVeiculos.getSelectedItem();
        String dataRetiradaStr = tfDataRetirada.getText();
        
        // Validando se a data foi preenchida corretamente
        LocalDate dataRetirada;
        try {
            dataRetirada = LocalDate.parse(dataRetiradaStr);
        } catch (Exception e) {
            taResultado.setText("Erro: Data de retirada inválida.");
            return;
        }
        
        int diasPrevistos = (int) spDiasPrevistos.getValue();
        
        try {
            // Registrar a locação
            Locacao locacao = locacaoController.registrarLocacao(cliente, veiculo, dataRetirada, diasPrevistos);
            taResultado.setText("Locação registrada com sucesso! ID Locação: " + locacao.getIdLocacao());
        } catch (Exception e) {
            taResultado.setText("Erro: " + e.getMessage());
        }
    }

    private void registrarDevolucao() {
        Cliente cliente = (Cliente) cbClientes.getSelectedItem();
        Veiculo veiculo = (Veiculo) cbVeiculos.getSelectedItem();
        
        // Procurando a locação ativa para o cliente e veículo selecionados
        Locacao locacao = locacaoController.buscarLocacaoAtiva(cliente, veiculo);
        
        if (locacao == null) {
            taResultado.setText("Erro: Locação não encontrada ou já devolvida.");
            return;
        }

        // Solicitando a data de devolução
        String dataDevolucaoStr = JOptionPane.showInputDialog(this, "Informe a data de devolução (yyyy-MM-dd):");
        
        LocalDate dataDevolucao;
        try {
            dataDevolucao = LocalDate.parse(dataDevolucaoStr);
        } catch (Exception e) {
            taResultado.setText("Erro: Data de devolução inválida.");
            return;
        }
        
        // Registro da devolução
        double multaPorDia = 10.0; // Defina o valor da multa por dia de atraso
        locacaoController.registrarDevolucao(locacao, dataDevolucao, multaPorDia);
        
        taResultado.setText("Devolução registrada com sucesso! ID Locação: " + locacao.getIdLocacao());
    }
}
