import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class RoadSpot implements Unit{
    private int number;
    private GPS gps;
    private ArrayList<Integer> connected;
    private static JSONParser jsonParser= new JSONParser();

    public RoadSpot(int _number, double _latitude, double _longitude){
        this.number = _number;
        this.gps = new GPS( _longitude, _latitude);
        this.connected = new ArrayList<Integer>();
    }

    public RoadSpot(int _number,
                    double _latitude,
                    double _longitude,
                    ArrayList<Integer> _connected){
        this.number = _number;
        this.gps = new GPS( _longitude, _latitude);
        this.connected = _connected;
    }

    public RoadSpot(String _jsonDataString) throws ParseException, JSONException {
        final JSONObject _rawData = (JSONObject) jsonParser.parse(_jsonDataString);
        final JSONObject _data = _rawData.get("data");
        final JSONObject _gps = (JSONObject) _data.get("gps");
        final JSONArray _connected = (JSONArray) _data.get("connected");

        this.number = (int) _data.get("number");
        this.gps = new GPS( (double) _gps.get("longitude"),
                            (double) _gps.get("latitude"));

        this.connected = new ArrayList<Integer>();
        for( int idx = 0; idx < _connected.size(); idx++)
            this.connected.add(  (int) _connected.get(idx));
    }

    private int get_number(){ return this.number;}
    private GPS get_gps(){ return this.gps;}
    private ArrayList<Integer> get_connected(){ return this.connected;}
    // private Iterator<Integer> get_list_iterator() { return this.connected.iterator();}

    public static ArrayList<RoadSpot> get_listified(String _jsonArrayDataString) throws ParseException, JSONException {
        ArrayList<RoadSpot> _retDataList = new ArrayList<RoadSpot>();
        JSONObject _jsonData = (JSONObject) jsonParser.parse(_jsonArrayDataString);
        final JSONArray _jsonList = (JSONArray) _jsonData.get("data");

        for( int idx = 0; idx < _jsonList.size(); idx++) {
            String _roadSpotStringData = _jsonList.get(idx).toString();
            _retDataList.add( new RoadSpot( _roadSpotStringData));
        }

        return _retDataList;
    }

    @Override
    public String toString() {
        return String.format("{number: %s, gps: %s, connected: %s}", this.number, this.gps.toString(), this.connected.toString());
    }
}