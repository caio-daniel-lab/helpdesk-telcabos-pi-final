package helpdesktelcabos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaListaChamados extends JFrame {

    private JTable tabelaChamados;
    private DefaultTableModel modeloTabela;
    private List<Chamado> listaChamados;

    public TelaListaChamados() {

        setTitle("HelpDesk Telcabos - Lista de Chamados");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= CABEÇALHO =================
        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setBackground(new Color(0, 86, 163));
        painelCabecalho.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel labelTitulo = new JLabel("📋 LISTA DE CHAMADOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);
        painelCabecalho.add(labelTitulo, BorderLayout.WEST);

        JButton botaoVoltar = new JButton("← Voltar ao Dashboard");
        botaoVoltar.addActionListener(e -> voltarDashboard());
        painelCabecalho.add(botaoVoltar, BorderLayout.EAST);

        add(painelCabecalho, BorderLayout.NORTH);

        // ================= TABELA =================
        String[] colunas = {
            "Código", "Vendedor", "Cidade",
            "Problema", "Prioridade", "Status", "Data"
        };

        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaChamados = new JTable(modeloTabela);
        tabelaChamados.setRowHeight(35);

        JScrollPane scroll = new JScrollPane(tabelaChamados);
        add(scroll, BorderLayout.CENTER);

        // ================= BOTÕES =================
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));

        JButton botaoNovo = new JButton("🎫 Novo Chamado");
        botaoNovo.addActionListener(e -> abrirNovoChamado());

        JButton botaoResolver = new JButton("✅ Resolver");
        botaoResolver.addActionListener(e -> resolverChamado());

        JButton botaoDetalhes = new JButton("👁️ Ver Detalhes");
        botaoDetalhes.addActionListener(e -> verDetalhes());

        painelBotoes.add(botaoNovo);
        painelBotoes.add(botaoResolver);
        painelBotoes.add(botaoDetalhes);

        add(painelBotoes, BorderLayout.SOUTH);

        // ================= DADOS =================
        listaChamados = new ArrayList<>();
        carregarDadosDoBanco();
        preencherTabela();
    }

    // ================= DADOS DE EXEMPLO =================
    private void carregarDadosDoBanco() {
    // Limpa a lista atual
    listaChamados.clear();
    
    // Busca chamados do banco de dados
    java.util.List<Chamado> chamadosDB = ConexaoBanco.buscarChamados();
    
    if (chamadosDB != null) {
        listaChamados.addAll(chamadosDB);
        System.out.println("✅ Carregados " + chamadosDB.size() + " chamados do banco");
    } else {
        System.out.println("❌ Nenhum chamado encontrado no banco");
    }
}

    // ================= PREENCHER TABELA =================
    private void preencherTabela() {

        modeloTabela.setRowCount(0);

        for (Chamado c : listaChamados) {
            modeloTabela.addRow(new Object[]{
                "TEL-" + c.getId(),
                c.getVendedor(),
                c.getCidade(),
                c.getTipoProblema(),
                c.getPrioridade(),
                c.getStatus(),
                c.getDataAbertura()
            });
        }
    }

    // ================= AÇÕES =================
    private void abrirNovoChamado() {
        TelaNovoChamado tela = new TelaNovoChamado();
        tela.setVisible(true);
        dispose();
    }

    private void resolverChamado() {

        int linha = tabelaChamados.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um chamado!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        modeloTabela.setValueAt("✅ RESOLVIDO", linha, 5);

        JOptionPane.showMessageDialog(this,
                "Chamado resolvido com sucesso!",
                "OK",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void verDetalhes() {

        int linha = tabelaChamados.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um chamado!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigo = modeloTabela.getValueAt(linha, 0).toString();
        String vendedor = modeloTabela.getValueAt(linha, 1).toString();
        String cidade = modeloTabela.getValueAt(linha, 2).toString();
        String problema = modeloTabela.getValueAt(linha, 3).toString();
        String prioridade = modeloTabela.getValueAt(linha, 4).toString();
        String status = modeloTabela.getValueAt(linha, 5).toString();

        JOptionPane.showMessageDialog(this,
                "Código: " + codigo +
                "\nVendedor: " + vendedor +
                "\nCidade: " + cidade +
                "\nProblema: " + problema +
                "\nPrioridade: " + prioridade +
                "\nStatus: " + status,
                "Detalhes do Chamado",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void voltarDashboard() {
        TelaDashboard dashboard = new TelaDashboard();
        dashboard.setVisible(true);
        dispose();
    }

    // ================= MAIN PARA TESTE =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaListaChamados().setVisible(true);
        });
    }
}