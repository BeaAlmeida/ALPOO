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
import model.Disciplina;

public class DisciplinaDAO {
    private static DisciplinaDAO dao = null;
    public DisciplinaDAO(){ }
    
    public static DisciplinaDAO getInstance(){
        if (dao == null) dao = new DisciplinaDAO();
        return dao;  
    }
    
    public int create(Disciplina d) {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO Disciplinas (NomeD, CargaHorariaD, AulasSemanais, CodCurso) VALUES (?,?,?,?)";
        
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, d.getNome());
            pst.setDouble(2, d.getCargaHoraria());
            pst.setInt(3, d.getAulasSemanais());
            pst.setInt(4, d.getCodCurso());
            
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
    
       
    public List<Disciplina> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        List<Disciplina> listaDisci = new ArrayList<>();
        try {
            pst = con.prepareStatement("SELECT CodDisci, NomeD, CargaHorariaD, AulasSemanais,CodCurso "
                                        + "FROM Disciplinas ");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Disciplina disci = new Disciplina(0,"",0,0,0);
                
                disci.setCodDisci(rs.getInt("CodDisci"));
                disci.setNome(rs.getString("NomeD"));
                disci.setCargaHoraria(rs.getDouble("CargaHorariaD"));
                disci.setAulasSemanais(rs.getInt("AulasSemanais"));
                disci.setCodCurso(rs.getInt("CodCurso"));
                listaDisci.add(disci);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return listaDisci;
        
    }
        
    public void delete (int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "DELETE FROM Disciplinas WHERE CodDisci = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Disciplina excluida com sucesso!");
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
    }    
    
    public void update(Disciplina d){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "UPDATE Disciplinas SET NomeD = ?, CargaHorariaD = ?, AulasSemanais = ?, CodCurso = ? "
                + "where CodDisci = ?";
        
        try{
            pst = con.prepareStatement(sql);            
                        
            pst.setString(1, d.getNome());
            pst.setDouble(2, d.getCargaHoraria());
            pst.setInt(3, d.getAulasSemanais());
            pst.setInt(4, d.getCodCurso());
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Disciplina atualizada com sucesso!");
            
        } catch(SQLException ex) {
            throw new RuntimeException("Erro no update");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }

    }
    
    public List<Disciplina> busca(String name){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;        
        
        List<Disciplina> listaDisci = new ArrayList<>();
        
        try {
            pst = con.prepareStatement("select * from Disciplinas where nome like '%" + name + "%'");
            rs = pst.executeQuery();
                                    
            while (rs.next()) {
                Disciplina disci = new Disciplina(0,"",0,0,0);
                
                disci.setCodDisci(rs.getInt("CodDisci"));
                disci.setNome(rs.getString("NomeD"));
                disci.setCargaHoraria(rs.getDouble("CargaHorariaD"));
                disci.setAulasSemanais(rs.getInt("AulasSemanais"));
                disci.setCodCurso(rs.getInt("CodCurso"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return listaDisci;
    }
    
    
    public Disciplina findByCodDisci(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Disciplina d = null;
        String sql = ("SELECT CodDisci, NomeD, CargaHorariaD, AulasSemanais, CodCurso"
                    + "FROM Disciplinas ");
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            
            while (rs.next()){
                int codDisci = rs.getInt("CodDisci");
                String nome = rs.getString("NomeD");
                double cargaHoraria = rs.getDouble("CargaHorariaD");
                int aulasSemanais = rs.getInt("AulasSemanais");
                int codCurso = rs.getInt("CodCurso");
                d = new Disciplina (codDisci, nome, cargaHoraria, aulasSemanais, codCurso);
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
            
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);            
        }
        
        return d;
    }

}
