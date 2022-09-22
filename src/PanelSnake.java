import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class PanelSnake extends JPanel {

    Color colorSnake = Color.blue;
    Color colorFruta = Color.MAGENTA;
    int MaxSize, Size, SizePanelM;
    static int puntos = 0;

    List<int[]> Snake = new ArrayList<>();
    int[] comida =  new int[2];
    String Direccion = "Right";
    String DireccionN = "Right";
    Thread CaminarH;
    public static String tiempof;

    HiloCaminar Caminar;
    Boolean Pintar = false;


    public PanelSnake(int TamMax, int cant) {
        this.MaxSize = TamMax;
        this.SizePanelM = cant;
        this.Size = TamMax / cant;
    }

    public void iniciar(boolean _Iniciar, int _Dificultad){
        if(_Iniciar == true){
            int[] a = {SizePanelM / 2 - 1, SizePanelM / 2 - 1};
            int[] b = {SizePanelM / 2, SizePanelM / 2 - 1};
            Snake.add(a);
            Snake.add(b);
            Caminar = new HiloCaminar(this);
            CaminarH = new Thread(Caminar);
            Caminar.Dificult(_Dificultad);
            Pintar =true;
            generarComida();
            repaint();
            CaminarH.start();

        }
    }

    public void Reiniciar(boolean _Reiniciar){
        if(_Reiniciar == true);

    }

    @Override
    public void paint(Graphics Pintor) {

        if(Pintar == true){

            super.paint(Pintor);
            Pintor.setColor(colorSnake);

            for (int i = 0; i < Snake.size(); i++) {
                Pintor.fillRect(Snake.get(i)[0] * Size, Snake.get(i)[1] * Size, Size - 1, Size - 1);
            }
            Pintor.setColor(colorFruta);
            Pintor.fillRect(comida[0] * Size, comida[1] * Size, Size - 1, Size - 1);
        }

    }

    public void Avanzar() {
        IgualarDireccion();
        int[] ultimo = Snake.get(Snake.size() - 1);
        int MoveX = 0;
        int MoveY = 0;
        switch (Direccion) {
            case "Right":
                MoveX = 1;
                break;
            case "Left":
                MoveX = -1;
                break;
            case "Up":
                MoveY = -1;
                break;
            case "Down":
                MoveY = 1;
                break;
        }

        int[] nuevo = {Math.floorMod(ultimo[0] + MoveX, SizePanelM), Math.floorMod(ultimo[1] + MoveY, SizePanelM)};
        boolean  existe = false;

        for (int i = 0; i < Snake.size(); i++){
            if(nuevo[0] == Snake.get(i)[0] && nuevo[1] == Snake.get(i)[1]){
                existe = true;
                break;
            }
        }

        if(existe){
            tiempof = Principal.Time.getText();
            Principal.Finalizar();
            Caminar.stop();

            JOptionPane.showMessageDialog(null,"Perdiste");
            Principal.fin();
            Principal.Reiniciar.setEnabled(true);
            Principal.Inciar.setEnabled(false);

        }else {
            if(nuevo[0] == comida[0] && nuevo[1] == comida[1]){
                puntos++;
                Snake.add(nuevo);
                generarComida();
                Principal.Puntaje.setText("Puntos: " + puntos+"/25");
                if (puntos == 25){
                    JOptionPane.showMessageDialog(null,"🎊 ¡¡¡HAZ GANADO!!! 🎊");

                    tiempof = Principal.Time.getText();
                    Principal.fin();
                    Principal.Finalizar();

                    Caminar.stop();
                    Principal.Reiniciar.setEnabled(true);
                    Principal.Inciar.setEnabled(false);


                }
            }else{
                Snake.add(nuevo);
                Snake.remove(0);
            }
        }

    }


        public void generarComida() {
            boolean Exist = false;
            int a = (int) (Math.random() * SizePanelM);
            int b = (int) (Math.random() * SizePanelM);
            for (int[] par : Snake) {
                if (par[0] == a && par[1] == b) {
                    Exist = true;
                    break;
                }
            }
            if(!Exist){
                this.comida[0] = a;
                this.comida[1] = b;
            }
        }
    public void CambiarDireccion(String Dir){
        if ((this.Direccion.equals("Right")||this.Direccion.equals("Left"))&&(Dir.equals("Up") || Dir.equals("Down"))){
            this.DireccionN = Dir;
        }
        if ((this.Direccion.equals("Up")||this.Direccion.equals("Down"))&&(Dir.equals("Left") || Dir.equals("Right"))){
            this.DireccionN = Dir;
        }
    }
    public void IgualarDireccion(){
        this.Direccion = this.DireccionN;
    }
}
