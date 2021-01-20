import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateBookForm extends JFrame{
    private JPanel updatebookMainPanel;
    private JTextField isbnField;
    private JTextField newBookNameField;
    private JTextField newAuthorNameField;
    private JTextField newPublisherNameField;
    private JButton updateBookButton;

    UpdateBookForm(){
        super("Update Book Form");
        this.setContentPane(updatebookMainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        Database db = new Database();


        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbnNo = isbnField.getText();
                String newBookName = newBookNameField.getText();
                String newAuthorName = newAuthorNameField.getText();
                String newPublisherName = newPublisherNameField.getText();

                db.updateBook(newBookName,newAuthorName, newPublisherName, isbnNo);
                JOptionPane.showMessageDialog(updatebookMainPanel, "Updated books details");
            }
        });
    }
}
