public class HiloCaminar implements Runnable{
    PanelSnake Snake;
    boolean estado =true;
    int Dificultad;

    public HiloCaminar(PanelSnake Snake){
    this.Snake = Snake;
    }
    public void Dificult(int _Dificultad){
        if(_Dificultad == 0){
            Dificultad = 883;
        }
        if(_Dificultad == 1){
            Dificultad = 856;
        }
        if(_Dificultad == 2){
            Dificultad = 500;
        }

    }
    @Override
    public void run() {
        while (estado) {
            Snake.Avanzar();
            Snake.repaint();
            try {
                Thread.sleep(Dificultad);
            } catch (InterruptedException ex) {

            }
        }
    }
    public void stop(){
        estado= false;
    }
}
