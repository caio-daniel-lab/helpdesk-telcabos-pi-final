package helpdesktelcabos.service;

import helpdesktelcabos.model.Chamado;
import helpdesktelcabos.repository.ChamadoRepositoryMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários da classe ChamadoService.
 *
 * IMPORTANTE: estes testes usam ChamadoRepositoryMemoria (em memória RAM),
 * e NÃO acessam o banco de dados MySQL. Isso é permitido pelo enunciado,
 * que dispensa testes de funcionalidades com acesso a banco.
 *
 * O objetivo aqui é testar as REGRAS DE NEGÓCIO (validação, integração com
 * a calculadora de prioridade), não a persistência em si.
 */
class ChamadoServiceTest {

    private ChamadoService service;

    // Executado antes de cada teste: garante que cada teste comeca "limpo"
    @BeforeEach
    void configurar() {
        service = new ChamadoService(new ChamadoRepositoryMemoria());
    }

    @Test
    @DisplayName("Deve abrir um chamado valido com todos os campos preenchidos")
    void deveAbrirChamadoComDadosValidos() {
        Chamado chamado = service.abrirChamado(
                "Joao Silva", "Sao Paulo", "Vendas", "Sistema Autcom", "Sistema nao abre"
        );

        assertNotNull(chamado);
        assertEquals("Joao Silva", chamado.getVendedor());
        assertEquals("Alta", chamado.getPrioridade());
        assertEquals("PENDENTE", chamado.getStatus());
    }

    @Test
    @DisplayName("Nao deve abrir chamado quando o vendedor estiver em branco")
    void naoDeveAbrirChamadoComVendedorEmBranco() {
        Chamado chamado = service.abrirChamado(
                "", "Curitiba", "TI", "Internet lenta", "Descricao valida"
        );

        assertNull(chamado, "O chamado deveria ser rejeitado (vendedor vazio)");
    }

    @Test
    @DisplayName("Nao deve abrir chamado quando a descricao estiver em branco")
    void naoDeveAbrirChamadoComDescricaoEmBranco() {
        Chamado chamado = service.abrirChamado(
                "Maria Souza", "Curitiba", "TI", "Internet lenta", "   "
        );

        assertNull(chamado, "O chamado deveria ser rejeitado (descricao vazia)");
    }

    @Test
    @DisplayName("Deve listar corretamente os chamados salvos")
    void deveListarChamadosSalvos() {
        service.abrirChamado("Vendedor 1", "SP", "Vendas", "Email nao funciona", "Descricao 1");
        service.abrirChamado("Vendedor 2", "RJ", "TI", "Impressora", "Descricao 2");

        List<Chamado> chamados = service.listarChamados();

        assertEquals(2, chamados.size());
    }

    @Test
    @DisplayName("Deve marcar um chamado existente como resolvido")
    void deveResolverChamadoExistente() {
        Chamado chamado = service.abrirChamado("Vendedor 1", "SP", "Vendas", "Email nao funciona", "Descricao");

        boolean resolvido = service.resolverChamado(chamado.getId());

        assertTrue(resolvido);
        assertEquals("RESOLVIDO", service.listarChamados().get(0).getStatus());
    }

    @Test
    @DisplayName("Nao deve resolver um chamado que nao existe")
    void naoDeveResolverChamadoInexistente() {
        boolean resolvido = service.resolverChamado(999);

        assertFalse(resolvido);
    }
}
