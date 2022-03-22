import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class client {
    public static void main(String[] args) {
        Socket soc = null;
        InputStreamReader input = null;
        OutputStreamWriter output = null;
        BufferedReader read = null;
        BufferedWriter write = null;

        try {
            soc = new Socket("localhost", 1234);

            input = new InputStreamReader(soc.getInputStream());
            output = new OutputStreamWriter(soc.getOutputStream());

            read = new BufferedReader(input);
            write = new BufferedWriter(output);

            Scanner scan = new Scanner(System.in);

            while (true) {
                String msg = scan.nextLine();
                write.write(msg);
                write.newLine();
                write.flush();

                System.out.println("Server: " + read.readLine());

                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (soc != null) {
                    soc.close();
                }
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
                if (read != null) {
                    read.close();
                }
                if (write != null) {
                    write.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}