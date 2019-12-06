package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{
  
	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private String model;
	
	@Column
	private double price;
	
	@Column(name = "year_of_production", nullable = false)
	private int yearOfProduction;
	
	@Column
	private double maxSpeed;
	
	@Column
	private double zeroToSixty;
	
	@ManyToOne(targetEntity = Racer.class)
	@JoinColumn(name = "racer_id", referencedColumnName = "id")
	private Racer racer;

	public Car() {}

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

	public Racer getRacer() {
		return racer;
	}

	public void setRacer(Racer racer) {
		this.racer = racer;
	}
	
	
	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
	}
	
	
	
}
