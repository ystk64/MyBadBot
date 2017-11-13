package connection;

import static connection.BotsBuildBots.channel;
import static connection.BotsBuildBots.write;

public class Commands {


    public static String[] commandParts;
    public static void testReply(String input) {

        try {
            // inhoud van bericht ophalen
            String substr = input.substring(input.indexOf(channel) + channel.length() + 2);
            // command en parameter scheiden
            commandParts = substr.split(" ", 2);
            if (commandParts[0].equals("@test")) {
                System.out.println(commandParts[0]);
                write("PRIVMSG", channel + " "+ "Test geslaagd");
            }
               else if(commandParts[0].equals("@reverse")){
                    String reversed = new StringBuilder(commandParts[1]).reverse().toString();
                System.out.println(commandParts[1]);
                    write("PRIVMSG",  channel + " Reversed: " + reversed);
                }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("String part 1: " + commandParts[1] + "\n String part 2: " + commandParts[1]);
        }
    }
}

