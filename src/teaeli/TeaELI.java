
package teaeli;

public class TeaELI {

    public static void main(String[] args) {
        LoginFrame lf = new LoginFrame();
        //lf.setVisible(true);
        AdminPannel adminPannel = new AdminPannel();
        
        //Keep the window fullscreen
        adminPannel.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        adminPannel.setVisible(true);
    }
    
}
