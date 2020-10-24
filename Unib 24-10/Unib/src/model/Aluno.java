package model;

import dao.AlunoDAO;

public class Aluno {
    
    private int matricula, codCurso, codigo;
    private String nome, dataNasc, nomeCurso;
    private Disciplina disciplinas[] = new Disciplina[5];

    
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
            int codigo,
            String nome,
            int matricula,
            String dataNasc,
            int codCurso) {
        
        setCodigo(codigo);
        setNome(nome);
        setMatricula(matricula);
        setCodCurso(codCurso);
        
    }

    public Aluno(int codigo,
            String nome,
            int matricula,
            String dataNasc,
            String nomeCurso) {
        
        setCodigo(codigo);
        setNome(nome);
        setMatricula(matricula);
        setDataNasc(dataNasc);
        setNomeCurso(nomeCurso);
    }
     
    @Override
    public String toString() {
        String ret = null;
        
        ret = "Matricula.....: " + getMatricula() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Nascimento....: " + getDataNasc() + "\n" +
              "Cód. Curso....: " + getCodCurso() + "\n" +
              "Disciplinas...: ";
        //imprimir vetores com nome disciplina, np1, np2, media e faltas
        
        return ret;
    }

    /*private void gravar(){
        AlunoDAO dao = new AlunoDAO();
        int codigo = dao.create(this);
        if(codigo > 0) setCodigo(codigo);
    }*/

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    
}
