package model;

public class Curso {
    
    private int codInstituto;
    private long codigo;
    private String nome, tipo;
    private double cargaHoraria;

    public Curso(
            int codigo, 
            int codInstituto, 
            String tipo, 
            double cargaHoraria) {
        
        setCodigo(codigo);
        setCodInstituto(codInstituto);
        geraNome(codigo);
        setTipo(tipo);
        setCargaHoraria(cargaHoraria);
    }

    public Curso(
            int codInstituto, 
            String nome, 
            String tipo, 
            double cargaHoraria) {
        
        geraCodigo(nome);
        setCodInstituto(codInstituto);
        setNome(nome);
        setTipo(tipo);
        setCargaHoraria(cargaHoraria);
    }    
    
    public void geraCodigo(String nome){
        
        switch(nome){
            
            case "Administração de empresas":
                setCodigo(1);
                break;
            
            case "Biomedicina":
                setCodigo(2);
                break;

            case "Ciências Biológicas":
                setCodigo(3);
                break;
                
            case "Ciência da Computação":
                setCodigo(4);
                break;    
            
            case "Direito":
                setCodigo(5);
                break;    
                
            case "Educação Física":
                setCodigo(6);
                break;    
                
             case "Farmacologia":
                setCodigo(7);
                break;
                
            case "Rede de Computadores":
                setCodigo(8);
                break;                
                
            case "Sistemas de Informações":
                setCodigo(9);
                break;

            default:
                setCodigo(0);
                
        }
        
    }
    
    public void geraNome(int codigo){
        
        switch(codigo){
            
            case 1:
                setNome("Administração de empresas");
                break;
            
            case 2:
                "Biomedicina");
                break;

            case "Ciências Biológicas":
                setCodigo(3);
                break;
                
            case "Ciência da Computação":
                setCodigo(4);
                break;    
            
            case "Direito":
                setCodigo(5);
                break;    
                
            case "Educação Física":
                setCodigo(6);
                break;    
                
             case "Farmacologia":
                setCodigo(7);
                break;
                
            case "Rede de Computadores":
                setCodigo(8);
                break;                
                
            case "Sistemas de Informações":
                setCodigo(9);
                break;

            default:
                setCodigo(0);
                
        }
        
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
                
    }

    public void setCodInstituto(int codInstituto) {
        this.codInstituto = codInstituto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public long getCodigo() {
        return codigo;
    }

    public int getCodInstituto() {
        return codInstituto;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    
}
