
public class Location {
	public Location(String ramp, String freeway){
		this.ramp = ramp;
		this.freeway = freeway;
	}
	public Location(double longitude, double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
	}
	private String ramp;
	private String freeway;
	private double longitude;
	private double latitude;
	public String getRamp() {
		return ramp;
	}
	public String getFreeway() {
		return freeway;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}

}
