package clinic.site.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinic.site.controller.model.ClinicSiteData;
import clinic.site.controller.model.ClinicSiteData.ClinicSitePatient;
import clinic.site.controller.model.ClinicSiteData.ClinicSiteEmployee;
import clinic.site.dao.PatientDao;
import clinic.site.dao.EmployeeDao;
import clinic.site.dao.ClinicSiteDao;
import clinic.site.entity.Patient;
import clinic.site.entity.Employee;
import clinic.site.entity.ClinicSite;

@Service
public class ClinicSiteService {
	
      	//....Spring can inject DAO object into the variable autowired
	    //ClinicSiteDao object named clinicSiteDao as pvt instance variable
      	@Autowired
      	private ClinicSiteDao clinicSiteDao;
      	@Autowired 
      	private EmployeeDao employeeDao;
      	@Autowired
      	private PatientDao patientDao;

      	
      	
      	
      	
      	
      	
              //........Method below saves any new clinic site that is entered through JSON. ClinicSiteData object as a parameter and return a ClinicSiteData object
	           public ClinicSiteData saveClinicSite(ClinicSiteData clinicSiteData) {
		     Long clinicSiteId = clinicSiteData.getClinicSiteId();
		     ClinicSite clinicSite  = findOrCreateClinicSite(clinicSiteId);
		     copyClinicSiteFields(clinicSite, clinicSiteData);
		     return new ClinicSiteData(clinicSiteDao.save(clinicSite));//....Call the ClinicSiteDao method save(clinicSite). Return a new ClinicSiteData object created from the return value of the save() method.
	         }
	   
	    
	    
	    
	    
	    
                 //...below...Call copyClinicSiteFields(). This method takes a ClinicSite object and a ClinicSiteData object as parameters. Matching fields are copied from the ClinicSiteData object to  ClinicSite object.
	            private void copyClinicSiteFields(ClinicSite clinicSite, ClinicSiteData clinicSiteData) {
	        clinicSite.setClinicSiteId(clinicSiteData.getClinicSiteId());
			clinicSite.setClinicSiteName(clinicSiteData.getClinicSiteName());
			clinicSite.setClinicSiteHours(clinicSiteData.getClinicSiteHours());
			clinicSite.setClinicSiteModality(clinicSiteData.getClinicSiteModality());
		     }
          
	            
	            
	            //...Below.. method finds clinic site by Id using clinicSiteDao. If a clinic site with the given ID is found, it is returned.If no clinic site is found, it throws a NoSuchElementException with an error message.
	     	private ClinicSite findClinicSiteById(Long clinicSiteId)  {
		    return clinicSiteDao.findById(clinicSiteId)
		   .orElseThrow(() -> new NoSuchElementException("Clinic site with ID=" + clinicSiteId + " was not found."));
		    }
	     	
	     	
	     	
            //...............This method either finds an existing clinic site by its ID using the findClinicSiteById method, or creates a new ClinicSite object if the clinicSiteId is null. If clinicSiteId is null, it creates a new ClinicSite instance. If  clinicSiteId is not null, it calls findClinicSiteById method to get corresponding ClinicSite instance. Method returns the obtained ClinicSite object.
		    private ClinicSite findOrCreateClinicSite(Long clinicSiteId) {
			ClinicSite clinicSite;
			if(Objects.isNull(clinicSiteId)) {
			clinicSite = new ClinicSite();
			}
			else {
			clinicSite = findClinicSiteById(clinicSiteId);
			}
			return clinicSite;
		    }//...end findOrCreateClinicSite 
		    
		    
		   
		    

		     //.........saveEmployyee method.........
		    @Transactional(readOnly = false)
		    public ClinicSiteEmployee saveEmployee(Long clinicSiteId, ClinicSiteEmployee clinicSiteEmployee) {
	        ClinicSite clinicSite = findClinicSiteById(clinicSiteId);
			Long employeeId = clinicSiteEmployee.getEmployeeId();
			Employee employee = findOrCreateEmployee(clinicSiteId, employeeId);
			
			copyEmployeeFields(employee, clinicSiteEmployee);
			  
			  //set clinicSite in employee
			  //add employee to clinic site list of employees
			
			employee.setClinicSite(clinicSite);
			clinicSite.getEmployees().add(employee);
			
			Employee dbEmployee = employeeDao.save(employee);//...save employee
			return new ClinicSiteEmployee(dbEmployee);
		}
             
		    
		    //  // Note that findById returns an Optional. If the Optional is
		    // empty .orElseThrow throws a NoSuchElementException. If the
		    // Optional is not empty an Employee is returned.
		    //......findEmployeeById() method @Promineo
		    
