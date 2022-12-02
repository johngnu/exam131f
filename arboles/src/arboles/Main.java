package arboles;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        // Colas viviendas
        CSimpleV c1 = new CSimpleV();
        c1.adicionar(new Vivienda(1, "Alcantarillado", "panel solar", "gas de garrafa", "Contenedor", "televisor"));
        c1.adicionar(new Vivienda(2, "Camara Septica", "motor propio", "Gas domiciliario", "Recoleccion", "computadora"));
        c1.adicionar(new Vivienda(3, "Alcantarillado", "Empresa electrica", "gas de garrafa", "contenedor", "radio"));
        c1.adicionar(new Vivienda(4, "Alcantarillado", "motor propio", "Leña", "Terreno baldio", "radio"));

        //c1.mostrar();

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
        m2.setM(new Municipio("La Paz", "Batallas", cr1, zu1));

        NodoM m3 = new NodoM();
        m3.setM(new Municipio("La Paz", "Viacha", cr1, zu1));

        NodoM m4 = new NodoM();
        m4.setM(new Municipio("La Paz", "Caranavi", cr1, zu1));

        NodoM m5 = new NodoM();
        m5.setM(new Municipio("La Paz", "Achacachi", cr1, zu1));

        NodoM m6 = new NodoM();
        m6.setM(new Municipio("La Paz", "Chulumani", cr1, zu1));

        // hojas
        m1.setDer(m2);
        m1.setIzq(m4);
        m2.setIzq(m3);
        m4.setIzq(m5);
        m4.setDer(m6);

        // ARBOL
        ArbolM a = new ArbolM();
        a.setRaiz(m1);

        a.niveles();

        solucionA(m1, "leña");

    }

    public static void solucionA(NodoM a, String x) {
        PilaM nivel = new PilaM();
        PilaM desc = new PilaM();
        nivel.adicionar(a);
        //int may = mayorNota(a);
        int c = 0;
        while (!nivel.esvacia()) {//paso de nivel
            while (!nivel.esvacia()) {
                NodoM r = nivel.eliminar();
                /*if (r.getQ().getNota() == may) {
                    c++;
                }
                if (r.getIzq() != null) {
                    desc.adicionar(r.getIzq());
                }
                if (r.getDer() != null) {
                    desc.adicionar(r.getDer());
                }*/
            }
            nivel.vaciar(desc);
        }
        System.out.println("La cantidad de estudiantes que tienen la mejor onoota es: " + c);
    }

}
