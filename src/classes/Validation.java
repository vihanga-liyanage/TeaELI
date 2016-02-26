
package classes;


public class Validation {
    
    public boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
        
    }
}
