package connection;

import static connection.BotsBuildBots.channel;
import static connection.BotsBuildBots.channel1;
import static connection.BotsBuildBots.write;

public class Roti {
    static char c;
    public static String chan;
    public static String[] commandParts;


    public static void Roti(String input) {
        if (input.contains(channel)) {
            chan = channel;
        } else if (input.contains(channel1)) {
            chan = channel1;
        }

        System.out.println("chan: " + chan);

        String substr = input.substring(input.indexOf(chan) + chan.length() + 2);


        // command en parameter scheiden
        commandParts = substr.split(" ", 2);

        String s = null;
        for (int i = 0; i < substr.length(); i++) {
            c = input.charAt(i);
            if (c - 26 > 0) {
                c += 13;
            } else {
                c -= 13;
            }

            s = new StringBuilder().append(c).toString();
            i++;
        }
        write("PRIVMSG", chan + " :" + s);
    }
}
