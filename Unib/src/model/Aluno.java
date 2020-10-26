package model;

public class Aluno {
    
    private int matricula, codCurso;
    private String nome, dataNasc, nomeCurso;
    private Disciplina disciplinas[] = new Disciplina[5];

    
    public Aluno(
            int matricula, 
            String nome, 
            String dataNasc,
            int codCurso,
            String nomeCurso) {
        
        setMatricula(matricula);
        setNome(nome);
        setDataNasc(dataNasc);
        setCodCurso(codCurso);
        setNomeCurso(nomeCurso);
        
    }
    
    public Aluno(
            String nome, 
            String dataNasc,
            int codCurso,
            String nomeCurso) {
               
        setNome(nome);
        setDataNasc(dataNasc);
        setCodCurso(codCurso);
        setNomeCurso(nomeCurso);
        
    }
    
    public Aluno(
            int matricula,
            String nome, 
            String dataNasc,
            String nomeCurso) {
        
        setMatricula(matricula);
        setNome(nome);
        setDataNasc(dataNasc);
        setNomeCurso(nomeCurso);
        
    }
     
    public Aluno(
            int matricula, 
            String nome, 
            String dataNasc,
            int codCurso) {
        
        setMatricula(matricula);
        setNome(nome);
        setDataNasc(dataNasc);
        setCodCurso(codCurso);
        
    }
    
    public Aluno( 
            String nome, 
            String dataNasc,
            int codCurso) {
       
        setNome(nome);
        setDataNasc(dataNasc);
        setCodCurso(codCurso);
        
    }
    
    @Override
    public String toString() {
        String ret = null;
        
        ret = "Matricula.....: " + getMatricula() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Nascimento....: " + getDataNasc() + "\n" +
              "Cód. Curso....: " + getCodCurso() + "\n" +
              "Nome Curso....: " + getNomeCurso() + "\n" +
              "Disciplinas...: ";
        //imprimir vetores com nome disciplina, np1, np2, media e faltas
        
        return ret;
    }


    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
        
    public int getCodCurso() {
        return codCurso;
    }
    
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }
    
    public String getDataNasc() {
        return dataNasc;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    
    
    
}
