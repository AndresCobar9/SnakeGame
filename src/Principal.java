import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import static java.awt.Color.*;

public class Principal extends JFrame implements ActionListener {
    PanelSnake Snake;
    Thread tiempo_consumir;
    static Tiempo Time;
    public static int contadorTexto = 0;
    public static String[] TextoTOP;
    static int contadorTop=0;

    static Puntuaciones[] arreglotemporal  = new Puntuaciones[contadorTop];
    public static JButton Inciar;
    public static JButton Reiniciar;

    public static JLabel Puntaje;

    public static Puntuaciones[] top15 = new Puntuaciones[15];

    int dificultad = 1;
    PanelJ fondo;
    public static Boolean Repit = false;
    public static Boolean Finalizo = false;

    public Principal() {

        // ===== Selector de Dificultades =====

        JComboBox Dificultades = new JComboBox();
        Dificultades.setForeground(BLACK);
        Dificultades.setBackground(WHITE);
        Dificultades.setBorder(null);
        Dificultades.setBounds(614, 250, 265, 80);
        Dificultades.setFont(new Font("Ubuntu", Font.PLAIN, 26));
        Dificultades.addItem("Facil 😁");
        Dificultades.addItem("Normal 🙂");
        Dificultades.addItem("Dificil 😢");
        Dificultades.setSelectedItem("Normal 🙂");
        Dificultades.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
                setBorder(null);
                super.paint(g);
                setHorizontalAlignment(CENTER);
            }
        });
        this.add(Dificultades);
        Dificultades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Dificultades.getSelectedItem() == "Facil 😁") {
                    dificultad = 0;
                } else if (Dificultades.getSelectedItem() == "Normal 🙂") {
                    dificultad = 1;
                } else if (Dificultades.getSelectedItem() == "Dificil 😢") {
                    dificultad = 2;
                }
            }
        });

        // ===== Agregar contador de Tiempo =====
        this.Time = new Tiempo();
        this.tiempo_consumir = new Thread(this.Time);
        Time.setBounds(614, 10, 265, 80);
        Time.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Time.setBackground(BLACK);
        Time.setOpaque(true);
        Time.setForeground(WHITE);
        this.add(Time);

        // ===== Agregar Snake =====
        Snake = new PanelSnake(600, 15);
        this.add(Snake);
        Snake.setBounds(6, 10, 600, 600);
        Snake.setOpaque(false);

        // ===== Agregar Tablero =====
        fondo = new PanelJ(600, 15);
        this.add(fondo);
        fondo.setBounds(6, 10, 600, 600);

        // ===== Agregar Boton TOP =====
        JButton TOP15 = new JButton("🏆  TOP 15  🏆");
        TOP15.setBounds(614, 430, 265, 80);
        TOP15.setBackground(white);
        TOP15.setForeground(BLACK);
        TOP15.setFont(new Font("Ubuntu", Font.BOLD, 30));
        TOP15.setBorder(null);
        TOP15.setVisible(true);
        this.add(TOP15);
        TOP15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarTop();
            }
        });
        TOP15.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent evt) {
                TOP15.setBackground(lightGray);
            }

            public void mouseExited(MouseEvent evt) {
                TOP15.setBackground(white);
            }
        });


        // ===== Iniciar Boton =====
        Inciar = new JButton("INICIAR");
        Inciar.setBounds(614, 90, 265, 80);
        Inciar.setBackground(white);
        Inciar.setForeground(BLACK);
        Inciar.setFont(new Font("Ubuntu", Font.BOLD, 30));
        Inciar.setBorder(null);
        Inciar.setVisible(true);
        Inciar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                Inciar.setBackground(lightGray);
            }

            public void mouseExited(MouseEvent evt) {
                Inciar.setBackground(white);
            }
        });
        Inciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Repit == false) {
                    Snake.iniciar(true, dificultad);
                    requestFocus();
                    tiempo_consumir = new Thread(Time);
                    Time.reiniciar();
                    tiempo_consumir.start();
                    Inciar.setEnabled(false);
                }
            }
        });
        this.add(Inciar);

        // ===== Agregar Boton Reiniciar =====
        Reiniciar = new JButton("REINTENTAR");
        Reiniciar.setBounds(614, 170, 265, 80);
        Reiniciar.setVisible(true);
        Reiniciar.setEnabled(false);
        Reiniciar.addActionListener(this);
        Reiniciar.setBackground(white);
        Reiniciar.setForeground(BLACK);
        Reiniciar.setFont(new Font("Ubuntu", Font.BOLD, 30));
        Reiniciar.setBorder(null);
        Reiniciar.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent evt) {
                Reiniciar.setBackground(lightGray);
            }

            public void mouseExited(MouseEvent evt) {
                Reiniciar.setBackground(white);
            }
        });
        this.add(Reiniciar);

        // ===== Puntaje =====
        Puntaje = new JLabel("Puntos:  /25");
        Puntaje.setBounds(614, 350, 265, 440);
        Puntaje.setVisible(true);
        Puntaje.setFont(new Font("Ubuntu", Font.BOLD, 24));
        Puntaje.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        this.add(Puntaje);

        // ===== Fondo =====
        JLabel fondo = new JLabel();
        fondo.setBackground(white);
        fondo.setOpaque(true);
        fondo.setBounds(614, 170, 265, 440);
        fondo.setVisible(true);
        this.add(fondo);


        // ---- Ventana Desing -----
        this.setTitle("Snake");
        this.setLayout(null);
        this.setBounds(0, 0, 900, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(gray);
        this.setVisible(true);

        setLocationRelativeTo(null);


        // ==== Detector de Tecla Presionada ====
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        this.requestFocus(true);
    }

    public static void Finalizar() {
        Time.stop();
    }

    public static void main(String args[]) {
        Principal principal = new Principal();

    }

    public static void fin(){
        Puntuaciones c = new Puntuaciones(PanelSnake.puntos,Time.getText());
        Principal.generarPuntaje(c);
    }
    public static void generarPuntaje(Puntuaciones c){
        if(contadorTop <15){
            top15[contadorTop] = c;
            contadorTop++;
        }
    }



