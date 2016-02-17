
package teaeli;

public class TeaELI {

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        //loginFrame.setVisible(true);
        
        AdminPannel adminPannel = new AdminPannel();
        
        //Keep the window fullscreen
        adminPannel.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        adminPannel.setVisible(true);
    }
    
}
