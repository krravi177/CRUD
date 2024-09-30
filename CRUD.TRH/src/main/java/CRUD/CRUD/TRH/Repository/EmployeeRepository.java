package CRUD.CRUD.TRH.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CRUD.CRUD.TRH.Entity.EmployeeEntity;

@Repository

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
