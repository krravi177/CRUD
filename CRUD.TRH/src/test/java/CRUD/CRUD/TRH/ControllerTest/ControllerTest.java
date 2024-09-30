package CRUD.CRUD.TRH.ControllerTest;  

import static org.mockito.ArgumentMatchers.any;  
import static org.mockito.ArgumentMatchers.eq;  
import static org.mockito.Mockito.doNothing;  
import static org.mockito.Mockito.when;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;  

import java.util.Arrays;  
import java.util.List;  

import org.junit.jupiter.api.BeforeEach;  
import org.junit.jupiter.api.Test;  
import org.mockito.InjectMocks;  
import org.mockito.Mock;  
import org.mockito.MockitoAnnotations;  
import org.springframework.http.MediaType;   
import org.springframework.test.web.servlet.MockMvc;  
import org.springframework.test.web.servlet.setup.MockMvcBuilders;  

import com.fasterxml.jackson.databind.ObjectMapper;  

import CRUD.CRUD.TRH.Controller.EmployeeController;  
import CRUD.CRUD.TRH.Entity.EmployeeEntity;  
import CRUD.CRUD.TRH.Service.EmployeeService;  

public class ControllerTest {  

    @Mock  
    EmployeeService employeeService;  

    @InjectMocks  
    EmployeeController employeeController;  

    private MockMvc mockMvc;  

    ObjectMapper objectMapper = new ObjectMapper();  
    EmployeeEntity employeeEntity;  
    List<EmployeeEntity> list;  

    @BeforeEach  
    private void setUp() throws Exception {  
        MockitoAnnotations.openMocks(this);  
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();  
        employeeEntity = getEmployee();  
        list = getEmployeeList();  
    }  

    @Test  
    void getAllEmployees() throws Exception {  
        when(employeeService.getAllEmployees()).thenReturn(list);  

        mockMvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON_VALUE))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.length()").value(list.size()));  
    }  

    @Test  
    void getEmployeeById() throws Exception {  
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeEntity);  

        mockMvc.perform(get("/api/employees/{id}", 1L).contentType(MediaType.APPLICATION_JSON))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.name").value(employeeEntity.getName()));  
    }  

    @Test  
    void createEmployee() throws Exception {  
        when(employeeService.createEmployee(any(EmployeeEntity.class))).thenReturn(employeeEntity);  

        mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)  
                .content(objectMapper.writeValueAsString(employeeEntity)))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.name").value(employeeEntity.getName()));  
    }  

    @Test  
    void updateEmployee() throws Exception {  
        when(employeeService.updateEmployee(eq(1L), any(EmployeeEntity.class))).thenReturn(employeeEntity);  

        mockMvc.perform(put("/api/employees/{id}", 1L).contentType(MediaType.APPLICATION_JSON)  
                .content(objectMapper.writeValueAsString(employeeEntity)))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.name").value(employeeEntity.getName()));  
    }  

    @Test  
    void deleteEmployee() throws Exception {  
        doNothing().when(employeeService).deleteEmployee(1L);  

        mockMvc.perform(delete("/api/employees/{id}", 1L)).andExpect(status().isNoContent());  
    }  

    private EmployeeEntity getEmployee() {  
        EmployeeEntity emp = new EmployeeEntity();  
        emp.setId(1L);  
        emp.setName("John Doe");  
        emp.setEmail("john.doe@example.com");  
        return emp;  
    }  

    private List<EmployeeEntity> getEmployeeList() {  
        return Arrays.asList(getEmployee(), new EmployeeEntity(2L, "Jane Doe", "HR", "jane.doe@example.com"));  
    }  
}