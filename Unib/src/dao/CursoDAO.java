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
import model.Curso;
import model.Disciplina;


public class CursoDAO {
    private static CursoDAO dao = null;
    public CursoDAO(){ }
    
    public static CursoDAO getInstance(){
        if (dao == null) dao = new CursoDAO();
        return dao;  
    }
    
    public int create(Curso c) {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO Cursos (NomeC, CargaHorariaC, Tipo, CodInstituto) VALUES (?,?,?,?)";
        
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, c.getNome());
            pst.setInt(2, c.getCargaHoraria());
            pst.setString(3, c.getTipo());
            pst.setInt(4, c.getCodInstituto());
            
            pst.execute();
            rs = pst.getGeneratedKeys();
            
            if (rs.next()) id = rs.getInt(1);
            
            JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!");
            
        }catch(SQLException ex){
            id = 0;
            JOptionPane.showMessageDialog(null,"Erro ao inserir os dados!/nErro: " + ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        
        return id;
    }
    
       
    public List<Curso> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        List<Curso> listaCurso = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT CodCurso, NomeC, CargaHorariaC, Tipo,CodInstituto "
                                    + "FROM Cursos ");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Curso curso = new Curso(0,"",0,"",0);
                
                curso.setCodCurso(rs.getInt("CodCurso"));
                curso.setNome(rs.getString("NomeC"));
                curso.setCargaHoraria(rs.getInt("CargaHorariaC"));
                curso.setTipo(rs.getString("Tipo"));
                curso.setCodInstituto(rs.getInt("CodInstituto"));
                listaCurso.add(curso);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return listaCurso;
        
    }
        
    public void delete (int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "DELETE FROM Cursos WHERE CodCurso = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Curso excluido com sucesso!");
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
    }    
    
    public void update(Curso c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "UPDATE Cursos SET NomeC = ?, CargaHorariaC = ?, Tipo = ?, CodInstituto = ? where CodCurso = ?";
        
        try{
            pst = con.prepareStatement(sql);            
                        
            pst.setString(1, c.getNome());
            pst.setDouble(2, c.getCargaHoraria());
            pst.setString(3, c.getTipo());
            pst.setInt(4, c.getCodInstituto());
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Curso atualizado com sucesso!");
            
        } catch(SQLException ex) {
            throw new RuntimeException("Erro no update");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }

    }
    
    public List<Curso> busca(String name){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;        
        
        List<Curso> listaCurso = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("select * from Cursos where nome like '%" + name + "%'");
            rs = pst.executeQuery();
                        
            while (rs.next()) {
                Curso curso = new Curso(0,"",0,"",0);
                
                curso.setCodCurso(rs.getInt("CodCurso"));
                curso.setNome(rs.getString("NomeC"));
                curso.setCargaHoraria(rs.getInt("CargaHorariaC"));
                curso.setTipo(rs.getString("Tipo"));
                curso.setCodInstituto(rs.getInt("CodInstituto"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return listaCurso;
    }
    
    
    public Curso findByCodCurso(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Curso c = null;
        String sql = ("SELECT CodCurso, NomeC, CargaHorariaC, Tipo, CodInstituto FROM Cursos ");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                int codCurso = rs.getInt("CodCurso");
                String nome = rs.getString("NomeC");
                int cargaHoraria = rs.getInt("CargaHorariaC");
                String tipo = rs.getString("Tipo");
                int codInstituto = rs.getInt("CodInstituto");
                c = new Curso (codCurso, nome, cargaHoraria, tipo, codInstituto);
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);            
        }
        
        return c;
    }    
    
    public Curso findByCurso(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Curso c = null;
        String sql =("SELECT NomeC FROM Cursos WHERE CodCurso = ?");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                String nome = rs.getString("NomeC");
                c = new Curso(nome);
            }
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);            
        }
        
        return c;
        
    }

    
}
