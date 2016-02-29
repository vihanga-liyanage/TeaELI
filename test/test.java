

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
    public static void main(String[] args) {
        String num = "1568718017898754034";
        int i = num.length();
        while (i > 3) {
            String part1 = num.substring(0, i-3);
            String part2 = num.substring(i-3);
            num = part1 + "," + part2;
            i-=3;
            System.out.println(num);
        }
    }
}
