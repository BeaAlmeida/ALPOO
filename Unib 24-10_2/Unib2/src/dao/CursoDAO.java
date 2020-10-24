/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Curso;


public class CursoDAO {
    private static CursoDAO dao = null;
    public CursoDAO(){
        
    }
    
    public static CursoDAO getInstance(){
        if (dao == null) dao = new CursoDAO();
        return dao;  
    }
    
    public Curso findByCurso(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Curso c = null;
        String sql =("SELECT NM_Curso "
                    + "FROM Cursos C "
                    + "WHERE COD_Curso = ?");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                String nome = rs.getString("NM_curso");
                c = new Curso (nome);
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return c;
    }

    
}
