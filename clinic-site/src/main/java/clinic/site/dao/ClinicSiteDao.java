package clinic.site.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import clinic.site.entity.ClinicSite;

public interface ClinicSiteDao extends JpaRepository<ClinicSite, Long> {

}





/*//..HTTP also defines standard response codes.

200 - SUCCESS
404 - RESOURCE NOT FOUND
400 - BAD REQUEST
201 - CREATED
401 - UNAUTHORIZED
415 - UNSUPPORTED TYPE - Representation not supported for the resource
500 - SERVER ERROR
*/