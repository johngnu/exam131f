package inf154;

import java.util.*;

/*  Ejemplos de cadenas:
   
    +55000 -5000
    -895.6 +451.1 15151 777e -e +e
    = += -= *= /= %= **= <<= >>= >>>= &= ^= |=
    a 4e 4ee 5.6 -5.6e int double string main extends asdfasd lll okui a 4e $ _ 6.5 -968 -e = += -= *=
    /= %= **= <<= >>= >>>= &= ^= |=
 */
public class Inf154 {

    enum Estado {
        INICIO, Q1, Q2, Q3, ENTERO, REAL, IDENTIFICADOR, OPERADORESASIGNACION,
        PALABRARESERVADA, CUALQUIER_OTRO
    }

    public static void main(String[] args) {
        System.out.println("Ingresar el(los) token(s) para reconocimiento: ");
        Scanner sc = new Scanner(System.in);
        AFD automata = new AFD();
        while (sc.hasNext()) {            
            String cadena = sc.next();
            String evalu = automata.operadorAsig(cadena);
            if (palabrasReservada(cadena)) {
                System.out.println("La cadena ingresada es una palabra reservada: " + cadena);
            } else {
                Estado ultimo = reconocerNumero(cadena);
                if (ultimo == Estado.ENTERO) {
                    System.out.println("La cadena ingresada es un numero entero: " + cadena);
                } else if (ultimo == Estado.REAL) {
                    System.out.println("La cadena ingresada es un numero real: " + cadena);
                } else if (ultimo == Estado.IDENTIFICADOR) {
                    System.out.println("La cadena ingresada es un identificador: " + cadena);
                } else if (evalu.equals("aceptada")) {
                    System.out.println("La cadena ingresada es un operador de asignacion: " + cadena);
                } else {
                    System.out.println("La cadena ingresada fue rechazada: " + cadena);
                }
            }
        }
    }

