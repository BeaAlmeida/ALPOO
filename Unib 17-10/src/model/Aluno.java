
package model;


public class Aluno extends Pessoa{
    private double nota1, nota2, media;
    private int codCurso, faltas;
    private String matricula;

    public Aluno(double nota1, double nota2, double media, int codCurso, int faltas, String matricula, String nome, String dataNasc, String tel, String cel, String email, String cpf, String rg) {
        super(nome, dataNasc, tel, cel, email, cpf, rg);
        setNota1(nota1);
        setNota2(nota2);
        setMedia(media);
        setCodCurso(codCurso);
        setFaltas(faltas);
        setMatricula(matricula);
    }

 
    
    

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
