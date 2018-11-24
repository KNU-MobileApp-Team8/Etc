import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Iterator;


public class Building implements Unit{
    private int number;
    private String name;
    private GPS gps;
    private ArrayList<String> colleges;
    private String description;
    private static JSONParser jsonParser= new JSONParser();

    public Building(int _number, String _name, double _longitude, double _latitude){
        this.number = _number;
        this.name = _name;
        this.gps = new GPS(_longitude, _latitude);
        this.colleges = new ArrayList<String>();
        this.description = "";
    }

    public Building(int _number,
                    String _name,
                    double _longitude,
                    double _latitude,
                    ArrayList<String> _colleges){
        this.number = _number;
        this.name = _name;
        this.gps = new GPS(_longitude, _latitude);
        this.colleges = _colleges;
        this.description = "";
    }

    public Building(int _number,
                    String _name,
                    double _longitude,
                    double _latitude,
                    ArrayList<String> _colleges,
                    String _description){
        this.number = _number;
        this.name = _name;
        this.gps = new GPS(_longitude, _latitude);
        this.colleges = _colleges;
        this.description = _description;
    }

    public Building( String _jsonDataString) throws ParseException, JSONException {
        final JSONObject _data = (JSONObject) jsonParser.parse(_jsonDataString);
        final JSONObject _gps = (JSONObject) _data.get("gps");
        final JSONArray _colleges = (JSONArray) _data.get("colleges");

        this.number = (int) _data.get("number");
        this.name = (String) _data.get("name");
        this.gps = new GPS( (Double) _gps.get("longitude"),
                            (Double) _gps.get("latitude"));
        this.description = (String)_data.get("_description");
        this.colleges = new ArrayList<String>();
        for( int idx = 0; idx < _colleges.size(); idx++)
            this.colleges.add( (String) _colleges.get(idx));
    }

    private int get_number(){ return this.number;}
    private String get_name(){ return this.name;}
    private GPS get_gps(){ return this.gps;}
    private ArrayList<String> get_connected(){ return this.colleges;}
//    private Iterator<String> get_colleges_iterator() { return this.colleges.iterator();}
    private String get_description(){ return this.description;}

    public static ArrayList<Building> get_listified(String _jsonArrayDataString) throws ParseException, JSONException {
        ArrayList<Building> _retDataList = new ArrayList<Building>();
        JSONObject _jsonData = (JSONObject) jsonParser.parse(_jsonArrayDataString);
        final JSONArray _jsonList = (JSONArray) _jsonData.get("data");

        for( int idx = 0; idx < _jsonList.size(); idx++){
            String _buildingStringData = _jsonList.get(idx).toString();
            _retDataList.add( new Building( _buildingStringData));
        }

        return _retDataList;
    }

    @Override
    public String toString() {
        return String.format("{number: %s, name: %s, gps: %s, colleges: %s, description: %s}",
                this.number, this.name, this.gps.toString(), this.colleges.toString(), this.description);
    }
}
