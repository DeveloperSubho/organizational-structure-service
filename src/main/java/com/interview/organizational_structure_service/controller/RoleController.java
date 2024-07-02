package com.interview.organizational_structure_service.controller;

import com.interview.organizational_structure_service.model.Role;
import com.interview.organizational_structure_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Get all roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    // Get role by ID
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.findById(id).orElse(null);
    }

    // Create a new role
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    // Update role details
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        Role existingRole = roleService.findById(id).orElse(null);
        if (existingRole != null) {
            updatedRole.setId(id); // Ensure the ID is set to the correct value
            return roleService.save(updatedRole);
        }
        return null; // Handle the case where role with given ID is not found
    }

    // Delete role by ID
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}