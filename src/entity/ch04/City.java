package entity.ch04;

import java.io.Serializable;

public class City implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3102663909774035303L;
	private Integer cityID;
	private String cityName;
	private Province province;

	public City() {
		super();
	}

	public City(Integer cityID, String cityName, Province province) {
		super();
		this.cityID = cityID;
		this.cityName = cityName;
		this.province = province;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}
