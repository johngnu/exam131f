package arboles;

public class CSimpleV {

    private int max = 40;
    private Vivienda v[] = new Vivienda[max + 1];
    private int ini, fin;

    public CSimpleV() {
        ini = fin = 0;
    }

    public boolean esvacia() {
        if (ini == 0 && fin == 0) {
            return (true);
        }
        return (false);
    }

    public boolean esllena() {
        if (fin == max) {
            return (true);
        }
        return (false);
    }

    public int nroelem() {
        return (fin - ini);
    }

    public void adicionar(Vivienda elem) {
        if (!esllena()) {
            fin++;
            v[fin] = elem;
        } else {
            System.out.println("Cola Simple llena");
        }
    }

    public Vivienda eliminar() {
        Vivienda elem = null;
        if (!esvacia()) {
            ini++;
            elem = v[ini];
            if (ini == fin) {
                ini = fin = 0;
            }
        } else {
            System.out.println("Cola Simple vacia");
        }
        return (elem);
    }

    public void mostrar() {
        Vivienda elem;
        if (esvacia()) {
            System.out.println("Cola vacia");
        } else {
            System.out.println("Datos de la Cola ESCENARIOS: ");
            CSimpleV aux = new CSimpleV();
            while (!esvacia()) {
                elem = eliminar();
                aux.adicionar(elem);
                elem.mostrar();
            }
            while (!aux.esvacia()) {
                elem = aux.eliminar();
                adicionar(elem);
            }
        }
    }

    public void vaciar(CSimpleV a) {
        while (!a.esvacia()) {
            adicionar(a.eliminar());
        }
    }
}
