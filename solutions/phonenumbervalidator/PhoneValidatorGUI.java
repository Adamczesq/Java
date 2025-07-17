package solutions.phonenumbervalidator;

import javax.swing.*;
import java.awt.*;

public class PhoneValidatorGUI {

    public static boolean isValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        } else if (phoneNumber.startsWith("+")) {
            phoneNumber = phoneNumber.substring(3);
        }
        String digitsOnly = phoneNumber.replaceAll("\\D", "");
        return digitsOnly.matches("\\d{9}");
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Walidator Numeru Telefonu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 220);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel instructionLabel = new JLabel("Wprowadź numer telefonu:");
        JTextField phoneInputField = new JTextField();
        JButton validateButton = new JButton("Sprawdź numer");
        JLabel resultLabel = new JLabel("Czekam na numer...", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        validateButton.addActionListener(e -> {
            String phoneNumber = phoneInputField.getText();
            if (isValid(phoneNumber)) {
                resultLabel.setText("Poprawny numer!");
                resultLabel.setForeground(new Color(0, 128, 0));
            } else {
                resultLabel.setText("Niepoprawny numer!");
                resultLabel.setForeground(Color.RED);
            }
        });

        panel.add(instructionLabel);
        panel.add(phoneInputField);
        panel.add(validateButton);
        panel.add(resultLabel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhoneValidatorGUI::createAndShowGUI);
    }
}
