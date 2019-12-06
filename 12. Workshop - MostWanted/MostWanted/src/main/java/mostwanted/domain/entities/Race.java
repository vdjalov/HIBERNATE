package mostwanted.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {
   
	@Column(nullable = false, columnDefinition = "INT default 0")
	private int laps;
	
	@ManyToOne(targetEntity = District.class)
	@JoinColumn(name = "district_id", referencedColumnName = "id")
	private District district;
	
	@OneToMany(targetEntity = RaceEntry.class, mappedBy = "race")
	private List<RaceEntry> raceEntries;
	
	public Race() {
		setRaceEntries(new ArrayList<RaceEntry>());
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<RaceEntry> getRaceEntries() {
		return raceEntries;
	}

	public void setRaceEntries(List<RaceEntry> raceEntries) {
		this.raceEntries = raceEntries;
	}
	
}
