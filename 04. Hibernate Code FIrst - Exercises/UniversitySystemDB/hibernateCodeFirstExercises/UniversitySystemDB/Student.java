package hibernateCodeFirstExercises.UniversitySystemDB;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends BasicPerson{

	private double averageGrade;
	private String attendance;
	
	@ManyToMany
	private Set<Course> courses;
	
	protected Student(){};
	
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	
}
