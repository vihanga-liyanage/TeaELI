
import java.io.IOException;



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
    public static void main(String[] args) throws IOException {
        String str = "2_37,790.188776";
        if (str.matches("[[,]*[0-9]{1,3}]*.[0-9]*")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
   
}
