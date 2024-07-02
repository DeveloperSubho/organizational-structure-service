package com.interview.organizational_structure_service.service;

import com.interview.organizational_structure_service.model.Employee;
import com.interview.organizational_structure_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findMaxNumberOfDirectReports() {
        // Retrieve all employees
        List<Employee> employees = employeeRepository.findAll();

        // Initialize variables to keep track of the maximum number of direct reports and the employee with the most direct reports
        int maxDirectReports = 0;
        Employee employeeWithMaxDirectReports = null;

        // Iterate through each employee to count their direct reports
        for (Employee employee : employees) {
            int directReportCount = 0;
            // Iterate through all employees to check if the current employee is their manager
            for (Employee emp : employees) {
                if (emp.getManager() != null && emp.getManager().getId().equals(employee.getId())) {
                    directReportCount++;
                }
            }
            // Update the maximum number of direct reports and the employee with the most direct reports if necessary
            if (directReportCount > maxDirectReports) {
                maxDirectReports = directReportCount;
                employeeWithMaxDirectReports = employee;
            }
        }

        return employeeWithMaxDirectReports;
    }

    public Employee findCommonManager(Employee employee1, Employee employee2) {
        // Create sets to keep track of visited managers for both employees
        Set<Employee> visitedManagers1 = new HashSet<>();
        Set<Employee> visitedManagers2 = new HashSet<>();

        // Trace the path to the root for employee1 and add all managers to the set
        Employee current = employee1.getManager();
        while (current != null) {
            visitedManagers1.add(current);
            current = current.getManager();
        }

        // Trace the path to the root for employee2 and check for the first common manager excluding employee1
        current = employee2.getManager();
        while (current != null) {
            // If the current manager is already visited by employee1, it is the common manager excluding the employees
            if (visitedManagers1.contains(current)) {
                return current;
            }
            visitedManagers2.add(current);
            current = current.getManager();
        }

        // Trace the path to the root for employee1 again, excluding visited managers of employee2
        current = employee1.getManager();
        while (current != null) {
            // If the current manager is already visited by employee2, it is the common manager excluding the employees
            if (visitedManagers2.contains(current)) {
                return current;
            }
            current = current.getManager();
        }

        return null; // If there's no common manager excluding the employees (shouldn't happen in a valid org structure)
    }

    public List<Employee> findAllReportsToManager(Long managerId) {
        return employeeRepository.findAll().stream()
                .filter(emp -> emp.getManager() != null && emp.getManager().getId().equals(managerId))
                .collect(Collectors.toList());
    }

    public void updateSalary(Long employeeId, double newSalary) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setSalary(newSalary);
            employeeRepository.save(employee);
        }
    }

    public Double calculateTotalChangeInBudget(Long managerId) {
        return employeeRepository.findTotalChangeInBudget(managerId);
    }
}