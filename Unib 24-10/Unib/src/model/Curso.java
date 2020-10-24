package model;

public class Curso {
    
    private int codInstituto;
    private long codigo;
    private String nome, tipo;
    private double cargaHoraria;

    public Curso(
            int codigo,
            String nome,             
            String tipo, 
            double cargaHoraria,
            int codInstituto) {
        
        setCodigo(codigo);
        setNome(nome);        
        setTipo(tipo);
        setCargaHoraria(cargaHoraria);
        setCodInstituto(codInstituto);
    }
    
    @Override
    public String toString() {
        String ret = null;
        
        ret = "Código........: " + getCodigo() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Tipo..........: " + getTipo() + "\n" +
              "Carga Horária.: " + getCargaHoraria() + "\n" +
              "Cód. Instituto: " + getCodInstituto();
        
        return ret;
    }    
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
                
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

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public long getCodigo() {
        return codigo;
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

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    
}
