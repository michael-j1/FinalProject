package clinic.site.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data//......generates boilerplate code for getters setters equals hashCode toString methods
@Entity//....tells JPA persistent entity in database

public class ClinicSite {
	  @Id//......identifies PK "clinicSiteId"
	  @GeneratedValue(strategy = GenerationType.IDENTITY)	  
	  private Long   clinicSiteId;	  
	  private String clinicSiteName;
	  private String clinicSiteHours;
	  private String clinicSiteModality;

	  @EqualsAndHashCode.Exclude//..........This will prevent recursion from occurring when the .toString(), .equals(), or .hashCode() methods are called.
	  @ToString.Exclude
	  @ManyToMany(cascade = CascadeType.PERSIST)
	  @JoinTable(name = "clinic_site_patient", 
	    joinColumns = @JoinColumn(name = "clinic_site_id"),
	    inverseJoinColumns = @JoinColumn(name = "patient_id"))
	  Set<Patient> patients = new HashSet<>();

	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @OneToMany(mappedBy = "clinicSite", cascade = CascadeType.ALL, orphanRemoval = true)
	  Set<Employee> employees = new HashSet<>();
	

}
//...end....good
