package arboles;

public class ZonaUrbana {

    private String nomZona;
    private CSimpleV c;

    public ZonaUrbana(String nomZona, CSimpleV c) {
        this.nomZona = nomZona;
        this.c = c;
    }

    public String getNomZona() {
        return nomZona;
    }

    public void setNomZona(String nomZona) {
        this.nomZona = nomZona;
    }

    public CSimpleV getC() {
        return c;
    }

    public void setC(CSimpleV c) {
        this.c = c;
    }

}