// ===== Metodo para ordenar los puntajes realizados ======
    public static void generarTop() {
        System.out.println();
        String texto = "";
        System.out.println(" ======= TOP 15 ======");
        Puntuaciones[] arreglotemporal = new Puntuaciones[contadorTop];
        for (int i = 0; i < contadorTop; i++) {
            arreglotemporal[i] = top15[i];
        }
        Arrays.sort(arreglotemporal);

        for (int i = 0; i < contadorTop; i++) {
            System.out.println(arreglotemporal[i].getPuntuacion() + " " + arreglotemporal[i].getTiempo());
            texto = texto + "<p>"+(i+1) + ". Puntuacion: " + arreglotemporal[i].getPuntuacion() + " Tiempo: " + arreglotemporal[i].getTiempo() + "</p>\n";
        }
        System.out.println(" =====================");
        System.out.println();
    htmlGenerator.generarReporte(texto);
    }


public void generarTexto(){

}
   /* public void ComprobarIgual(){
        for (int i = 1 ; i < contadorTop ; i++){
            Puntuaciones top = top15[i];
            int j = i-1;
            while ((j >= 0) && (top.getPuntuacion() == top15[j].getPuntuacion()));{

            }
            top15[j+1] = top;
        }
        for ( int i = 0; i<contadorTop;i++){
            System.out.println(top15[i].getPuntuacion() +" " + top15[i].getTiempo());
        }
    }*/

    private void formKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                Snake.CambiarDireccion("Left");
                break;
            case KeyEvent.VK_UP:
                Snake.CambiarDireccion("Up");
                break;
            case KeyEvent.VK_RIGHT:
                Snake.CambiarDireccion("Right");
                break;
            case KeyEvent.VK_DOWN:
                Snake.CambiarDireccion("Down");
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Reiniciar) {
            this.remove(Snake);
            this.remove(fondo);
            this.remove(Time);
            Time = new Tiempo();
            this.add(Time);
            repaint();
            Time.setVisible(true);
            Time.setBounds(614, 10, 265, 80);
            Time.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            Time.setBackground(BLACK);
            Time.setOpaque(true);
            Time.setForeground(WHITE);
            PanelSnake.puntos = 0;
            fondo = new PanelJ(600, 15);
            fondo.setBounds(6, 10, 600, 600);
            Snake = new PanelSnake(600, 15);
            Snake.setBounds(6, 10, 600, 600);
            Snake.setOpaque(false);
            this.add(Snake);
            Snake.setVisible(true);
            this.add(fondo);
            Reiniciar.setEnabled(false);
            Inciar.setEnabled(true);
            Puntaje.setText("Puntos:  /25");
            tiempo_consumir.interrupt();
        }
    }


}
