import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonDemo {
    private JFrame frame;
    private JLabel imageLabel;
    private ButtonGroup group;

    public RadioButtonDemo() {
        // Create the main frame
        frame = new JFrame("Radio Button Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create a panel to hold radio buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Define pet options
        String[] pets = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
        group = new ButtonGroup();

        // Create radio buttons for each pet
        for (String pet : pets) {
            JRadioButton radioButton = new JRadioButton(pet);
            group.add(radioButton);
            panel.add(radioButton);
            
            // Add action listener to each button
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayImage(pet);
                }
            });
        }

        // Label to display selected pet image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the frame
        frame.add(panel, BorderLayout.WEST);
        frame.add(imageLabel, BorderLayout.CENTER);

        // Make frame visible
        frame.setVisible(true);
    }

    // Method to display image and show message dialog
    private void displayImage(String pet) {
        String imagePath = "images/" + pet.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(imagePath);
        
        // Check if the image exists
        if (icon.getIconWidth() == -1) {
            JOptionPane.showMessageDialog(frame, "Image not found: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            imageLabel.setIcon(icon);
            JOptionPane.showMessageDialog(frame, "You selected: " + pet);
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RadioButtonDemo();
            }
        });
    }
}