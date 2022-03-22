package Server;

import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader input = null;
        OutputStreamWriter output = null;
        BufferedReader read = null;
        BufferedWriter write = null;

        ServerSocket server = null;

        server = new ServerSocket(1234);

        try {
            socket = server.accept();
            input = new InputStreamReader(socket.getInputStream());
            output = new OutputStreamWriter(socket.getOutputStream());
            read = new BufferedReader(input);
            write = new BufferedWriter(output);
            while (true) {
                String msgClient = read.readLine();
                System.out.println("Client: " + msgClient);

                write.write("Message Received");
                write.newLine();
                write.flush();

                if (msgClient.equalsIgnoreCase("Bye")) {
                    break;
                }

            }
            socket.close();
            input.close();
            output.close();
            read.close();
            write.close();

        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}