package mostwanted.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "race_entries")
public class RaceEntry extends BaseEntity{
    
	@Column(name = "has_finished")
	private boolean hasFinished;
	
	@Column(name = "finish_time")
	private double finishTime;
	
	@ManyToOne(targetEntity = Car.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;
	
	@ManyToOne(targetEntity = Race.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "race_id", referencedColumnName = "id")
	private Race race;
	
	@ManyToOne(targetEntity = Racer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "racer_id", referencedColumnName = "id")
	private Racer racer;

	
	public RaceEntry() {}

	public boolean isHasFinished() {
		return hasFinished;
	}

	public void setHasFinished(boolean hasFinished) {
		this.hasFinished = hasFinished;
	}

	public double getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(double finishTime) {
		this.finishTime = finishTime;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Racer getRacer() {
		return racer;
	}

	public void setRacer(Racer racer) {
		this.racer = racer;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
	
}
