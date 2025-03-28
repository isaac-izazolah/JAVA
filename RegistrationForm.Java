import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame implements ActionListener {
    JTextField nameField, mobileField;
    JRadioButton male, female;
    JComboBox<String> day, month, year;
    JTextArea addressField;
    JCheckBox terms;
    JButton submit, reset;
    JTextArea displayArea;
    
    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new JLabel("Name"));
        nameField = new JTextField(15);
        add(nameField);
        
        add(new JLabel("Mobile"));
        mobileField = new JTextField(15);
        add(mobileField);
        
        add(new JLabel("Gender"));
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        add(male);
        add(female);
        
        add(new JLabel("DOB"));
        String[] days = {"1", "2", "3", "4", "5"};
        String[] months = {"Jan", "Feb", "Mar", "Apr"};
        String[] years = {"1990", "1995", "2000", "2005"};
        day = new JComboBox<>(days);
        month = new JComboBox<>(months);
        year = new JComboBox<>(years);
        add(day);
        add(month);
        add(year);
        
        add(new JLabel("Address"));
        addressField = new JTextArea(3, 15);
        add(addressField);
        
        terms = new JCheckBox("Accept Terms and Conditions");
        add(terms);
        
        submit = new JButton("Submit");
        reset = new JButton("Reset");
        add(submit);
        add(reset);
        
        displayArea = new JTextArea(5, 20);
        add(displayArea);
        
        submit.addActionListener(this);
        reset.addActionListener(this);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (!terms.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please accept terms and conditions.");
                return;
            }
            
            String name = nameField.getText();
            String mobile = mobileField.getText();
            String gender = male.isSelected() ? "Male" : "Female";
            String dob = day.getSelectedItem() + " " + month.getSelectedItem() + " " + year.getSelectedItem();
            String address = addressField.getText();
            
            displayArea.setText("Name: " + name + "\nMobile: " + mobile + "\nGender: " + gender + "\nDOB: " + dob + "\nAddress: " + address);
            
            saveToDatabase(name, mobile, gender, dob, address);
        } else if (e.getSource() == reset) {
            nameField.setText("");
            mobileField.setText("");
            genderGroup.clearSelection();
            day.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
            addressField.setText("");
            terms.setSelected(false);
        }
    }
    
    private void saveToDatabase(String name, String mobile, String gender, String dob, String address) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "");
            PreparedStatement pst = con.prepareStatement("INSERT INTO users (name, mobile, gender, dob, address) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, mobile);
            pst.setString(3, gender);
            pst.setString(4, dob);
            pst.setString(5, address);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Saved Successfully!");
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data.");
        }
    }
    
    public static void main(String[] args) {
        new RegistrationForm();
    }
}
