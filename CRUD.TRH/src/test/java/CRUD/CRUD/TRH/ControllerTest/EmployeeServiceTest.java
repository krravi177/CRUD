package CRUD.CRUD.TRH.ControllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import CRUD.CRUD.TRH.Entity.EmployeeEntity;
import CRUD.CRUD.TRH.Repository.EmployeeRepository;
import CRUD.CRUD.TRH.Service.EmployeeService;

public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	private EmployeeEntity employee;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		employee = new EmployeeEntity();
		employee.setId(1L);
		employee.setName("John Doe");
		employee.setDepartment("Engineering");
		employee.setEmail("john.doe@example.com");
	}

	@Test
	void getAllEmployees() {
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

		List<EmployeeEntity> employees = employeeService.getAllEmployees();

		assertThat(employees).isNotEmpty();
		assertThat(employees.size()).isEqualTo(1);
	}

	@Test
	void getEmployeeById_WhenExists() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

		EmployeeEntity foundEmployee = employeeService.getEmployeeById(1L);

		assertThat(foundEmployee).isNotNull();
		assertThat(foundEmployee.getName()).isEqualTo("John Doe");
	}

	@Test
	void getEmployeeById_WhenNotExists() {
		when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

		EmployeeEntity foundEmployee = employeeService.getEmployeeById(999L);

		assertThat(foundEmployee).isNull();
	}

	@Test
	void createEmployee() {
		when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employee);

		EmployeeEntity createdEmployee = employeeService.createEmployee(employee);

		assertThat(createdEmployee).isNotNull();
		assertThat(createdEmployee.getName()).isEqualTo("John Doe");
	}

	@Test
	void updateEmployee_WhenEmployeeExists() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

		EmployeeEntity updatedDetails = new EmployeeEntity();
		updatedDetails.setName("Jane Doe");
		updatedDetails.setDepartment("HR");
		updatedDetails.setEmail("jane.doe@example.com");


		when(employeeRepository.save(any(EmployeeEntity.class))).thenAnswer(invocation -> {
			EmployeeEntity emp = invocation.getArgument(0);
			emp.setId(1L);
			return emp;
		});

		EmployeeEntity updatedEmployee = employeeService.updateEmployee(1L, updatedDetails);

		assertThat(updatedEmployee).isNotNull();
		assertThat(updatedEmployee.getName()).isEqualTo("Jane Doe");
		assertThat(updatedEmployee.getDepartment()).isEqualTo("HR");
		assertThat(updatedEmployee.getEmail()).isEqualTo("jane.doe@example.com");
	}

	@Test
	void deleteEmployee() {
		employeeService.deleteEmployee(1L);

		verify(employeeRepository, times(1)).deleteById(1L);
	}
}
