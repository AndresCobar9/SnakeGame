import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Tiempo extends JLabel implements Runnable{
    public static int Minutes;
    public static int seconds;
    DecimalFormat formato = new DecimalFormat("00");
    Boolean exit = false;
    public Tiempo(){
        seconds=0;
        setText(formato.format(Minutes)+":"+formato.format(seconds));
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        setFont(new Font("Ubuntu", Font.BOLD, 50));
        setVisible(true);
        exit=false;
    }
    public void reiniciar()
    {
        seconds = 0;
    }
    public void stop()
    {
        exit = true;
    }

    public void imprimirTiempo()
    {
         Minutes = (int) seconds/60;
        int seconds_1 = seconds - (Minutes*60);
        setText(formato.format(Minutes) +":"+formato.format(seconds_1));
    }

    @Override
    public void run() {
        try{
            while(exit == false)
            {
                imprimirTiempo();
                Thread.sleep(1000);
                this.seconds++;
            }
        }catch(Exception e)
        {
        }
    }
}