    public static Estado reconocerNumero(String cadena) {
        int pos = 0;
        Estado actual = Estado.INICIO;
        boolean cadenaRechazada = false;
        while (!cadenaRechazada && pos < cadena.length()) {
            char simbolo = cadena.charAt((pos));
            switch (actual) {
                case INICIO:
                    if (simbolo >= '0' && simbolo <= '9') {
                        actual = Estado.ENTERO;
                    } else {
                        if (simbolo == '+' || simbolo == '-') {
                            actual = Estado.Q1;
                        } else if (simbolo == 'e') {
                            actual = Estado.REAL;
                        } else if (simbolo == '$' || simbolo == '_' || (simbolo >= 'a'
                                && simbolo <= 'z')
                                || (simbolo >= 'A' && simbolo <= 'Z')) {
                            actual = Estado.IDENTIFICADOR;
                        } else {
                            cadenaRechazada = true;
                        }
                    }
                    break;
                case Q1:
                    if (simbolo >= '0' && simbolo <= '9') {
                        actual = Estado.ENTERO;
                    } else if (simbolo == '.') {
                        actual = Estado.Q2;
                    } else if (simbolo == 'e') {
                        actual = Estado.REAL;
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                case Q2:
                    if (simbolo >= '0' && simbolo <= '9') {
                        actual = Estado.REAL;
                    } else if (simbolo == 'e') {
                        actual = Estado.REAL;
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                case ENTERO:
                    if (simbolo >= '0' && simbolo <= '9') {
                        actual = Estado.ENTERO;
                    } else {
                        if (simbolo == '.') {
                            actual = Estado.Q2;
                        } else if (simbolo == 'e') {
                            actual = Estado.REAL;
                        } else {
                            cadenaRechazada = true;
                        }
                    }
                    break;
                case REAL:
                    if (simbolo >= '0' && simbolo <= '9') {
                        actual = Estado.REAL;
                    } else if (simbolo == 'e') {
                        actual = Estado.REAL;
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                case IDENTIFICADOR:
                    if (simbolo >= 'a' && simbolo <= 'z') {
                        actual = Estado.IDENTIFICADOR;
                    } else if ((simbolo >= 'A' && simbolo <= 'Z')) {
                        actual = Estado.IDENTIFICADOR;
                    } else if (simbolo == '$' || simbolo == '_') {
                        actual = Estado.IDENTIFICADOR;
                    } else if (simbolo >= '0' && simbolo <= '9') {
                        actual = Estado.IDENTIFICADOR;
                    } else {
                        cadenaRechazada = true;
                    }
                    break;
                case OPERADORESASIGNACION:
                    break;
            }
            pos++;
        }
        if (cadenaRechazada) {
            return Estado.CUALQUIER_OTRO;
        }
        return actual;
    }

    public static int convierteNO(char val) {
        int simb = -1;
        switch (val) {
            case '=':
                simb = 0;
                break;
            case '+':
                simb = 1;
                break;
            case '-':
                simb = 2;
                break;
            case '*':
                simb = 3;
                break;
            case '/':
                simb = 4;
                break;
            case '%':
                simb = 5;
                break;
            case '<':
                simb = 6;
                break;
            case '>':
                simb = 7;
                break;
            case '&':
                simb = 8;
                break;
            case '^':
                simb = 9;
                break;
            case '|':
                simb = 10;
                break;
            case ' ':
                simb = 11;
                break;
        }
        return simb;
    }

    public static boolean palabrasReservada(String x) {
        x = x.toLowerCase();
        boolean sw = false;
        
        if (x.equals("while")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 5 && estado != 4 && estado != -1 && i < x.length()) {
                simbolo = convWhile(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 4) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("do")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 2 && estado != 1 && estado != -1 && i < x.length()) {
                simbolo = convDo(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 1) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("for")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 3 && estado != 2 && estado != -1 && i < x.length()) {
                simbolo = convFor(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 2) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("class")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][3] = 5;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 5 && estado != 4 && estado != -1 && i < x.length()) {
                simbolo = convClass(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 4) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("return")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][0] = 5;
            nt[5][5] = 6;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 6 && estado != 5 && estado != -1 && i < x.length()) {
                simbolo = convReturn(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 5) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("protected")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            nt[5][5] = 6;
            nt[6][3] = 7;
            nt[7][4] = 8;
            nt[8][8] = 9;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 9 && estado != 8 && estado != -1 && i < x.length()) {
                simbolo = convProtected(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 8) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("public")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            nt[5][5] = 6;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 6 && estado != 5 && estado != -1 && i < x.length()) {
                simbolo = convPublic(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 5) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("float")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 5 && estado != 4 && estado != -1 && i < x.length()) {
                simbolo = convFloat(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 4) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        if (x.equals("default")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            nt[5][5] = 6;
            nt[6][3] = 7;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 7 && estado != 6 && estado != -1 && i < x.length()) {
                simbolo = convDefault(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 6) {
                sw = true;
            } else if (estado == -1) {
                sw = false;
            }
        }
        
        return sw;
    }

    /* whilw */
    private static int convWhile(char y) {
        int simb = -1;
        switch (y) {
            case 'w':
                simb = 0;
                break;
            case 'h':
                simb = 1;
                break;
            case 'i':
                simb = 2;
                break;
            case 'l':
                simb = 3;
                break;
            case 'e':
                simb = 4;
                break;
        }
        return simb;
    }
    
    /* do */
    private static int convDo(char y) {
        int simb = -1;
        switch (y) {
            case 'd':
                simb = 0;
                break;
            case '0':
                simb = 1;
                break;
        }
        return simb;
    }
    
    /* for */
    private static int convFor(char y) {
        int simb = -1;
        switch (y) {
            case 'f':
                simb = 0;
                break;
            case 'o':
                simb = 1;
                break;
            case 'r':
                simb = 2;
                break;
        }
        return simb;
    }
    
    /* class */
    private static int convClass(char y) {
        int simb = -1;
        switch (y) {
            case 'c':
                simb = 0;
                break;
            case 'l':
                simb = 1;
                break;
            case 'a':
                simb = 2;
                break;
            case 's':
                simb = 3;
                break;    
        }
        return simb;
    }
    
    /* return */
    private static int convReturn(char y) {
        int simb = -1;
        switch (y) {
            case 'r':
                simb = 0;
                break;
            case 'e':
                simb = 1;
                break;
            case 't':
                simb = 2;
                break;
            case 'u':
                simb = 3;
                break;
            case 'n':
                simb = 4;
                break;
        }
        return simb;
    }
    
    /* float */
    private static int convFloat(char y) {
        int simb = -1;
        switch (y) {
            case 'f':
                simb = 0;
                break;
            case 'l':
                simb = 1;
                break;
            case 'o':
                simb = 2;
                break;
            case 'a':
                simb = 3;
                break;
            case 't':
                simb = 4;
                break;
        }
        return simb;
    }
    
    /* public */
    private static int convPublic(char y) {
        int simb = -1;
        switch (y) {
            case 'p':
                simb = 0;
                break;
            case 'u':
                simb = 1;
                break;
            case 'b':
                simb = 2;
                break;
            case 'l':
                simb = 3;
                break;
            case 'i':
                simb = 4;
                break;
            case 'c':
                simb = 5;
                break;
        }
        return simb;
    }
    
    /* protected */
    private static int convProtected(char y) {
        int simb = -1;
        switch (y) {
            case 'p':
                simb = 0;
                break;
            case 'r':
                simb = 1;
                break;
            case 'o':
                simb = 2;
                break;
            case 't':
                simb = 3;
                break;
            case 'e':
                simb = 4;
                break;
            case 'c':
                simb = 5;
                break;
            case 'd':
                simb = 6;
                break;    
        }
        return simb;
    }
        
    /* default */
    private static int convDefault(char y) {
        int simb = -1;
        switch (y) {
            case 'd':
                simb = 0;
                break;
            case 'e':
                simb = 1;
                break;
            case 'f':
                simb = 2;
                break;
            case 'a':
                simb = 3;
                break;
            case 'u':
                simb = 4;
                break;
            case 'l':
                simb = 5;
                break;
            case 't':
                simb = 6;
                break;    
        }
        return simb;
    }
}
