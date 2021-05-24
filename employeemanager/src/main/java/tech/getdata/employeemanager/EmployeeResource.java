package tech.getdata.employeemanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getdata.employeemanager.model.Employee;
import tech.getdata.employeemanager.service.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200","https://employee-manager-001.herokuapp.com"})
@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> allEmployees= employeeService.findAllEmployees();

        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }



    @GetMapping("/find/{id}")
    public Map<String,Object> getEmployee(@PathVariable("id") Long id){
        Map<String,Object> map = new HashMap<>();
        try{
            Employee employee = employeeService.findEmployeeById(id);
            map.put("data",employee);
            map.put("status","success");
        }catch(Exception e){
            map.put("message",e.getMessage());
            map.put("status","failed");
        }
        return map;
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee= employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee= employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
