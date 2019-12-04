package hibernateCodeFirstExercises.UniversitySystemDB;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher extends BasicPerson { 

	private String email;
	@Column(name = "salary_per_hour")
	private double salaryPerHour;
	
	@OneToMany(targetEntity = Course.class, mappedBy = "teacher")
	private Set<Course> courses;
	
	
	
	protected Teacher(){};
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getSalaryPerHour() {
		return salaryPerHour;
	}
	public void setSalaryPerHour(double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}


	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
