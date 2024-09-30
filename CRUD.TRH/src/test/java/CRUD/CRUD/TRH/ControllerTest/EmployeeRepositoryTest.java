package CRUD.CRUD.TRH.ControllerTest;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import CRUD.CRUD.TRH.Entity.EmployeeEntity;
import CRUD.CRUD.TRH.Repository.EmployeeRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeEntityRepository;

	@Test
	@Rollback(value = false)
	public void saveEmployeeTest() {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setDepartment("HR");
		employee.setName("Ravi");
		employee.setEmail("ravi@example.com");

		EmployeeEntity savedEmployee = employeeEntityRepository.save(employee);

		Assertions.assertThat(savedEmployee).isNotNull();
		Assertions.assertThat(savedEmployee.getDepartment()).isEqualTo(employee.getDepartment());
		Assertions.assertThat(savedEmployee.getName()).isEqualTo(employee.getName());
		Assertions.assertThat(savedEmployee.getEmail()).isEqualTo(employee.getEmail());
	}

	@Test
	public void getAllEmployeesTest() {
		EmployeeEntity employee1 = new EmployeeEntity();
		employee1.setDepartment("HR");
		employee1.setName("Ravi");
		employee1.setEmail("ravi@example.com");

		EmployeeEntity employee2 = new EmployeeEntity();
		employee2.setDepartment("Engineering");
		employee2.setName("Alice");
		employee2.setEmail("alice@example.com");

		employeeEntityRepository.save(employee1);
		employeeEntityRepository.save(employee2);

		List<EmployeeEntity> employees = employeeEntityRepository.findAll();

		Assertions.assertThat(employees).isNotNull();
		Assertions.assertThat(employees.size()).isGreaterThan(1);
		Assertions.assertThat(employees).extracting(EmployeeEntity::getName).contains("Ravi", "Alice");
	}

	@Test
	@Rollback(value = false)
	public void updateEmployeeTest() {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setDepartment("HR");
		employee.setName("Ravi");
		employee.setEmail("ravi@example.com");
		EmployeeEntity savedEmployee = employeeEntityRepository.save(employee);

		savedEmployee.setName("Ravi Updated");
		EmployeeEntity updatedEmployee = employeeEntityRepository.save(savedEmployee);

		Assertions.assertThat(updatedEmployee.getName()).isEqualTo("Ravi Updated");
	}

	@Test
	public void deleteEmployeeTest() {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setDepartment("HR");
		employee.setName("Ravi");
		employee.setEmail("ravi@example.com");
		EmployeeEntity savedEmployee = employeeEntityRepository.save(employee);

		employeeEntityRepository.deleteById(savedEmployee.getId());

		Assertions.assertThat(employeeEntityRepository.findById(savedEmployee.getId())).isEmpty();
	}
}
