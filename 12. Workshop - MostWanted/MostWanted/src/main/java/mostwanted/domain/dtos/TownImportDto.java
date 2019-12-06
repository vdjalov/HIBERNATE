package mostwanted.domain.dtos;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
public class TownImportDto implements Serializable {
	
	@Expose
	private String name;

	
	public TownImportDto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
