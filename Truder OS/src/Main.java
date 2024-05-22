
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FirstStartUI firstStartUI = new FirstStartUI();
            LockScreenUI lockScreenUI = new LockScreenUI();
            new FirstStartController(firstStartUI, lockScreenUI);
        });
    }
}
