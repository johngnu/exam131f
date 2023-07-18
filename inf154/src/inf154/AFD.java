package inf154;

public class AFD {

    static char[] caracteres;
    boolean aceptacion;
    int count;

    public static String operadorAsig(String cad) {
        AFD automata = new AFD();
        String res = "";
        caracteres = cad.toCharArray();
        automata.Inicial();
        if (automata.aceptacion) {
            res = "aceptada";
        } else {
            res = "La Cadena fue rechazada";
        }
        return res;
    }

    public void Inicial() {
        count = 0; // Valor inicial
        q0();
    }

    public void q0() {
        aceptacion = false;
        if (count < caracteres.length) {
            if (caracteres[count] == '>' || caracteres[count] == '<') { // Si es el caracter que define el automata para la transicion
                count++;
                q0(); // Transiciones si vale 'a' se mantenga en el estado q0
            } else if (caracteres[count] == '=') {
                count++;
                q1();
            } else if (caracteres[count] == '&') {
                count++;
                q2();
            } else if (caracteres[count] == '+' || caracteres[count] == '-' || caracteres[count] == '*' || caracteres[count] == '/' || caracteres[count] == '%') {
                count++;
                q2();
            } else if (caracteres[count] == '^') {
                count++;
                q2();
            } else if (caracteres[count] == '|') {
                count++;
                q2();
            }
        }
    }

    public void q1() {
        aceptacion = true;
        if (count < caracteres.length) {
            if (caracteres[count] == '=') {
                count++;
                q2();
            } else if (caracteres[count] == ' ') {
                count++;
                q2();
            }
        }
    }

    public void q2() {
        aceptacion = true;
        if (count < caracteres.length) {
            if (caracteres[count] == '=') {
                count++;
                q2();
            } else if (caracteres[count] == ' ') {
                count++;
                q2();
                return;
            }
        }
    }
}
