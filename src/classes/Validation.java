package classes;

public class Validation {
    
    public boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){
            if (str.matches("[[0-9]{1,2}+,]*")) {
                return true;
            } else {
                return false;
            }
        } 
    }
    
    public boolean isFloat(String str){
        try{
            Float.parseFloat(str);
            return true;
        }catch(NumberFormatException e){
            if (str.matches("[[0-9]{1,2}+,]*.[0-9]*")) {
                return true;
            } else {
                return false;
            }
        } 
    }
}
