package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "towns")
public class Town extends BaseEntity{
   
	
	@Column(nullable = false, unique = true)
	private String name;

	
	public Town() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return super.getId();
	}

	public void setId(Integer id) {
		super.setId(id);
	}
}
