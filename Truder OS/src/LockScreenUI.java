import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LockScreenUI extends JFrame {

    private JPasswordField passwordField;
    private JButton unlockButton, changeBackgroundButton, shutdownButton;
    private JLabel backgroundLabel, deviceNameLabel, systemNameLabel;
    private JPanel centerPanel;
    private boolean allowPasswordChange = false;

    public LockScreenUI() {
        initializeFrame();
        setupBackground();
        setupCenterPanel();
        addComponentsToFrame();
    }

    private void initializeFrame() {
        setTitle("Pantalla de bloqueo - TruderOS");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupBackground() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon backgroundIcon = loadIcon("/resources/Fondo.jpg", screenSize.width, screenSize.height);
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout());
    }

    private void setupCenterPanel() {
        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = createGbc();

        systemNameLabel = createLabel("TruderOS", Color.BLACK, new Font("Arial", Font.BOLD, 30));
        gbc.gridy = 0;
        centerPanel.add(systemNameLabel, gbc);

        JPanel deviceNamePanel = new JPanel(new BorderLayout());
        deviceNamePanel.setOpaque(false);

        deviceNameLabel = createLabel("Unknown Device", Color.WHITE, new Font("Arial", Font.BOLD, 24));
        deviceNamePanel.add(deviceNameLabel, BorderLayout.CENTER);

        changeBackgroundButton = new JButton(loadIcon("/resources/Image Icon.png", 30, 30));
        styleButton(changeBackgroundButton);
        deviceNamePanel.add(changeBackgroundButton, BorderLayout.WEST);

        shutdownButton = new JButton(loadIcon("/resources/OFF Icon.png", 30, 30));
        styleButton(shutdownButton);
        deviceNamePanel.add(shutdownButton, BorderLayout.EAST);

        gbc.gridy = 1;
        centerPanel.add(deviceNamePanel, gbc);

        setupPasswordField(gbc);
        setupUnlockButton(gbc);
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

    private JLabel createLabel(String text, Color color, Font font) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setForeground(color);
        label.setFont(font);
        return label;
    }

    private void setupPasswordField(GridBagConstraints gbc) {
        passwordField = new JPasswordField(20);
        stylePasswordField(passwordField);
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 50, 10, 50);  // Adjusted insets for better layout
        centerPanel.add(passwordField, gbc);
    }

    private void stylePasswordField(JPasswordField field) {
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(new Font("Arial", Font.PLAIN, 18));
        field.setOpaque(false);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    private void setupUnlockButton(GridBagConstraints gbc) {
        unlockButton = new JButton(loadIcon("/resources/Flecha Icon.png", 30, 30));
        styleButton(unlockButton);
        gbc.gridy = 3;  // Adjusted gridy for layout
        centerPanel.add(unlockButton, gbc);
    }

    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addComponentsToFrame() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        backgroundLabel.add(centerPanel, c);
        add(backgroundLabel);
    }

    private ImageIcon loadIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addUnlockListener(ActionListener listener) {
        unlockButton.addActionListener(listener);
        passwordField.addActionListener(listener);  // Allows pressing Enter in the password field to trigger unlock
    }

    public void setBackgroundImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        backgroundLabel.setIcon(icon);
    }

    public JButton getChangeBackgroundButton() {
        return changeBackgroundButton;
    }

    public JButton getShutdownButton() {
        return shutdownButton;
    }

    public void setDeviceName(String name) {
        deviceNameLabel.setText(name);
    }

    public void setPassword(String password) {
        if (allowPasswordChange) {
            passwordField.setText(password);
        }
    }

    public void allowPasswordChange(boolean flag) {
        allowPasswordChange = flag;
    }
}
