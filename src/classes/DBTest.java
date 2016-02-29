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
public class DBTest {
    
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        String query = "SELECT * FROM blend";
        ResultArray res = db.getResultArray(query);
        while (res.next()){
            int i = 0;
            while (res.getString(i) != null){
                System.out.print(res.getString(i++) + " ");
            }
            System.out.println("");
        }
    }
}
