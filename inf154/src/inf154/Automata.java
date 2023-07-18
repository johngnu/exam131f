package inf154;

import java.util.Scanner;

public class Automata {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mt[][] = new int[10][10];
        mt[0][0] = 1;
        mt[0][1] = 9;
        mt[0][2] = 4;
        mt[0][3] = 9;
        mt[0][4] = 6;
        mt[0][5] = 9;
        mt[0][6] = 9;

        mt[1][1] = 2;
        mt[2][0] = 3;
        mt[3][2] = 4;
        mt[4][3] = 5;

        mt[5][4] = 6;
        mt[5][6] = 9;

        mt[6][3] = 9;
        mt[6][5] = 7;

        mt[7][0] = 8;

        mt[9][0] = 9;
        mt[9][1] = 9;
        mt[9][2] = 9;

        mt[9][3] = 9;
        mt[9][4] = 9;
        mt[9][5] = 9;

        mt[9][6] = 9;
        System.out.println("Escriba la cadena a evaluar: \n");
        String x = sc.next(); // sistemas
        int lon = x.length();
        int estado = 0;
        int i = 0;
        while (estado != 7 && estado != 8 && estado != 9 && i < lon) {
            int simbolo = conv(x.charAt(i));
            estado = mt[estado][simbolo];
            i++;
            System.out.println("estado: " + estado);
        }
        System.out.println("estado: " + estado);
        if (estado == 7 || estado == 8) {
            System.out.println("Cadena aceptada: " + x);
        } else {
            System.out.println("Cadena rechazada: " + x);
        }

    }

    public static int conv(char c) {
        switch (c) {
            case 's': return 0;
            case 'i': return 1;
            case 't': return 2;
            case 'e': return 3;
            case 'm': return 4;
            case 'a': return 5;
            default: return 6;
        }
    }
}
