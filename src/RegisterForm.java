

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame {
    private JPanel registerMainPanel;
    private JTextField regIdentifyField;
    private JTextField regFirstnameField;
    private JTextField regLastnameField;
    private JTextField regPasswordField;
    private JButton registerButton;

    RegisterForm(){
        add(registerMainPanel);
        setSize(400,400);
        setTitle("Library System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




        Database db = new Database();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifyNum = regIdentifyField.getText();
                String firstName = regFirstnameField.getText();
                String lastName = regLastnameField.getText();
                String password = regPasswordField.getText();
                String role = "user";

                if(identifyNum.equals("") || firstName.equals("") || lastName.equals("") || password.equals("")){
                    JOptionPane.showMessageDialog(registerMainPanel,"Please fill in the blanks.");
                }
                else{
                    User newUser = new User(firstName, lastName, password, identifyNum, role);
                    db.addUser(newUser);
                    JOptionPane.showMessageDialog(registerMainPanel,"Registration success. Please login.");
                }



            }
        });
    }
}
