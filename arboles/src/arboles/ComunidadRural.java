package arboles;

public class ComunidadRural {

    private String nomComunidad;
    private CSimpleV c;

    public ComunidadRural(String nomComunidad, CSimpleV c) {
        this.nomComunidad = nomComunidad;
        this.c = c;
    }

    public String getNomComunidad() {
        return nomComunidad;
    }

    public void setNomComunidad(String nomComunidad) {
        this.nomComunidad = nomComunidad;
    }

    public CSimpleV getC() {
        return c;
    }

    public void setC(CSimpleV c) {
        this.c = c;
    }

}
