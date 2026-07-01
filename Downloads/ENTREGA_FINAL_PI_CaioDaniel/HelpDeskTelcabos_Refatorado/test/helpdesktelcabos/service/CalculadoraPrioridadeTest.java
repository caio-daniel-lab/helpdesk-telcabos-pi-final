package helpdesktelcabos.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários da classe CalculadoraPrioridade.
 *
 * Por que esta classe foi escolhida para o teste unitário:
 * - É um CÁLCULO simples (entrada -> saída, sem efeitos colaterais)
 * - NÃO acessa banco de dados (conforme permitido dispensar pelo enunciado)
 * - NÃO depende de tela nem de outras classes
 * - Por isso é a candidata ideal para JUnit: rápida, isolada e confiável
 *
 * Cada teste cobre um cenário diferente da regra de negócio.
 */
class CalculadoraPrioridadeTest {

    // ===================== calcularPrioridade() =====================

    @Test
    @DisplayName("Problema 'Sistema Autcom' deve ter prioridade Alta")
    void deveRetornarPrioridadeAltaParaSistemaAutcom() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("Sistema Autcom");
        assertEquals("Alta", resultado);
    }

    @Test
    @DisplayName("Problema 'Computador nao liga' deve ter prioridade Alta")
    void deveRetornarPrioridadeAltaParaComputador() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("Computador nao liga");
        assertEquals("Alta", resultado);
    }

    @Test
    @DisplayName("Problema 'Software travando' deve ter prioridade Alta")
    void deveRetornarPrioridadeAltaParaTravamento() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("Software travando");
        assertEquals("Alta", resultado);
    }

    @Test
    @DisplayName("Problema 'Email nao funciona' deve ter prioridade Media")
    void deveRetornarPrioridadeMediaParaEmail() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("Email nao funciona");
        assertEquals("Media", resultado);
    }

    @Test
    @DisplayName("Problema 'Internet lenta' deve ter prioridade Media")
    void deveRetornarPrioridadeMediaParaInternet() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("Internet lenta");
        assertEquals("Media", resultado);
    }

    @Test
    @DisplayName("Problema 'Impressora sem tinta' deve ter prioridade Baixa")
    void deveRetornarPrioridadeBaixaParaImpressora() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("Impressora sem tinta");
        assertEquals("Baixa", resultado);
    }

    @Test
    @DisplayName("Tipo de problema nulo deve retornar prioridade Media (padrao)")
    void deveRetornarMediaQuandoTipoForNulo() {
        String resultado = CalculadoraPrioridade.calcularPrioridade(null);
        assertEquals("Media", resultado);
    }

    @Test
    @DisplayName("Tipo de problema vazio deve retornar prioridade Media (padrao)")
    void deveRetornarMediaQuandoTipoForVazio() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("   ");
        assertEquals("Media", resultado);
    }

    @Test
    @DisplayName("Calculo deve ser case-insensitive (AUTCOM em maiusculas)")
    void deveIgnorarCaixaAltaEBaixa() {
        String resultado = CalculadoraPrioridade.calcularPrioridade("PROBLEMA NO AUTCOM");
        assertEquals("Alta", resultado);
    }

    // ===================== calcularPrazoAtendimentoEmHoras() =====================

    @Test
    @DisplayName("Prioridade Alta deve ter prazo de 4 horas")
    void deveRetornar4HorasParaPrioridadeAlta() {
        int horas = CalculadoraPrioridade.calcularPrazoAtendimentoEmHoras("Alta");
        assertEquals(4, horas);
    }

    @Test
    @DisplayName("Prioridade Media deve ter prazo de 24 horas")
    void deveRetornar24HorasParaPrioridadeMedia() {
        int horas = CalculadoraPrioridade.calcularPrazoAtendimentoEmHoras("Media");
        assertEquals(24, horas);
    }

    @Test
    @DisplayName("Prioridade Baixa deve ter prazo de 72 horas")
    void deveRetornar72HorasParaPrioridadeBaixa() {
        int horas = CalculadoraPrioridade.calcularPrazoAtendimentoEmHoras("Baixa");
        assertEquals(72, horas);
    }

    @Test
    @DisplayName("Prioridade nula deve retornar prazo padrao de 48 horas")
    void deveRetornar48HorasParaPrioridadeNula() {
        int horas = CalculadoraPrioridade.calcularPrazoAtendimentoEmHoras(null);
        assertEquals(48, horas);
    }

    @Test
    @DisplayName("Fluxo completo: prioridade calculada deve corresponder ao prazo correto")
    void deveIntegrarCalculoDePrioridadeComPrazo() {
        String prioridade = CalculadoraPrioridade.calcularPrioridade("Sistema Autcom");
        int prazo = CalculadoraPrioridade.calcularPrazoAtendimentoEmHoras(prioridade);

        assertEquals("Alta", prioridade);
        assertEquals(4, prazo);
    }
}
