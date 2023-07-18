package telematica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

    public static void main(String[] args) {

        final int PUERTO = 8888;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciando el servidor UDP");
            // Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            boolean sw = true;
            while (sw) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                // Recibo del datagrama
                socketUDP.receive(peticion);

                String mensaje = new String(peticion.getData());
                System.out.println(mensaje); // mostrra mensaje enviado

                // Obtengo el puerto y la direccion de origen
                // Sino se quiere responder, no es necesario
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                mensaje = contarVocales(mensaje);
                System.out.println(mensaje);
                buffer = mensaje.getBytes();

                // Creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                // Envio la información
                System.out.println("Envio la informacion del cliente");
                socketUDP.send(respuesta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String contarVocales(String c) {
        int ca, ce, ci, co, cu;
        // contadores en 0
        ca = ce = ci = co = cu = 0;
        for (int i = 0; i < c.length(); i++) {
            char ch = c.charAt(i);
            if (ch == 'a') {
                ca++;
            }
            if (ch == 'e') {
                ce++;
            }
            if (ch == 'i') {
                ci++;
            }
            if (ch == 'o') {
                co++;
            }
            if (ch == 'u') {
                cu++;
            }
        }
        // mostrando respuesta
        return "a=" + ca + " e=" + ce + " i=" + ci + " o=" + co + " u=" + cu;
    }
}
