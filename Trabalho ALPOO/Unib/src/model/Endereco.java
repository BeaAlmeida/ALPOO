package model;

public class Endereco {
    
    private String cep, endereco, bairro, cidade, estado, tel, email;
    
    public Endereco(String cep, 
            String endereco, 
            String bairro, 
            String cidade, 
            String estado,
            String tel,
            String email) {
        
        setCep(cep);
        setEndereco(endereco);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setTel(tel);
        setEmail(email);
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    
}
