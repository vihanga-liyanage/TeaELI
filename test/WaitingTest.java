/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vihanga Liyanage
 */
public class WaitingTest {
    
    public static void main(String[] args) throws InterruptedException {
        WaitingScreen w = new WaitingScreen();
        System.out.println("Start");
        
        for (int i = 0; i < 10; i++) {
            w.setVisible(true);
            Thread.sleep(500);
            w.setVisible(false);
            System.out.println(i);
            Thread.sleep(500);
        }
        
        System.out.println("End");
    }
}
