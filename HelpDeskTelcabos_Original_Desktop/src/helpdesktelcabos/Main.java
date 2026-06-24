package helpdesktelcabos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA ===");
        
        SwingUtilities.invokeLater(() -> {
            try {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                System.out.println("✅ Tela de login aberta com sucesso!");
            } catch (Exception e) {
                System.err.println("❌ ERRO: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}