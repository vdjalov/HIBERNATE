package app.ccb.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name = "branches")
public class Branch extends BaseEntity{

	
	@Column(nullable = false)
	@NotNull
	private String name;

	
	public Branch() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
