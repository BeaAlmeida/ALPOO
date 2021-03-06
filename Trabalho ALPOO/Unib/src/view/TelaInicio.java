package view;

import dao.AlunoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.Endereco;
import model.Professor;
import util.AdicionaFoto;
import util.AlinhaTabela;
import util.Mascara;


public class TelaInicio extends javax.swing.JFrame {

    public static boolean CLICOU_ALUNO = false;
    public static boolean CLICOU_PROF = false;
    public static boolean CLICOU_DISCI = false;
    public static boolean CLICOU_CURSO = false;
    Color azulM = new Color(65, 119, 155);
    Color azulE = new Color(35, 88, 123);
    private static KeyListener kl_uppercase = new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
            
                SwingUtilities.invokeLater(new Runnable() {
                
                    @Override
                    public void run() {
                        JTextField campo = (JTextField) e.getSource();
                        int posicaoCursor = campo.getCaretPosition();
                        campo.setText(campo.getText().toUpperCase());
                        if (posicaoCursor != campo.getCaretPosition()) {
                                campo.setCaretPosition(posicaoCursor);
                        }
                    }                 
                });            
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
            
        };
    
        List<JTextField> listaTf = new ArrayList<JTextField>();
        List<JComboBox> listaCb = new ArrayList<JComboBox>();
    
  
    public TelaInicio() {
        initComponents(); 
        this.setLocationRelativeTo(null);
        trocaPanel(pn_inicio);
        pn_botao_inf.setVisible(false);
        setMask();
        addListaTf();
        addListaCb();
        readJTable();
        
    }
    
    private void preencheCampos(){
        if (CLICOU_ALUNO){
            
            tf_matricula_aluno.setText(tb_busca_aluno.getValueAt(tb_busca_aluno.getSelectedRow(), 0).toString());
            tf_nome_aluno.setText(tb_busca_aluno.getValueAt(tb_busca_aluno.getSelectedRow(), 1).toString());

            String dataJunta = tb_busca_aluno.getValueAt(tb_busca_aluno.getSelectedRow(), 2).toString();
            String[] dataSeparada = dataJunta.split("/");
            tf_dia_aluno.setSelectedItem(dataSeparada[0]);
            tf_mes_aluno.setSelectedItem(dataSeparada[1]);
            tf_ano_aluno.setSelectedItem(dataSeparada[2]);

            tf_nomeCurso_aluno.setText(tb_busca_aluno.getValueAt(tb_busca_aluno.getSelectedRow(), 3).toString());

            trocaPanel(pn_aluno);
        }
        else if (CLICOU_PROF){
                       
            tf_id_prof.setText(tb_busca_prof.getValueAt(tb_busca_prof.getSelectedRow(), 0).toString());
            tf_nome_prof.setText(tb_busca_prof.getValueAt(tb_busca_prof.getSelectedRow(), 1).toString());

            String dataJunta = tb_busca_prof.getValueAt(tb_busca_prof.getSelectedRow(), 2).toString();
            String[] dataSeparada = dataJunta.split("/");
            tf_dia_prof.setSelectedItem(dataSeparada[0]);
            tf_mes_prof.setSelectedItem(dataSeparada[1]);
            tf_ano_prof.setSelectedItem(dataSeparada[2]);

            tf_titulo_prof.setSelectedItem(tb_busca_prof.getValueAt(tb_busca_prof.getSelectedRow(), 3).toString());
            tf_espec_prof.setSelectedItem(tb_busca_prof.getValueAt(tb_busca_prof.getSelectedRow(), 4).toString());

            trocaPanel(pn_prof);
        }
        else if (CLICOU_DISCI){
            
            tf_codigo_disci.setText(tb_busca_disci.getValueAt(tb_busca_disci.getSelectedRow(), 0).toString());
            tf_nome_disci.setText(tb_busca_disci.getValueAt(tb_busca_disci.getSelectedRow(), 1).toString());
            tf_cargaHoraria_disci.setText(tb_busca_disci.getValueAt(tb_busca_disci.getSelectedRow(), 2).toString());
            tf_aulas_disci.setText(tb_busca_disci.getValueAt(tb_busca_disci.getSelectedRow(), 3).toString());
            tf_codCurso_disci.setText(tb_busca_disci.getValueAt(tb_busca_disci.getSelectedRow(), 4).toString());
            
            trocaPanel(pn_disci);
        }
        else if (CLICOU_CURSO){
            
            tf_codigo_curso.setText(tb_busca_curso.getValueAt(tb_busca_curso.getSelectedRow(), 0).toString());
            tf_nome_curso.setText(tb_busca_curso.getValueAt(tb_busca_curso.getSelectedRow(), 1).toString());
            tf_cargaHoraria_curso.setText(tb_busca_curso.getValueAt(tb_busca_curso.getSelectedRow(), 2).toString());
            tf_tipo_curso.setSelectedItem(tb_busca_curso.getValueAt(tb_busca_curso.getSelectedRow(), 3).toString());
            tf_codInstituto_curso.setText(tb_busca_curso.getValueAt(tb_busca_curso.getSelectedRow(), 4).toString());
            
            trocaPanel(pn_curso);
        }
    }
    
    private void acionaBotao(String btn){
        switch (btn){
            case "cadastrar":                
                ativaBotoes(btn_salvar);
                limpaCampos();
                setUppercase();
                if (CLICOU_ALUNO)trocaPanel(pn_aluno);
                else if (CLICOU_PROF)trocaPanel(pn_prof);
                else if (CLICOU_DISCI)trocaPanel(pn_disci);
                else if (CLICOU_CURSO)trocaPanel(pn_curso);                
                
                break;
                
            case "tabela":
                ativaBotoes(btn_excluir, btn_atualizar);
                preencheCampos();
                
                break;
                
            case "salvar":                
                if (CLICOU_ALUNO)cadastraAluno();
                else if (CLICOU_PROF)cadastraProf();
                else if (CLICOU_DISCI)cadastraDisci();
                else if (CLICOU_CURSO)cadastraCurso();                
                trocaPanel(pn_busca);
                readJTable();
                
                break;          
                
            case "atualizar":
                
                if (CLICOU_ALUNO)updateAluno();                    
                else if (CLICOU_PROF)updateProf();
                else if (CLICOU_DISCI)updateDisci();
                else if (CLICOU_CURSO)updateCurso();
                trocaPanel(pn_busca);
                readJTable();
                
                break;
                
            case "excluir":
                if (CLICOU_ALUNO)deleteAluno();                    
                else if (CLICOU_PROF)deleteProf();
                else if (CLICOU_DISCI)deleteDisci();
                else if (CLICOU_CURSO)deleteCurso();
                trocaPanel(pn_busca);
                readJTable();
                
                break;
            
            default:               
            
        }        
    }
    
    private void addListaTf(){
        listaTf.add(tf_nome_aluno);
        listaTf.add(tf_aulas_disci);
        listaTf.add(tf_bairro);
        listaTf.add(tf_busca);
        listaTf.add(tf_cargaHoraria_curso);
        listaTf.add(tf_cargaHoraria_disci);
        listaTf.add(tf_cep);
        listaTf.add(tf_cidade);
        listaTf.add(tf_codCurso_aluno);
        listaTf.add(tf_codCurso_disci);
        listaTf.add(tf_codigo_curso);
        listaTf.add(tf_codigo_disci);
        listaTf.add(tf_email);
        listaTf.add(tf_endereco);
        listaTf.add(tf_id_prof);
        listaTf.add(tf_codInstituto_curso);        
        listaTf.add(tf_nomeCurso_aluno);
        listaTf.add(tf_nome_aluno);
        listaTf.add(tf_nome_curso);
        listaTf.add(tf_nome_disci);
        listaTf.add(tf_nome_prof);
        listaTf.add(tf_tel);
    }
    
    private void addListaCb(){
        listaCb.add(tf_ano_aluno);
        listaCb.add(tf_ano_prof);
        listaCb.add(tf_dia_aluno);
        listaCb.add(tf_dia_prof);    
        listaCb.add(tf_espec_prof);
        listaCb.add(tf_estado); 
        listaCb.add(tf_mes_aluno);
        listaCb.add(tf_mes_prof);
        listaCb.add(tf_tipo_curso);
        listaCb.add(tf_titulo_prof);
    }
    
    private void limpaCampos(){
        tf_matricula_aluno.setEnabled(false);
        tf_codigo_disci.setEnabled(false);
        tf_id_prof.setEnabled(false);
        tf_codigo_curso.setEnabled(false);
        for(int i = 0; i < listaTf.size(); i ++){
            JTextField tf = (JTextField)listaTf.get(i);
            tf.setText(" ");
        }
        for(int i = 0; i < listaCb.size(); i ++){
            JComboBox cb = (JComboBox)listaCb.get(i);
            cb.setSelectedIndex(0);
        }
        
    }
    
    private void setUppercase(){
        for(int i = 0; i < listaTf.size(); i ++){
            JTextField tf = (JTextField)listaTf.get(i);
            tf.addKeyListener(kl_uppercase);
        }
        for(int i = 0; i < listaCb.size(); i ++){
            JComboBox cb = (JComboBox)listaCb.get(i);
            cb.addKeyListener(kl_uppercase);
        }
        
    }
        
    private void trocaCor(javax.swing.JPanel panel){
        if (panel != null){
        btn_aluno2.setBackground(azulM);
        btn_prof2.setBackground(azulM);
        btn_disci2.setBackground(azulM);
        btn_curso2.setBackground(azulM);
        panel.setBackground(azulE);
        }
    }
    
    private void trocaPanel(javax.swing.JPanel panel){
        pn_inicio.setVisible(false);
        pn_busca.setVisible(false);
        pn_aluno.setVisible(false);
        pn_prof.setVisible(false);
        pn_disci.setVisible(false);
        pn_curso.setVisible(false);
        pn_botao_inf.setVisible(false);
        pn_botao_sup.setVisible(false);        
        panel.setVisible(true);
        if (panel != pn_inicio && panel != pn_busca) pn_botao_inf.setVisible(true);
        if (panel != pn_inicio) pn_botao_sup.setVisible(true);
        
        if (CLICOU_ALUNO) trocaTable(pn_tb_aluno);
        if (CLICOU_PROF) trocaTable(pn_tb_prof);
        if (CLICOU_DISCI) trocaTable(pn_tb_disci);
        if (CLICOU_CURSO) trocaTable(pn_tb_curso);
        
        
        
    }
    
    private void trocaTable(javax.swing.JScrollPane table){
        readJTable();
        pn_tb_aluno.setVisible(false);
        pn_tb_prof.setVisible(false);
        pn_tb_disci.setVisible(false);
        pn_tb_curso.setVisible(false);
        table.setVisible(true);
    }
    
    private void ativaBotoes(javax.swing.JLabel btn ){
        btn_salvar.setVisible(false);
        btn_atualizar.setVisible(false);
        btn_excluir.setVisible(false);
        btn.setVisible(true);
        
    }
    
    private void ativaBotoes(javax.swing.JLabel btn1, javax.swing.JLabel btn2){
        btn_salvar.setVisible(false);
        btn_atualizar.setVisible(false);
        btn_excluir.setVisible(false);
        btn1.setVisible(true);
        btn2.setVisible(true);
        
    }
    
    private void identificaPanel(String panel){
        
        switch(panel){
            
            case "aluno":
                CLICOU_ALUNO = true;
                CLICOU_PROF = false;
                CLICOU_DISCI = false;
                CLICOU_CURSO = false;                                
                
                break;
            
            case "prof":
                CLICOU_ALUNO = false;
                CLICOU_PROF = true;
                CLICOU_DISCI = false;
                CLICOU_CURSO = false;                
                
                break;
                
            case "disci":
                CLICOU_ALUNO = false;
                CLICOU_PROF = false;
                CLICOU_DISCI = true;
                CLICOU_CURSO = false;
                                
                break;
                
            case "curso":
                CLICOU_ALUNO = false;
                CLICOU_PROF = false;
                CLICOU_DISCI = false;
                CLICOU_CURSO = true;                
                
                break;
 
            default:
                CLICOU_ALUNO = false;
                CLICOU_PROF = false;
                CLICOU_DISCI = false;
                CLICOU_CURSO = false;                
                
        }
        

   }
         
    private void addFoto(){
        ImageIcon imagem = AdicionaFoto.addFoto();
        if(CLICOU_ALUNO)
            pn_foto_professor.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(pn_foto_professor.getWidth(),pn_foto_professor.getHeight(), Image.SCALE_DEFAULT)));
        else if(CLICOU_PROF)
            pn_foto_aluno.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(pn_foto_aluno.getWidth(),pn_foto_aluno.getHeight(), Image.SCALE_DEFAULT)));
    }

    private void setMask(){
        tf_tel.setFormatterFactory(Mascara.getTelMask());
        tf_cep.setFormatterFactory(Mascara.getCepMask());
    }
    
    private void cadastraAluno(){
        String nome = tf_nome_aluno.getText();
        String dia = tf_dia_aluno.getSelectedItem().toString();
        String mes = tf_mes_aluno.getSelectedItem().toString();
        String ano = tf_ano_aluno.getSelectedItem().toString();        
        String dataNasc = (dia + " / " + mes + " / " + ano );
        int codCurso = Integer.parseInt(tf_codCurso_aluno.getText());
        String nomeCurso = tf_nomeCurso_aluno.getText();
        
        try{
            Aluno aluno = new Aluno(nome, dataNasc, codCurso, nomeCurso);
            AlunoDAO dao = new AlunoDAO();
            int matricula = dao.create(aluno);
            if(matricula > 0) aluno.setMatricula(matricula);
            System.out.println("Aluno Cadastrado");            
            System.out.println(aluno.toString());            
            
        } catch(Exception ex){
            System.out.println("Erro no cadastro do Aluno");            
        }
    }
    
    private void cadastraProf(){
        
        String nome = tf_nome_prof.getText();
        String dia = tf_dia_prof.getSelectedItem().toString();
        String mes = tf_mes_prof.getSelectedItem().toString();
        String ano = tf_ano_prof.getSelectedItem().toString();        
        String dataNasc = (dia + "/" + mes + "/" + ano );
        String espec = tf_espec_prof.getSelectedItem().toString();
        String titulo = tf_titulo_prof.getSelectedItem().toString();
        
        String cep = tf_endereco.getText();
        String endereco = tf_tel.getText();
        String bairro = tf_bairro.getText();
        String cidade = tf_cidade.getText();
        String estado = (String)tf_estado.getSelectedItem();
        String tel = tf_tel.getText();
        String email = tf_email.getText();
        
        try{
            Endereco end_prof = new Endereco(cep, endereco, bairro, cidade, estado, tel, email);
            Professor prof = new Professor (nome, dataNasc, espec, titulo, end_prof);
            ProfessorDAO dao = new ProfessorDAO();
            int id = dao.create(prof, end_prof);
            if(id > 0) prof.setId(id);
            
            System.out.println("Professor Cadastrado");            
            System.out.println(prof.toString());
            
        } catch(Exception ex){
            System.out.println("Erro no cadastro do Professor");            
        }
    }
    
    private void cadastraDisci(){
         
        String nome = tf_nome_disci.getText();
        double cargaHoraria = Double.parseDouble(tf_cargaHoraria_disci.getText());
        int aulasSemanais = Integer.parseInt(tf_aulas_disci.getText());
        int codCurso = Integer.parseInt(tf_codCurso_disci.getText());        
        
        try{
            Disciplina disci = new Disciplina(nome, cargaHoraria, aulasSemanais, codCurso);
            DisciplinaDAO dao = new DisciplinaDAO();
            int codigo = dao.create(disci);
            if(codigo > 0) disci.setCodDisci(codigo);
            System.out.println("Disciplina Cadastrada");            
            System.out.println(disci.toString());
            
        }catch(Exception ex){
            System.out.println("Erro no cadastro da Disciplina");            
        }        
    }
    
    private void cadastraCurso(){
        int codCurso = Integer.parseInt(tf_codigo_curso.getText());
        String nome = tf_nome_curso.getText();
        String tipo = tf_tipo_curso.getSelectedItem().toString(); 
        int cargaHoraria = Integer.parseInt(tf_cargaHoraria_curso.getText());
        int codInstituto = Integer.parseInt(tf_codInstituto_curso.getText());
        
        try{
            Curso curso = new Curso(codCurso, nome, cargaHoraria, tipo, codInstituto);
            CursoDAO dao = new CursoDAO();
            int codigo = dao.create(curso);
            if(codigo > 0) curso.setCodCurso(codigo);
            
            System.out.println("Curso Cadastrado");            
            System.out.println(curso.toString());
            
        } catch(Exception ex){
            System.out.println("Erro no cadastro do Curso");
            
        }
        
    }
    
    private void updateAluno(){
        String nome = tf_nome_aluno.getText();
        String dia = tf_dia_aluno.getSelectedItem().toString();
        String mes = tf_mes_aluno.getSelectedItem().toString();
        String ano = tf_ano_aluno.getSelectedItem().toString();        
        String dataNasc = (dia + " / " + mes + " / " + ano );        
        int codCurso = Integer.parseInt(tf_codCurso_aluno.getText());
        
        try{
            Aluno aluno = new Aluno(nome, dataNasc, codCurso);
            aluno.setMatricula(Integer.parseInt(tf_matricula_aluno.getText()));
            dao.AlunoDAO.getInstance().update(aluno);
            System.out.println("Aluno Atualizado");            
            System.out.println(aluno.toString());
            
            
        } catch(Exception ex){
            System.out.println("Erro na atualização do Aluno");
            
        }
    }
    
    private void updateProf(){
        String nome = tf_nome_prof.getText();
        String dia = tf_dia_prof.getSelectedItem().toString();
        String mes = tf_mes_prof.getSelectedItem().toString();
        String ano = tf_ano_prof.getSelectedItem().toString();        
        String dataNasc = (dia + " / " + mes + " / " + ano );
        String espec = tf_espec_prof.getSelectedItem().toString();
        String titulo = tf_titulo_prof.getSelectedItem().toString();
        
        String cep = tf_endereco.getText();
        String endereco = tf_tel.getText();
        String bairro = tf_bairro.getText();
        String cidade = tf_cidade.getText();
        String estado = (String)tf_estado.getSelectedItem();
        String tel = tf_tel.getText();
        String email = tf_email.getText();
        
        try{
            Endereco end_prof = new Endereco(cep, endereco, bairro, cidade, estado, tel, email);
            Professor prof = new Professor (nome, dataNasc, espec, titulo, end_prof);
            prof.setId(Integer.parseInt(tf_id_prof.getText()));
            dao.ProfessorDAO.getInstance().update(prof, end_prof);
            
            System.out.println("Professor Cadastrado");            
            System.out.println(prof.toString());            
            
        } catch(Exception ex){
            System.out.println("Erro na atualização do Professor");
            
        }
    }
    
    private void updateDisci(){
        String nome = tf_nome_disci.getText();
        double cargaHoraria = Double.parseDouble(tf_cargaHoraria_disci.getText());
        int aulasSemanais = Integer.parseInt(tf_aulas_disci.toString());
        int codCurso = Integer.parseInt(tf_codCurso_disci.getText());    
        
        try{
            Disciplina disci = new Disciplina(nome, cargaHoraria, aulasSemanais, codCurso);
            disci.setCodDisci(Integer.parseInt(tf_codigo_disci.getText()));
            dao.DisciplinaDAO.getInstance().update(disci);
            
            System.out.println("Disciplina Cadastrada");            
            System.out.println(disci.toString());            
            
        } catch(Exception ex){
            System.out.println("Erro na atualização da Disciplina");
            
        }
    }
    
    private void updateCurso(){
        String nome = tf_nome_curso.getText();
        int cargaHoraria = Integer.parseInt(tf_cargaHoraria_curso.getText());
        String tipo = tf_tipo_curso.getSelectedItem().toString();
        int codInstituto = Integer.parseInt(tf_codInstituto_curso.getText());    
        
        try{
            Curso curso = new Curso(nome, cargaHoraria, tipo, codInstituto);
            curso.setCodCurso(Integer.parseInt(tf_codigo_curso.getText()));
            dao.CursoDAO.getInstance().update(curso);
            
            System.out.println("Disciplina Cadastrada");            
            System.out.println(curso.toString());            
            
        } catch(Exception ex){
            System.out.println("Erro na atualização da Disciplina");            
        }
    }
    
    private void deleteAluno(){
        dao.AlunoDAO.getInstance().delete((int) 
            tb_busca_aluno.getModel().getValueAt(tb_busca_aluno.getSelectedRow(), 0));
    }
    
    private void deleteProf(){
        dao.ProfessorDAO.getInstance().delete((int) 
            tb_busca_prof.getModel().getValueAt(tb_busca_prof.getSelectedRow(), 0));
    }
    
    private void deleteDisci(){
        dao.DisciplinaDAO.getInstance().delete((int) 
            tb_busca_disci.getModel().getValueAt(tb_busca_disci.getSelectedRow(), 0));
    }
    
    private void deleteCurso(){
        dao.CursoDAO.getInstance().delete((int) 
            tb_busca_curso.getModel().getValueAt(tb_busca_curso.getSelectedRow(), 0));
    }
    
    public void readJTable(){
        alinhaTabela();
        
        if(CLICOU_ALUNO){
            DefaultTableModel modelo_aluno = (DefaultTableModel) tb_busca_aluno.getModel();
            modelo_aluno.setNumRows(0);
            AlunoDAO alunoDAO = new AlunoDAO();
            for(Aluno a: alunoDAO.read()){
                modelo_aluno.addRow(new Object[] {
                    a.getMatricula(),
                    a.getNome(),
                    a.getDataNasc(),
                    a.getNomeCurso()
                });
            }
        }
        
        else if(CLICOU_PROF){
            DefaultTableModel modelo_prof = (DefaultTableModel) tb_busca_prof.getModel();
            modelo_prof.setNumRows(0);
            ProfessorDAO profDAO = new ProfessorDAO();
            for(Professor p: profDAO.read()){
                modelo_prof.addRow(new Object[] {                    
                    p.getId(),
                    p.getNome(),
                    p.getDataNasc(),
                    p.getTitulo(),
                    p.getEspec(),

                });
            }            
        }
        
        else if(CLICOU_DISCI){
            DefaultTableModel modelo_disci = (DefaultTableModel) tb_busca_disci.getModel();
            modelo_disci.setNumRows(0);
            DisciplinaDAO disciDAO = new DisciplinaDAO();
            for(Disciplina d: disciDAO.read()){
                modelo_disci.addRow(new Object[] {                    
                    d.getCodDisci(),
                    d.getNome(),
                    d.getCargaHoraria(),
                    d.getAulasSemanais(),
                    d.getCodCurso(),
                });
            }            
        }
        
        else if(CLICOU_CURSO){
            DefaultTableModel modelo_curso = (DefaultTableModel) tb_busca_curso.getModel();
            modelo_curso.setNumRows(0);
            CursoDAO cursoDAO = new CursoDAO();
            for(Curso c: cursoDAO.read()){
                modelo_curso.addRow(new Object[] {                    
                    c.getCodCurso(),
                    c.getNome(),
                    c.getCargaHoraria(),
                    c.getTipo(),
                    c.getCodInstituto(),
                });
            }            
            
        }
        
    }
    
    private void buscaJTable(){
        alinhaTabela();        
        
        if(CLICOU_ALUNO){
            DefaultTableModel modelo_aluno = (DefaultTableModel) tb_busca_aluno.getModel();
            modelo_aluno.setNumRows(0);
            AlunoDAO alunoDAO = new AlunoDAO();
            String busca = tf_busca.getText();
            for(Aluno b: alunoDAO.busca(busca)){
                modelo_aluno.addRow(new Object[] {
                    b.getMatricula(),
                    b.getNome(),
                    b.getDataNasc(),
                    b.getCodCurso()
                });
            }
        }
        
        else if(CLICOU_PROF){
            DefaultTableModel modelo_prof = (DefaultTableModel) tb_busca_prof.getModel();
            modelo_prof.setNumRows(0);
            ProfessorDAO profDAO = new ProfessorDAO();
            String busca = tf_busca.getText();
            for(Professor p: profDAO.busca(busca)){
                modelo_prof.addRow(new Object[] {
                    p.getId(),
                    p.getNome(),
                    p.getDataNasc(),
                    p.getTitulo(),
                    p.getEspec(),
                });
            }            
        }
        
        else if(CLICOU_DISCI){
            DefaultTableModel modelo_disci = (DefaultTableModel) tb_busca_disci.getModel();
            modelo_disci.setNumRows(0);
            DisciplinaDAO disciDAO = new DisciplinaDAO();
            String busca = tf_busca.getText();
            for(Disciplina d: disciDAO.busca(busca)){
                modelo_disci.addRow(new Object[] {                    
                    d.getCodDisci(),
                    d.getNome(),
                    d.getCargaHoraria(),
                    d.getAulasSemanais(),
                    d.getCodCurso(),
                });
            }
        }
        
        else if(CLICOU_CURSO){
            
        }
        
    }
    
    private void alinhaTabela(){
        TableCellRenderer l1 = new AlinhaTabela();
        TableCellRenderer l2 = new AlinhaTabela();
        TableCellRenderer l3 = new AlinhaTabela();
        TableCellRenderer l4 = new AlinhaTabela();
        TableCellRenderer l5 = new AlinhaTabela();
        
        TableColumn column1a =  tb_busca_aluno.getColumnModel().getColumn(0);
        TableColumn column2a =  tb_busca_aluno.getColumnModel().getColumn(1);
        TableColumn column3a =  tb_busca_aluno.getColumnModel().getColumn(2);
        TableColumn column4a =  tb_busca_aluno.getColumnModel().getColumn(3);
        
        column1a.setCellRenderer(l1);
        column2a.setCellRenderer(l2);
        column3a.setCellRenderer(l3);
        column4a.setCellRenderer(l4);
        
        TableColumn column1p =  tb_busca_prof.getColumnModel().getColumn(0);
        TableColumn column2p =  tb_busca_prof.getColumnModel().getColumn(1);
        TableColumn column3p =  tb_busca_prof.getColumnModel().getColumn(2);
        TableColumn column4p =  tb_busca_prof.getColumnModel().getColumn(3);
        TableColumn column5p =  tb_busca_prof.getColumnModel().getColumn(4);
        
        column1p.setCellRenderer(l1);
        column2p.setCellRenderer(l2);
        column3p.setCellRenderer(l3);
        column4p.setCellRenderer(l4);
        column5p.setCellRenderer(l5);
        
        TableColumn column1d =  tb_busca_disci.getColumnModel().getColumn(0);
        TableColumn column2d =  tb_busca_disci.getColumnModel().getColumn(1);
        TableColumn column3d =  tb_busca_disci.getColumnModel().getColumn(2);
        TableColumn column4d =  tb_busca_disci.getColumnModel().getColumn(3);
        TableColumn column5d =  tb_busca_disci.getColumnModel().getColumn(4);
        
        column1d.setCellRenderer(l1);
        column2d.setCellRenderer(l2);
        column3d.setCellRenderer(l3);
        column4d.setCellRenderer(l4);
        column5d.setCellRenderer(l5);
    }
    
    private void buscaCurso () {
        Curso curso = new Curso ("");
        curso = dao.CursoDAO.getInstance().findByCurso(Integer.parseInt(tf_codCurso_aluno.getText()));
        tf_nomeCurso_aluno.setText(curso.getNome());
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_busca = new javax.swing.JPanel();
        tf_busca = new javax.swing.JTextField();
        btn_cadastrar = new javax.swing.JLabel();
        linha1 = new javax.swing.JLabel();
        lb_bemvindo1 = new javax.swing.JLabel();
        lb_realiza_busca = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pn_tb_disci = new javax.swing.JScrollPane();
        tb_busca_disci = new javax.swing.JTable();
        pn_tb_aluno = new javax.swing.JScrollPane();
        tb_busca_aluno = new javax.swing.JTable();
        pn_tb_prof = new javax.swing.JScrollPane();
        tb_busca_prof = new javax.swing.JTable();
        pn_tb_curso = new javax.swing.JScrollPane();
        tb_busca_curso = new javax.swing.JTable();
        pn_disci = new javax.swing.JPanel();
        tf_nome_disci = new javax.swing.JTextField();
        tf_codigo_disci = new javax.swing.JTextField();
        tf_cargaHoraria_disci = new javax.swing.JTextField();
        tf_aulas_disci = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        tf_codCurso_disci = new javax.swing.JTextField();
        pn_curso = new javax.swing.JPanel();
        tf_nome_curso = new javax.swing.JTextField();
        tf_cargaHoraria_curso = new javax.swing.JTextField();
        tf_codInstituto_curso = new javax.swing.JTextField();
        tf_tipo_curso = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        tf_codigo_curso = new javax.swing.JTextField();
        pn_prof = new javax.swing.JPanel();
        tf_cidade = new javax.swing.JTextField();
        tf_estado = new javax.swing.JComboBox<>();
        tf_email = new javax.swing.JTextField();
        tf_id_prof = new javax.swing.JTextField();
        tf_dia_prof = new javax.swing.JComboBox<>();
        tf_mes_prof = new javax.swing.JComboBox<>();
        tf_ano_prof = new javax.swing.JComboBox<>();
        tf_nome_prof = new javax.swing.JTextField();
        tf_espec_prof = new javax.swing.JComboBox<>();
        tf_titulo_prof = new javax.swing.JComboBox<>();
        tf_endereco = new javax.swing.JTextField();
        tf_bairro = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        pn_foto_professor = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        tf_tel = new javax.swing.JFormattedTextField();
        tf_cep = new javax.swing.JFormattedTextField();
        pn_aluno = new javax.swing.JPanel();
        tf_matricula_aluno = new javax.swing.JTextField();
        tf_dia_aluno = new javax.swing.JComboBox<>();
        tf_mes_aluno = new javax.swing.JComboBox<>();
        tf_ano_aluno = new javax.swing.JComboBox<>();
        tf_nome_aluno = new javax.swing.JTextField();
        tf_codCurso_aluno = new javax.swing.JTextField();
        tf_nomeCurso_aluno = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        pn_foto_aluno = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pn_botao_inf = new javax.swing.JPanel();
        btn_salvar = new javax.swing.JLabel();
        linha3 = new javax.swing.JLabel();
        btn_excluir = new javax.swing.JLabel();
        btn_atualizar = new javax.swing.JLabel();
        pn_botao_sup = new javax.swing.JPanel();
        btn_voltar = new javax.swing.JLabel();
        linha10 = new javax.swing.JLabel();
        lb_bemvindo6 = new javax.swing.JLabel();
        pn_inicio = new javax.swing.JPanel();
        btn_aluno = new javax.swing.JLabel();
        btn_prof = new javax.swing.JLabel();
        btn_disci = new javax.swing.JLabel();
        btn_curso = new javax.swing.JLabel();
        btn_calendario = new javax.swing.JLabel();
        btn_config = new javax.swing.JLabel();
        linha = new javax.swing.JLabel();
        lb_bemvindo = new javax.swing.JLabel();
        pn_menu = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        btn_aluno2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_prof2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_disci2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_curso2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_busca.setBackground(new java.awt.Color(208, 215, 220));

        tf_busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_buscaActionPerformed(evt);
            }
        });
        tf_busca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_buscaKeyReleased(evt);
            }
        });

        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 1.png"))); // NOI18N
        btn_cadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cadastrarMouseClicked(evt);
            }
        });

        linha1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Linha 2.png"))); // NOI18N

        lb_bemvindo1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 26)); // NOI18N
        lb_bemvindo1.setText("Consulta e Gerenciamento");

        lb_realiza_busca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/search_30px.png"))); // NOI18N

        tb_busca_disci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓD. DISCIPLINA", "NOME", "CARGA HORÁRIA", "AULAS SEMANAIS", "CÓD. CURSO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_busca_disci.getTableHeader().setReorderingAllowed(false);
        tb_busca_disci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_busca_disciMouseClicked(evt);
            }
        });
        pn_tb_disci.setViewportView(tb_busca_disci);
        if (tb_busca_disci.getColumnModel().getColumnCount() > 0) {
            tb_busca_disci.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_busca_disci.getColumnModel().getColumn(1).setPreferredWidth(110);
            tb_busca_disci.getColumnModel().getColumn(2).setResizable(false);
            tb_busca_disci.getColumnModel().getColumn(2).setPreferredWidth(50);
            tb_busca_disci.getColumnModel().getColumn(3).setResizable(false);
            tb_busca_disci.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        tb_busca_aluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATRÍCULA", "NOME", "DATA NASCIMENTO", "CURSO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_busca_aluno.getTableHeader().setReorderingAllowed(false);
        tb_busca_aluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_busca_alunoMouseClicked(evt);
            }
        });
        pn_tb_aluno.setViewportView(tb_busca_aluno);
        if (tb_busca_aluno.getColumnModel().getColumnCount() > 0) {
            tb_busca_aluno.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_busca_aluno.getColumnModel().getColumn(1).setPreferredWidth(110);
            tb_busca_aluno.getColumnModel().getColumn(2).setResizable(false);
            tb_busca_aluno.getColumnModel().getColumn(2).setPreferredWidth(50);
            tb_busca_aluno.getColumnModel().getColumn(3).setResizable(false);
            tb_busca_aluno.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        tb_busca_prof.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "DATA NASC", "TITULO", "ESPECIALIDADE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_busca_prof.getTableHeader().setReorderingAllowed(false);
        tb_busca_prof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_busca_profMouseClicked(evt);
            }
        });
        pn_tb_prof.setViewportView(tb_busca_prof);
        if (tb_busca_prof.getColumnModel().getColumnCount() > 0) {
            tb_busca_prof.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_busca_prof.getColumnModel().getColumn(1).setPreferredWidth(110);
            tb_busca_prof.getColumnModel().getColumn(2).setResizable(false);
            tb_busca_prof.getColumnModel().getColumn(2).setPreferredWidth(50);
            tb_busca_prof.getColumnModel().getColumn(3).setResizable(false);
            tb_busca_prof.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        pn_tb_curso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_tb_cursoMouseClicked(evt);
            }
        });

        tb_busca_curso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓD. CURSO", "NOME", "CARGA HORÁRIA", "TIPO", "CÓD. INSTITUTO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_busca_curso.getTableHeader().setReorderingAllowed(false);
        tb_busca_curso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_busca_cursoMouseClicked(evt);
            }
        });
        pn_tb_curso.setViewportView(tb_busca_curso);
        if (tb_busca_curso.getColumnModel().getColumnCount() > 0) {
            tb_busca_curso.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_busca_curso.getColumnModel().getColumn(1).setPreferredWidth(110);
            tb_busca_curso.getColumnModel().getColumn(2).setResizable(false);
            tb_busca_curso.getColumnModel().getColumn(2).setPreferredWidth(50);
            tb_busca_curso.getColumnModel().getColumn(3).setResizable(false);
            tb_busca_curso.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_tb_aluno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pn_tb_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pn_tb_disci, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pn_tb_curso, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_tb_aluno, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pn_tb_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pn_tb_disci, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pn_tb_curso, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_buscaLayout = new javax.swing.GroupLayout(pn_busca);
        pn_busca.setLayout(pn_buscaLayout);
        pn_buscaLayout.setHorizontalGroup(
            pn_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_buscaLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pn_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_bemvindo1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_buscaLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(pn_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_buscaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lb_realiza_busca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_buscaLayout.createSequentialGroup()
                        .addComponent(btn_cadastrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(84, 84, 84))
        );
        pn_buscaLayout.setVerticalGroup(
            pn_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_buscaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lb_bemvindo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(pn_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_realiza_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(btn_cadastrar)
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );

        getContentPane().add(pn_busca, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 770, 800));

        pn_disci.setBackground(new java.awt.Color(208, 215, 220));

        tf_nome_disci.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_nome_disci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nome_disciActionPerformed(evt);
            }
        });

        tf_codigo_disci.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_codigo_disci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_codigo_disciActionPerformed(evt);
            }
        });

        tf_cargaHoraria_disci.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_cargaHoraria_disci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cargaHoraria_disciActionPerformed(evt);
            }
        });

        tf_aulas_disci.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_aulas_disci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_aulas_disciActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel50.setText("DISCIPLINA");

        jLabel52.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel52.setText("CÓDIGO");

        jLabel57.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel57.setText("CARGA HORÁRIA");

        jLabel58.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel58.setText("AULAS SEMANAIS");

        jLabel59.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel59.setText("CÓDIGO CURSO");

        tf_codCurso_disci.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_codCurso_disci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_codCurso_disciActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_disciLayout = new javax.swing.GroupLayout(pn_disci);
        pn_disci.setLayout(pn_disciLayout);
        pn_disciLayout.setHorizontalGroup(
            pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_disciLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_disciLayout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_disciLayout.createSequentialGroup()
                        .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_disciLayout.createSequentialGroup()
                                .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(tf_cargaHoraria_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_aulas_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_disciLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tf_nome_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)))
                .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(tf_codigo_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_codCurso_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addGap(81, 81, 81))
        );
        pn_disciLayout.setVerticalGroup(
            pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_disciLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_nome_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_codigo_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_disciLayout.createSequentialGroup()
                        .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_disciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_cargaHoraria_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_aulas_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_disciLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addGap(41, 41, 41))
                    .addComponent(tf_codCurso_disci, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(634, 634, 634))
        );

        getContentPane().add(pn_disci, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 770, 600));

        pn_curso.setBackground(new java.awt.Color(208, 215, 220));

        tf_nome_curso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_nome_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nome_cursoActionPerformed(evt);
            }
        });

        tf_cargaHoraria_curso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_cargaHoraria_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cargaHoraria_cursoActionPerformed(evt);
            }
        });

        tf_codInstituto_curso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_codInstituto_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_codInstituto_cursoActionPerformed(evt);
            }
        });

        tf_tipo_curso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_tipo_curso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Bacharel", "Mestrado", "Doutorado" }));

        jLabel36.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel36.setText("CÓDIGO");

        jLabel38.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel38.setText("TIPO");

        jLabel40.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel40.setText("CURSO");

        jLabel64.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel64.setText("CARGA HORÁRIA");

        jLabel65.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel65.setText("CÓDIGO INSTITUTO");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        jLabel66.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel66.setText("DISCIPLINAS");

        tf_codigo_curso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_codigo_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_codigo_cursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_cursoLayout = new javax.swing.GroupLayout(pn_curso);
        pn_curso.setLayout(pn_cursoLayout);
        pn_cursoLayout.setHorizontalGroup(
            pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_cursoLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_cursoLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addGap(208, 208, 208))
                    .addGroup(pn_cursoLayout.createSequentialGroup()
                        .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pn_cursoLayout.createSequentialGroup()
                                    .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel38)
                                        .addComponent(tf_tipo_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(20, 20, 20)
                                    .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel64)
                                        .addComponent(tf_cargaHoraria_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tf_codInstituto_curso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel66)
                            .addGroup(pn_cursoLayout.createSequentialGroup()
                                .addComponent(tf_nome_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_codigo_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 81, Short.MAX_VALUE))))
        );
        pn_cursoLayout.setVerticalGroup(
            pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_cursoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_cursoLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(59, 59, 59))
                    .addGroup(pn_cursoLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_nome_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_codigo_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel65)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_cursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_codInstituto_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_tipo_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_cargaHoraria_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244))
        );

        getContentPane().add(pn_curso, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 770, 600));

        pn_prof.setBackground(new java.awt.Color(208, 215, 220));

        tf_cidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cidadeActionPerformed(evt);
            }
        });

        tf_estado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        tf_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_emailActionPerformed(evt);
            }
        });

        tf_id_prof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_id_prof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_id_profActionPerformed(evt);
            }
        });

        tf_dia_prof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_dia_prof.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        tf_mes_prof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_mes_prof.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        tf_ano_prof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_ano_prof.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));

        tf_nome_prof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_nome_prof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nome_profActionPerformed(evt);
            }
        });

        tf_espec_prof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_espec_prof.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "INFORMÁTICA", "MATEMÁTICA", "MEDICINA" }));

        tf_titulo_prof.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_titulo_prof.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "BACHAREL", "ESPECIALISTA", "MESTRADO", "DOUTORADO" }));

        tf_endereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_endereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_enderecoActionPerformed(evt);
            }
        });

        tf_bairro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_bairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_bairroActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        pn_foto_professor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_foto_professorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_foto_professor, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_foto_professor, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel22.setText("IDENTIFICAÇÃO");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel23.setText("DATA NASCIMENTO");

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel24.setText("NOME");

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel25.setText("ESPECIALIDADE");

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel26.setText("TÍTULO");

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel27.setText("ENDEREÇO");

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel30.setText("CEP");

        jLabel31.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel31.setText("BAIRRO");

        jLabel32.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel32.setText("CIDADE");

        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel33.setText("ESTADO");

        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel34.setText("TELEFONE");

        jLabel35.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel35.setText("EMAIL");

        javax.swing.GroupLayout pn_profLayout = new javax.swing.GroupLayout(pn_prof);
        pn_prof.setLayout(pn_profLayout);
        pn_profLayout.setHorizontalGroup(
            pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_profLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_profLayout.createSequentialGroup()
                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_profLayout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_nome_prof)
                                    .addComponent(jLabel24)
                                    .addGroup(pn_profLayout.createSequentialGroup()
                                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(tf_id_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pn_profLayout.createSequentialGroup()
                                                .addComponent(tf_dia_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_mes_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_ano_prof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel23)))))
                            .addGroup(pn_profLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(tf_titulo_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_espec_prof, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_profLayout.createSequentialGroup()
                        .addComponent(tf_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_profLayout.createSequentialGroup()
                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_profLayout.createSequentialGroup()
                                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel31)
                                    .addComponent(tf_bairro)
                                    .addComponent(jLabel34)
                                    .addComponent(tf_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_profLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pn_profLayout.createSequentialGroup()
                                                .addComponent(tf_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tf_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel35)
                                                .addComponent(tf_email, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_profLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_profLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(259, 259, 259)
                                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(tf_cep, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pn_profLayout.setVerticalGroup(
            pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_profLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_profLayout.createSequentialGroup()
                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_dia_prof)
                            .addComponent(tf_mes_prof)
                            .addComponent(tf_ano_prof)
                            .addComponent(tf_id_prof, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_nome_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_espec_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_titulo_prof, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_endereco)
                    .addComponent(tf_cep, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_profLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_profLayout.createSequentialGroup()
                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_profLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_email)
                    .addComponent(tf_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(318, 318, 318))
        );

        getContentPane().add(pn_prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 770, 600));

        pn_aluno.setBackground(new java.awt.Color(208, 215, 220));

        tf_matricula_aluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_matricula_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_matricula_alunoActionPerformed(evt);
            }
        });

        tf_dia_aluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_dia_aluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        tf_mes_aluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_mes_aluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        tf_ano_aluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_ano_aluno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));

        tf_nome_aluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_nome_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nome_alunoActionPerformed(evt);
            }
        });

        tf_codCurso_aluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_codCurso_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_codCurso_alunoActionPerformed(evt);
            }
        });
        tf_codCurso_aluno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_codCurso_alunoKeyTyped(evt);
            }
        });

        tf_nomeCurso_aluno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_nomeCurso_aluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_nomeCurso_alunoFocusGained(evt);
            }
        });
        tf_nomeCurso_aluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nomeCurso_alunoActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        pn_foto_aluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_foto_alunoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_foto_aluno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_foto_aluno, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel13.setText("MATRÍCULA");

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel14.setText("DATA NASCIMENTO");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel15.setText("NOME");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel16.setText("CÓDIGO DO CURSO");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel17.setText("NOME DO CURSO");

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel18.setText("DISCIPLINAS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pn_alunoLayout = new javax.swing.GroupLayout(pn_aluno);
        pn_aluno.setLayout(pn_alunoLayout);
        pn_alunoLayout.setHorizontalGroup(
            pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_alunoLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pn_alunoLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_nome_aluno)
                            .addComponent(jLabel15)
                            .addGroup(pn_alunoLayout.createSequentialGroup()
                                .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(tf_matricula_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_alunoLayout.createSequentialGroup()
                                        .addComponent(tf_dia_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_mes_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_ano_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14)))))
                    .addComponent(jLabel18)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_alunoLayout.createSequentialGroup()
                        .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(tf_codCurso_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_nomeCurso_aluno)
                            .addComponent(jLabel17))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pn_alunoLayout.setVerticalGroup(
            pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_alunoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_alunoLayout.createSequentialGroup()
                        .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_dia_aluno)
                            .addComponent(tf_mes_aluno)
                            .addComponent(tf_ano_aluno)
                            .addComponent(tf_matricula_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_nome_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_alunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_codCurso_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nomeCurso_aluno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
        );

        getContentPane().add(pn_aluno, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 770, 600));

        pn_botao_inf.setBackground(new java.awt.Color(208, 215, 220));

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 23_1.png"))); // NOI18N
        btn_salvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salvarMouseClicked(evt);
            }
        });

        linha3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Linha 2.png"))); // NOI18N

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 22.png"))); // NOI18N
        btn_excluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_excluirMouseClicked(evt);
            }
        });

        btn_atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 25.png"))); // NOI18N
        btn_atualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_atualizarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_botao_infLayout = new javax.swing.GroupLayout(pn_botao_inf);
        pn_botao_inf.setLayout(pn_botao_infLayout);
        pn_botao_infLayout.setHorizontalGroup(
            pn_botao_infLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_botao_infLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(pn_botao_infLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(linha3)
                    .addGroup(pn_botao_infLayout.createSequentialGroup()
                        .addComponent(btn_excluir)
                        .addGap(18, 18, 18)
                        .addComponent(btn_atualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salvar)))
                .addGap(84, 84, 84))
        );
        pn_botao_infLayout.setVerticalGroup(
            pn_botao_infLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_botao_infLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(linha3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(pn_botao_infLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_salvar)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        getContentPane().add(pn_botao_inf, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 700, 770, 100));

        pn_botao_sup.setBackground(new java.awt.Color(208, 215, 220));

        btn_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 9.png"))); // NOI18N
        btn_voltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_voltarMouseClicked(evt);
            }
        });

        linha10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Linha 2.png"))); // NOI18N

        lb_bemvindo6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 26)); // NOI18N
        lb_bemvindo6.setText("Consulta e Gerenciamento");

        javax.swing.GroupLayout pn_botao_supLayout = new javax.swing.GroupLayout(pn_botao_sup);
        pn_botao_sup.setLayout(pn_botao_supLayout);
        pn_botao_supLayout.setHorizontalGroup(
            pn_botao_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_botao_supLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pn_botao_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_botao_supLayout.createSequentialGroup()
                        .addComponent(lb_bemvindo6, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_voltar)
                        .addGap(83, 83, 83))
                    .addGroup(pn_botao_supLayout.createSequentialGroup()
                        .addComponent(linha10, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(81, Short.MAX_VALUE))))
        );
        pn_botao_supLayout.setVerticalGroup(
            pn_botao_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_botao_supLayout.createSequentialGroup()
                .addGroup(pn_botao_supLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_botao_supLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lb_bemvindo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_botao_supLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_voltar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(839, 839, 839))
        );

        getContentPane().add(pn_botao_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 770, 100));

        pn_inicio.setBackground(new java.awt.Color(208, 215, 220));

        btn_aluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 12.png"))); // NOI18N
        btn_aluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_alunoMouseClicked(evt);
            }
        });

        btn_prof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 17.png"))); // NOI18N
        btn_prof.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_profMouseClicked(evt);
            }
        });

        btn_disci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 16.png"))); // NOI18N
        btn_disci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_disciMouseClicked(evt);
            }
        });

        btn_curso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 13.png"))); // NOI18N
        btn_curso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cursoMouseClicked(evt);
            }
        });

        btn_calendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 14.png"))); // NOI18N

        btn_config.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Grupo 15.png"))); // NOI18N

        linha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Linha 2.png"))); // NOI18N

        lb_bemvindo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 26)); // NOI18N
        lb_bemvindo.setText("Bem Vindo(a) ao Sistema UNIB");

        javax.swing.GroupLayout pn_inicioLayout = new javax.swing.GroupLayout(pn_inicio);
        pn_inicio.setLayout(pn_inicioLayout);
        pn_inicioLayout.setHorizontalGroup(
            pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_inicioLayout.createSequentialGroup()
                .addGroup(pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_inicioLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lb_bemvindo))
                    .addGroup(pn_inicioLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_curso)
                            .addComponent(btn_aluno))
                        .addGap(41, 41, 41)
                        .addGroup(pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_prof, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_calendario, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(37, 37, 37)
                        .addGroup(pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_disci)
                            .addComponent(btn_config)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_inicioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pn_inicioLayout.setVerticalGroup(
            pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_inicioLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb_bemvindo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linha, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_aluno)
                    .addComponent(btn_disci)
                    .addComponent(btn_prof))
                .addGap(40, 40, 40)
                .addGroup(pn_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_calendario)
                    .addComponent(btn_curso)
                    .addComponent(btn_config))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        getContentPane().add(pn_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 770, 800));

        pn_menu.setBackground(new java.awt.Color(65, 119, 155));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoMouseClicked(evt);
            }
        });

        btn_aluno2.setBackground(new java.awt.Color(65, 119, 155));
        btn_aluno2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_aluno2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_aluno2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_aluno2MouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ALUNOS");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/student_male_30px.png"))); // NOI18N

        javax.swing.GroupLayout btn_aluno2Layout = new javax.swing.GroupLayout(btn_aluno2);
        btn_aluno2.setLayout(btn_aluno2Layout);
        btn_aluno2Layout.setHorizontalGroup(
            btn_aluno2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_aluno2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        btn_aluno2Layout.setVerticalGroup(
            btn_aluno2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_aluno2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addContainerGap())
        );

        btn_prof2.setBackground(new java.awt.Color(65, 119, 155));
        btn_prof2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_prof2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_prof2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_prof2MouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PROFESSORES");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/teacher_30px.png"))); // NOI18N

        javax.swing.GroupLayout btn_prof2Layout = new javax.swing.GroupLayout(btn_prof2);
        btn_prof2.setLayout(btn_prof2Layout);
        btn_prof2Layout.setHorizontalGroup(
            btn_prof2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_prof2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        btn_prof2Layout.setVerticalGroup(
            btn_prof2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addGroup(btn_prof2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_disci2.setBackground(new java.awt.Color(65, 119, 155));
        btn_disci2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_disci2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_disci2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_disci2MouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DISCIPLINAS");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/book_30px.png"))); // NOI18N

        javax.swing.GroupLayout btn_disci2Layout = new javax.swing.GroupLayout(btn_disci2);
        btn_disci2.setLayout(btn_disci2Layout);
        btn_disci2Layout.setHorizontalGroup(
            btn_disci2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_disci2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        btn_disci2Layout.setVerticalGroup(
            btn_disci2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addGroup(btn_disci2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_curso2.setBackground(new java.awt.Color(65, 119, 155));
        btn_curso2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_curso2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_curso2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_curso2MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CURSOS");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/diploma_30px.png"))); // NOI18N

        javax.swing.GroupLayout btn_curso2Layout = new javax.swing.GroupLayout(btn_curso2);
        btn_curso2.setLayout(btn_curso2Layout);
        btn_curso2Layout.setHorizontalGroup(
            btn_curso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_curso2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        btn_curso2Layout.setVerticalGroup(
            btn_curso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addGroup(btn_curso2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Consulta e Gerenciamento");

        javax.swing.GroupLayout pn_menuLayout = new javax.swing.GroupLayout(pn_menu);
        pn_menu.setLayout(pn_menuLayout);
        pn_menuLayout.setHorizontalGroup(
            pn_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_aluno2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_prof2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_disci2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_curso2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_menuLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(logo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        pn_menuLayout.setVerticalGroup(
            pn_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_menuLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(logo)
                .addGap(24, 24, 24)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_aluno2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_prof2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_disci2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_curso2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
        );

        getContentPane().add(pn_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents
      
    private void btn_aluno2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aluno2MouseEntered
        btn_aluno2.setBackground(azulE);  
    }//GEN-LAST:event_btn_aluno2MouseEntered

    private void btn_aluno2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aluno2MouseClicked
        identificaPanel("aluno");
        trocaCor(btn_aluno2);
        trocaPanel(pn_busca);
    }//GEN-LAST:event_btn_aluno2MouseClicked

    private void btn_aluno2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aluno2MouseExited
        if (!CLICOU_ALUNO) btn_aluno2.setBackground(azulM);
    }//GEN-LAST:event_btn_aluno2MouseExited

    private void btn_prof2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prof2MouseEntered
        btn_prof2.setBackground(azulE);
    }//GEN-LAST:event_btn_prof2MouseEntered

    private void btn_prof2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prof2MouseClicked
        identificaPanel("prof");
        trocaCor(btn_prof2);
        trocaPanel(pn_busca);
    }//GEN-LAST:event_btn_prof2MouseClicked

    private void btn_prof2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prof2MouseExited
        if (!CLICOU_PROF) btn_prof2.setBackground(azulM);
    }//GEN-LAST:event_btn_prof2MouseExited
        
    private void btn_disci2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_disci2MouseClicked
        identificaPanel("disci");
        trocaCor(btn_disci2);
        trocaPanel(pn_busca);
    }//GEN-LAST:event_btn_disci2MouseClicked

    private void btn_disci2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_disci2MouseEntered
        btn_disci2.setBackground(azulE);
    }//GEN-LAST:event_btn_disci2MouseEntered

    private void btn_disci2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_disci2MouseExited
        if (!CLICOU_DISCI) btn_disci2.setBackground(azulM);
    }//GEN-LAST:event_btn_disci2MouseExited

    private void btn_curso2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_curso2MouseClicked
        identificaPanel("curso");
        trocaCor(btn_curso2);
        trocaPanel(pn_busca);
    }//GEN-LAST:event_btn_curso2MouseClicked

    private void btn_curso2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_curso2MouseEntered
        btn_curso2.setBackground(azulE);
    }//GEN-LAST:event_btn_curso2MouseEntered

    private void btn_curso2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_curso2MouseExited
        if (!CLICOU_CURSO) btn_curso2.setBackground(azulM);
    }//GEN-LAST:event_btn_curso2MouseExited

    private void logoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoMouseClicked
        identificaPanel("inicio");
        trocaCor(null);
        trocaPanel(pn_inicio);
    }//GEN-LAST:event_logoMouseClicked

    private void tf_matricula_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_matricula_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_matricula_alunoActionPerformed

    private void tf_nome_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nome_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nome_alunoActionPerformed

    private void tf_codCurso_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_codCurso_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_codCurso_alunoActionPerformed

    private void tf_nomeCurso_alunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nomeCurso_alunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nomeCurso_alunoActionPerformed

    private void tf_id_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_id_profActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_id_profActionPerformed

    private void tf_nome_profActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nome_profActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nome_profActionPerformed

    private void tf_enderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_enderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_enderecoActionPerformed

    private void tf_bairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_bairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_bairroActionPerformed

    private void tf_cidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cidadeActionPerformed

    private void tf_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_emailActionPerformed

    private void btn_cadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrarMouseClicked
        acionaBotao("cadastrar");
    }//GEN-LAST:event_btn_cadastrarMouseClicked

    private void btn_alunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_alunoMouseClicked
        btn_aluno2MouseClicked(evt);
    }//GEN-LAST:event_btn_alunoMouseClicked

    private void btn_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_profMouseClicked
        btn_prof2MouseClicked(evt);
    }//GEN-LAST:event_btn_profMouseClicked

    private void btn_disciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_disciMouseClicked
        btn_disci2MouseClicked(evt);
    }//GEN-LAST:event_btn_disciMouseClicked

    private void btn_cursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cursoMouseClicked
        btn_curso2MouseClicked(evt);
    }//GEN-LAST:event_btn_cursoMouseClicked

    private void tf_cargaHoraria_disciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cargaHoraria_disciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cargaHoraria_disciActionPerformed

    private void tf_aulas_disciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_aulas_disciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_aulas_disciActionPerformed

    private void tf_nome_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nome_cursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nome_cursoActionPerformed

    private void tf_cargaHoraria_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cargaHoraria_cursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cargaHoraria_cursoActionPerformed

    private void tf_codInstituto_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_codInstituto_cursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_codInstituto_cursoActionPerformed

    private void tf_codigo_disciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_codigo_disciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_codigo_disciActionPerformed

    private void tf_nome_disciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nome_disciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nome_disciActionPerformed

    private void tf_codCurso_disciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_codCurso_disciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_codCurso_disciActionPerformed

    private void tf_codigo_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_codigo_cursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_codigo_cursoActionPerformed

    private void tf_buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_buscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_buscaActionPerformed

    private void btn_salvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salvarMouseClicked
        acionaBotao("salvar");
    }//GEN-LAST:event_btn_salvarMouseClicked

    private void btn_voltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_voltarMouseClicked
        trocaPanel(pn_busca);
    }//GEN-LAST:event_btn_voltarMouseClicked

    private void pn_foto_alunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_foto_alunoMouseClicked
        addFoto();
    }//GEN-LAST:event_pn_foto_alunoMouseClicked

    private void pn_foto_professorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_foto_professorMouseClicked
        addFoto();
    }//GEN-LAST:event_pn_foto_professorMouseClicked

    private void tf_buscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_buscaKeyReleased
        String busca = tf_busca.getText();
        if (busca.equals("")) {
            readJTable();
        }else {
            buscaJTable();
        }
    }//GEN-LAST:event_tf_buscaKeyReleased

    private void tb_busca_alunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_busca_alunoMouseClicked
        acionaBotao("tabela");
    }//GEN-LAST:event_tb_busca_alunoMouseClicked

    private void btn_excluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseClicked
        acionaBotao("excluir");
    }//GEN-LAST:event_btn_excluirMouseClicked

    private void btn_atualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_atualizarMouseClicked
        acionaBotao("atualizar");
    }//GEN-LAST:event_btn_atualizarMouseClicked

    private void tf_nomeCurso_alunoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_nomeCurso_alunoFocusGained
        
    }//GEN-LAST:event_tf_nomeCurso_alunoFocusGained

    private void tb_busca_profMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_busca_profMouseClicked
        acionaBotao("tabela");
    }//GEN-LAST:event_tb_busca_profMouseClicked

    private void tb_busca_disciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_busca_disciMouseClicked
        acionaBotao("tabela");
    }//GEN-LAST:event_tb_busca_disciMouseClicked

    private void tf_codCurso_alunoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_codCurso_alunoKeyTyped
        buscaCurso();
    }//GEN-LAST:event_tf_codCurso_alunoKeyTyped

    private void tb_busca_cursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_busca_cursoMouseClicked
        acionaBotao("tabela");
    }//GEN-LAST:event_tb_busca_cursoMouseClicked

    private void pn_tb_cursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_tb_cursoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pn_tb_cursoMouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_aluno;
    private javax.swing.JPanel btn_aluno2;
    private javax.swing.JLabel btn_atualizar;
    private javax.swing.JLabel btn_cadastrar;
    private javax.swing.JLabel btn_calendario;
    private javax.swing.JLabel btn_config;
    private javax.swing.JLabel btn_curso;
    private javax.swing.JPanel btn_curso2;
    private javax.swing.JLabel btn_disci;
    private javax.swing.JPanel btn_disci2;
    private javax.swing.JLabel btn_excluir;
    private javax.swing.JLabel btn_prof;
    private javax.swing.JPanel btn_prof2;
    private javax.swing.JLabel btn_salvar;
    private javax.swing.JLabel btn_voltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lb_bemvindo;
    private javax.swing.JLabel lb_bemvindo1;
    private javax.swing.JLabel lb_bemvindo6;
    private javax.swing.JLabel lb_realiza_busca;
    private javax.swing.JLabel linha;
    private javax.swing.JLabel linha1;
    private javax.swing.JLabel linha10;
    private javax.swing.JLabel linha3;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pn_aluno;
    private javax.swing.JPanel pn_botao_inf;
    private javax.swing.JPanel pn_botao_sup;
    private javax.swing.JPanel pn_busca;
    private javax.swing.JPanel pn_curso;
    private javax.swing.JPanel pn_disci;
    private javax.swing.JLabel pn_foto_aluno;
    private javax.swing.JLabel pn_foto_professor;
    private javax.swing.JPanel pn_inicio;
    private javax.swing.JPanel pn_menu;
    private javax.swing.JPanel pn_prof;
    private javax.swing.JScrollPane pn_tb_aluno;
    private javax.swing.JScrollPane pn_tb_curso;
    private javax.swing.JScrollPane pn_tb_disci;
    private javax.swing.JScrollPane pn_tb_prof;
    private javax.swing.JTable tb_busca_aluno;
    private javax.swing.JTable tb_busca_curso;
    private javax.swing.JTable tb_busca_disci;
    private javax.swing.JTable tb_busca_prof;
    private javax.swing.JComboBox<String> tf_ano_aluno;
    private javax.swing.JComboBox<String> tf_ano_prof;
    private javax.swing.JTextField tf_aulas_disci;
    private javax.swing.JTextField tf_bairro;
    private javax.swing.JTextField tf_busca;
    private javax.swing.JTextField tf_cargaHoraria_curso;
    private javax.swing.JTextField tf_cargaHoraria_disci;
    private javax.swing.JFormattedTextField tf_cep;
    private javax.swing.JTextField tf_cidade;
    private javax.swing.JTextField tf_codCurso_aluno;
    private javax.swing.JTextField tf_codCurso_disci;
    private javax.swing.JTextField tf_codInstituto_curso;
    private javax.swing.JTextField tf_codigo_curso;
    private javax.swing.JTextField tf_codigo_disci;
    private javax.swing.JComboBox<String> tf_dia_aluno;
    private javax.swing.JComboBox<String> tf_dia_prof;
    private javax.swing.JTextField tf_email;
    private javax.swing.JTextField tf_endereco;
    private javax.swing.JComboBox<String> tf_espec_prof;
    private javax.swing.JComboBox<String> tf_estado;
    private javax.swing.JTextField tf_id_prof;
    private javax.swing.JTextField tf_matricula_aluno;
    private javax.swing.JComboBox<String> tf_mes_aluno;
    private javax.swing.JComboBox<String> tf_mes_prof;
    private javax.swing.JTextField tf_nomeCurso_aluno;
    private javax.swing.JTextField tf_nome_aluno;
    private javax.swing.JTextField tf_nome_curso;
    private javax.swing.JTextField tf_nome_disci;
    private javax.swing.JTextField tf_nome_prof;
    private javax.swing.JFormattedTextField tf_tel;
    private javax.swing.JComboBox<String> tf_tipo_curso;
    private javax.swing.JComboBox<String> tf_titulo_prof;
    // End of variables declaration//GEN-END:variables

    private Object getSelectedItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
