
package model;


public class Curso {
    private int codCurso, codInstituto;
    private String nomeCurso, tipoCurso;
    private double cargaHoraria;
    private String[] cursos = { "Administração de empresas",
                                    "BioMedicina",
                                    "Ciências Biológicas",
                                    "Ciências da Computação",
                                    "Direito",
                                    "Educação Física",
                                    "Farmacologia",
                                    "Rede de Computadores",
                                    "Sistemas de Informações",
                                   };
    

    public Curso(int codCurso, int codInstituto, String nomeCurso, String tipoCurso, double cargaHoraria) {
        setCodCurso(codCurso);
        setCodInstituto(codInstituto);
        setNomeCurso(nomeCurso);
        setTipoCurso(tipoCurso);
        setCargaHoraria(cargaHoraria);
        setCursos(cursos);
    }

    
    
    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public int getCodInstituto() {
        return codInstituto;
    }

    public void setCodInstituto(int codInstituto) {
        this.codInstituto = codInstituto;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String[] getCursos() {
        return cursos;
    }

    public void setCursos(String[] cursos) {
        this.cursos = cursos;
    }
    
}
