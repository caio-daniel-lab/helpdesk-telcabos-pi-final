package helpdesktelcabos.repository;

import helpdesktelcabos.model.Chamado;
import java.util.List;

/**
 * Interface que define o contrato para persistência de Chamados.
 *
 * SOLID - DIP (Dependency Inversion Principle):
 * As classes de alto nível (ChamadoService) dependem desta abstração,
 * não de uma implementação concreta (banco de dados específico).
 *
 * SOLID - ISP (Interface Segregation Principle):
 * A interface só declara os métodos realmente necessários para Chamado,
 * sem misturar operações de outros contextos.
 *
 * Vantagem: se amanhã você quiser salvar em arquivo de texto ou outro banco,
 * basta criar uma nova classe que implementa esta interface, sem mexer no resto.
 */
public interface IChamadoRepository {

    /**
     * Salva um novo chamado. Retorna true se salvo com sucesso.
     */
    boolean salvar(Chamado chamado);

    /**
     * Retorna todos os chamados cadastrados.
     */
    List<Chamado> buscarTodos();

    /**
     * Atualiza o status (resolvido) de um chamado existente.
     */
    boolean atualizarStatus(int id, boolean resolvido);
}
