package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class serverThread extends Thread {
    private Socket socket;
    private ArrayList<serverThread> threadList;
    private PrintWriter output;

    public serverThread(Socket socket, ArrayList<serverThread> threads) {
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String outputString = input.readLine();

                if (outputString.equals("exit")) {
                    break;
                }
                printToAllClients(outputString);
                System.out.println("Server received" + outputString);
            }
        } catch (Exception e) {
            System.out.println("Error occurred " + e.getStackTrace());
        }
    }

    private void printToAllClients(String outString) {
        for (serverThread sT : threadList) {
            sT.output.println(outString);
        }
    }
}
