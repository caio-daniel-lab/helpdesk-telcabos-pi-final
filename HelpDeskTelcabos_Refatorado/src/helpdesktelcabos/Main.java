package helpdesktelcabos;

import helpdesktelcabos.model.Chamado;
import helpdesktelcabos.repository.ChamadoRepositoryMemoria;
import helpdesktelcabos.service.ChamadoService;

import java.util.List;

/**
 * Classe principal do projeto refatorado.
 *
 * O método main() serve como TESTE das regras de negócio.
 * Usa o repositório em memória (sem banco de dados), conforme pedido no enunciado.
 *
 * Para usar com banco de dados real, substitua:
 *   new ChamadoRepositoryMemoria()
 * por:
 *   new ChamadoRepositoryMySQL()
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("================================================");
        System.out.println("  TESTES DO SISTEMA HELPDESK TELCABOS");
        System.out.println("  (usando repositorio em memoria - sem banco)");
        System.out.println("================================================\n");

        // Injeção de Dependência: o Service recebe o repositório pelo construtor
        ChamadoService service = new ChamadoService(new ChamadoRepositoryMemoria());

        // -------------------------------------------------------
        // TESTE 1: Abrir chamado com dados válidos
        // -------------------------------------------------------
        System.out.println("--- TESTE 1: Abrir chamado valido ---");
        Chamado c1 = service.abrirChamado(
            "Joao Silva",
            "Sao Paulo",
            "Vendas",
            "Sistema Autcom",
            "Sistema nao abre apos a atualizacao"
        );
        if (c1 != null) {
            System.out.println("OK - Chamado criado: " + c1);
            System.out.println("   Prioridade calculada: " + c1.getPrioridade()); // deve ser "Alta"
        } else {
            System.out.println("FALHOU - Chamado nao deveria ser null");
        }

        // -------------------------------------------------------
        // TESTE 2: Abrir chamado de prioridade media
        // -------------------------------------------------------
        System.out.println("\n--- TESTE 2: Prioridade automatica (Media) ---");
        Chamado c2 = service.abrirChamado(
            "Maria Oliveira",
            "Campinas",
            "Financeiro",
            "Email nao funciona",
            "Nao consigo enviar emails desde ontem"
        );
        if (c2 != null) {
            System.out.println("OK - Chamado criado: " + c2);
            System.out.println("   Prioridade calculada: " + c2.getPrioridade()); // deve ser "Media"
        }

        // -------------------------------------------------------
        // TESTE 3: Validação - vendedor em branco deve retornar null
        // -------------------------------------------------------
        System.out.println("\n--- TESTE 3: Validacao - vendedor em branco ---");
        Chamado c3 = service.abrirChamado("", "Curitiba", "TI", "Internet lenta", "Descricao");
        if (c3 == null) {
            System.out.println("OK - Corretamente rejeitado (vendedor vazio)");
        } else {
            System.out.println("FALHOU - Deveria ter rejeitado");
        }

        // -------------------------------------------------------
        // TESTE 4: Listar chamados salvos
        // -------------------------------------------------------
        System.out.println("\n--- TESTE 4: Listar chamados ---");
        List<Chamado> lista = service.listarChamados();
        System.out.println("Total de chamados: " + lista.size()); // deve ser 2
        for (Chamado c : lista) {
            System.out.println("  " + c);
        }

        // -------------------------------------------------------
        // TESTE 5: Resolver um chamado
        // -------------------------------------------------------
        System.out.println("\n--- TESTE 5: Resolver chamado #1 ---");
        boolean resolvido = service.resolverChamado(1);
        System.out.println(resolvido ? "OK - Chamado #1 resolvido" : "FALHOU");

        // Verificar status após resolver
        lista = service.listarChamados();
        for (Chamado c : lista) {
            System.out.println("  " + c + " | Status: " + c.getStatus());
        }

        // -------------------------------------------------------
        System.out.println("\n================================================");
        System.out.println("  TODOS OS TESTES CONCLUIDOS");
        System.out.println("================================================");
    }
}
