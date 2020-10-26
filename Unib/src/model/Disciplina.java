package model;

public class Disciplina {
    
    private int codDisci, aulasSemanais, codCurso;
    private String nome;
    private double cargaHoraria;

    public Disciplina(
            int codigo, 
            String nome,            
            double cargaHoraria,
            int aulasSemanais,
            int codCurso) {
        
        setCodDisci(codigo);
        setNome(nome);
        setCargaHoraria(cargaHoraria);        
        setAulasSemanais(aulasSemanais);
        setCodCurso(codCurso);
    }
    
        public Disciplina(
            String nome,            
            double cargaHoraria,
            int aulasSemanais,
            int codCurso) {
        
        setNome(nome);
        setCargaHoraria(cargaHoraria);        
        setAulasSemanais(aulasSemanais);
        setCodCurso(codCurso);
    }
        

    @Override
    public String toString() {
        String ret = null;
        
        ret = "Código........: " + getCodDisci() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Carga horária.: " + getCargaHoraria() + "\n" +
              "Aulas semanais: " + getAulasSemanais() + "\n" +
              "Cód. do Curso.: " + getCodCurso();
        
        return ret;
    }
    
    
    public void setCodDisci(int codigo) {
        this.codDisci = codigo;
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
    
    public int getCodDisci() {
        return codDisci;
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
