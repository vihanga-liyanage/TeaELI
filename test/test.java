
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import teaeli.CreateNewBlendOrder1;

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
        DateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy");
        Date today = new Date();
        System.out.println(formatter.format(today));

    }
}
