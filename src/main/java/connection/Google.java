package connection;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static connection.BotsBuildBots.channel;
import static connection.BotsBuildBots.channel1;
import static connection.BotsBuildBots.write;
import static connection.Commands.chan;

public class Google {
    public static String[] commandParts;
    public static String cx;

    public static void Google(String input) throws IOException {

        if(input.contains(channel)) {
            chan = channel;
        }
        else if(input.contains(channel1)){
            chan = channel1;
        }

        String substr = input.substring(input.indexOf(chan) + chan.length() + 2);
        commandParts = substr.split(" ", 2);

        try {
            String searchQuery = commandParts[1]; //The query to search
            if(input.contains("@google")) {
                cx = "017159970878171620157:lk-ss4cvtek"; //Google custom search - whole web
            }
            else {
                cx="000333651083524450345:g2hhfhdhsgw"; // Custom search - wikipedia only
            }

            //Instance Customsearch
            Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                    .setApplicationName("badbotsearch")
                    .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyAbNAVxgkjOYrnCQY-um9FH_o83_OIX59M"))
                    .build();

            //Set search parameter
            Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(cx);

            //Execute search
            Search result = list.execute();
            int i=0;
            if (result.getItems() != null) {
                for (Result ri : result.getItems()) {
                        //Get title, link, body etc. from search
                        if (i==1) { break; }
                        write("PRIVMSG", chan + " :"+ri.getTitle() + ", " + ri.getLink());
                        i++;
                    }
                }

        } catch (GeneralSecurityException e) {
            e.printStackTrace();  }

            //finally{ write("PRIVMSG",chan + " :"+"Unknown error");}

    }
}




