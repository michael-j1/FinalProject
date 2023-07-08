package clinic.site.controller.model;

import java.util.HashSet;
import java.util.Set;

import clinic.site.entity.ClinicSite;
import clinic.site.entity.Employee;
import clinic.site.entity.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;







@Data
@NoArgsConstructor
public class ClinicSiteData {//....begin all
	  
	//....fields from ClinicSiteEntity
	  private Long   clinicSiteId;	  
	  private String clinicSiteName;
	  private String clinicSiteHours;
	  private String clinicSiteModality;
	  private Set<ClinicSitePatient> patients = new HashSet<>();
	  private Set<ClinicSiteEmployee> employees = new HashSet<>();
		
	
      //...constructor for ClinicSiteData takes ClinicSite as parameter. sets matching fields in the ClinicSiteData class to data in the ClinicSite class
      public ClinicSiteData(ClinicSite clinicSite) {
	  this.clinicSiteId = clinicSite.getClinicSiteId();
	  this.clinicSiteName = clinicSite.getClinicSiteName();
	  this.clinicSiteHours = clinicSite.getClinicSiteHours();
	  this.clinicSiteModality = clinicSite.getClinicSiteModality();
	  //.........Sets the employees and patients fields to the respective ClinicSitePatient and ClinicSiteEmployee objects. These are Sets so used loops 
     
	  
	  for(Patient patient : clinicSite.getPatients()) {
	  this.patients.add(new ClinicSitePatient(patient));
	  }
	  for(Employee employee : clinicSite.getEmployees()) {
	  this.employees.add(new ClinicSiteEmployee(employee));
	  }
      }
  	  
	  
	  
	  
	
//.................inner ClinicSitePatient DTO class
      @Data
      @NoArgsConstructor
public static class ClinicSitePatient {
	   //......Constructor it takes Patient object gets corresponding fields in ClinicSitePatient instance
      public ClinicSitePatient(Patient patient) { 
	   patientId = patient.getPatientId();
	   patientName = patient.getPatientName();
	   patientAnatomyScanned = patient.getPatientAnatomyScanned();
      }//...end ClinicSitePatient  constructor class
      //the following is fields from patient entity
      private Long   patientId;
      private String patientName;
      private String patientAnatomyScanned;
      }//...end ClinicSitePatient inner class
 
	  
	  
	  //.....inner ClinicSiteEmployee DTO Class. Takes Employee object
      
      
	  
       
  	   @NoArgsConstructor 									
public static class ClinicSiteEmployee {
      //....constructor takes Employee object gets the corresponding fields in ClinicSiteEmployee instance
  		public ClinicSiteEmployee(Employee employee) { 	
  		employeeId = employee.getEmployeeId();
  		employeeName = employee.getEmployeeName();
  		employeeRole = employee.getEmployeeRole();
  		} //end ClinicSiteEmployee Constructor class
  		private Long  employeeId;
  		private String employeeName;
  		private String employeeRole;
  		
  		
  		
		public Long getEmployeeId() {
			return null;
		}
		public String getEmployeeName() {
			return null;
		}
		public String getEmployeeRole() {
			return null;
		}
      
      
      } //..........end ClinicSiteEmployee class



	public static void setEmployeeId(String employeeId) {
		
	}



	public void setClinicSiteId(Long clinicSiteId2) {
		// TODO Auto-generated method stub
		
	}



	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}//....end all
