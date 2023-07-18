package automata;

public class FilaTS {

    private String token;
    private String tipo;
    private int idlexema;
    private int nolinea;
    private boolean refprevia;
    private int dirmem;

    FilaTS() {
        token = "";
        tipo = "";
        idlexema = 0;
        nolinea = 0;
        refprevia = false;
        dirmem = 0;
    }

    FilaTS(String token, String tipo, int idlexema, int nolinea, boolean refprevia, int dirmem) {
        SetModificaTodo(token, tipo, idlexema, nolinea, refprevia, dirmem);
    }

    // Métodos Get
    public String GetToken() {
        return token;
    }

    public String GetTipo() {
        return tipo;
    }

    public int GetIdLexema() {
        return idlexema;
    }

    public int GetNoLinea() {
        return nolinea;
    }

    public boolean GetRefPrevia() {
        return refprevia;
    }

    public int GetDirMem() {
        return dirmem;
    }

    // Métodos Set
    public void SetModificaTodo(String token, String tipo, int idlexema, int nolinea, boolean refprevia, int dirmem) {
        this.token = token;
        this.tipo = tipo;
        this.idlexema = idlexema;
        this.nolinea = nolinea;
        this.refprevia = refprevia;
        this.dirmem = dirmem;
    }

    public void SetToken(String token) {
        this.token = token;
    }

    public void SetTipo(String tipo) {
        this.tipo = tipo;
    }

    public void SetIdLexema(int idlexema) {
        this.idlexema = idlexema;
    }

    public void SetNoLinea(int nolinea) {
        this.nolinea = nolinea;
    }

    public void SetRefPrevia(boolean refprevia) {
        this.refprevia = refprevia;
    }

    public void SetDirMem(int dirmem) {
        this.dirmem = dirmem;
    }

    @Override
    public String toString() {
        String cadena = "";

        cadena = "Token: " + GetToken() + "\n"
                + "Tipo: " + GetTipo() + "\n"
                + "Identificador del lexema: " + Integer.toString(GetIdLexema()) + "\n"
                + "Numero de linea: " + Integer.toString(GetNoLinea()) + "\n"
                + "Existe referencia previa: " + Boolean.toString(GetRefPrevia()) + "\n"
                + "Direccion de memoria interna del token: " + Integer.toString(GetDirMem()) + "\n";
        return cadena;
    }

}  // Fin FilaTS
