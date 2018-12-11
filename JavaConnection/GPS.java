public class GPS {
    public double longitude;
    public double latitude;

    public GPS( double _longitude, double _latitude){
        this.longitude = _longitude;
        this.latitude = _latitude;
    }

    @Override
    public String toString() {
        return String.format("{longitude: %s, latitude: %s}", this.longitude, this.latitude);
    }
}
