package helpdesktelcabos.service;

/**
 * Classe responsável exclusivamente pelo CÁLCULO de prioridade e prazo de
 * atendimento de um chamado tecnico.
 *
 * SOLID - SRP (Single Responsibility Principle):
 * Responsabilidade única: calcular prioridade e prazo. Não acessa banco de
 * dados, não conhece tela, não depende de nada externo.
 *
 * Motivo da extração (fase de testes):
 * Esta lógica estava dentro de ChamadoService.calcularPrioridade() (método
 * privado). Foi extraída para uma classe própria, pública e sem dependências,
 * exatamente para poder ser testada de forma unitária e isolada com JUnit,
 * conforme pede a atividade ("prefira funcionalidades de cálculo simples").
 *
 * Por ser um cálculo puro (mesma entrada = mesma saída, sem efeitos
 * colaterais, sem banco), é o candidato ideal para teste unitário.
 */
public class CalculadoraPrioridade {

    // Construtor privado: classe utilitária, só com métodos estáticos
    private CalculadoraPrioridade() {}

    /**
     * Calcula a prioridade do chamado com base no tipo de problema informado.
     *
     * Regra de negócio:
     * - Problemas relacionados a "Autcom" ou "Computador" -> prioridade Alta
     * - Problemas relacionados a "Internet" ou "Rede"      -> prioridade Media
     * - Problemas relacionados a "Impressora"               -> prioridade Baixa
     * - Tipo nulo ou não reconhecido                        -> prioridade Media (padrão)
     *
     * @param tipoProblema descrição/tipo do problema relatado
     * @return "Alta", "Media" ou "Baixa"
     */
    public static String calcularPrioridade(String tipoProblema) {
        if (tipoProblema == null || tipoProblema.trim().isEmpty()) {
            return "Media";
        }

        String tipo = tipoProblema.toLowerCase();

        if (tipo.contains("autcom") || tipo.contains("computador") || tipo.contains("travando")) {
            return "Alta";
        }
        if (tipo.contains("impressora")) {
            return "Baixa";
        }
        // Internet, email, rede e demais problemas comuns -> Media
        return "Media";
    }

    /**
     * Calcula o prazo (em horas) para atendimento do chamado, de acordo com
     * a prioridade calculada. Este é o "cálculo simples" numérico exigido
     * pela atividade.
     *
     * Regra de negócio (SLA - Service Level Agreement):
     * - Prioridade Alta  -> 4 horas
     * - Prioridade Media -> 24 horas
     * - Prioridade Baixa -> 72 horas
     * - Prioridade desconhecida -> 48 horas (padrão de segurança)
     *
     * @param prioridade "Alta", "Media" ou "Baixa"
     * @return prazo em horas para atendimento
     */
    public static int calcularPrazoAtendimentoEmHoras(String prioridade) {
        if (prioridade == null) {
            return 48;
        }

        switch (prioridade.trim()) {
            case "Alta":
                return 4;
            case "Media":
                return 24;
            case "Baixa":
                return 72;
            default:
                return 48;
        }
    }
}
