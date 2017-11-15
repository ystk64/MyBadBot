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

public class Google {
    public static String[] commandParts;

    public static void Google(String input) throws IOException {

        String substr = input.substring(input.indexOf(channel) + channel.length() + 2);
        commandParts = substr.split(" ", 2);

        try {
            String searchQuery = commandParts[1]; //The query to search
            String cx = "017159970878171620157:lk-ss4cvtek"; //Your search engine

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
                while (i < 2) {
                    for (Result ri : result.getItems()) {
                        //Get title, link, body etc. from search
                        System.out.println(ri.getTitle() + ", " + ri.getLink());
                        i++;
                    }
                }
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } finally{ System.out.println("Error");}

    }
}




