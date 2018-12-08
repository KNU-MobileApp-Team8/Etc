public class GPS {
    public double longitude;
    public double latitude;

    public GPS( double _longitude, double _latitude){
        this.longitude = _longitude;
        this.latitude = _latitude;
    }

    public GPS( String _longitude, String _latitude){
        this.longitude = Double.parseDouble( _longitude);
        this.latitude = Double.parseDouble( _latitude);
    }

    @Override
    public String toString() {
        return String.format("{longitude: %s, latitude: %s}", this.longitude, this.latitude);
    }
}
