package arboles;

public class PilaM {

    private int max = 50;
    private NodoM v[] = new NodoM[max + 1];
    private int tope;

    PilaM() {
        tope = 0;
    }

    boolean esvacia() {
        if (tope == 0) {
            return (true);
        }
        return (false);
    }

    boolean esllena() {
        if (tope == max) {
            return (true);
        }
        return (false);
    }

    int nroelem() {
        return (tope);
    }

    void adicionar(NodoM elem) {
        if (!esllena()) {
            tope++;     //v[tope+1]=elem
            v[tope] = elem;  //tope++
        } else {
            System.out.println("Pila llena");
        }
    }

    NodoM eliminar() {
        NodoM elem = null;
        if (!esvacia()) {
            elem = v[tope];
            tope--;
        } else {
            System.out.println("Pila vacia");
        }
        return (elem);
    }

    void vaciar(PilaM a) {
        while (!a.esvacia()) {
            adicionar(a.eliminar());
        }
    }
}
