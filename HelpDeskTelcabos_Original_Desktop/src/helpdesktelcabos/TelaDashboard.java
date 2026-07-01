package helpdesktelcabos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDashboard extends JFrame {
    private JButton botaoNovoChamado;
    private JButton botaoListarChamados;
    private JButton botaoSair;
    
    public TelaDashboard() {
        // Configurações da janela
        setTitle("HelpDesk Telcabos - Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout principal
        setLayout(new BorderLayout());
        
        // Cabeçalho
        JPanel painelCabecalho = criarCabecalho();
        add(painelCabecalho, BorderLayout.NORTH);
        
        // Conteúdo principal
        JPanel painelConteudo = criarConteudo();
        add(painelConteudo, BorderLayout.CENTER);
        
        // Rodapé
        JPanel painelRodape = criarRodape();
        add(painelRodape, BorderLayout.SOUTH);
    }
    
    private JPanel criarCabecalho() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(0, 86, 163)); // Azul Telcabos
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        painel.setPreferredSize(new Dimension(900, 80));
        
        // Logo/Título
        JLabel labelTitulo = new JLabel("HELPDESK TELCABOS - PAINEL PRINCIPAL");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);
        painel.add(labelTitulo, BorderLayout.WEST);
        
        // Usuário logado
        JLabel labelUsuario = new JLabel("👤 Administrador");
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        labelUsuario.setForeground(Color.WHITE);
        painel.add(labelUsuario, BorderLayout.EAST);
        
        return painel;
    }
    
    private JPanel criarConteudo() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        painel.setBackground(new Color(245, 247, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Saudação
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel labelSaudacao = new JLabel("Bem-vindo ao Sistema HelpDesk Telcabos!");
        labelSaudacao.setFont(new Font("Arial", Font.BOLD, 18));
        labelSaudacao.setForeground(new Color(0, 86, 163));
        painel.add(labelSaudacao, gbc);
        
        // Subtítulo
        gbc.gridy = 1;
        JLabel labelSubtitulo = new JLabel("O que você gostaria de fazer hoje?");
        labelSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSubtitulo.setForeground(new Color(102, 102, 102));
        painel.add(labelSubtitulo, gbc);
        
        // Espaçamento
        gbc.gridy = 2;
        painel.add(Box.createVerticalStrut(40), gbc);
        
        // Card 1: Novo Chamado
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JPanel cardNovoChamado = criarCard(
            "🎫",
            "ABRIR NOVO CHAMADO",
            "Relatar um problema técnico",
            new Color(0, 86, 163) // Azul Telcabos
        );
        painel.add(cardNovoChamado, gbc);
        
        // Card 2: Listar Chamados
        gbc.gridx = 1;
        JPanel cardListarChamados = criarCard(
            "📋",
            "VER CHAMADOS",
            "Visualizar problemas pendentes",
            new Color(67, 160, 71) // Verde
        );
        painel.add(cardListarChamados, gbc);
        
        // Card 3: Estatísticas
        gbc.gridx = 0;
        gbc.gridy = 4;
        JPanel cardEstatisticas = criarCard(
            "📊",
            "ESTATÍSTICAS",
            "Ver relatórios e métricas",
            new Color(255, 179, 0) // Amarelo
        );
        painel.add(cardEstatisticas, gbc);
        
        // Card 4: Resolver
        gbc.gridx = 1;
        JPanel cardResolver = criarCard(
            "✅",
            "RESOLVER CHAMADOS",
            "Marcar problemas como solucionados",
            new Color(229, 57, 53) // Vermelho
        );
        painel.add(cardResolver, gbc);
        
        return painel;
    }
    
    private JPanel criarCard(String emoji, String titulo, String descricao, Color cor) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(cor, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setPreferredSize(new Dimension(350, 150));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Emoji e título
        JLabel labelEmoji = new JLabel(emoji + "  " + titulo);
        labelEmoji.setFont(new Font("Arial", Font.BOLD, 16));
        labelEmoji.setForeground(cor);
        card.add(labelEmoji, BorderLayout.NORTH);
        
        // Descrição
        JLabel labelDescricao = new JLabel("<html><div style='text-align: center;'>" + descricao + "</div></html>");
        labelDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
        labelDescricao.setForeground(new Color(102, 102, 102));
        labelDescricao.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(labelDescricao, BorderLayout.CENTER);
        
        // Adicionar ação de clique
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tratarCliqueCard(titulo);
            }
        });
        
        return card;
    }
    
    private JPanel criarRodape() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painel.setBackground(new Color(245, 247, 250));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Botão Sair
        botaoSair = new JButton("🚪 Sair do Sistema");
        botaoSair.setFont(new Font("Arial", Font.BOLD, 14));
        botaoSair.setForeground(Color.WHITE);
        botaoSair.setBackground(new Color(229, 57, 53)); // Vermelho
        botaoSair.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    TelaDashboard.this,
                    "Tem certeza que deseja sair do sistema?",
                    "Confirmar saída",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    // Voltar para tela de login
                    TelaLogin login = new TelaLogin();
                    login.setVisible(true);
                    dispose();
                }
            }
        });
        
        painel.add(botaoSair);
        return painel;
    }
    
    private void tratarCliqueCard(String tituloCard) {
        switch (tituloCard) {
            case "ABRIR NOVO CHAMADO":
                TelaNovoChamado telaNovo = new TelaNovoChamado();
                telaNovo.setVisible(true);
                this.dispose();
                break;
                
            case "VER CHAMADOS":
                TelaListaChamados telaLista = new TelaListaChamados();
                telaLista.setVisible(true);
                this.dispose();
                break;
                
            case "ESTATÍSTICAS":
                JOptionPane.showMessageDialog(this,
                    "<html><div style='text-align: center;'>"
                    + "<h2>📈 ESTATÍSTICAS</h2>"
                    + "<p>Chamados totais: <b>48</b></p>"
                    + "<p>🔴 Abertos: <b>12</b></p>"
                    + "<p>🟡 Em andamento: <b>8</b></p>"
                    + "<p>🟢 Resolvidos: <b>28</b></p>"
                    + "<p>⏱️ Tempo médio: <b>3h 20min</b></p>"
                    + "</div></html>",
                    "Estatísticas do Sistema",
                    JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "RESOLVER CHAMADOS":
                JOptionPane.showMessageDialog(this,
                    "Para resolver chamados, acesse a lista de chamados\n"
                    + "e clique no botão '✅ Resolver' ao lado de cada um.",
                    "Resolver Chamados",
                    JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}