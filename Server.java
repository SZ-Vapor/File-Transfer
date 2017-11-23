
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    static OutputStream os;
    public static int fLength;
    static Socket socket;
    static File file1 = new File("D:\\XD.txt");
    static File file2 = new File("D:\\Pride and Prejudice.txt");
    static File file3 = new File("D:\\Lmao.txt");

    public static void main(String[] args) throws Exception {
        System.out.println("Running...");
        while (true) {
            ServerSocket ssock = new ServerSocket(50015);
            socket = ssock.accept();           
            sendFileNames();
           
            os = socket.getOutputStream();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String choice = inFromClient.readLine();
           
            if (choice.equals("")) {

                System.exit(0);
            } else if (choice.equals("1")) {
                byteStream(file1);
                System.out.println("File sent successfully!");
            } else if (choice.equals("2")) {
                byteStream(file2);
                System.out.println("File sent successfully!");
            } else if (choice.equals("3")) {
                byteStream(file3);
                System.out.println("File sent successfully!");
            } else {
                invalidIn();
                //System.out.println("Invalid Input.");
            }

            os.flush();
           
            socket.close();
            ssock.close();
            
        } 
    }

    private static void byteStream(File file) throws IOException {
       
        long fileLength = file.length();
        FileInputStream fInput = new FileInputStream(file);
        BufferedInputStream buffIn = new BufferedInputStream(fInput);

        fLength = (int) fileLength;//had to typecast to put into byte array
        byte[] fileByte = new byte[fLength];
        buffIn.read(fileByte, 0, fLength);
        os.write(fileByte);
    }

    private static void sendFileNames() throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        String sentence = "Available Files:  (1):" + file1.getName() + "   (2):" + file2.getName() + "   (3):" + file3.getName();
        outToClient.writeBytes(sentence + "\n");

    }

    private static void invalidIn() throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        String sentence = "Ivalid Input";
        outToClient.writeBytes(sentence + "\n");

    }

}
