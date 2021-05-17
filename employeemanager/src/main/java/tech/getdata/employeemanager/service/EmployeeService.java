package tech.getdata.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getdata.employeemanager.exception.UserNotFoundException;
import tech.getdata.employeemanager.model.Employee;
import tech.getdata.employeemanager.repo.EmployeeRepo;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeerepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeerepo) {
        this.employeerepo = employeerepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeerepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeerepo.findAll();
    }

    public void updateEmployee(Employee employee){
        employeerepo.save(employee);
    }

    public Employee findEmployeeById(Long id){
        return employeerepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " +id+" was not found"));
    }

    public void deleteEmployee(Long id){
        employeerepo.deleteEmployeeById(id);
    }
}













