package telematica;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Telematica {

    public static void main(String[] args) {
        try {
            int puerto = 9001;
            ServerSocket s = new ServerSocket(puerto);

            Socket sc = s.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            PrintWriter out = new PrintWriter(sc.getOutputStream(), true /* autoFlush */);

            out.println("Servidor iniciado...");

            // bucle indefinido
            boolean sw = false;
            while (!sw) {
                String line = in.readLine();
                if (line == null) {
                    sw = true;
                } else {
                    String clientInfo = "client ip: " + sc.getInetAddress().getHostAddress();
                    out.println(clientInfo + " eco: " + line);
                    if (line.trim().equals("")) {
                        sw = true;
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
