package connection;
import static connection.BotsBuildBots.*;
import static connection.BotsBuildBots.chan;

class Uptime {

    static public void time(){
        int current = (int) System.currentTimeMillis();
        int delta = current - start;
        int minutes = delta / 60000;
            if (minutes > 60) {
            int hours = minutes / 60;
            minutes = minutes % 60 * 60;
            write("PRIVMSG", chan + " :Uptime is: " + hours + " hours and " + minutes + " minutes");
        }
        else write("PRIVMSG", chan + " :Uptime is: " + minutes + " minutes");
    }
}
