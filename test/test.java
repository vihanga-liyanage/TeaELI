
import java.math.BigDecimal;
import java.math.RoundingMode;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vihanga Liyanage
 */
public class test {
    public static double round(float num, int places){
        if (places < 0)
            throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(num);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
    public static void main(String[] args) {
        
        System.out.println(round(1234.00003f, 2));
    }
    
    
}
