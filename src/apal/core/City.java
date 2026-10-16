package apal.core;

public class City {
	
	private int cityCode;
	private String cityName;
	public static int MAX_CODE ;
	
	public City(int cityCode, String cityName) {
		super();
		MAX_CODE = MAX_CODE + 1;
		cityCode = MAX_CODE;
		this.cityCode = cityCode;
		this.cityName = cityName;
		
		
		System.out.println(MAX_CODE);
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

	public static int MAX_CODE() {
		
		return MAX_CODE;
	}

	
}
