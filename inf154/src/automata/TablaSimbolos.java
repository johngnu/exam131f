package automata;

import java.util.ArrayList;
import java.util.Iterator;

public class TablaSimbolos {

    private ArrayList<FilaTS> ts;

    TablaSimbolos() {
        ts = new ArrayList<>();
    }

    public void agregaFila(String token, String tipo, int idlexema, int nolinea, boolean refprevia, int dirmem) {
        FilaTS fila = new FilaTS(token, tipo, idlexema, nolinea, refprevia, dirmem);
        ts.add(fila);
    }

    public void muestaTS() {
        Iterator<FilaTS> it = ts.iterator();
        FilaTS fila = new FilaTS();
        while (it.hasNext()) {
            fila = it.next();
            System.out.println(fila.toString());
        }
    }

} // Fin clase TablaSimbolos