		        public Employee findEmployeeById(Long clinicSiteId, Long employeeId) {
                Employee employee = employeeDao.findById(employeeId).orElse(null);
			    if (employee == null) {
				throw new NoSuchElementException("Employee with ID=" + employeeId + " does not exist.");
		     	}
		    	if (!employee.getEmployeeId().equals(clinicSiteId)) {
				throw new IllegalArgumentException(
		     	"Employee with ID=" + employeeId + " does not exist in clinic site with ID=" + clinicSiteId);}
			    return employee;

		     }
           
		        
		    
		        //....findOrCreateEmployee Method
		        public Employee findOrCreateEmployee(Long clinicSiteId, Long employeeId) {
			    if (Objects.isNull(employeeId)) {
			    return new Employee();
		    	}
			    return findEmployeeById(clinicSiteId, employeeId);
		     }

		
		      //.....copyEmployeeFields method
		       public void copyEmployeeFields(Employee employee, ClinicSiteEmployee clinicSiteEmployee) {
		 	employee.setEmployeeId(clinicSiteEmployee.getEmployeeId());
			employee.setEmployeeName(clinicSiteEmployee.getEmployeeName());
			employee.setEmployeeRole(clinicSiteEmployee.getEmployeeRole());

		      }
		  
		       
		       
		       
		       //.....findClinicSiteById..............................
		    @Transactional(readOnly = false)
		    public ClinicSitePatient savePatient(Long clinicSiteId, ClinicSitePatient clinicSitePatient) {
			ClinicSite clinicSite = findClinicSiteById(clinicSiteId);
			Long patientId = clinicSitePatient.getPatientId();
			Patient patient = findOrCreatePatient(clinicSiteId, patientId);
			copyPatientFields(patient, clinicSitePatient);
			patient.getClinicSites().add(clinicSite);
			clinicSite.getPatients().add(patient);
			Patient dbPatient = patientDao.save(patient);
			return new ClinicSitePatient(dbPatient);
		    }
         
		    
		    
		    
		    
		    //.............findOrCreatePatient method............
	 	    private Patient findOrCreatePatient(Long clinicSiteId, Long patientId) {
		 	if (Objects.isNull(patientId)) {
	     	return new Patient();
			} return findPatientById(clinicSiteId, patientId);
	    	}
		    
		    
            //...findPatientById method
		    private Patient findPatientById(Long clinicSiteId, Long patientId) {
			Patient patient = patientDao.findById(patientId).orElse(null);
			if (patient == null) {
			throw new NoSuchElementException("Patient with ID=" + patientId + " does not exist.");
			}
			boolean clinicSiteFound = patient.getClinicSites().stream()
			.anyMatch(clinicSite -> clinicSite.getClinicSiteId().equals(clinicSiteId));
			if (!clinicSiteFound) {
			throw new IllegalArgumentException(
      		"ClinicSite with ID=" + clinicSiteId + " not found in patient's clinic site.");
			}
		    return patient;
		    }

		    
		    
		    
		    //....copyPatientFields
		    private void copyPatientFields(Patient patient, ClinicSitePatient clinicSitePatient) {
		 	patient.setPatientId(clinicSitePatient.getPatientId());
			patient.setPatientName(clinicSitePatient.getPatientName());
			patient.setPatientAnatomyScanned(clinicSitePatient.getPatientAnatomyScanned());
		    }
          
		    
		    
		    //.............retrieveAllClinicSites........................
	     	@Transactional(readOnly = true)
	     	public List<ClinicSiteData> retrieveAllClinicSites() {
	  	    List<ClinicSite> clinicSites = clinicSiteDao.findAll();
		    List<ClinicSiteData> result = new LinkedList<>();
		    for (ClinicSite clinicSite : clinicSites) {
	       ClinicSiteData psd = new ClinicSiteData(clinicSite);
		        psd.getPatients().clear();
		        psd.getEmployees().clear();
		        result.add(psd);
		        }
		        return result;
		         }
		
	     	
	     	
         //.....................retrieveClinicSiteById........
		@Transactional(readOnly = true)
		public ClinicSiteData retrieveClinicSiteById(Long clinicSiteId) {
			ClinicSite clinicSite = clinicSiteDao.findById(clinicSiteId).orElseThrow(() -> new NoSuchElementException("ClinicSite with ID=" + clinicSiteId + " does not exist."));
		    return new ClinicSiteData(clinicSite);
		}
		
      
		
		
		//.......deleteClinicSiteById method
		@Transactional(readOnly = false)
		public void deleteClinicSiteById(Long clinicSiteId) {
      	ClinicSite clinicSite = clinicSiteDao.findById(clinicSiteId).orElseThrow(() -> new NoSuchElementException("ClinicSite with ID=" + clinicSiteId + " does not exist."));
        clinicSiteDao.delete(clinicSite);

		}


	 
}//........................................end ClinicSiteService class
