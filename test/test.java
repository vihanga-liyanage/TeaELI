

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
    //formatting numbers to add commas
    private static String formatNum(String num){
        String decimal=num, point = null;
        if(num.contains(".")){
            String[] temp = num.split("\\.");
            decimal = temp[0];
            point = temp[1];
        }
        int i = decimal.length();
        while (i > 3) {
            String part1 = decimal.substring(0, i-3);
            String part2 = decimal.substring(i-3);
            decimal = part1 + "," + part2;
            i-=3;
        }
        if (point != null){
            decimal += "." + point;
        }
        return decimal;
    }
    private static String formatNum(int num){
        return formatNum(String.valueOf(num));
    }
    private static String formatNum(float num){
        return formatNum(Float.toString(num));
    }
    
    //overiding Integer.parseInt() to accept nums with commas
    private static int parseInt(String num){
        try{
            return Integer.parseInt(num);
        } catch (NumberFormatException e){
            if (num.matches("[[0-9]{1,2}+,]*")) {
                num = num.replace(",", "");
                return Integer.parseInt(num);
            }
        }
        return 0;
    }
    
    //overiding Float.parseFloat() to accept nums with commas
    private static float parseFloat(String num){
        try{
            return Float.parseFloat(num);
        } catch (NumberFormatException e){
            if (num.matches("[[0-9]{1,2}+,]*.[0-9]*")) {
                num = num.replace(",", "");
                return Float.parseFloat(num);
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        String nums = "123,123,123";
        //float numf = parseFloat(nums);
        int numi = parseInt(nums);
        //System.out.println(numf);
        System.out.println(numi);
        System.out.println(formatNum("12334538445345.347885"));
    }
    
    
}
