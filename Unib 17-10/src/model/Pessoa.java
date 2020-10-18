
package model;


public class Pessoa{
    private String nome, dataNasc, tel, cel, email, cpf, rg;
    private Endereco endereco;

    public Pessoa(String nome, String dataNasc, String tel, String cel, String email, String cpf, String rg) {
        setNome(nome);
        setDataNasc(dataNasc);
        setTel(tel);
        setCel(cel);
        setEmail(email);
        setCpf(cpf);
        setRg(rg);
        
        /*  ***Pegar Dados da Pessoa***
            
                endereco.getCep();
                endereco.getRua();
                endereco.getBairro();
                endereco.getNumero();
                endereco.getCidade();
                endereco.getEstado();       */
    }

    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
