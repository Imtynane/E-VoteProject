package sn.ecpi.e_Vote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.e_Vote.entities.Role;
import sn.ecpi.e_Vote.repository.RoleRepository;

import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/roles")

public class RoleController {

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRole(@PathVariable UUID roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new RuntimeException("Rôle non trouvé avec l'ID: " + roleId));
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleRepository.save(role);
        return  new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable UUID roleId, @RequestBody Role role) {
        if (!roleRepository.existsById(roleId)) {
            throw new RuntimeException("Rôle non trouvé avec l'ID: " + roleId);
        }
        role.setRoleId(roleId);
        Role updatedRole = roleRepository.save(role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping ("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID roleId) {
        roleRepository.deleteById(roleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byName")
    public  ResponseEntity<Role> getRoleByName(@RequestParam String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new RuntimeException("Rôle non trouvé avec l'ID: " + name);
        }
        return ResponseEntity.ok(role);
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<Role> getRoleByUserId(@PathVariable UUID userId) {
        Role role = roleRepository.findByUserId(userId);
        if (role == null) {
            throw new RuntimeException("Rôle non trouvé avec l'ID: " + userId);
        }
        return ResponseEntity.ok(role);
    }
}
