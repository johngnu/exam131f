package automata;

public class Principal {

    public static void main(String[] args) {
        boolean aceptado;
        String token, tipo, sintaxis;
        int idlexema;
        TAutomata lexico = new TAutomata();
        TablaSimbolos ts = new TablaSimbolos();
        TAutSintactico sintactico = new TAutSintactico();

        // Análisis Léxico
        token = "1234";
        aceptado = lexico.EvaluaAutomata(token);
        if (aceptado) {
            tipo = lexico.getTipo();
            idlexema = lexico.getIdLexema();
            System.out.printf("Token: %s\nTipo: %s\nLexema: %d\nAceptado: %b\n", token, tipo, idlexema, aceptado);
            ts.agregaFila(token, tipo, idlexema, 0, false, 0);
            System.out.println("\nContenido tabla de simbolos:");
            ts.muestaTS();
        }

        // Análisis Sintáctico
        //sintaxis = "81363546CD3E69";
        sintaxis = "823613613635763546CD3E35";
        aceptado = sintactico.EvaluaAutomata(sintaxis);
        System.out.println("Sintaxis correcta: " + aceptado);

    }

}  // Fin Principal
