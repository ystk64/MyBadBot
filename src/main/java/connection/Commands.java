package connection;

import static connection.BotsBuildBots.channel;
import static connection.BotsBuildBots.channel1;
import static connection.BotsBuildBots.write;


public class Commands {


    public static String[] commandParts;
    public static String chan;

    public static void testReply(String input) {

try {
            // inhoud van bericht ophalen
            if(input.contains(channel)) {
                chan = channel;
            }
            else if(input.contains(channel1)){
                chan = channel1;
            }

            System.out.println("chan: "+chan);

            String substr = input.substring(input.indexOf(chan) + chan.length() + 2);


            // command en parameter scheiden
            commandParts = substr.split(" ", 2);
            if (commandParts[0].equals("@test")) {
                System.out.println(commandParts[0]);
                write("PRIVMSG",chan + " :"+"Test geslaagd");
            }
            else if(commandParts[0].equals("@reverse")){
                String reversed = new StringBuilder(commandParts[1]).reverse().toString();
                System.out.println(commandParts[1]);
                write("PRIVMSG", chan + " :" + reversed);
                }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds.\nString part 1: " + commandParts[1] + "\n String part 2: " + commandParts[1]);
        }
    }
}

