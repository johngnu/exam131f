
package arboles;

import java.util.LinkedList;


public class Main {


    public static void main(String[] args) {
        // Colas viviendas
        CSimpleV c1 = new CSimpleV();
        c1.adicionar(new Vivienda(1, "si", "panel solar", "gas de garrafa", "si", "televisor"));
        c1.adicionar(new Vivienda(2, "si", "motor propio", "leña", "si", "computadora"));
        c1.adicionar(new Vivienda(1, "si", "panel solar", "gas de garrafa", "si", "televisor"));
        c1.adicionar(new Vivienda(2, "si", "motor propio", "leña", "si", "computadora"));
        c1.adicionar(new Vivienda(1, "si", "panel solar", "gas de garrafa", "si", "televisor"));
        c1.adicionar(new Vivienda(2, "si", "motor propio", "leña", "si", "computadora"));
        
        c1.mostrar();
        
        CSimpleV c2 = new CSimpleV();
        c2.adicionar(new Vivienda(3, "si", "panel solar", "gas de garrafa", "si", "televisor"));
        c2.adicionar(new Vivienda(4, "si", "motor propio", "leña", "si", "computadora"));
        
        // Linked Lists
        // Comunidades
        LinkedList<ComunidadRural> cr1 = new LinkedList<>();
        cr1.add(new ComunidadRural("Jupapina", c2));
        
        // Zonas
        LinkedList<ZonaUrbana> zu1 = new LinkedList<>();
        zu1.add(new ZonaUrbana("San Pedro", c1));
     
        // NODOS
        NodoM m1 = new NodoM();
        m1.setM(new Municipio("La Paz", "Murillo", cr1, zu1));
        
        NodoM m2 = new NodoM();
        m2.setM(new Municipio("La Paz", "Larecaja", cr1, zu1));
        
        NodoM m3 = new NodoM();
        m3.setM(new Municipio("La Paz", "Los Andes", cr1, zu1));
        
        m1.setDer(m2);
        m2.setIzq(m3);
        
        // ARBOL
        ArbolM a = new ArbolM();
        a.setRaiz(m1);
        
        a.niveles();
    }
    
}
