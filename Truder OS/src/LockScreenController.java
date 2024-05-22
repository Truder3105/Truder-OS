import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Arrays;

public class LockScreenController {
    private LockScreenUI lockScreenUI;
    private DesktopUI desktopUI;
    private char[] correctPassword; // Almacenamos la contraseña aquí

    public LockScreenController(LockScreenUI lockScreenUI, char[] password) {
        this.lockScreenUI = lockScreenUI;
        this.correctPassword = password; // Guardamos la contraseña pasada
        this.desktopUI = new DesktopUI();
        addListeners();
        lockScreenUI.setVisible(true);
    }

    private void addListeners() {
        lockScreenUI.addUnlockListener(e -> unlock());
        lockScreenUI.getChangeBackgroundButton().addActionListener(e -> changeBackground());
        lockScreenUI.getShutdownButton().addActionListener(e -> shutdown());
    }

    private void unlock() {
        char[] passwordEntered = lockScreenUI.getPassword().toCharArray();
        if (Arrays.equals(passwordEntered, correctPassword)) {
            System.out.println("Desbloqueado!");
            lockScreenUI.dispose();
            desktopUI.setVisible(true);
            Arrays.fill(correctPassword, '0'); // Limpia la contraseña de la memoria
        } else {
            JOptionPane.showMessageDialog(lockScreenUI, "Por favor, ingrese la contraseña correcta.",
                    "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeBackground() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione una nueva imagen de fondo");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "png", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(lockScreenUI);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            lockScreenUI.setBackgroundImage(selectedFile.getAbsolutePath());
        }
    }

    private void shutdown() {
        System.exit(0);
    }
}
