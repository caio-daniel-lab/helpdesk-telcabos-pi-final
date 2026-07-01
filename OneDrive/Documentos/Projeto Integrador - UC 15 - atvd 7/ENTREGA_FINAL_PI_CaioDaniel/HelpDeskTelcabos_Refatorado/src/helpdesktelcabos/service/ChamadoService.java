package helpdesktelcabos.service;

import helpdesktelcabos.model.Chamado;
import helpdesktelcabos.repository.IChamadoRepository;

import java.util.List;

/**
 * Classe de serviço que contém as REGRAS DE NEGÓCIO relacionadas a Chamado.
 *
 * SOLID - SRP: Responsabilidade única: coordenar operações de negócio sobre Chamado.
 * SOLID - DIP: Depende da interface IChamadoRepository, não de uma implementação concreta.
 *              O repositório é "injetado" pelo construtor (Injeção de Dependência).
 *
 * Refatoração aplicada:
 * - A lógica de determinar prioridade estava dentro da tela (TelaNovoChamado).
 *   Isso é um "code smell" chamado "Feature Envy" - a tela estava fazendo
 *   trabalho que pertence à regra de negócio. Agora está aqui, no lugar certo.
 * - A validação de campos também saiu da tela e veio para cá.
 */
public class ChamadoService {

    // Dependência injetada pelo construtor (DIP)
    private final IChamadoRepository repositorio;

    public ChamadoService(IChamadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Cria e salva um novo chamado, aplicando regras de negócio.
     *
     * Regra de negócio: a prioridade é determinada automaticamente pelo tipo de problema.
     *
     * @return o Chamado salvo, ou null se a validação falhar.
     */
    public Chamado abrirChamado(String vendedor, String cidade, String departamento,
                                 String tipoProblema, String descricao) {

        // Validação de campos obrigatórios
        if (vendedor == null || vendedor.trim().isEmpty()) {
            System.out.println("[SERVICO] Erro: vendedor nao pode ser vazio.");
            return null;
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            System.out.println("[SERVICO] Erro: descricao nao pode ser vazia.");
            return null;
        }

        // Regra de negócio: calcular prioridade (extraída para classe própria - testável via JUnit)
        String prioridade = CalculadoraPrioridade.calcularPrioridade(tipoProblema);

        // Criar o objeto chamado
        Chamado chamado = new Chamado(vendedor, cidade, departamento, tipoProblema, prioridade, descricao);

        // Persistir via repositório (sem saber se é MySQL, memória, etc.)
        boolean salvo = repositorio.salvar(chamado);

        if (salvo) {
            System.out.println("[SERVICO] Chamado aberto com sucesso: " + chamado);
            return chamado;
        } else {
            System.out.println("[SERVICO] Falha ao salvar o chamado.");
            return null;
        }
    }

    /**
     * Retorna a lista de todos os chamados.
     */
    public List<Chamado> listarChamados() {
        return repositorio.buscarTodos();
    }

    /**
     * Marca um chamado como resolvido.
     */
    public boolean resolverChamado(int id) {
        System.out.println("[SERVICO] Resolvendo chamado #" + id);
        return repositorio.atualizarStatus(id, true);
    }

}
