public class Puntuaciones {
    private int puntuacion;
    private String tiempo;
    private int segundos;
    private int minutos;

    private int total;

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Puntuaciones(int puntuacion, String tiempo) {
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        String[] obtener = tiempo.split(":");
        minutos = Integer.valueOf(obtener[0]);
        segundos= Integer.valueOf(obtener[1]);
        total = segundos + (minutos*60);


    }
}
