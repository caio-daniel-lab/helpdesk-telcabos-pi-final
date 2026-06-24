package helpdesktelcabos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoBanco {
    
    // Configurações do banco (ajuste se necessário)
    private static final String URL = "jdbc:mysql://localhost:3306/helpdesk_telcabos";
    private static final String USUARIO = "root"; // normalmente é root
    private static final String SENHA = "admin"; // coloque sua senha aqui
    
    // Método para conectar
    public static Connection getConnection() {
        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Cria a conexão
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("✅ Conectado ao banco de dados!");
            return conexao;
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Erro ao conectar ao banco: " + e.getMessage(), 
                "Erro de Conexão", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    // Método para salvar um chamado no banco
    public static boolean salvarChamado(Chamado chamado) {
        String sql = "INSERT INTO chamados (vendedor, cidade, departamento, "
                   + "tipo_problema, prioridade, descricao) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, chamado.getVendedor());
            stmt.setString(2, chamado.getCidade());
            stmt.setString(3, chamado.getDepartamento());
            stmt.setString(4, chamado.getTipoProblema());
            stmt.setString(5, chamado.getPrioridade());
            stmt.setString(6, chamado.getDescricao());
            
            stmt.executeUpdate();
            System.out.println("✅ Chamado salvo no banco!");
            return true;
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar chamado: " + e.getMessage());
            return false;
        }
    }
    
    // Método para buscar todos os chamados
    public static java.util.List<Chamado> buscarChamados() {
        java.util.List<Chamado> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM chamados ORDER BY data_abertura DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Chamado chamado = new Chamado(
                    rs.getString("vendedor"),
                    rs.getString("cidade"),
                    rs.getString("departamento"),
                    rs.getString("tipo_problema"),
                    rs.getString("prioridade"),
                    rs.getString("descricao")
                );
                
                chamado.setId(rs.getInt("id"));
                chamado.setResolvido(rs.getBoolean("resolvido"));
                chamado.setTecnicoResponsavel(rs.getString("tecnico_responsavel"));
                
                lista.add(chamado);
            }
            System.out.println("✅ Chamados carregados do banco!");
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar chamados: " + e.getMessage());
        }
        
        return lista;
    }
}
