package com.interview.organizational_structure_service.controller;

import com.interview.organizational_structure_service.model.Employee;
import com.interview.organizational_structure_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id).orElse(null);
    }

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // Update employee details
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employeeService.findById(id).orElse(null);
        if (existingEmployee != null) {
            updatedEmployee.setId(id); // Ensure the ID is set to the correct value
            return employeeService.save(updatedEmployee);
        }
        return null; // Handle the case where employee with given ID is not found
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/max-direct-reports")
    public Employee getMaxNumberOfDirectReports() {
        return employeeService.findMaxNumberOfDirectReports();
    }

    @GetMapping("/common-manager")
    public Employee getCommonManager(@RequestParam Long employee1Id, @RequestParam Long employee2Id) {
        Employee employee1 = employeeService.findById(employee1Id).orElse(null);
        Employee employee2 = employeeService.findById(employee2Id).orElse(null);
        if (employee1 == null || employee2 == null) {
            return null; // Handle the case where one or both employees are not found
        }
        return employeeService.findCommonManager(employee1, employee2);
    }

    @GetMapping("/reports/{managerId}")
    public List<Employee> getReportsToManager(@PathVariable Long managerId) {
        return employeeService.findAllReportsToManager(managerId);
    }

    @GetMapping("/{employeeId}/salary")
    public double getSalary(@PathVariable Long employeeId) {
        return employeeService.findById(employeeId).map(Employee::getSalary).orElse(0.0);
    }

    @PutMapping("/{employeeId}/salary")
    public void updateSalary(@PathVariable Long employeeId, @RequestParam double newSalary) {
        employeeService.updateSalary(employeeId, newSalary);
    }

    @GetMapping("/total-change/{managerId}")
    public ResponseEntity<Double> getTotalChangeInBudget(@PathVariable Long managerId) {
        Double totalChange = employeeService.calculateTotalChangeInBudget(managerId);
        return ResponseEntity.ok(totalChange);
    }

}