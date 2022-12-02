package arboles;

import java.util.LinkedList;

public class Municipio {

    private String departamento, municipio;
    LinkedList<ComunidadRural> cr;
    LinkedList<ZonaUrbana> zu;

    public Municipio(String departamento, String municipio, LinkedList<ComunidadRural> cr, LinkedList<ZonaUrbana> zu) {
        this.departamento = departamento;
        this.municipio = municipio;
        this.cr = cr;
        this.zu = zu;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public LinkedList<ComunidadRural> getCr() {
        return cr;
    }

    public void setCr(LinkedList<ComunidadRural> cr) {
        this.cr = cr;
    }

    public LinkedList<ZonaUrbana> getZu() {
        return zu;
    }

    public void setZu(LinkedList<ZonaUrbana> zu) {
        this.zu = zu;
    }

    public void mostrar() {
        System.out.println("Depto: " + departamento + ", Muni: " + municipio);
        System.out.println("* Comunidades");
        for (ComunidadRural c : cr) {
            c.mostrar();
        }
        System.out.println("* Zonas urvanas");
        for (ZonaUrbana z : zu) {
            z.mostrar();
        }
    }

}
