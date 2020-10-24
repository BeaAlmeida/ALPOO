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
        String sql = "INSERT INTO Alunos (Nome,Nascimento,Matricula,CD_Curso) VALUES (?,?,?,?)";
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, a.getNome());
            pst.setString(2, a.getDataNasc());
            pst.setInt(3, a.getMatricula());
            pst.setInt(4, a.getCodCurso());
            pst.execute();
            rs = pst.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!");
        }catch(SQLException ex){
            id = 0;
            JOptionPane.showMessageDialog(null,"Erro ao inserir os dados!!/n Erro:" + ex);
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
            pst = con.prepareStatement("SELECT A.Codigo, A.Nome, A.Matricula, A.Nascimento, C.NM_Curso "
                                        + "FROM Alunos A "
                                        + "INNER JOIN Cursos C "
                                        + "ON A.CD_Curso = COD_Curso");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno(0,"",0,"","");
                
                aluno.setCodigo(rs.getInt("Codigo"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setMatricula(rs.getInt("Matricula"));
                aluno.setDataNasc(rs.getString("Nascimento"));
                aluno.setNomeCurso(rs.getString("NM_Curso"));
                alunos.add(aluno);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return alunos;
    }
    
    public List<Aluno> busca(String name){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        List<Aluno> alunos = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT A.Codigo, A.Nome, A.Matricula, A.Nascimento, C.NM_Curso "
                                        + "FROM Alunos A "
                                        + "INNER JOIN Cursos C "
                                        + "ON A.CD_Curso = COD_Curso "
                                        + "WHERE A.Nome like '%" + name + "%'");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno(0,"",0,"","");
                
                aluno.setCodigo(rs.getInt("Codigo"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setMatricula(rs.getInt("Matricula"));
                aluno.setDataNasc(rs.getString("Nascimento"));
                aluno.setNomeCurso(rs.getString("NM_Curso"));
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
        String sql = "DELETE FROM Alunos WHERE Codigo = ?";
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
        String sql = "UPDATE Alunos SET Nome=?,Nascimento=?,Matricula=?,CD_Curso=? where Codigo=?";
        try{
            pst = con.prepareStatement(sql);
            
            pst.setString(1, a.getNome());
            pst.setString(2, a.getDataNasc());
            pst.setInt(3, a.getMatricula());
            pst.setInt(4, a.getCodCurso());
            pst.setInt(5, a.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Aluno atualizado com sucesso!");
        } catch(SQLException ex) {
            throw new RuntimeException("Erro no update");
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }

    } 
    
    public Aluno findByCodigo(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Aluno a = null;
        String sql = ("SELECT A.Codigo, A.Nome, A.Matricula, A.Nascimento,A.CD_curso, C.NM_Curso "
                                        + "FROM Alunos A "
                                        + "INNER JOIN Cursos C "
                                        + "ON A.CD_Curso = COD_Curso "
                                        + "WHERE CODIGO = ?");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                int codigo = rs.getInt("Codigo");
                String nome = rs.getString("Nome");
                int matricula = rs.getInt("Matricula");
                String dataNasc = rs.getString("Nascimento");
                int codCurso = rs.getInt("CD_Curso");
                String nomeCurso = rs.getString("NM_Curso");
                a = new Aluno (codigo,nome,matricula,dataNasc,codCurso,nomeCurso);
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return a;
    }

}
