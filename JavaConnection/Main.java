import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws ParseException, org.json.simple.parser.ParseException {
        ConnectionManager CONN = new ConnectionManager();
        ArrayList<Building> res = CONN.get_all_buildings();
        for( Building buildingIter : res){
            System.out.println( buildingIter);
        }

        ArrayList<RoadSpot> roadSpotResponse = CONN.get_all_roadSpots();
        for( RoadSpot roadSpotIter : roadSpotResponse){
            System.out.println( roadSpotIter);
        }
    }
}
