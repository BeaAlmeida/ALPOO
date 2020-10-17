
package model;


public class Endereco {
    private String cep, rua, bairro, numero, cidade, estado;
    public Endereco(String cep, String rua, String bairro, String numero, String cidade, String estado) {
        setCep(cep);
        setRua(rua);
        setBairro(bairro);
        setNumero(numero);
        setCidade(cidade);
        setEstado(estado);
    }
    
    

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
