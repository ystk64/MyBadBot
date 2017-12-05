package connection;

import java.util.Arrays;

import static connection.BotsBuildBots.*;
public class Memo {

    static String memoIsFor;
    private static String message;
    static String sender;
    static String reply;
    static boolean memoHasMessage;
    public static void sendMessage(String input){

        parseMemo(input);
        reply = memoIsFor + ": " + sender + " sent you a memo at " + "time" + ": " + "'"+message+"'";
        write("PRIVMSG", chan + " :" + "Message saved, will be sent once " + memoIsFor + " is seen on the channel.");
        memoHasMessage = true;
    }

    static void parseMemo(String input){
        try {
            System.out.println("Full msg: " + input);
            String substr = input.substring(input.indexOf("@note"));
            String[] split = substr.split(" ", 3);
            memoIsFor = split[1];
            message = split[2];
            System.out.println("Nick: " + memoIsFor + ", Message: " + message);
            System.out.println("Full: " + Arrays.toString(split));

            String senderF = input.substring(input.indexOf(":") + 1);
            String[] senderFu = senderF.split( "!", 2);
            sender = senderFu[0];
            System.out.println("Sender: "+sender);
        } catch (IndexOutOfBoundsException iobe){
           iobe.printStackTrace();
        }
    }
}