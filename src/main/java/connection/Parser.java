package connection;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static connection.BotsBuildBots.*;
import static connection.Commands.chan;

class Parser {

    private static String hyperlink;
    static void parseHtml(String input) throws IOException {

        if(input.contains(channel)) {
            chan = channel;
        }
        else if(input.contains(channel1)){
            chan = channel1;
        }

        try {
            String substr = input.substring(input.indexOf("http"));
            String[] link = substr.split(" ", 2);
            hyperlink = link[0];
            System.out.println("link: " + hyperlink);
            if (!StringUtil.isBlank(hyperlink)) {
                try{ Thread.sleep(1000);
                }catch(InterruptedException sl){
                    System.out.println("Caught InterruptedException");
                }
                Document doc = Jsoup.connect(hyperlink).get();
                write("PRIVMSG", chan + " :"+ doc.title());
            }
        } catch (StringIndexOutOfBoundsException siobe) {
            System.out.println("invalid input");
        }
    }
}