package clinic.site.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clinic.site.controller.model.ClinicSiteData;
import clinic.site.service.ClinicSiteService;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class EmployeeController {
	
	
	  @Autowired
      private ClinicSiteService clinicSiteService;

      
       
   	
   	   @PutMapping("/employee/{employeeId}")
       public ClinicSiteData updateEmployee(@PathVariable String employeeId,
       @RequestBody ClinicSiteData clinicSiteData) {
       ClinicSiteData.setEmployeeId(employeeId);
       log.info("Updating employee {}", clinicSiteData);	
       return clinicSiteService.saveClinicSite(clinicSiteData);
           	}
       		
   	
}