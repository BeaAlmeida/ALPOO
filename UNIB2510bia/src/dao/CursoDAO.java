package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Curso;


public class CursoDAO {
    private static CursoDAO dao = null;
    public CursoDAO(){ }
    
    public static CursoDAO getInstance(){
        if (dao == null) dao = new CursoDAO();
        return dao;  
    }
    
    public Curso findByCurso(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Curso c = null;
        String sql =("SELECT NomeCurso " +  
                    "FROM Cursos C " +
                    "WHERE CodCurso = ?");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                String nome = rs.getString("NomeCurso");
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
