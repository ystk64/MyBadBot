package connection;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static connection.BotsBuildBots.write;
import static connection.Commands.chan;
import static connection.Parser.weatherReport;

public class Weather {
    static String query;

    public static void getWeatherXML(String input) throws IOException, ArrayIndexOutOfBoundsException, ParserConfigurationException {

        String substr = input.substring(input.indexOf("@weather"));
        String[] link = substr.split(" ", 2);

        try{
            assert link[1] != null;
        }catch(ArrayIndexOutOfBoundsException aiobe){
            write("PRIVMSG", chan + " :" + "Error parsing input. Syntax: \"@weather {city}\"");
        }
        String inputCity = link[1];
        String apikey = "4a52f52fd0bc4c6542cb49f115fda173";
        StringBuilder queryBuild = new StringBuilder();
        System.out.println("Query before adding link: " + queryBuild);
        queryBuild.append("http://api.openweathermap.org/data/2.5/weather?q=");
        queryBuild.append(inputCity);
        queryBuild.append("&appid=" + apikey);
        queryBuild.append("&mode=xml");

        query = queryBuild + "";
        System.out.println("Query: " + query);

        Parser.parseXML(query);
        System.out.println(weatherReport);
        write("PRIVMSG", chan + " :" + weatherReport);

    }
}