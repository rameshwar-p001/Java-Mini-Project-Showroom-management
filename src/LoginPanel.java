import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField userField;
    private JPasswordField passField;

    public LoginPanel(JFrame frame, JPanel mainContent) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        userField = new JTextField(15);
        passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                frame.getContentPane().removeAll();
                frame.setContentPane(mainContent);
                frame.revalidate();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login!");
            }
        });
    }
}
