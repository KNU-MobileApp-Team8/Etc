
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ConnectionManager {
    private static final String API_SERVER_HOST = "http://49.236.134.66:3389";
    private static final String API_BASE_PATH = "/api";
    private static final String HttpMethodType = "GET";
    private static final String BUILDING_PATH = "/building";
    private static final String ROADSPOT_PATH = "/roadspot";

    public ConnectionManager(){}

    public ArrayList<Building> get_all_buildings() throws ParseException {
        return Building.get_listified( request(BUILDING_PATH));
    }
    public ArrayList<RoadSpot> get_all_roadSpots() throws ParseException {
        return RoadSpot.get_listified( request(ROADSPOT_PATH));
    }
    public Building get_building_with_number(int _number) throws ParseException {
        return new Building( request(BUILDING_PATH + "/" + _number));
    }
    public RoadSpot get_roadSpot_with_number(int _number) throws ParseException {
        return new RoadSpot( request( ROADSPOT_PATH + "/" + _number));
    }

    private String request(final String apiPath){
        /*
            Client only use Get method
         */
        String requestUri = API_SERVER_HOST + API_BASE_PATH + apiPath;

        HttpURLConnection CONN;
        InputStreamReader INPUT_READER = null;
        BufferedReader BUF_READER = null;

        try{
            final URL url= new URL( requestUri);
            CONN = (HttpURLConnection) url.openConnection();
            CONN.setRequestMethod( HttpMethodType);
            /* add Authorization in here ! */
            CONN.setAllowUserInteraction(false);
            CONN.setDoOutput(true);

            CONN.setRequestProperty("Content-Type", "application/json");
            CONN.setRequestProperty("charset", "utf-8");

            final int responseCode = CONN.getResponseCode();
            System.out.println(String.format("Request[%s] to URI: %s", HttpMethodType, url));
            System.out.println("Response Code: " + responseCode);
            if( responseCode == 200)
                /* Success */
                INPUT_READER = new InputStreamReader( CONN.getInputStream());
            else
                INPUT_READER = new InputStreamReader( CONN.getErrorStream());

            /* Read string data from input stream */
            BUF_READER = new BufferedReader( INPUT_READER);
            final StringBuffer BUFFER = new StringBuffer();
            String dataLine;
            while( (dataLine = BUF_READER.readLine()) != null)
                BUFFER.append( dataLine);

            return BUFFER.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
