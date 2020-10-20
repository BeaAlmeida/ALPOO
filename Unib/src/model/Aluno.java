package model;

public class Aluno {
    
    private int codCurso;
    private String matricula, nome, mes, dia, ano;
    private Disciplina disciplinas[] = new Disciplina[5];

    
    public Aluno(
            int codCurso, 
            String matricula, 
            String nome, 
            String dia,
            String mes,
            String ano) {
        
        setCodCurso(codCurso);
        setMatricula(matricula);
        setNome(nome);
        setDia(dia);
        setMes(mes);
        setAno(ano);
        
    }

     
    @Override
    public String toString() {
        String ret = null;
        
        ret = "Matricula.....: " + getMatricula() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Nascimento....: " + getDia() + getMes() + getAno() + "\n" +
              "Cód. Curso....: " + getCodCurso() + "\n" +
              "Disciplinas...: ";
        //imprimir vetores com nome disciplina, np1, np2, media e faltas
        
        return ret;
    }


    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
        
    public int getCodCurso() {
        return codCurso;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMes() {
        return mes;
    }

    public String getDia() {
        return dia;
    }

    public String getAno() {
        return ano;
    }
    
}
