package connection;

import static connection.BotsBuildBots.channel;
import static connection.BotsBuildBots.channel1;
import static connection.BotsBuildBots.write;

public class Roti {
    static String s;
    static char c;
    public static String chan;
    public static String[] commandParts;


    public static void Roti(String input) {
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

        for (int i = 0; i < substr.length(); i++) {
            c = input.charAt(i);
            if (c >= 'a' && c <= 'm') c += 13;
            else if (c >= 'A' && c <= 'M') c += 13;
            else if (c >= 'n' && c <= 'z') c -= 13;
            else if (c >= 'N' && c <= 'Z') c -= 13;

            s = new StringBuilder().append(c).toString();
        }
        write("PRIVMSG", chan + " :"+s);
    }
}
