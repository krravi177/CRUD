package CRUD.CRUD.TRH.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CRUD.CRUD.TRH.Entity.EmployeeEntity;   
import CRUD.CRUD.TRH.Repository.EmployeeRepository;   

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository; 

	public List<EmployeeEntity> getAllEmployees() { 
		return employeeRepository.findAll(); 
	}

	public EmployeeEntity getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null); 
	}

	public EmployeeEntity createEmployee(EmployeeEntity employee) {
		return employeeRepository.save(employee); 
	}

	public EmployeeEntity updateEmployee(Long id, EmployeeEntity employeeDetails) {
		EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
		if (employee != null) {
			employee.setName(employeeDetails.getName());
			employee.setDepartment(employeeDetails.getDepartment());
			employee.setEmail(employeeDetails.getEmail());
			return employeeRepository.save(employee); 
		}
		return null; 
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id); 
	}
}