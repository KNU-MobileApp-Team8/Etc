import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws ParseException, JSONException {
        ConnectionManager CONN = new ConnectionManager();
        ArrayList<Building> res = CONN.get_all_buildings();

    }
}
