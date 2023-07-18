package automata;

import java.io.*;

public class TAutSintactico {

    private String sigma;
    private int edoinicial, edofinal, no_edos;
    private int delta[][];
    private String tipo;
    //private int idlexema;

    TAutSintactico() {
        sigma = LeerAlfabeto();
        System.out.println(sigma);
        System.out.println(sigma.length());
        LeerDelta();
        tipo = "";
        //idlexema = 0;
    }

    private String LeerAlfabeto() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea = "";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("D:/DTM/alfaSintactico.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            linea = br.readLine();
            //System.out.println("Modo depuracion: " + linea);
            //System.out.println("Modo depuracion (tamanio cadena): " + linea.length());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return linea;
    }

    private void LeerDelta() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("D:/DTM/sintactico2.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea = "";
            int nfilas, ncol;

            // Lectura del fichero
            linea = br.readLine();
            System.out.println("Modo depuracion: " + linea + " (tamanio cadena): " + linea.length());
            nfilas = Integer.parseInt(linea);
            linea = br.readLine();
            System.out.println("Modo depuracion: " + linea + " (tamanio cadena): " + linea.length());
            ncol = Integer.parseInt(linea);
            System.out.println("Modo depuracion. No. filas: " + nfilas + " No. col: " + ncol);
            delta = new int[nfilas][ncol];
            edoinicial = 0;
            //edofinal=4;
            no_edos = nfilas;

            for (int i = -1; i < nfilas; i++) {
                String[] splited = linea.split(" ");
                for (int j = 0; j < splited.length; j++) {
                    if (i >= 0) {
                        delta[i][j] = Integer.parseInt(splited[j]);
                    }
                    //System.out.print(splited[j]+ " "); 
                }
                linea = br.readLine();
                //System.out.println();
            }

            /// Quitar
            System.out.println("Matriz delta");
            for (int i = 0; i < nfilas; i++) {
                for (int j = 0; j < ncol; j++) {
                    System.out.print(delta[i][j] + " ");
                }
                System.out.println();
            }
            /// Quitar	 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private int BuscaCar(char letra) {
        int indice;
        boolean encontrado = false;

        indice = 0;
        while (indice < sigma.length() && !encontrado) {
            if (letra == sigma.charAt(indice)) {
                encontrado = true;
            }
            indice++;
        }
        if (encontrado) {
            return --indice;
        } else {
            return -1;
        }
    }

    public boolean EvaluaAutomata(String cadena) {
        boolean aceptado = false;
        boolean error = false;
        int edoactual, edoanterior, ic, lc, j;

        ic = 0;
        lc = cadena.length();
        edoanterior = -1;
        System.out.println("Modo depuracion -  cadena: " + cadena);
        edoactual = edoinicial;
        while (ic < lc && !error) {
            System.out.println("Modo depuracion - Estado actual: " + edoactual + " entrada: " + cadena.charAt(ic));
            if (edoactual != -1) {
                edoanterior = edoactual;
                j = BuscaCar(cadena.charAt(ic));
                if (j != -1) {
                    edoactual = delta[edoanterior][j];
                } else {
                    error = true;
                }
            } else {
                error = true;
            }
            ic++;
        }
        System.out.println("Modo depuracion - Estado actual: " + edoactual + "\nEstado anterior: " + edoanterior);
        if (edoactual == 12 && !error) {
            aceptado = true;
            switch (edoactual) {
                case 12:
                    tipo = "Sentencia sintacticamente correcta";
                    //idlexema = 1;
                    System.out.println("Programa sintacticamente correcto");
                    break;
                /*default:
						tipo = "Identificador";
						idlexema = 3;
						//System.out.println(tipo);*/
            }
        } else {
            aceptado = false;
            if (edoactual == -1) {
                switch (edoanterior) {
                    case 1:
                        System.out.println("Se esperaba } | print | identificador | int | float");
                        break;
                    case 2:
                        System.out.println("Se esperaba un identificador");
                        break;
                    case 3:
                        System.out.println("Se esperaba un identificador");
                        break;
                    case 4:
                        System.out.println("Se esperaba (");
                        break;
                    case 5:
                        System.out.println("Se esperaba ;");
                        break;
                    case 6:
                        System.out.println("Se esperaba un identificador | numero entero | numero flotante");
                        break;
                    case 7:
                        System.out.println("Se esperaba )");
                        break;
                    case 8:
                        System.out.println("Se esperaba ;");
                        break;
                    case 9:
                        System.out.println("Se esperaba un =");
                        break;
                    case 10:
                        System.out.println("Se esperaba un identificador | numero entero | numero flotante");
                        break;
                    case 11:
                        System.out.println("Se esperaba un + | * ");
                        break;
                }
            } else {
                switch (edoactual) {
                    case 1:
                        System.out.println("Se esperaba } | print | identificador | int | float");
                        break;
                    case 2:
                        System.out.println("Se esperaba un identificador");
                        break;
                    case 3:
                        System.out.println("Se esperaba un identificador");
                        break;
                    case 4:
                        System.out.println("Se esperaba (");
                        break;
                    case 5:
                        System.out.println("Se esperaba ;");
                        break;
                    case 6:
                        System.out.println("Se esperaba un identificador | numero entero | numero flotante");
                        break;
                    case 7:
                        System.out.println("Se esperaba )");
                        break;
                    case 8:
                        System.out.println("Se esperaba ;");
                        break;
                    case 9:
                        System.out.println("Se esperaba un =");
                        break;
                    case 10:
                        System.out.println("Se esperaba un identificador | numero entero | numero flotante");
                        break;
                    case 11:
                        System.out.println("Se esperaba un + | * ");
                        break;
                }
            }
            //System.out.printf("Error, token no v%clido\n", 160); 
        }

        return aceptado;
    }

    public String getTipo() {
        return tipo;
    }
    /*
   public int getIdLexema(){
	   return idlexema;
   } */

} // Fin de la clase TAutomata
