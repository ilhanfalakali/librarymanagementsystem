

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBooks extends JFrame {
    private JPanel viewBooksPanel;
    private JTable booksTable;
    private JButton deleteBookButton;
    private JButton updateBookButton;

    ViewBooks(){
        super("View Books");
        this.setContentPane(this.viewBooksPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();

        DefaultTableModel bookDataModel = (DefaultTableModel) booksTable.getModel();
        Database db = new Database();
        booksTable.setModel(db.viewBooks(bookDataModel));

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookISBN= JOptionPane.showInputDialog("Please type ISBN no: ");
                db.removeBook(bookISBN);
                bookDataModel.setRowCount(0);
                bookDataModel.setColumnCount(0);
                booksTable.setModel(db.viewBooks(bookDataModel));
            }
        });
        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateBookForm updateBookForm = new UpdateBookForm();
                updateBookForm.setVisible(true);
            }
        });
    }
}
