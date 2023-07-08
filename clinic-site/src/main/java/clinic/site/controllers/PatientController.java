package clinic.site.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clinic.site.controller.model.ClinicSiteData;
import clinic.site.service.ClinicSiteService;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class PatientController {
	 
	    
	    
	    @Autowired
        private ClinicSiteService clinicSiteService;

     
	  
        
        @GetMapping("/clinic_site/patient/{patientId}")
       public List<ClinicSiteData> getAllClinicSites() {
    	  log.info("Retrieving all clinic sites");
       List<ClinicSiteData> clinicSites = clinicSiteService.retrieveAllClinicSites();
       return clinicSites;
       }

	}
