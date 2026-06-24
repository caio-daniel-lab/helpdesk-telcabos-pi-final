package helpdesktelcabos;

import java.util.Date;

public class Chamado {
    private int id;
    private String vendedor;
    private String cidade;
    private String departamento;
    private String tipoProblema;
    private String prioridade;
    private String descricao;
    private Date dataAbertura;
    private boolean resolvido;
    private String tecnicoResponsavel;
    
    // Construtor
    public Chamado(String vendedor, String cidade, String departamento, 
                   String tipoProblema, String prioridade, String descricao) {
        this.vendedor = vendedor;
        this.cidade = cidade;
        this.departamento = departamento;
        this.tipoProblema = tipoProblema;
        this.prioridade = prioridade;
        this.descricao = descricao;
        this.dataAbertura = new Date();
        this.resolvido = false;
        this.tecnicoResponsavel = "Aguardando";
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getVendedor() { return vendedor; }
    public void setVendedor(String vendedor) { this.vendedor = vendedor; }
    
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    
    public String getTipoProblema() { return tipoProblema; }
    public void setTipoProblema(String tipoProblema) { this.tipoProblema = tipoProblema; }
    
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Date getDataAbertura() { return dataAbertura; }
    
    public boolean isResolvido() { return resolvido; }
    public void setResolvido(boolean resolvido) { this.resolvido = resolvido; }
    
    public String getTecnicoResponsavel() { return tecnicoResponsavel; }
    public void setTecnicoResponsavel(String tecnicoResponsavel) { 
        this.tecnicoResponsavel = tecnicoResponsavel; 
    }
    
    // Método para obter status formatado
    public String getStatus() {
        if (resolvido) {
            return "✅ RESOLVIDO";
        } else {
            return "⏳ PENDENTE";
        }
    }
    
    // Método para obter cor baseada na prioridade
    public String getCorPrioridade() {
        switch (prioridade) {
            case "Alta": return "#FF0000";  // Vermelho
            case "Média": return "#FFA500"; // Laranja
            case "Baixa": return "#008000"; // Verde
            default: return "#000000";      // Preto
        }
    }
    
    @Override
    public String toString() {
        return "Chamado #" + id + " - " + vendedor + " - " + tipoProblema + " - " + getStatus();
    }
}