import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Timer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionListener;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class DesktopUI extends JFrame {

    private JPanel backgroundPanel;
    private JLabel backgroundLabel;
    private JToolBar taskBar;
    private JLabel clockLabel;
    private JButton startButton, shutdownButton;

    public DesktopUI() {
        initializeFrame();
        setupBackground();
        setupTaskBar();
        //setupZyvoxIcon(); // Agregar el icono de Zyvox
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Escritorio - TruderOS");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupBackground() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/resources/Fondo.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(screenSize.width, screenSize.height,
                Image.SCALE_SMOOTH);

        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(backgroundLabel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
    }

    private void setupTaskBar() {
        taskBar = new JToolBar();
        taskBar.setFloatable(false);
        taskBar.setRollover(true);
        taskBar.setPreferredSize(new Dimension(getWidth(), 50));
        taskBar.setBackground(new Color(0, 0, 0)); // Barra de tareas negra sólida
        taskBar.setLayout(new BorderLayout());

        setupStartButton();
        setupShutdownButton();

        backgroundPanel.add(taskBar, BorderLayout.SOUTH);
    }

    private void setupStartButton() {
        ImageIcon startIcon = new ImageIcon(getClass().getResource("/resources/Inicio Icon.png"));
        Image img = startIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        startButton = new JButton(new ImageIcon(img));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> displayStartMenu());

        taskBar.add(startButton, BorderLayout.WEST);
    }

    private void setupShutdownButton() {
        ImageIcon shutdownIcon = new ImageIcon(getClass().getResource("/resources/OFF Desktop Icon.png"));
        Image img = shutdownIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        shutdownButton = new JButton(new ImageIcon(img));
        shutdownButton.setBorderPainted(false);
        shutdownButton.setContentAreaFilled(false);
        shutdownButton.setFocusPainted(false);
        shutdownButton.addActionListener(e -> System.exit(0));

        taskBar.add(shutdownButton, BorderLayout.EAST);
    }

    private void displayStartMenu() {
        JPopupMenu startMenu = new JPopupMenu();
        startMenu.setPreferredSize(new Dimension(200, 200)); // Ajuste del tamaño del menú
        startMenu.setBackground(Color.BLACK); // Fondo negro

        // Crear y configurar cada JMenuItem con fondo transparente y letra blanca
        JMenuItem textEditorItem = new JMenuItem("Editor de Texto");
        textEditorItem.setOpaque(true);
        textEditorItem.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
        textEditorItem.setForeground(Color.WHITE);
        textEditorItem.setFont(new Font("Arial", Font.BOLD, 12));
        textEditorItem.addActionListener(e -> new TextEditorApp().setVisible(true));
        startMenu.add(textEditorItem);

        JMenuItem calculatorItem = new JMenuItem("Calculadora");
        calculatorItem.setOpaque(true);
        calculatorItem.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
        calculatorItem.setForeground(Color.WHITE);
        calculatorItem.setFont(new Font("Arial", Font.BOLD, 12));
        calculatorItem.addActionListener(e -> new CalculatorApp().setVisible(true));
        startMenu.add(calculatorItem);

        JMenuItem webBrowserItem = new JMenuItem("Navegador Web");
        webBrowserItem.setOpaque(true);
        webBrowserItem.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
        webBrowserItem.setForeground(Color.WHITE);
        webBrowserItem.setFont(new Font("Arial", Font.BOLD, 12));
        webBrowserItem.addActionListener(e -> new WebBrowserApp().setVisible(true));
        startMenu.add(webBrowserItem);

        JMenuItem mathQuizItem = new JMenuItem("Quiz Matemático");
        mathQuizItem.setOpaque(true);
        mathQuizItem.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
        mathQuizItem.setForeground(Color.WHITE);
        mathQuizItem.setFont(new Font("Arial", Font.BOLD, 12));
        mathQuizItem.addActionListener(e -> new MathQuizApp().setVisible(true));
        startMenu.add(mathQuizItem);

        JMenuItem documentationItem = new JMenuItem("Documentación");
        documentationItem.setOpaque(true);
        documentationItem.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
        documentationItem.setForeground(Color.WHITE);
        documentationItem.setFont(new Font("Arial", Font.BOLD, 12));
        documentationItem.addActionListener(e -> new DocumentationApp().setVisible(true));
        startMenu.add(documentationItem);
        
        

        //startMenu.show(startButton, 0, -startMenu.getPreferredSize().height);
        
       /* File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
        if (desktopDir.exists() && desktopDir.isDirectory()) {
            File[] files = desktopDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        JMenuItem menuItem = new JMenuItem(file.getName());
                        menuItem.addActionListener(e -> openFile(file));
                        startMenu.add(menuItem);
                    }
                }
            }
        }

        startMenu.show(startButton, 0, -startMenu.getPreferredSize().height);
        */
       File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
if (desktopDir.exists() && desktopDir.isDirectory()) {
    File[] files = desktopDir.listFiles();
    if (files != null) {
        // Calcular el tamaño del menú
        int menuHeight = Math.min(300, files.length * 30); // Altura máxima de 200 píxeles o 25 píxeles por elemento
        startMenu.setPreferredSize(new Dimension(500,900));

        for (File file : files) {
            if (file.isFile()) {
                JMenuItem menuItem = new JMenuItem(file.getName());
                menuItem.setFont(new Font("Arial", Font.PLAIN, 14)); // Establecer el tamaño de fuente más grande
                menuItem.addActionListener(e -> openFile(file));
                startMenu.add(menuItem);
            }
        }
    }
}

startMenu.show(startButton, 0, -startMenu.getPreferredSize().height);

        
        
        
    }

    private void openFile(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }
    


    /*private void setupZyvoxIcon() {
        // Crear el icono de Zyvox
        ImageIcon zyvoxIcon = new ImageIcon(getClass().getResource("/resources/ZyvoxIcon.png"));
        Image img = zyvoxIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton zyvoxButton = new JButton(new ImageIcon(img));
        zyvoxButton.setBorderPainted(false);
        zyvoxButton.setContentAreaFilled(false);
        zyvoxButton.setFocusPainted(false);
        zyvoxButton.setToolTipText("Abrir Zyvox");

        // Agregar un ActionListener para abrir la aplicación Zyvox
        zyvoxButton.addActionListener(e -> abrirZyvox());

        // Añadir un espacio entre el botón de inicio y el icono de Zyvox
        Component rigidArea = Box.createRigidArea(new Dimension(taskBar.getWidth() / 5, 0));
        taskBar.add(rigidArea);

        // Añadir el botón de Zyvox al taskBar
        taskBar.add(zyvoxButton);
    }

    // Método para abrir la aplicación Zyvox
    private void abrirZyvox() {
        ZyvoxApp zyvox = new ZyvoxApp();
        zyvox.setVisible(true);
    }
 
*/



