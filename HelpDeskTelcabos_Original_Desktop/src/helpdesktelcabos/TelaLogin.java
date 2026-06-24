package helpdesktelcabos;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;

    public TelaLogin() {

        setTitle("HelpDesk Telcabos - Login");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(227, 242, 253));
        setLayout(new GridBagLayout());

        GridBagConstraints gbcJanela = new GridBagConstraints();
        gbcJanela.insets = new Insets(20, 20, 20, 20);
        gbcJanela.gridx = 0;
        gbcJanela.gridy = 0;

        // ================= PAINEL PRINCIPAL =================
        JPanel painelLogin = new JPanel(new BorderLayout());
        painelLogin.setBackground(Color.WHITE);
        painelLogin.setPreferredSize(new Dimension(400, 450));
        painelLogin.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
            )
        );

        // ================= TOPO =================
        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setBackground(Color.WHITE);

        JLabel labelTitulo = new JLabel("HELPDESK TELCABOS");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setForeground(new Color(0, 86, 163));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelSubtitulo = new JLabel("Sistema de Chamados Técnicos");
        labelSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSubtitulo.setForeground(new Color(102, 102, 102));
        labelSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelTopo.add(labelTitulo);
        painelTopo.add(Box.createVerticalStrut(5));
        painelTopo.add(labelSubtitulo);
        painelTopo.add(Box.createVerticalStrut(25));

        painelLogin.add(painelTopo, BorderLayout.NORTH);

        // ================= FORMULÁRIO =================
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Usuário
        gbc.gridy = 0;
        JLabel labelUsuario = new JLabel("👤 USUÁRIO:");
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        painelFormulario.add(labelUsuario, gbc);

        gbc.gridy++;
        campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(300, 40));
        campoUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        painelFormulario.add(campoUsuario, gbc);

        // Senha
        gbc.gridy++;
        JLabel labelSenha = new JLabel("🔒 SENHA:");
        labelSenha.setFont(new Font("Arial", Font.BOLD, 14));
        painelFormulario.add(labelSenha, gbc);

        gbc.gridy++;
        campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(300, 40));
        campoSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        painelFormulario.add(campoSenha, gbc);

        // Mostrar senha
        gbc.gridy++;
        JCheckBox checkMostrarSenha = new JCheckBox("👁️ Mostrar senha");
        checkMostrarSenha.setBackground(Color.WHITE);
        checkMostrarSenha.addActionListener(e ->
            campoSenha.setEchoChar(
                checkMostrarSenha.isSelected() ? (char) 0 : '•'
            )
        );
        painelFormulario.add(checkMostrarSenha, gbc);

        // Botão
        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 10, 0);
        botaoEntrar = new JButton("ENTRAR NO SISTEMA");
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setBackground(new Color(0, 86, 163));
        botaoEntrar.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        botaoEntrar.setFocusPainted(false);

        // 🔹 AQUI está a ligação correta com o login
        botaoEntrar.addActionListener(e -> fazerLogin());

        painelFormulario.add(botaoEntrar, gbc);

        painelLogin.add(painelFormulario, BorderLayout.CENTER);

        add(painelLogin, gbcJanela);

        // ================= RODAPÉ =================
        gbcJanela.gridy = 1;
        JLabel labelSlogan = new JLabel("\"Juntos, entregamos valor\"");
        labelSlogan.setFont(new Font("Arial", Font.ITALIC, 14));
        labelSlogan.setForeground(new Color(0, 86, 163));
        add(labelSlogan, gbcJanela);

        // Enter faz login
        getRootPane().setDefaultButton(botaoEntrar);

        // Pré-preencher para teste
        //campoUsuario.setText("admin");
        //campoSenha.setText("123");
    }

    // ================= LOGIN =================
    private void fazerLogin() {

        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, preencha todos os campos!",
                "Campos obrigatórios",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (usuario.equalsIgnoreCase("admin") && senha.equals("123")) {

            JOptionPane.showMessageDialog(this,
                "Login realizado com sucesso!\n\nBem-vindo ao HelpDesk Telcabos!",
                "Login OK",
                JOptionPane.INFORMATION_MESSAGE);

             TelaDashboard dashboard = new TelaDashboard();
             dashboard.setVisible(true);
             dispose(); // fecha a tela de login

        } else {
            JOptionPane.showMessageDialog(this,
                "Credenciais inválidas!\n\nUse:\nUsuário: admin\nSenha: 123",
                "Login inválido",
                JOptionPane.ERROR_MESSAGE);

            campoSenha.setText("");
            campoSenha.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}