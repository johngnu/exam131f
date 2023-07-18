package telematica.tcp;

import java.io.DataInputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        String host = "localhost";
        try {
            Socket s = new Socket(host, 7777);
            DataInputStream in = null;
            in = new DataInputStream(s.getInputStream());
            String resp = in.readUTF();
            System.out.println(resp);
            s.close();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

    }

}
