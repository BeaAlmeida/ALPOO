package model;

public class Professor {
    
    private int id;
    private String nome, espec, titulo, dataNasc;
    private Endereco endereco;


    public Professor(int id,
            String nome,
            String dataNasc,
            String espec, 
            String titulo,            
            Endereco endereco) {
        
        setId(id);
        setNome(nome);
        setEspec(espec);
        setTitulo(titulo);        
        setDataNasc(dataNasc);
        setEndereco(endereco);
     }

    
    @Override
    public String toString() {
        String ret = null;
        
        ret = "Id............: " + getId() + "\n" +
              "Nome..........: " + getNome() + "\n" +
              "Nascimento....: " + getDataNasc() + "\n" +
              "Especialização: " + getEspec() + "\n" +
              "Título........: " + getTitulo() + "\n" +
              "Endereço......: " + "\n" +
              "Cep...........: " + endereco.getCep() + "\n" +
              "Endereço......: " + endereco.getEndereco() + "\n" +
              "Bairro........: " + endereco.getBairro() + "\n" +
              "Cidade........: " + endereco.getCidade() + "\n" +
              "Estado........: " + endereco.getEstado() + "\n" +
              "Contato.......: " + "\n" +
              "Telefone......: " + endereco.getTel() + "\n" +
              "Email.........: " + endereco.getEmail() + "\n";
        
        return ret;
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

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
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

    public String getDataNasc() {
        return dataNasc;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    
}
