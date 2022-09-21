import javax.swing.*;
import java.awt.*;

public class PanelJ extends JPanel {
    Color colorf = Color.lightGray;
    int MaxSize, Size, SizePanelM;
    public PanelJ (int TamMax, int cant){
        this.MaxSize = TamMax;
        this.SizePanelM = cant ;
        this.Size = TamMax/cant;
    }
    public void paint(Graphics Pintor){
        super.paint(Pintor);
        Pintor.setColor(colorf);
        for (int i = 0; i < Size;i++){
            for (int o = 0;o < Size; o++ ){
                Pintor.fillRect(i*Size, o*Size, Size-1, Size-1);
            }

        }
    }
}
