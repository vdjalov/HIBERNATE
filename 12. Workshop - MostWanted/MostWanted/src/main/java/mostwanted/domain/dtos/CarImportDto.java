package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

public class CarImportDto {

	@Expose
	private String brand;
	
	@Expose
	private String model;
	
	@Expose
	private double price;
	
	@Expose
	private int yearOfProduction;
	
	@Expose
	private double maxSpeed;
	
	@Expose
	private double zeroToSixty;
	
	@Expose
	private String racerName;

	public CarImportDto() {
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getZeroToSixty() {
		return zeroToSixty;
	}

	public void setZeroToSixty(double zeroToSixty) {
		this.zeroToSixty = zeroToSixty;
	}

	public String getRacerName() {
		return racerName;
	}

	public void setRacerName(String racerName) {
		this.racerName = racerName;
	}
	
	
	
	
	
}
