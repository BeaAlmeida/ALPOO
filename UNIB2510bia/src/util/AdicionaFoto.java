
package util;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import view.TelaInicio;

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
