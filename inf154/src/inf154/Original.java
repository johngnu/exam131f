package inf154;

import java.util.*;

/*
    +55000 -5000
    -895.6 +451.1 15151 777e -e +e
    _ $ a1 asd 123 as32
    = += -= *= /= %= **= <<= >>= >>>= &= ^= |=
    a 4e 4ee 5.6 -5.6e int double string main extends asdfasd lll okui a 4e $ _ 6.5 -968 -e = += -= *=
    /= %= **= <<= >>= >>>= &= ^= |=
    a* gg= .=
    5ass5d552233.==
 */
public class Original {

    enum Estado {
        INICIO, Q1, Q2, Q3, ENTERO, REAL, IDENTIFICADOR, OPERADORESASIGNACION,
        PALABRARESERVADA, CUALQUIER_OTRO
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AFD automata = new AFD();
        while (sc.hasNext()) {
            // System.out.println("Programa para reconocer cadenas");
            String cadena = sc.next();
            String pal = PalabrasReservada(cadena);
            String evalu = automata.operadorAsig(cadena);
            if (pal.equals("Palabra reservada!")) {
                System.out.println("La cadena ingresada es una palabra reservada");
            } else {
                Estado ultimo = ReconocerNumero(cadena);
                if (ultimo == Estado.ENTERO) {
                    System.out.println("La cadena ingresada es un numero entero");
                } else if (ultimo == Estado.REAL) {
                    System.out.println("La cadena ingresada es un numero real");
                } else if (ultimo == Estado.IDENTIFICADOR) {
                    System.out.println("La cadena ingresada es un identificador");
                } else if (evalu.equals("aceptada")) {
                    System.out.println("La cadena ingresada es un operador de asignacion");
                } else {
                    System.out.println("La cadena ingresada fue rechazada");
                }
            }
        }
        // operadoresAsignacion();
    }

