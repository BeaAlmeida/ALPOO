
package model;


public class Professor extends Pessoa{
    private int idProf;
    private String especProf, tituloProf;


    public Professor(int idProf, String especProf, String tituloProf, String nome, String dataNasc, String tel, String cel, String email, String cpf, String rg) {
        super(nome, dataNasc, tel, cel, email, cpf, rg);
        setIdProf(idProf);
        setEspecProf(especProf);
        setTituloProf(tituloProf);
    }
    
    

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    public String getEspecProf() {
        return especProf;
    }

    public void setEspecProf(String especProf) {
        this.especProf = especProf;
    }

    public String getTituloProf() {
        return tituloProf;
    }

    public void setTituloProf(String tituloProf) {
        this.tituloProf = tituloProf;
    }
}
