package com.interview.organizational_structure_service.repository;

import com.interview.organizational_structure_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT SUM(e.salary) FROM Employee e WHERE e.manager.id = :managerId")
    Double findTotalChangeInBudget(Long managerId);
}