    public static Estado ReconocerNumero(String cadena) {
        int pos = 0;
        Estado actual = Estado.INICIO;
        boolean cadenaRechazada = false;
        while (!cadenaRechazada && pos < cadena.length()) {
            char simbolo = cadena.charAt((pos));
            // System.out.println(simbolo);
            // if(simbolo=='e') {
            // System.out.println("Es igual"); }
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

    /*
        * public static void operadoresAsignacion() {
        * System.out.println("INSERTE CADENA!:"); Scanner sc = new Scanner(System.in);
        * String cadena=sc.next(); int[][] mt=new int[50][50];
        * mt[0][0]=8;mt[0][1]=10;mt[0][2]=13; mt[0][3]=16;mt[0][4]=19;mt[0][5]=22;
        * mt[0][6]=29;mt[0][7]=33;mt[0][8]=42; mt[0][9]=45;mt[0][10]=48; mt[3][0]=11;
        * mt[1][11]=9; mt[4][11]=12; mt[6][0]=14; mt[7][11]=15; mt[9][0]=17;
        * mt[10][11]=18; mt[12][0]=20; mt[13][11]=21; mt[15][0]=23; mt[16][11]=24;
        * mt[18][3]=26; mt[19][0]=27; mt[20][11]=28; mt[22][6]=30; mt[23][0]=31;
        * mt[24][11]=32; mt[26][7]=34; mt[27][0]=35; mt[28][11]=36; mt[30][7]=38;
        * mt[31][7]=39; mt[32][0]=40; mt[33][11]=41; mt[35][0]=43; mt[36][11]=44;
        * mt[38][0]=46; mt[39][11]=47; mt[41][0]=49; mt[42][11]=50; int estado=0; int
        * i=0;
        *
        * int caracter; while ( //estado!=9&&estado!=12&& //estado!=15&&estado!=18&&
        * //estado!=21&&estado!=24&& //estado!=28&&estado!=32&&
        * //estado!=36&&estado!=41&& //estado!=44&&estado!=47&& //estado!=50&&
        * estado!=50&&estado!=50&& estado!=-1&&i<cadena.length()) { caracter=(char)
        * convierteNO(cadena.charAt(i)); estado=mt[estado][caracter]; i++; } if
        * (estado==7||estado==9||estado==12|| estado==15||estado==18||
        * estado==21||estado==24|| estado==28||estado==32|| estado==36||estado==41||
        * estado==4||estado==47|| estado==50) {
        * System.out.println("La Cadena ingresada es un operador de asignacion ");
        * }else { System.out.println("Cadena Rechazada"); } }
     */
    public static String PalabrasReservada(String x) {
        //Scanner lee = new Scanner(System.in);
        int j = 0;
        String res = "";
        // while (lee.hasNext()) {
        /// System.out.println("Ingrese una palabra o escriba fin para acabar el
        // proceso");
        // String x = lee.next();
        x = x.toLowerCase();
        if (x.equals("string")) {
            int estado = 0;
            int i = 0, simbolo;
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[0][5] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            nt[5][5] = 6;
            while (estado != 6 && estado != 5 && estado != -1 && i < x.length()) {
                simbolo = convierte(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 5) {
                res = "Palabra reservada!";
                if (estado == -1) {
                    res = "Cadena Rechazada";
                }
            }
        }
        if (x.equals("switch")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[0][5] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            nt[5][5] = 6;
            x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 6 && estado != 5 && estado != -1 && i < x.length()) {
                simbolo = convierte1(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 5) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("case")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;            
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 4 && estado != 3 && estado != -1 && i < x.length()) {
                simbolo = convierte2(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            // System.out.println(estado);
            if (estado == 3) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("boolean")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[0][5] = -1;
            nt[1][1] = 2;
            nt[2][1] = 3;
            nt[3][2] = 4;
            nt[4][3] = 5;
            nt[5][4] = 6;
            nt[6][5] = 7;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 8 && estado != 7 && estado != -1 && i < x.length()) {
                simbolo = convierte3(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            System.out.print(estado);
            if (estado == 7) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("double")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[0][5] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            nt[5][5] = 6;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 6 && estado != 5 && estado != -1 && i < x.length()) {
                simbolo = convierte4(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 5) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("int")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 3 && estado != 2 && estado != -1 && i < x.length()) {
                simbolo = convierte5(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 2) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("new")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 3 && estado != 2 && estado != -1 && i < x.length()) {
                simbolo = convierte6(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 2) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("extends")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[0][5] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][0] = 4;
            nt[4][3] = 5;
            nt[5][4] = 6;
            nt[6][5] = 7;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 8 && estado != 7 && estado != -1 && i < x.length()) {
                simbolo = convierte7(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 7) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("this")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 5 && estado != 4 && estado != -1 && i < x.length()) {
                simbolo = convierte8(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 4) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("main")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 5 && estado != 4 && estado != -1 && i < x.length()) {
                simbolo = convierte9(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 4) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("true")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 5 && estado != 4 && estado != -1 && i < x.length()) {
                simbolo = convierte10(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 4) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        if (x.equals("false")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[0][2] = -1;
            nt[0][4] = -1;
            nt[0][1] = -1;
            nt[0][3] = -1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            // String x = lee.next();
            // x = x.toLowerCase();
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 5 && estado != 4 && estado != -1 && i < x.length()) {
                simbolo = convierte11(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 4) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        
        // while
        if (x.equals("while")) {
            int nt[][] = new int[10][10];
            nt[0][0] = 1;
            nt[1][1] = 2;
            nt[2][2] = 3;
            nt[3][3] = 4;
            nt[4][4] = 5;
            int estado = 0;
            int i = 0, simbolo;
            while (estado != 6 && estado != 5 && estado != -1 && i < x.length()) {
                simbolo = convWhile(x.charAt(i));
                estado = nt[estado][simbolo];
                i++;
            }
            if (estado == 5) {
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        
        // protected
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
                res = "Palabra reservada!";
            } else if (estado == -1) {
                res = "Cadena Rechazada";
            }
        }
        
        return res;

    }

    private static int convierte(char y) {
        int simb = -1;
        switch (y) {
            case 's':
                simb = 0;
                break;
            case 't':
                simb = 1;
                break;
            case 'r':
                simb = 2;
                break;
            case 'i':
                simb = 3;
                break;
            case 'n':
                simb = 4;
                break;
            case 'g':
                simb = 5;
                break;
        }
        return simb;
    }
    
    private static int convierte1(char y) {
        int simb = -1;
        switch (y) {
            case 's':
                simb = 0;
                break;
            case 'w':
                simb = 1;
                break;
            case 'i':
                simb = 2;
                break;
            case 't':
                simb = 3;
                break;
            case 'c':
                simb = 4;
                break;
            case 'h':
                simb = 5;
                break;
        }
        return simb;
    }

    private static int convierte2(char y) {
        int simb = -1;
        switch (y) {
            case 'c':
                simb = 0;
                break;
            case 'a':
                simb = 1;
                break;
            case 's':
                simb = 2;
                break;
            case 'e':
                simb = 3;
                break;
        }
        return simb;
    }

    private static int convierte3(char y) {
        int simb = -1;
        switch (y) {
            case 'b':
                simb = 0;
                break;
            case 'o':
                simb = 1;
                break;
            case 'l':
                simb = 2;
                break;
            case 'e':
                simb = 3;
                break;
            case 'a':
                simb = 4;
                break;
            case 'n':
                simb = 5;
                break;
        }
        return simb;
    }

    private static int convierte4(char y) {
        int simb = -1;
        switch (y) {
            case 'd':
                simb = 0;
                break;
            case 'o':
                simb = 1;
                break;
            case 'u':
                simb = 2;
                break;
            case 'b':
                simb = 3;
                break;
            case 'l':
                simb = 4;
                break;
            case 'e':
                simb = 5;
                break;
        }
        return simb;
    }

    private static int convierte5(char y) {
        int simb = -1;
        switch (y) {
            case 'i':
                simb = 0;
                break;
            case 'n':
                simb = 1;
                break;
            case 't':
                simb = 2;
                break;
        }
        return simb;
    }

    private static int convierte6(char y) {
        int simb = -1;
        switch (y) {
            case 'n':
                simb = 0;
                break;
            case 'e':
                simb = 1;
                break;
            case 'w':
                simb = 2;
                break;
        }
        return simb;
    }

    private static int convierte7(char y) {
        int simb = -1;
        switch (y) {
            case 'e':
                simb = 0;
                break;
            case 'x':
                simb = 1;
                break;
            case 't':
                simb = 2;
                break;
            case 'n':
                simb = 3;
                break;
            case 'd':
                simb = 4;
                break;
            case 's':
                simb = 5;
                break;
        }
        return simb;
    }

    private static int convierte8(char y) {
        int simb = -1;
        switch (y) {
            case 't':
                simb = 0;
                break;
            case 'h':
                simb = 1;
                break;
            case 'i':
                simb = 2;
                break;
            case 's':
                simb = 3;
                break;
        }
        return simb;
    }

    private static int convierte9(char y) {
        int simb = -1;
        switch (y) {
            case 'm':
                simb = 0;
                break;
            case 'a':
                simb = 1;
                break;
            case 'i':
                simb = 2;
                break;
            case 'n':
                simb = 3;
                break;
        }
        return simb;
    }

    private static int convierte10(char y) {
        int simb = -1;
        switch (y) {
            case 't':
                simb = 0;
                break;
            case 'r':
                simb = 1;
                break;
            case 'u':
                simb = 2;
                break;
            case 'e':
                simb = 3;
                break;
        }
        return simb;
    }

    private static int convierte11(char y) {
        int simb = -1;
        switch (y) {
            case 'f':
                simb = 0;
                break;
            case 'a':
                simb = 1;
                break;
            case 'l':
                simb = 2;
                break;
            case 's':
                simb = 3;
                break;
            case 'e':
                simb = 4;
                break;
        }
        return simb;
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
