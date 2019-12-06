package mostwanted.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "districts")
public class District  extends  BaseEntity{
    
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "town_id", referencedColumnName = "id")
	private Town town;

	
	public District() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}
	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
	}
	
}
