package sn.ecpi.e_Vote.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ecpi.e_Vote.DTO.UserDTO;
import sn.ecpi.e_Vote.repository.UserRepository;
import sn.ecpi.e_Vote.services.UserServices;

import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/users")

public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId) {
        UserDTO userDTO = userServices.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userServices.createUser(userDTO);
        return  new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userServices.updateUser(userId, userDTO);
        return  ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userServices.deleteUser(userId);
        return  ResponseEntity.noContent().build();
    }
}
