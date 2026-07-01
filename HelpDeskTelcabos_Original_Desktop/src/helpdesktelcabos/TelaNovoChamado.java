package helpdesktelcabos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaNovoChamado extends JFrame {
    private JTextField campoVendedor;
    private JComboBox<String> comboDepartamento;
    private JComboBox<String> comboCidade;
    private JComboBox<String> comboProblema;
    private JTextArea areaDescricao;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    
    public TelaNovoChamado() {
        setTitle("HelpDesk Telcabos - Novo Chamado");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Layout principal
        setLayout(new BorderLayout());
        
        // Cabeçalho
        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setBackground(new Color(0, 86, 163));
        painelCabecalho.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel labelTitulo = new JLabel("🎫 NOVO CHAMADO TÉCNICO");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);
        painelCabecalho.add(labelTitulo, BorderLayout.WEST);
        
        // Botão voltar
        JButton botaoVoltar = new JButton("← Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoVoltar.addActionListener(e -> voltarDashboard());
        painelCabecalho.add(botaoVoltar, BorderLayout.EAST);
        
        add(painelCabecalho, BorderLayout.NORTH);
        
        // Formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Vendedor
        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(new JLabel("👤 Vendedor:"), gbc);
        
        gbc.gridx = 1;
        campoVendedor = new JTextField(20);
        painelFormulario.add(campoVendedor, gbc);
        
        // Departamento
        gbc.gridx = 0; gbc.gridy = 1;
        painelFormulario.add(new JLabel("🏢 Departamento:"), gbc);
        
        gbc.gridx = 1;
        String[] departamentos = {"Vendas", "TI", "Administrativo", "Financeiro", "Marketing"};
        comboDepartamento = new JComboBox<>(departamentos);
        painelFormulario.add(comboDepartamento, gbc);
        
        // Cidade
        gbc.gridx = 0; gbc.gridy = 2;
        painelFormulario.add(new JLabel("🏙️ Cidade:"), gbc);
        
        gbc.gridx = 1;
        String[] cidades = {"São Paulo", "Campinas", "Curitiba", "Rio de Janeiro", "Bahia", "Fortaleza", "Recife"};
        comboCidade = new JComboBox<>(cidades);
        painelFormulario.add(comboCidade, gbc);
        
        // Tipo de Problema
        gbc.gridx = 0; gbc.gridy = 3;
        painelFormulario.add(new JLabel("🔧 Tipo de Problema:"), gbc);
        
        gbc.gridx = 1;
        String[] problemas = {"Sistema Autcom", "Computador não liga", "Email não funciona", 
                              "Internet lenta", "Impressora com problema", "Software travando"};
        comboProblema = new JComboBox<>(problemas);
        painelFormulario.add(comboProblema, gbc);
        
        // Descrição
        gbc.gridx = 0; gbc.gridy = 4;
        painelFormulario.add(new JLabel("📝 Descrição Detalhada:"), gbc);
        
        gbc.gridx = 1;
        areaDescricao = new JTextArea(5, 30);
        areaDescricao.setLineWrap(true);
        areaDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(areaDescricao);
        painelFormulario.add(scrollDescricao, gbc);
        
        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        botaoSalvar = new JButton("💾 SALVAR CHAMADO");
        botaoSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoSalvar.setBackground(new Color(0, 86, 163));
        botaoSalvar.setForeground(Color.WHITE);
        botaoSalvar.addActionListener(e -> salvarChamado());
        
        botaoCancelar = new JButton("❌ CANCELAR");
        botaoCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoCancelar.setBackground(new Color(229, 57, 53));
        botaoCancelar.setForeground(Color.WHITE);
        botaoCancelar.addActionListener(e -> voltarDashboard());
        
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoCancelar);
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        painelFormulario.add(painelBotoes, gbc);
        
        add(painelFormulario, BorderLayout.CENTER);
        
        // Rodapé informativo
        JLabel labelInfo = new JLabel("⚠️ Chamados de ALTA prioridade serão atendidos em até 2 horas úteis");
        labelInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        labelInfo.setForeground(new Color(229, 57, 53));
        labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
        labelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(labelInfo, BorderLayout.SOUTH);
    }
    
   private void salvarChamado() {
    // Validar campos
    if (campoVendedor.getText().trim().isEmpty() || areaDescricao.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Por favor, preencha todos os campos obrigatórios!", 
            "Campos obrigatórios", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Coletar dados do formulário
    String vendedor = campoVendedor.getText();
    String departamento = (String) comboDepartamento.getSelectedItem();
    String cidade = (String) comboCidade.getSelectedItem();
    String problema = (String) comboProblema.getSelectedItem();
    String descricao = areaDescricao.getText();
    
    // Determinar prioridade automaticamente
    String prioridade = "Média";
    if (problema.contains("Autcom") || problema.contains("Computador")) {
        prioridade = "Alta";
    }
    
    // Criar objeto Chamado
    Chamado novoChamado = new Chamado(vendedor, cidade, departamento, problema, prioridade, descricao);
    
    // Salvar no banco de dados
    boolean salvo = ConexaoBanco.salvarChamado(novoChamado);
    
    if (salvo) {
        JOptionPane.showMessageDialog(this,
            "✅ Chamado salvo com sucesso no banco de dados!\n\n" +
            "Vendedor: " + vendedor + "\n" +
            "Problema: " + problema + "\n" +
            "Prioridade: " + prioridade,
            "Sucesso!",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Limpar campos
        campoVendedor.setText("");
        areaDescricao.setText("");
        
        // Voltar ao dashboard
        voltarDashboard();
    } else {
        JOptionPane.showMessageDialog(this,
            "❌ Erro ao salvar chamado!\nTente novamente.",
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void voltarDashboard() {
        TelaDashboard dashboard = new TelaDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }
}