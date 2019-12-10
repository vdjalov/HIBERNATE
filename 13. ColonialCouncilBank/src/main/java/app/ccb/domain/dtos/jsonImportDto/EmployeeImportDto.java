package app.ccb.domain.dtos.jsonImportDto;

import java.util.Set;

import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.ccb.domain.entities.Client;

public class EmployeeImportDto {

	@Expose
	@SerializedName(value = "full_name")
	private String fullName;
	
	@Expose
	private double salary;
	
	@Expose
	@SerializedName(value = "started_on")
	private String startedOn;
	
	@Expose
	@SerializedName(value = "branch_name")
	private String branch;
	
	@Transient
	private Set<Client> clients;

	public EmployeeImportDto() {
		
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(String startedOn) {
		this.startedOn = startedOn;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	
	
	
	
}
