public class DesktopController {
    private DesktopUI desktopUI;

    public DesktopController() {
        desktopUI = new DesktopUI();
    }

    public void showDesktop() {
        desktopUI.setVisible(true);
    }
}
