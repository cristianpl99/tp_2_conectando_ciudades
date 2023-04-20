package tp.logic;

public class City {
    private String name;
    private String province;
    private double latitude;
    private double longitude;

    public City(String name, String province, double latitude, double longitude) {
        this.name = name;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
