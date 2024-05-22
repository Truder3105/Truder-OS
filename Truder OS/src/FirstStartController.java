import javax.swing.*;

public class FirstStartController {
    private FirstStartUI firstStartUI;
    private LockScreenUI lockScreenUI;
    private char[] password; // Almacena la contraseña para uso posterior

    public FirstStartController(FirstStartUI firstStartUI, LockScreenUI lockScreenUI) {
        this.firstStartUI = firstStartUI;
        this.lockScreenUI = lockScreenUI;
        firstStartUI.addSetupListener(e -> performSetup());
        firstStartUI.setVisible(true);
    }

    private void performSetup() {
        if (!validateInputs()) {
            return;
        }

        System.out.println("Configuración completada con éxito.");
        firstStartUI.dispose();
        lockScreenUI.setDeviceName(firstStartUI.getDeviceName());
        SwingUtilities.invokeLater(() -> new LockScreenController(lockScreenUI, password));
    }

    private boolean validateInputs() {
        String deviceName = firstStartUI.getDeviceName();
        password = firstStartUI.getPassword();
        char[] confirmPassword = firstStartUI.getConfirmPassword();

        if (deviceName.isEmpty() || password.length < 4) {
            JOptionPane.showMessageDialog(firstStartUI, "Por favor, complete todos los campos. La contraseña debe tener al menos 4 caracteres.", "Error de configuración", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!java.util.Arrays.equals(password, confirmPassword)) {
            JOptionPane.showMessageDialog(firstStartUI, "Las contraseñas no coinciden. Intente de nuevo.", "Error de configuración", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
