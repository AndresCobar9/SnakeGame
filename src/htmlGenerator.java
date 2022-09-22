import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class htmlGenerator
{


    static StringBuilder textohtml = new StringBuilder();


    public static void inicioHTML(String texto){
        textohtml.append("<!doctype html>\n" +
                "<html lang=\"en\">\n"+
                "<head>\n"+
                "<meta charset=\"UTF-8\">"+
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
                "<link rel=\"stylesheet\" href=\"./style.css\">"+
                "<title>Mejores Puntaciones Snake</title>"+
                "</head>\n"+
                "<header>\n"+
                "<img src=\"./images/Serpiente.png\" alt=\"Andres Cobar\">\n"+
               "<section>\n"+
                "<h1>Top Puntuaciones Snake</h1>\n"+
                "</section>  \n"  +
                "<div class=\"spacer\">\n"+
                "<img src=\"./images/fiusac_negro.png\" alt=\"Andres Cobar\">\n"+
                "</div>\n"+
                "</header>\n"+
                "<body>\n"+
                "<div class = \"caja\">\n"+
                "<div class=\"top\">\n"+
                "<h1>Top 15</h1>\n"
        );

            textohtml.append(texto);


        textohtml.append("</div>\n" +
                "</div>\n" +
                "</body>\n");

    }

    public static void generarReporte(String texto){
        inicioHTML(texto);
        crearReporte();
        abrirReporte();
    }

    public static void crearReporte(){
        File reporte1 = new File("./Pagina/Top15Snake.html");
        try{
            FileWriter escribir = new FileWriter(reporte1);
            escribir.write(textohtml.toString());
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte generado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void abrirReporte(){
        try
        {
            File file = new File("./Reportes/Reporte_Errores.html");
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

