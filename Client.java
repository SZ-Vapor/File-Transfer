
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class FileClient {

    static Socket socket;

    public static void main(String[] args) throws Exception {

        while (true) {
            socket = new Socket(InetAddress.getLocalHost(), 50015);

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String files = inFromServer.readLine();
            System.out.println(files);

            InputStream is = socket.getInputStream();

            byte[] contents = new byte[10000];
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            String sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');

            FileOutputStream fos;

            int bytesRead = 0;
            if (sentence.equals("")) {
                break;
                //System.exit(0);
            } else if (sentence.equals("1")) {
                fos = new FileOutputStream("D:\\Output\\XD2.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) != -1) {
                    bos.write(contents, 0, bytesRead);
                }

                bos.flush();
            } else if (sentence.equals("2")) {
                fos = new FileOutputStream("D:\\Output\\Pride and Prejudice 2.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) != -1) {
                    bos.write(contents, 0, bytesRead);
                }

                bos.flush();
            } else if (sentence.equals("3")) {
                fos = new FileOutputStream("D:\\Output\\Lmao2.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                while ((bytesRead = is.read(contents)) != -1) {
                    bos.write(contents, 0, bytesRead);
                }

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
}
