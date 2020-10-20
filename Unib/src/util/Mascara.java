package util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;


public class Mascara {
        
    public static DefaultFormatterFactory getTelMask(){
        MaskFormatter mask = null;
        try{
            mask = new MaskFormatter("(##) ####-####");
            mask.setPlaceholderCharacter('_');
        }catch (ParseException ex){
            return null;
        }
        return(new DefaultFormatterFactory (mask, mask));
    }
    
        
    public static DefaultFormatterFactory getDataNascMask(){
        MaskFormatter mask = null;
        try{
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
        }catch (ParseException ex){
            return null;
        }
        return(new DefaultFormatterFactory (mask, mask));
    }
    
}
