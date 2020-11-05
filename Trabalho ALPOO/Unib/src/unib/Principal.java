package unib;

import util.Configura;
import view.TelaInicio;

public class Principal {

    public static void main(String[] args) {
        Configura.LookAndFeel("Windows");
        TelaInicio ti = new TelaInicio();
        ti.setVisible(true);
        
    }
    
}
