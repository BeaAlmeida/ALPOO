package model;

public class Professor {
    
    private int id;
    private String nome, espec, titulo, dia, mes, ano;
    private Endereco endereco;


    public Professor(int id,
            String nome,
            String espec, 
            String titulo,
            String dia, 
            String mes, 
            String ano,
            Endereco endereco) {
        
        setId(id);
        setNome(nome);
        setEspec(espec);
        setTitulo(titulo);        
        setDia(dia);
        setMes(mes);
        setAno(ano);
        setEndereco(endereco);
     }

    
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspec() {
        return espec;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAno() {
        return ano;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    
}
