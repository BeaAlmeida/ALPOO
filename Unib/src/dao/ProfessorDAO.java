package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Endereco;
import model.Professor;

public class ProfessorDAO {
    private static ProfessorDAO dao = null;
    public ProfessorDAO(){
        
    }
    
    public static ProfessorDAO getInstance(){
        if (dao == null) dao = new ProfessorDAO();
        return dao;  
    }
    
    public int create(Professor p, Endereco e) {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO Professores (Nome, DataNasc, Titulo, Espec, Cep, Endereco, Bairro, Cidade, Estado, Tel, Email) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDataNasc());
            pst.setString(3, p.getTitulo());
            pst.setString(4, p.getEspec());
            pst.setString(5, e.getCep());
            pst.setString(6, e.getEndereco());
            pst.setString(7, e.getBairro());
            pst.setString(8, e.getCidade());
            pst.setString(9, e.getEstado());
            pst.setString(10, e.getTel());
            pst.setString(11, e.getEmail());
            
            pst.execute();
            rs = pst.getGeneratedKeys();
            
            if (rs.next()){
                id = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!");
            
        }catch(SQLException ex){
            id = 0;
            JOptionPane.showMessageDialog(null,"Erro ao inserir os dados!/nErro: " + ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        
        return id;
    }
    
       
    public List<Professor> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        List<Professor> professores = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT ID, Nome, DataNasc, Titulo, Espec, Cep, Endereco, Bairro, Cidade, Estado, Tel, Email FROM Professores ");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Endereco end = new Endereco("","","","","","","");
                Professor prof = new Professor(0, "", "", "", "", end);
                
                prof.setId(rs.getInt("ID"));
                prof.setNome(rs.getString("Nome"));
                prof.setDataNasc(rs.getString("DataNasc"));
                prof.setTitulo(rs.getString("Titulo"));
                prof.setEspec(rs.getString("Espec"));
                end.setCep(rs.getString("Cep"));
                end.setEndereco(rs.getString("Endereco"));
                end.setBairro(rs.getString("Bairro"));
                end.setCidade(rs.getString("Cidade"));
                end.setEstado(rs.getString("Estado"));
                end.setTel(rs.getString("Tel"));
                end.setEmail(rs.getString("Email"));
                professores.add(prof);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return professores;
        
    }
        
    public void delete (int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "DELETE FROM Professores WHERE ID = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Professor excluido com sucesso!");
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
    }    
    
    public void update(Professor p, Endereco e){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "UPDATE Professores SET Nome = ?, DataNasc = ?, Titulo = ?, Espec = ?, "
                + "Cep = ?, Endereco = ?, Bairro = ?, Cidade = ?, Estado = ?, Tel = ?, Email = ?, "
                + "where ID = ?";
        
        try{
            pst = con.prepareStatement(sql);            
                        
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDataNasc());
            pst.setString(3, p.getTitulo());
            pst.setString(4, p.getEspec());
            pst.setString(5, e.getCep());
            pst.setString(6, e.getEndereco());
            pst.setString(7, e.getBairro());
            pst.setString(8, e.getCidade());
            pst.setString(9, e.getEstado());
            pst.setString(10, e.getTel());
            pst.setString(11, e.getEmail());
            pst.setInt(12, p.getId());
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Professor atualizado com sucesso!");
            
        } catch(SQLException ex) {
            throw new RuntimeException("Erro no update");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }

    }
    
    public List<Professor> busca(String name){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;        
        
        List<Professor> professores = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("select * from alunos where nome like '%" + name + "%'");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Endereco end = new Endereco("","","","","","","");
                Professor prof = new Professor(0, "", "", "", "", end);
                
                prof.setId(rs.getInt("ID"));
                prof.setNome(rs.getString("Nome"));
                prof.setDataNasc(rs.getString("DataNasc"));
                prof.setTitulo(rs.getString("Titulo"));
                prof.setEspec(rs.getString("Espec"));
                end.setCep(rs.getString("Cep"));
                end.setEndereco(rs.getString("Endereco"));
                end.setBairro(rs.getString("Bairro"));
                end.setCidade(rs.getString("Cidade"));
                end.setEstado(rs.getString("Estado"));
                end.setTel(rs.getString("Tel"));
                end.setEmail(rs.getString("Email"));
                professores.add(prof);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return professores;
    }
    
    
    public Professor findByID(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Professor p = null;
        Endereco e = null;
        String sql = ("SELECT ID, Nome, DataNasc, Titulo, Espec, Cep, Endereco, Bairro, Cidade, Estado, Tel, Email "
                + "FROM Professores"
                + "WHERE ID = ?");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                                
                int Id = (rs.getInt("ID"));
                String nome = (rs.getString("Nome"));
                String dataNasc = (rs.getString("DataNasc"));
                String titulo = (rs.getString("Titulo"));
                String espec = (rs.getString("Espec"));
                String cep = (rs.getString("Cep"));
                String endereco = (rs.getString("Endereco"));
                String bairro = (rs.getString("Bairro"));
                String cidade = (rs.getString("Cidade"));
                String estado = (rs.getString("Estado"));
                String tel = (rs.getString("Tel"));
                String email = (rs.getString("Email"));
                
                e = new Endereco (cep, endereco, bairro, cidade, estado, tel, email);
                p = new Professor (Id, nome, dataNasc, titulo, espec, e);
                
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);            
        }
        
        return p;
    }

}