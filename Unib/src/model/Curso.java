package model;

public class Curso {
    
    private int codInstituto, cargaHoraria;
    private long codCurso;
    private String nome, tipo;

    public Curso(
            int codigo,
            String nome,
            int cargaHoraria,
            String tipo,            
            int codInstituto) {
        
        setCodCurso(codigo);
        setNome(nome);        
        setTipo(tipo);
        setCargaHoraria(cargaHoraria);
        setCodInstituto(codInstituto);
    }
    
    public Curso(
            String nome,
            int cargaHoraria,
            String tipo,            
            int codInstituto) {
        
        setNome(nome);        
        setTipo(tipo);
        setCargaHoraria(cargaHoraria);
        setCodInstituto(codInstituto);
    }
    
    public Curso(String nome) {
        setNome(nome);  
    }
    
    @Override
    public String toString() {
        String ret = null;
        
        ret = "Código........: " + getCodCurso() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Tipo..........: " + getTipo() + "\n" +
              "Carga Horária.: " + getCargaHoraria() + "\n" +
              "Cód. Instituto: " + getCodInstituto();
        
        return ret;
    }    
    
    public void setCodCurso(int codigo) {
        this.codCurso = codigo;
                
    }

    public void setCodInstituto(int codInstituto) {
        this.codInstituto = codInstituto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public long getCodCurso() {
        return codCurso;
    }

    public int getCodInstituto() {
        return codInstituto;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    
}
