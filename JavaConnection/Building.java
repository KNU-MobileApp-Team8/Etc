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
    private String description;
    private static JSONParser jsonParser= new JSONParser();

    public Building(int _number, String _name, double _longitude, double _latitude){
        this.number = _number;
        this.name = _name;
        this.gps = new GPS(_longitude, _latitude);
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
        this.description = _description;
    }

    public Building( String _jsonDataString) throws ParseException {
        final JSONObject _data = (JSONObject) jsonParser.parse(_jsonDataString);
        final JSONObject _gps = (JSONObject) _data.get("gps");
        final JSONArray _colleges = (JSONArray) _data.get("colleges");
        System.out.println("Parsing json string to object: " + _jsonDataString);

        this.number = Integer.parseInt( _data.get("number").toString());
        this.name = (String) _data.get("name");
        this.gps = new GPS( (Double) _gps.get("longitude"),
                            (Double) _gps.get("latitude"));
        this.description = (String)_data.get("_description");
    }

    private int get_number(){ return this.number;}
    private String get_name(){ return this.name;}
    private GPS get_gps(){ return this.gps;}
    private String get_description(){ return this.description;}

    public static ArrayList<Building> get_listified(String _jsonArrayDataString) throws ParseException {
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
        return String.format("{number: %s, name: %s, gps: %s, description: %s}",
                this.number, this.name, this.gps.toString(), this.description);
    }
}
