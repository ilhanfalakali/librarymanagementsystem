

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JTextField identifyField;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField passwordField;
    private JPanel loginPanel;

    private static LoginForm f = null;

    public LoginForm(){
        add(loginPanel);
        setSize(800,600);
        setTitle("Library System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                String identify_num = identifyField.getText();
                String password = passwordField.getText();


                if(identify_num.equals("")){ // If identify num is null
                    JOptionPane.showMessageDialog(null,"Please enter identify number");
                }
                else if(password.equals("")){ // If password is null
                    JOptionPane.showMessageDialog(null,"Please enter password");
                }
                else{
                    Database db = new Database();
                    try{
                        db.connect();
                        db.stat.executeUpdate("USE library_system");


                        String query = ("SELECT * FROM users WHERE identif_number='"+identify_num+"' AND password='"+password+"'");
                        db.rs = db.stat.executeQuery(query);
                        if(!db.rs.next()){
                            System.out.print("No user");
                            JOptionPane.showMessageDialog(loginPanel,"No such user, please check your information");
                        }
                        else{
                            db.rs.beforeFirst();
                            while(db.rs.next())
                            {
                                String admin = db.rs.getString("role");
                                if(admin.equals("admin")) {
                                    goToAdminForm(e);
                                }
                                else{
                                    goToUserForm(e);
                                }
                            }
                        }
                    }catch(Exception error){
                        System.err.println(error);
                    }
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                registerForm.setVisible(true);
            }
        });
    }

    public static synchronized LoginForm getInstance(){
        try {
            if (f == null) {
                f = (LoginForm) Class.forName("LoginForm").newInstance();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.toString());
        }
        return f;
    }

    private void goToAdminForm(ActionEvent evt) {
        AdminForm.getInstance().setVisible(true);
        this.dispose();
    }

    private void goToUserForm(ActionEvent evt) {
        UserForm.getInstance().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> LoginForm.getInstance().setVisible(false));
    }
}

