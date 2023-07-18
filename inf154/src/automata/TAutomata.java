package automata;

import java.io.*;

public class TAutomata {

    private String sigma;
    private int edoinicial, edofinal, no_edos;
    private int delta[][];
    private String tipo;
    private int idlexema;

    TAutomata() {
        sigma = LeerAlfabeto();
        //System.out.println(sigma);
        //System.out.println(sigma.length());
        LeerDelta();
        tipo = "";
        idlexema = 0;
    }

    private String LeerAlfabeto() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea = "";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("D:/DTM/alfabeto.txt");
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
            archivo = new File("D:/DTM/lexico2.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea = "";
            int nfilas, ncol;

            // Lectura del fichero
            linea = br.readLine();
            //System.out.println("Modo depuracion: " + linea + " (tamanio cadena): " + linea.length());
            nfilas = Integer.parseInt(linea);
            linea = br.readLine();
            //System.out.println("Modo depuracion: " + linea + " (tamanio cadena): " + linea.length());
            ncol = Integer.parseInt(linea);
            //System.out.println("Modo depuracion. No. filas: " + nfilas + " No. col: " + ncol);
            delta = new int[nfilas][ncol];
            edoinicial = 0;
            //edofinal=4;
            no_edos = nfilas;

            for (int i = -1; i < nfilas; i++) {
                String[] splited = linea.split("\t");
                for (int j = 0; j < splited.length; j++) {
                    if (i >= 0) {
                        delta[i][j] = Integer.parseInt(splited[j]);
                    }
                    //System.out.print(splited[j]+ " "); 
                }
                linea = br.readLine();
                //System.out.println();
            }

            /*
	     /// Quitar
	     System.out.println("Matriz delta");
	     for (int i=0; i<nfilas; i++){
			 for (int j=0; j<ncol; j++){
				  System.out.print(delta[i][j] + " ");
			 } 
			 System.out.println();
	     }
	     /// Quitar	 
             */
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
        edoactual = edoinicial;
        while (ic < lc && !error) {
            edoanterior = edoactual;
            j = BuscaCar(cadena.charAt(ic));
            if (j != -1) {
                edoactual = delta[edoanterior][j];
            } else {
                error = true;
            }
            ic++;
        }
        if (edoactual > 0 && !error) {
            aceptado = true;
            switch (edoactual) {
                case 7:
                    tipo = "Palabra reservada int";
                    idlexema = 1;
                    //System.out.println(tipo);
                    break;
                case 10:
                    tipo = "Palabra reservada float";
                    idlexema = 2;
                    //System.out.println(tipo);
                    break;
                case 4:
                    tipo = "Numero entero";
                    idlexema = 4;
                    //System.out.println(tipo);
                    break;
                default:
                    tipo = "Identificador";
                    idlexema = 3;
                //System.out.println(tipo);
            }
        } else {
            aceptado = false;
            System.out.printf("Error, token no v%clido\n", 160);
        }

        return aceptado;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdLexema() {
        return idlexema;
    }

} // Fin de la clase TAutomata
