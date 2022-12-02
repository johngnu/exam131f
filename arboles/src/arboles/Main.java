
package arboles;


public class Main {


    public static void main(String[] args) {
        // Colas viviendas
        CSimpleV c1 = new CSimpleV();
        c1.adicionar(new Vivienda(1, "si", "panel solar", "gas de garrafa", "si", "televisor"));
        c1.adicionar(new Vivienda(2, "si", "motor propio", "leña", "si", "computadora"));
        
        c1.mostrar();
        
        CSimpleV c2 = new CSimpleV();
        c2.adicionar(new Vivienda(3, "si", "panel solar", "gas de garrafa", "si", "televisor"));
        c2.adicionar(new Vivienda(4, "si", "motor propio", "leña", "si", "computadora"));
        
        
    }
    
}
