package model;

public class Disciplina {
    
    private int codigo, aulasSemanais, faltas;
    private String nome;
    private double cargaHoraria, np1, np2, media;

    public Disciplina(
            int codigo, 
            String nome,
            double np1,
            double np2,
            double media,
            int faltas,
            int aulasSemanais,
            double cargaHoraria) {
        
        setCodigo(codigo);
        setNome(nome);
        setNp1(np1);
        setNp2(np2);
        setMedia(media);
        setFaltas(faltas);
        setAulasSemanais(aulasSemanais);
        setCargaHoraria(cargaHoraria);
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
    
    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public void setNp1(double np1) {
        this.np1 = np1;
    }

    public void setNp2(double np2) {
        this.np2 = np2;
    }

    public void setMedia(double media) {
        this.media = media;
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

    public int getFaltas() {
        return faltas;
    }

    public double getNp1() {
        return np1;
    }

    public double getNp2() {
        return np2;
    }

    public double getMedia() {
        return media;
    }

    
    
}