class ZyvoxApp extends JFrame {
    public ZyvoxApp() {
        setTitle("Zyvox - Plataforma de Colaboración");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear un panel de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña de reuniones
        tabbedPane.addTab("Reuniones", new ReunionesPanel());

        // Pestaña de tareas
        tabbedPane.addTab("Tareas", new TareasPanel());

        // Pestaña de administración de archivos
        tabbedPane.addTab("Archivos", new ArchivosPanel());

        // Agregar el panel de pestañas a la ventana principal
        add(tabbedPane, BorderLayout.CENTER);
    }
}

class ReunionesPanel extends JPanel {
    public ReunionesPanel() {
        setLayout(new BorderLayout());

        // Controles para iniciar y unirse a reuniones
        JButton iniciarReunionButton = new JButton("Iniciar Reunión");
        JButton unirseReunionButton = new JButton("Unirse a Reunión");

        iniciarReunionButton.addActionListener(e -> {
            // Lógica para iniciar una reunión
            JOptionPane.showMessageDialog(null, "Iniciando reunión...");
        });

        unirseReunionButton.addActionListener(e -> {
            // Lógica para unirse a una reunión
            JOptionPane.showMessageDialog(null, "Uniéndose a la reunión...");
        });

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(iniciarReunionButton);
        botonesPanel.add(unirseReunionButton);

        add(botonesPanel, BorderLayout.NORTH);
    }
}

