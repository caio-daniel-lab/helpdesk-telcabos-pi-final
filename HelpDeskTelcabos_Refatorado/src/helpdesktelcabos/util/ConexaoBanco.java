package helpdesktelcabos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitária responsável APENAS por fornecer a conexão com o banco.
 *
 * SOLID - SRP: Responsabilidade única: gerenciar a conexão com o banco de dados.
 *
 * Refatoração aplicada (Code Smell removido: "Inappropriate Intimacy"):
 * - A classe original ConexaoBanco misturava 3 responsabilidades:
 *   1. Conectar ao banco (OK, ficou aqui)
 *   2. Executar SQL de Chamado (movido para ChamadoRepositoryMySQL)
 *   3. Mostrar erros na tela com JOptionPane (removido - UI nao pertence aqui)
 *
 * Agora erros de conexão lançam SQLException, que quem chamou decide como tratar.
 */
public class ConexaoBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/helpdesk_telcabos";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin"; // altere para sua senha

    /**
     * Retorna uma conexão com o banco de dados.
     * Lança SQLException em caso de falha (sem popup de UI).
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("[BANCO] Conexao estabelecida com sucesso.");
            return conn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL nao encontrado: " + e.getMessage(), e);
        }
    }

    // Construtor privado: esta classe só tem métodos estáticos, não deve ser instanciada
    private ConexaoBanco() {}
}
