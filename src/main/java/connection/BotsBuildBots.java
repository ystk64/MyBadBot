package connection;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class BotsBuildBots {

    public static String nick;
    public static String username;
    public static String realName;
    public static PrintStream out;
    public static Scanner in;
    public static String serverMessage;
    public static String channel = "##w3tutorial";
    public static final String channel1= "#jsldfkjsdlfk";

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter nickname");
        nick = console.nextLine();
      //  System.out.println("Enter username");
       // username = console.nextLine();
       // System.out.println("Enter real name");
       // realName = console.nextLine();

        if(!channel.startsWith("#")) {
            System.out.println("Channel has to start with #. Changing...");
            channel = "#" + channel;
        }

        Connect freenode = new Connect();
        freenode.createSocket("chat.freenode.net", 6667);

        write("NICK", nick);
        write("USER", username + " 0 * :" + realName);
        write("JOIN", channel);
        write("JOIN", channel1);

        while(in.hasNextLine()) {
            serverMessage = in.nextLine();
            System.out.println("<<< " + serverMessage);
            if(serverMessage.contains("@reverse") || serverMessage.contains("@test")){
                Commands.testReply(serverMessage);
            }
            else if (serverMessage.startsWith("PING")){
                String ping = serverMessage.split(" ", 2)[1];
                write("PONG", ping);
            }
            else if(serverMessage.contains("@google") || serverMessage.contains("@wiki") || serverMessage.contains("@yt")){
                Google.Google(serverMessage);
            }
        }

        in.close();
        out.close();
        System.out.println("Done!");
    }

    public static void write(String command, String message) {
        String fullMessage = command + " " + message;
        System.out.println(">>> " + fullMessage);
        out.print(fullMessage + "\n\r");
        out.flush();
    }

}