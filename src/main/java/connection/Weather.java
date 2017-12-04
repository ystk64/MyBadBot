package connection;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import static connection.BotsBuildBots.write;
import static connection.BotsBuildBots.chan;
import static connection.Parser.weatherReport;

public class Weather {
    static String query;
    static org.w3c.dom.Document document;


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
        queryBuild.append("http://api.openweathermap.org/data/2.5/weather?q=");
        queryBuild.append(inputCity);
        queryBuild.append("&appid=" + apikey);
        queryBuild.append("&mode=xml");

        query = queryBuild + "";

        try {
            URL url = new URL(query);
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(conn.getInputStream());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Parser.parseXML(query);
        if(weatherReport!=null) {
            write("PRIVMSG", chan + " :" + weatherReport);
        }
    }
}