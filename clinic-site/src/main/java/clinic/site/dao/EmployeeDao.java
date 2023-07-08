package clinic.site.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import clinic.site.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
