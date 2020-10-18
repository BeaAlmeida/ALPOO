/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import view.TelaInicio;

/**
 *
 * @author Maicoln
 */
public class AdicionaFoto {
    public static ImageIcon addFoto(){
        ImageIcon imagem = null;
            JFileChooser arquivo = new JFileChooser();
            TelaInicio obj = new TelaInicio();
            arquivo.setDialogTitle("Selecione uma foto");
            arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
            int opc = arquivo.showOpenDialog(obj);
            if(opc == JFileChooser.APPROVE_OPTION){
                File file = new File("Caminho");
                file = arquivo.getSelectedFile();
            
                imagem = new ImageIcon(arquivo.getSelectedFile().getPath());
            }
        return imagem;
    }
}
