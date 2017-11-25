
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class FileClient {

    static Socket socket;
    static Socket socket2;

    public static void main(String[] args) throws Exception {
        socket2 = new Socket(InetAddress.getLocalHost(), 50016);
            BufferedReader inFromServer;
            printFiles();

        while (true) {
            socket = new Socket(InetAddress.getLocalHost(), 50015);

            InputStream is = socket.getInputStream();

            byte[] contents = new byte[10000];
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            System.out.print("Enter the number next to one of the file names to receive it.(Enter nothing to terminate the program): ");
            String sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');

            FileOutputStream fos;

            int bytesRead = 0;
            if (sentence.equals("")) {
                break;
                //System.exit(0);
            } else if (sentence.equals("1")) {
                File file = new File("D:\\Test\\Output\\XD2.txt");
                fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) > 0) {
                    bos.write(contents, 0, bytesRead);
                }
                System.out.println("File saved to "+file.getAbsolutePath()+"\n");

                bos.flush();
            } else if (sentence.equals("2")) {
                File file = new File("D:\\Test\\Output\\Pride and Prejudice 2.txt");
                fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) > 0) {
                    bos.write(contents, 0, bytesRead);
                }
                System.out.println("File saved to "+file.getAbsolutePath()+"\n");

                bos.flush();
            } else if (sentence.equals("3")) {
                File file = new File("D:\\Test\\Output\\Lmao2.txt");
                fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) > 0) {
                    bos.write(contents, 0, bytesRead);
                }
                System.out.println("File saved to "+file.getAbsolutePath()+"\n");

                bos.flush();
            } else {
                inFromServer
                        = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String invalid = inFromServer.readLine();
                System.out.println(invalid);
            }
        }

        socket.close();
    }

    private static void printFiles() throws IOException {
       BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket2.getInputStream()));

        String files = inFromServer.readLine();
        System.out.println(files+"\n");
    }
}
