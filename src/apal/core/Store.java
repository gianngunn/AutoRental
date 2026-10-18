package apal.core;

public class Store {
	
	private int storeCode;
	private String storeName;
	private String storeCity;
	
	public Store(int storeCode, String storeName, String storeCity) {
		this.storeCode = storeCode;
		this.storeName = storeName;
		this.storeCity = storeCity;
	}

	public int getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(int storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreCity() {
		return storeCity;
	}

	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}
	
}
