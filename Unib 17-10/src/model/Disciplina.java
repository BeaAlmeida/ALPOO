
package model;


public class Disciplina {
    private int codDisc, qtAulasSemanais;
    private String nomeDisc;
    private double cargaHorariaDisc;

    public Disciplina(int codDisc, int qtAulasSemanais, String nomeDisc, double cargaHorariaDisc) {
        setCodDisc(codDisc);
        setQtAulasSemanais(qtAulasSemanais);
        setNomeDisc(nomeDisc);
        setCargaHorariaDisc(cargaHorariaDisc);
    }
    
    

    public int getCodDisc() {
        return codDisc;
    }

    public void setCodDisc(int codDisc) {
        this.codDisc = codDisc;
    }

    public int getQtAulasSemanais() {
        return qtAulasSemanais;
    }

    public void setQtAulasSemanais(int qtAulasSemanais) {
        this.qtAulasSemanais = qtAulasSemanais;
    }

    public String getNomeDisc() {
        return nomeDisc;
    }

    public void setNomeDisc(String nomeDisc) {
        this.nomeDisc = nomeDisc;
    }

    public double getCargaHorariaDisc() {
        return cargaHorariaDisc;
    }

    public void setCargaHorariaDisc(double cargaHorariaDisc) {
        this.cargaHorariaDisc = cargaHorariaDisc;
    }
}
