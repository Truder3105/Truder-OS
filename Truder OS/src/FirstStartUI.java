import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.text.JTextComponent;

public class FirstStartUI extends JFrame {
    private JTextField deviceNameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton nextButton, changeBackgroundButton, shutdownButton;
    private JLabel backgroundLabel;
    private JLabel osNameLabel;
    private JLabel errorLabel;  // JLabel to display error messages

    public FirstStartUI() {
        setTitle("Configuración Inicial - TruderOS");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/resources/Fondo.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setLayout(new GridBagLayout());

        addComponentsToPane(backgroundLabel);
        addChangeBackgroundButton();
        addShutdownButton();
        add(backgroundLabel);
    }

    private void addComponentsToPane(Container pane) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);

        osNameLabel = new JLabel("TruderOS", JLabel.CENTER);
        osNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        osNameLabel.setForeground(Color.BLACK);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        pane.add(osNameLabel, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.NORTH;

        deviceNameField = new JTextField("Ingrese nombre del dispositivo", 20);
        passwordField = new JPasswordField("Contraseña", 20);
        confirmPasswordField = new JPasswordField("Confirmar contraseña", 20);
        setupTextField(deviceNameField, "Ingrese nombre del dispositivo");
        setupPasswordField(passwordField, "Contraseña");
        setupPasswordField(confirmPasswordField, "Confirmar contraseña");

        ImageIcon nextIcon = new ImageIcon(getClass().getResource("/resources/Flecha Icon.png"));
        nextButton = new JButton("Siguiente", new ImageIcon(nextIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        nextButton.setHorizontalTextPosition(SwingConstants.LEFT);
        nextButton.setIconTextGap(10);
        nextButton.setForeground(Color.BLACK);
        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pane.add(deviceNameField, gbc);
        pane.add(passwordField, gbc);
        pane.add(confirmPasswordField, gbc);
        pane.add(nextButton, gbc);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setOpaque(false);
        gbc.anchor = GridBagConstraints.CENTER;
        pane.add(errorLabel, gbc);

        getRootPane().setDefaultButton(nextButton);
    }

    private void addChangeBackgroundButton() {
        ImageIcon changeIcon = new ImageIcon(getClass().getResource("/resources/Image Icon.png"));
        changeBackgroundButton = new JButton(new ImageIcon(changeIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        changeBackgroundButton.setBorderPainted(false);
        changeBackgroundButton.setContentAreaFilled(false);
        changeBackgroundButton.setFocusPainted(false);
        changeBackgroundButton.addActionListener(e -> changeBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(0, 0, 10, 140);
        backgroundLabel.add(changeBackgroundButton, gbc);
    }

    private void addShutdownButton() {
        ImageIcon shutdownIcon = new ImageIcon(getClass().getResource("/resources/OFF Icon.png"));
        shutdownButton = new JButton(new ImageIcon(shutdownIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        shutdownButton.setBorderPainted(false);
        shutdownButton.setContentAreaFilled(false);
        shutdownButton.setFocusPainted(false);
        shutdownButton.addActionListener(e -> System.exit(0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(10, 10, 10, 10);
        backgroundLabel.add(shutdownButton, gbc);
    }

    private void changeBackground() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif", "bmp"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                Image newBackground = ImageIO.read(selectedFile).getScaledInstance(backgroundLabel.getWidth(), backgroundLabel.getHeight(), Image.SCALE_SMOOTH);
                backgroundLabel.setIcon(new ImageIcon(newBackground));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + ex.getMessage());
            }
        }
    }

    private void setupTextField(JTextComponent textComponent, String defaultText) {
        textComponent.setForeground(Color.GRAY);
        textComponent.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textComponent.getText().equals(defaultText)) {
                    textComponent.setText("");
                    textComponent.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textComponent.getText().isEmpty()) {
                    textComponent.setText(defaultText);
                    textComponent.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void setupPasswordField(JPasswordField passwordField, String defaultText) {
        passwordField.setEchoChar((char) 0);
        passwordField.setText(defaultText);
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).equals(defaultText)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('•');
                    passwordField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(defaultText);
                    passwordField.setEchoChar((char) 0);
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
    }

    public void addSetupListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void displayError(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    public String getDeviceName() {
        return deviceNameField.getText().equals("Ingrese nombre del dispositivo") ? "" : deviceNameField.getText();
    }

    public char[] getPassword() {
        return new String(passwordField.getPassword()).equals("Contraseña") ? new char[0] : passwordField.getPassword();
    }

    public char[] getConfirmPassword() {
        return new String(confirmPasswordField.getPassword()).equals("Confirmar contraseña") ? new char[0] : confirmPasswordField.getPassword();
    }
}
