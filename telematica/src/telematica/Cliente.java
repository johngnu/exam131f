package telematica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

    public static void main(String[] args) {

        final int PUERTO = 8888;
        byte[] buffer = new byte[1024];
        
        String cadena = "john castillo";

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            DatagramSocket socketUDP = new DatagramSocket();
            
            buffer = cadena.getBytes();
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO);

            socketUDP.send(pregunta); // envio de datagrama

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(peticion); // respuesta

            cadena = new String(peticion.getData());
            System.out.println(cadena);  // mostrar resultado

            socketUDP.close(); // fin del cliente
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}