package arboles;

public class ArbolM {

    private NodoM raiz;

    public NodoM getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoM raiz) {
        this.raiz = raiz;
    }

    //nivel 
    public void niveles() {
        PilaM nivel = new PilaM();
        PilaM desc = new PilaM();
        nivel.adicionar(getRaiz());
        int c = 0;
        while (!nivel.esvacia()) {//paso de nivel
            System.out.println("Nivel: " + c + " >>");
            while (!nivel.esvacia()) {

                NodoM r = nivel.eliminar();
                r.getM().mostrar();
                if (r.getIzq() != null) {
                    desc.adicionar(r.getIzq());
                }
                if (r.getDer() != null) {
                    desc.adicionar(r.getDer());
                }
            }
            nivel.vaciar(desc);
            c++;
            System.out.println("");
        }
    }

    // preorden RID
    public void preorden(NodoM r) {
        if (r != null) {
            r.getM().mostrar();
            preorden(r.getIzq());
            preorden(r.getDer());
        }
    }

    // Inorden IRD
    public void inorden(NodoM r) {
        if (r != null) {
            inorden(r.getIzq());
            r.getM().mostrar();
            inorden(r.getDer());
        }
    }

    // postorden IDR
    public void posorden(NodoM r) {
        if (r != null) {
            posorden(r.getIzq());
            posorden(r.getDer());
            r.getM().mostrar();
        }
    }
}
