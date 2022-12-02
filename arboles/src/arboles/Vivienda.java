package arboles;

public class Vivienda {

    private int nroPropiedad;
    private String servicioSanitario, tipoEnergia, usaCocinar, eliminaBasura, comunicacion;

    public Vivienda(int nroPropiedad, String servicioSanitario, String tipoEnergia, String usaCocinar, String eliminaBasura, String comunicacion) {
        this.nroPropiedad = nroPropiedad;
        this.servicioSanitario = servicioSanitario;
        this.tipoEnergia = tipoEnergia;
        this.usaCocinar = usaCocinar;
        this.eliminaBasura = eliminaBasura;
        this.comunicacion = comunicacion;
    }

    public void mostrar() {
        System.out.println("nro: " + nroPropiedad + ", SS: " + servicioSanitario + ", TE: " + tipoEnergia + ", UC:" + usaCocinar + ", EB: " + eliminaBasura + ", Com: " + comunicacion);
    }

    public int getNroPropiedad() {
        return nroPropiedad;
    }

    public void setNroPropiedad(int nroPropiedad) {
        this.nroPropiedad = nroPropiedad;
    }

    public String getServicioSanitario() {
        return servicioSanitario;
    }

    public void setServicioSanitario(String servicioSanitario) {
        this.servicioSanitario = servicioSanitario;
    }

    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public String getUsaCocinar() {
        return usaCocinar;
    }

    public void setUsaCocinar(String usaCocinar) {
        this.usaCocinar = usaCocinar;
    }

    public String getEliminaBasura() {
        return eliminaBasura;
    }

    public void setEliminaBasura(String eliminaBasura) {
        this.eliminaBasura = eliminaBasura;
    }

    public String getComunicacion() {
        return comunicacion;
    }

    public void setComunicacion(String comunicacion) {
        this.comunicacion = comunicacion;
    }

}