class TareasPanel extends JPanel {
    public TareasPanel() {
        setLayout(new BorderLayout());

        // Modelo de tabla para las tareas
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");

        // Tabla para mostrar las tareas
        JTable tablaTareas = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaTareas);

        // Botón para añadir nuevas tareas
        JButton añadirTareaButton = new JButton("Añadir Tarea");
        añadirTareaButton.addActionListener(e -> {
            String descripcion = JOptionPane.showInputDialog("Descripción de la tarea:");
            if (descripcion != null && !descripcion.isEmpty()) {
                modelo.addRow(new Object[] { modelo.getRowCount() + 1, descripcion, "Pendiente" });
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(añadirTareaButton, BorderLayout.SOUTH);
    }
}

class ArchivosPanel extends JPanel {
    private JTree tree;
    private DefaultTreeModel treeModel;

    public ArchivosPanel() {
        setLayout(new BorderLayout());

        // Nodo raíz
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Archivos");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        // Botón para añadir archivos
        JButton añadirArchivoButton = new JButton("Añadir Archivo");
        añadirArchivoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                root.add(node);
                treeModel.reload();
            }
        });

        add(new JScrollPane(tree), BorderLayout.CENTER);
        add(añadirArchivoButton, BorderLayout.SOUTH);
    }
}

class TextEditorApp extends JFrame {
    public TextEditorApp() {
        setTitle("Editor de Texto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }
}

class CalculatorApp extends JFrame {
    private JTextField display;

    public CalculatorApp() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(e -> onButtonClick(e));
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void onButtonClick(ActionEvent e) {
        String command = e.getActionCommand();
        // Lógica de la calculadora aquí
    }
}

class WebBrowserApp extends JFrame {
    public WebBrowserApp() {
        setTitle("Navegador Web Básico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JEditorPane webPane = new JEditorPane();
        webPane.setEditable(false);
        try {
            webPane.setPage("https://youtu.be/3IUAqtqhuYE");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(webPane);
        add(scrollPane, BorderLayout.CENTER);
    }
}

class MathQuizApp extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;

    public MathQuizApp() {
        setTitle("Quiz Matemático");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("¿Cuánto es 2 + 2?");
        add(questionLabel, BorderLayout.NORTH);

        answerField = new JTextField();
        add(answerField, BorderLayout.CENTER);

        submitButton = new JButton("Responder");
        submitButton.addActionListener(e -> checkAnswer());
        add(submitButton, BorderLayout.SOUTH);
    }

    private void checkAnswer() {
        String answer = answerField.getText();
        if (answer.equals("4")) {
            JOptionPane.showMessageDialog(this, "¡Correcto!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto. Intenta nuevamente.");
        }
    }
}

class DocumentationApp extends JFrame {
    public DocumentationApp() {
        setTitle("Documentación y Tutoriales");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea documentationTextArea = new JTextArea();
        documentationTextArea
                .setText("Bienvenido a TruderOS. Aquí encontrarás tutoriales sobre cómo usar las aplicaciones...\n");
        JScrollPane scrollPane = new JScrollPane(documentationTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }
}









   
    
/*

  

        // Obtener la lista de aplicaciones en el escritorio
        File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
        if (desktopDir.exists() && desktopDir.isDirectory()) {
            File[] files = desktopDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        JMenuItem menuItem = new JMenuItem(file.getName());
                        menuItem.addActionListener(e -> openFile(file));
                        startMenu.add(menuItem);
                    }
                }
            }
        }

        startMenu.show(startButton, 0, -startMenu.getPreferredSize().height);
    }

    private void openFile(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DesktopUI());
    }

*/