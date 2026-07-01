package helpdesktelcabos.repository;

import helpdesktelcabos.model.Chamado;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do repositório que armazena chamados em memória (RAM).
 *
 * SOLID - OCP (Open/Closed Principle):
 * Esta classe é uma implementação alternativa de IChamadoRepository.
 * O sistema está "aberto para extensão" (novas implementações do repositório)
 * mas "fechado para modificação" (não precisamos alterar o ChamadoService).
 *
 * Uso principal: TESTES no método main() sem precisar de banco de dados real.
 * Isso permite testar toda a lógica de negócio de forma isolada.
 */
public class ChamadoRepositoryMemoria implements IChamadoRepository {

    private final List<Chamado> banco = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public boolean salvar(Chamado chamado) {
        chamado.setId(proximoId++);
        banco.add(chamado);
        System.out.println("[MEMORIA] Chamado salvo com ID " + chamado.getId());
        return true;
    }

    @Override
    public List<Chamado> buscarTodos() {
        System.out.println("[MEMORIA] Buscando " + banco.size() + " chamado(s)");
        return new ArrayList<>(banco); // retorna cópia para proteger a lista interna
    }

    @Override
    public boolean atualizarStatus(int id, boolean resolvido) {
        for (Chamado c : banco) {
            if (c.getId() == id) {
                c.setResolvido(resolvido);
                System.out.println("[MEMORIA] Chamado #" + id + " atualizado para: "
                        + (resolvido ? "RESOLVIDO" : "PENDENTE"));
                return true;
            }
        }
        System.out.println("[MEMORIA] Chamado #" + id + " nao encontrado.");
        return false;
    }
}
