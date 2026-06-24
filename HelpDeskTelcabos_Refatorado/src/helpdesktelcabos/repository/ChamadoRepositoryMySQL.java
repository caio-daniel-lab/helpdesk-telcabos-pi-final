package helpdesktelcabos.repository;

import helpdesktelcabos.model.Chamado;
import helpdesktelcabos.util.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do repositório que persiste chamados no banco MySQL.
 *
 * SOLID - SRP: Esta classe tem UMA responsabilidade: executar SQL para Chamado.
 * SOLID - DIP: Implementa IChamadoRepository, portanto o Service não depende dela diretamente.
 *
 * Refatoração aplicada:
 * - O acesso ao banco foi separado da classe ConexaoBanco original,
 *   que misturava configuração de conexão + operações SQL + chamadas de UI (JOptionPane).
 * - Agora ConexaoBanco só cuida da conexão. Esta classe só cuida do SQL de Chamado.
 */
public class ChamadoRepositoryMySQL implements IChamadoRepository {

    @Override
    public boolean salvar(Chamado chamado) {
        String sql = "INSERT INTO chamados (vendedor, cidade, departamento, "
                   + "tipo_problema, prioridade, descricao) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, chamado.getVendedor());
            stmt.setString(2, chamado.getCidade());
            stmt.setString(3, chamado.getDepartamento());
            stmt.setString(4, chamado.getTipoProblema());
            stmt.setString(5, chamado.getPrioridade());
            stmt.setString(6, chamado.getDescricao());

            stmt.executeUpdate();
            System.out.println("[MySQL] Chamado salvo com sucesso!");
            return true;

        } catch (SQLException e) {
            System.err.println("[MySQL] Erro ao salvar chamado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Chamado> buscarTodos() {
        List<Chamado> lista = new ArrayList<>();
        String sql = "SELECT * FROM chamados ORDER BY data_abertura DESC";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Chamado c = new Chamado(
                    rs.getString("vendedor"),
                    rs.getString("cidade"),
                    rs.getString("departamento"),
                    rs.getString("tipo_problema"),
                    rs.getString("prioridade"),
                    rs.getString("descricao")
                );
                c.setId(rs.getInt("id"));
                c.setResolvido(rs.getBoolean("resolvido"));
                c.setTecnicoResponsavel(rs.getString("tecnico_responsavel"));
                lista.add(c);
            }
            System.out.println("[MySQL] " + lista.size() + " chamado(s) carregado(s).");

        } catch (SQLException e) {
            System.err.println("[MySQL] Erro ao buscar chamados: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean atualizarStatus(int id, boolean resolvido) {
        String sql = "UPDATE chamados SET resolvido = ? WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, resolvido);
            stmt.setInt(2, id);
            int linhas = stmt.executeUpdate();
            System.out.println("[MySQL] Status atualizado. Linhas afetadas: " + linhas);
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("[MySQL] Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }
}
