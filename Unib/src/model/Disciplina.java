package model;

public class Disciplina {
    
    private int codigo, aulasSemanais, codCurso;
    private String nome;
    private double cargaHoraria;

    public Disciplina(
            int codigo, 
            String nome,            
            double cargaHoraria,
            int aulasSemanais,
            int codCurso) {
        
        setCodigo(codigo);
        setNome(nome);
        setCargaHoraria(cargaHoraria);        
        setAulasSemanais(aulasSemanais);
        setCodCurso(codCurso);
    }

    @Override
    public String toString() {
        String ret = null;
        
        ret = "Código........: " + getCodigo() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Carga horária.: " + getCargaHoraria() + "\n" +
              "Aulas semanais: " + getAulasSemanais() + "\n" +
              "Cód. do Curso.: " + getCodCurso();
        
        return ret;
    }
    
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setAulasSemanais(int aulasSemanais) {
        this.aulasSemanais = aulasSemanais;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    } 

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public int getAulasSemanais() {
        return aulasSemanais;
    }

    public String getNome() {
        return nome;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public int getCodCurso() {
        return codCurso;
    }

    
    
}
