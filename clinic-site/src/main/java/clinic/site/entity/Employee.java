
	
	package clinic.site.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Entity// specifies persistent entity in database
@Data//....generates boiler plate code for getters setters equals hashCode
public class Employee {
@Id //primary key
@GeneratedValue(strategy = GenerationType.IDENTITY)//...value of identity will be auto generated
private Long employeeId;//...unique identifier for patient
//.........................columns in patient table
private String employeeName;
private String employeeRole;
@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "clinic_site_id")  
ClinicSite clinicSite;
}