package clinic.site.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import clinic.site.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
