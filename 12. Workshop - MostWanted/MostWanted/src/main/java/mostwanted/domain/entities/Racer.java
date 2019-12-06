package mostwanted.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "racers")
public class Racer extends BaseEntity{
   
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column
	private int age;
	
	@Column
	private double bounty;
	
	@ManyToOne(targetEntity = Town.class)
	@JoinColumn(name = "town_id", referencedColumnName = "id")
	private Town homeTown;

	public Racer() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getBounty() {
		return bounty;
	}

	public void setBounty(double bounty) {
		this.bounty = bounty;
	}

	public Town getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(Town homeTown) {
		this.homeTown = homeTown;
	}
	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
	}
	
}
