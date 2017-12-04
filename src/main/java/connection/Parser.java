package connection;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

import static connection.BotsBuildBots.*;
import static connection.Commands.chan;
import static connection.Weather.query;

public class Parser {
    static String weatherReport;
    static String sun;
    static org.w3c.dom.Document document;

    public static void parseHTML(String input) throws IOException {

        if(input.contains(channel)) {
            chan = channel;
        }
        else if(input.contains(channel1)){
            chan = channel1;
        }

        try {
            String substr = input.substring(input.indexOf("http"));
            String[] link = substr.split(" ", 2);
            String hyperlink = link[0];
            System.out.println("link: " + hyperlink);
            if (!StringUtil.isBlank(hyperlink)) {
                Document doc = Jsoup.connect(hyperlink).get();
                write("PRIVMSG", chan + " :" + "Title: "+ doc.title());
                String title = doc.title();
            }
        } catch (StringIndexOutOfBoundsException siobe) {
            System.out.println("invalid input");
        }
    }

    public static void parseXML(String input) throws IOException, ParserConfigurationException {

        System.out.println(chan);

        XPath xPath =  XPathFactory.newInstance().newXPath();
        String cityX = "/current/city/@name";
        String countryX = "/current/city/country";
        String degreesFX = "/current/temperature/@value";
        String humidityX = "/current/humidity/@value";
        String stateX = "/current/weather/@value";
        String windDescriptionX = "/current/wind/speed/@name";
        String windDirectionX = "/current/wind/direction/@name";
        String sunriseX = "/current/city/sun/@rise";
        String sunsetX = "/current/city/sun/@set";

        try {
            URL url = new URL(input);
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(conn.getInputStream());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String city = xPath.compile(cityX).evaluate(document);
            String country = xPath.compile(countryX).evaluate(document);
            String degreesF = xPath.compile(degreesFX).evaluate(document);
            String humidity = xPath.compile(humidityX).evaluate(document);
            String state = xPath.compile(stateX).evaluate(document);
            String windDescription = xPath.compile(windDescriptionX).evaluate(document);
            String windDirection = xPath.compile(windDirectionX).evaluate(document);
            String sunrise = xPath.compile(sunriseX).evaluate(document);
            String sunset = xPath.compile(sunsetX).evaluate(document);

            DecimalFormat df = new DecimalFormat("#.#");
            double degreesCdouble = Double.parseDouble(degreesF) - 273.15;
            double degreesFa = Double.parseDouble(degreesF) * 9/5 - 459.67;

            weatherReport = "Weather for " + city + ", " + country + ": " + df.format(degreesFa)+ "F/" + df.format(degreesCdouble) + "C; " + humidity + "% humidity and " + state + ". Wind: " + windDescription + ", coming from the " + windDirection + ". " +query;
            sun = "Sunrise is at " + sunrise + " and sunset is at " + sunset +".";


        } catch (XPathExpressionException e) {
            e.printStackTrace();
            write("PRIVMSG", chan +" :" + "Error getting results");
        }


    }
}