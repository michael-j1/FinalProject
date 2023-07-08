package clinic.site.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import clinic.site.controller.model.ClinicSiteData;
import clinic.site.controller.model.ClinicSiteData;
import clinic.site.controller.model.ClinicSiteData.ClinicSitePatient;
import clinic.site.controller.model.ClinicSiteData.ClinicSiteEmployee;
import clinic.site.service.ClinicSiteService;
@RestController
@Slf4j
public class ClinicSiteController {//..begin all
	            

	            	/* Source: @Promineo...
	               @RestController – This tells Spring that this class is a REST controller. As such it expects and returns JSON in the request/response bodies. 
	             The default response status code is 200 (OK) if you don't specify something different. And finally, this annotation tells Spring to map HTTP 
	             requests to class methods. The annotation is in the org.springframework.web.bind.annotation package.
	           	 @RequestMapping("/clinic_site") – This tells Spring that the URI for every HTTP request that is mapped to a method in 
	           	 this controller class must start with "/clinic_site". This annotation is in the org.springframework.web.bind.annotation package.
	             @Slf4j – This is a Lombok annotation that creates an SLF4J logger. It adds the logger as an instance variable named log.
	              Use it like this:
	            	 log.info("This is a log line"): 
	            	*/ 
	            	 	            	 
	            	 	            	            	 
	
	            	 
		         @Autowired
		         private ClinicSiteService clinicSiteService;
		
		         @PostMapping("/clinic_site")
		         @ResponseStatus(code = HttpStatus.CREATED)
		         public ClinicSiteData createClinicSite(@RequestBody ClinicSiteData clinicSiteData) {log.info("Creating clinic site{}", clinicSiteData);		
		      	  return clinicSiteService.saveClinicSite(clinicSiteData);
		         }
		
		         
		         
		       @PutMapping("/clinic_site/{clinicSiteId}")
		       public ClinicSiteData updateClinicSite(@PathVariable Long clinicSiteId, @RequestBody ClinicSiteData clinicSiteData) {
			   clinicSiteData.setClinicSiteId(clinicSiteId); log.info("Updating clinic site{}", clinicSiteData);	
			   return clinicSiteService.saveClinicSite(clinicSiteData);
		        }
		
		       @PostMapping("/clinic_site/{clinicSiteId}/patient")
		        @ResponseStatus(code = HttpStatus.CREATED)
		        public ClinicSitePatient addPatient1(@PathVariable Long clinicSiteId, 
	            @RequestBody ClinicSitePatient clinicSitePatient) {	
		        log.info("Creating clinic site patient {}", clinicSitePatient);			
		         ClinicSitePatient patient = clinicSiteService.savePatient(clinicSiteId, clinicSitePatient);	
		         return patient;
		          }
		
		       
		         @PostMapping("/clinic_site/{clinicSiteId}/employee")
		        @ResponseStatus(code = HttpStatus.CREATED)
		        public ClinicSiteEmployee addEmployee(@PathVariable Long clinicSiteId, 
	            @RequestBody ClinicSiteEmployee clinicSiteEmployee) {	
		        log.info("Creating clinic site employee {}", clinicSiteEmployee);			
		         ClinicSiteEmployee employee = clinicSiteService.saveEmployee(clinicSiteId, clinicSiteEmployee);	
		         return employee;
		          }
		
		
		     
	    		
		    	@PutMapping("/clinic_site/{clinicSiteId}/employee/{employeeId}")
		        public ClinicSiteData updateEmployee(@PathVariable String employeeId,
		        @RequestBody ClinicSiteData clinicSiteData) {
		        ClinicSiteData.setEmployeeId(employeeId);
		        log.info("Updating employee {}", clinicSiteData);	
		        return clinicSiteService.saveClinicSite(clinicSiteData);
		            	}
		        		
		    	
		
		           @PostMapping("/clinic_site/{clinicSiteId}/patient1")
		           @ResponseStatus(code = HttpStatus.CREATED)
		            public ClinicSitePatient addPatient(@PathVariable Long clinicSiteId, @RequestBody ClinicSitePatient clinicSitePatient) {
				    log.info("Creating clinic site patient{}", clinicSitePatient);		
					ClinicSitePatient patient = clinicSiteService.savePatient(clinicSiteId, clinicSitePatient);
			        return patient;
		             }
		
		
		           
		           @GetMapping("/clinic_site")
		          public List<ClinicSiteData> getAllClinicSites() {
		       	  log.info("Retrieving all clinic sites");
		          List<ClinicSiteData> clinicSites = clinicSiteService.retrieveAllClinicSites();
		          return clinicSites;
		          }
		
		           
		
		          @GetMapping("/clinic_site/{clinicSiteId}")
		       public ClinicSiteData getClinicSiteById(@PathVariable Long clinicSiteId) {
			       log.info("Retrieving clinic site with ID= {}", clinicSiteId);
		           ClinicSiteData clinicSite = clinicSiteService.retrieveClinicSiteById(clinicSiteId);
		              return clinicSite;
		            }
		
		
		
		
		           @DeleteMapping("/clinic_site/{clinicSiteId}/employees")public Map<String, String> deleteClinicSiteById(@PathVariable Long clinicSiteId) {
			         log.info("Deleting clinic site with ID={}", clinicSiteId);
		          clinicSiteService.deleteClinicSiteById(clinicSiteId);
		          return Map.of("message", "Delete clinic site w/ ID=" + clinicSiteId + " was a success!");
		           }
		
		
}//....end all.........
