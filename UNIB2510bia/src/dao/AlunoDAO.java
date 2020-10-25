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
import model.Aluno;

public class AlunoDAO {
    private static AlunoDAO dao = null;
    public AlunoDAO(){
        
    }
    
    public static AlunoDAO getInstance(){
        if (dao == null) dao = new AlunoDAO();
        return dao;  
    }
    
    public int create(Aluno a) {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO Alunos (Nome, DataNasc, CodCurso) VALUES (?,?,?)";
        
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, a.getNome());
            pst.setString(2, a.getDataNasc());
            pst.setInt(3, a.getCodCurso());
            
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
    
       
    public List<Aluno> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT A.Matricula, A.Nome, A.DataNasc, C.NomeCurso "
                                        + "FROM Alunos A "
                                        + "INNER JOIN Cursos C "
                                        + "ON A.CodCurso = C.CodCurso");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno(0, "", "", "");
                
                aluno.setMatricula(rs.getInt("Matricula"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setDataNasc(rs.getString("DataNasc"));
                aluno.setNomeCurso(rs.getString("NomeCurso"));
                alunos.add(aluno);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return alunos;
        
    }
        
    public void delete (int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "DELETE FROM Alunos WHERE Matricula = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Aluno excluido com sucesso!");
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
    }    
    
    public void update(Aluno a){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "UPDATE Alunos SET Nome = ?, DataNasc = ?, CodCurso = ? where Matricula = ?";
        
        try{
            pst = con.prepareStatement(sql);            
                        
            pst.setString(1, a.getNome());
            pst.setString(2, a.getDataNasc());
            pst.setInt(3, a.getCodCurso());
            pst.setInt(4, a.getMatricula());
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Aluno atualizado com sucesso!");
            
        } catch(SQLException ex) {
            throw new RuntimeException("Erro no update");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }

    }
    
    public List<Aluno> busca(String name){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;        
        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("select * from alunos where nome like '%" + name + "%'");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno(0,"","","");
                                
                aluno.setMatricula(rs.getInt("Matricula"));
                aluno.setNome(rs.getString("Nome"));                
                aluno.setDataNasc(rs.getString("DataNasc"));
                aluno.setCodCurso(rs.getInt("CodCurso"));
                alunos.add(aluno);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return alunos;
    }
    
    
    public Aluno findByMatricula(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Aluno a = null;
        String sql = ("SELECT A.Matricula, A.Nome, A.DataNasc, A.CodCurso, C.Nome"
                                        + "FROM Alunos A "
                                        + "INNER JOIN Cursos C "
                                        + "ON A.CodCurso = C.CodCurso "
                                        + "WHERE Matricula = ?");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                int matricula = rs.getInt("Matricula");
                String nome = rs.getString("Nome");
                String dataNasc = rs.getString("DataNasc");
                int codCurso = rs.getInt("CodCurso");
                String nomeCurso = rs.getString("NomeCurso");
                a = new Aluno (matricula, nome, dataNasc, codCurso, nomeCurso);
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);            
        }
        
        return a;
    }

}
