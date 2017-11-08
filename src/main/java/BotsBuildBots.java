import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BotsBuildBots {

    private static String nick;
    private static String username;
    private static String realName;
    private static PrintWriter out;
    private static Scanner in;

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter nickname");
        nick = console.nextLine();
        System.out.println("Enter username");
        username = console.nextLine();
        System.out.println("Enter real name");
        realName = console.nextLine();

        Socket socket = new Socket("chat.freenode.net", 6667);

        out = new PrintWriter(socket.getOutputStream(), true);
        in = new Scanner(new InputStreamReader(socket.getInputStream()));

        write("NICK", nick);
        write("USER", username + " 0 * :" + realName);
        write("JOIN", "##w3tutorial");
        write("TOPIC", "");

        while(in.hasNext()) {
            String serverMessage = in.nextLine();
            System.out.println("<<< " + serverMessage);
            if (serverMessage.startsWith("PING")){
                String ping = serverMessage.split(" ", 2)[1];
                write("PONG", ping);
            }
        }

        if(in.nextLine().endsWith("End of /NAMES list."))
        {
            write("PRIVMSG", "I'M BACK, BITCHES");
        }

        in.close();
        out.close();
        socket.close();

        System.out.println("Done!");
    }

    private static void write(String command, String message) {

        String fullMessage = command + " " + message;
        System.out.println(">>> " + fullMessage);
        out.print(fullMessage + "\r\n");
        out.flush();
    }




}

