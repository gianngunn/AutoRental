package apal.core;

public class City {
	
	private int cityCode;
	private String cityName;
	
	public City(int cityCode, String cityName) {
		this.cityCode = cityCode;
		this.cityName = cityName;
	}

	public int getCityCode() {
		
		
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	public String toString() {
		String ret=new String("Name: "+cityName+"\n"+"Code: "+ cityCode);
		return ret;
	}

}
