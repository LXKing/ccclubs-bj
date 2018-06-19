
public class LatLng {
	  public final double latitude;
	  public final double longitude;
	  public final double latitudeE6;
	  public final double longitudeE6;
	  
	public LatLng(double paramDouble1, double paramDouble2)
	  {
	    double d1 = paramDouble1 * 1000000.0D;
	    double d2 = paramDouble2 * 1000000.0D;
	    this.latitudeE6 = d1;
	    this.longitudeE6 = d2;
	    this.latitude = (d1 / 1000000.0D);
	    this.longitude = (d2 / 1000000.0D);
	  }

}
