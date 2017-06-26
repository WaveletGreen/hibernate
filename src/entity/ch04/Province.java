package entity.ch04;

import java.io.Serializable;
import java.util.Set;

public class Province implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3026700667525885640L;
	private Integer proID;
	private String proName;
	private Set<City> cities;

	public Province() {
		super();
	}

	public Province(Integer proID, String proName, Set<City> cities) {
		super();
		this.proID = proID;
		this.proName = proName;
		this.cities = cities;
	}

	

	public Integer getProID() {
		return proID;
	}

	public void setProID(Integer proID) {
		this.proID = proID;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}
