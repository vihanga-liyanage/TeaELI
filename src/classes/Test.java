/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author Vihanga Liyanage
 */
public class Test {
    
    public static void main(String[] args) {
        DBConnection1 db = new DBConnection1();
        String query = "SELECT * FROM blend";
        ResultArray res = db.getResultArray(query);
        System.out.println(res.size());
    }
}
