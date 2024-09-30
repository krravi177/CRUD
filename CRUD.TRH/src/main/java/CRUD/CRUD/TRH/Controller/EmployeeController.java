package CRUD.CRUD.TRH.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CRUD.CRUD.TRH.Entity.EmployeeEntity; // Changed to EmployeeEntity  
import CRUD.CRUD.TRH.Service.EmployeeService; // Ensure correct service import  

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<EmployeeEntity> getAllEmployees() { // Use EmployeeEntity
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
		EmployeeEntity employee = employeeService.getEmployeeById(id);
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(employee);
	}

	@PostMapping
	public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) { // Use EmployeeEntity
		return employeeService.createEmployee(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id,
			@RequestBody EmployeeEntity employeeDetails) { // Use EmployeeEntity
		EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
		if (updatedEmployee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build(); // Return no content response
	}
}