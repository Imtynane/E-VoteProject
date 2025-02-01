package sn.ecpi.e_Vote.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ecpi.e_Vote.DTO.UserDTO;
import sn.ecpi.e_Vote.entities.Role;
import sn.ecpi.e_Vote.entities.User;
import sn.ecpi.e_Vote.repository.RoleRepository;
import sn.ecpi.e_Vote.repository.UserRepository;
import sn.ecpi.e_Vote.services.UserServices;

import java.util.UUID;

@Service

public class UserServicesImpl  implements UserServices {

    private final RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO){
        // Création d'un nouvel utilisateur
        User newUser = new User();
        newUser.setFirstname(userDTO.getFirstname());
        newUser.setLastname(userDTO.getLastname());
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());

        //Récupération du rôle de l'utilisateur à partir de son identifiant
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(()-> new RuntimeException("Le rôle spécifié n'existe pas"));

        //Assigner le rôle à l'utilisateur
        newUser.setRole(role);

        //Sauvegarde de l'utilisateur en base de donnéees
        User createdUser = userRepository.save(newUser);

        //Conversion de l'utilisateur sauvegardé en UserDTO avant de le retourner
        UserDTO createdUserDTO = new UserDTO();
        createdUserDTO.setUserId(createdUser.getUserId());
        createdUserDTO.setFirstname(createdUser.getFirstname());
        createdUserDTO.setLastname(createdUser.getLastname());
        createdUserDTO.setUsername(createdUser.getUsername());
        createdUserDTO.setPassword(createdUser.getPassword());
        createdUserDTO.setRoleId(createdUser.getRole().getRoleId());

        return createdUserDTO;

    }

    @Override
    public UserDTO getUserById(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Utilisateur n'existe pas avec l'ID: " + userId));

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleId(user.getRole().getRoleId());

        return userDTO;
    }

    @Override
    public UserDTO updateUser(UUID userId, UserDTO userDTO){
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));

        existingUser.setFirstname(userDTO.getFirstname());
        existingUser.setLastname(userDTO.getLastname());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(()-> new RuntimeException("Le rôle n'existe pas"));

        existingUser.setRole(role);

        User updatedUser = userRepository.save(existingUser);

        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setUserId(updatedUser.getUserId());
        updatedUserDTO.setFirstname(updatedUser.getFirstname());
        updatedUserDTO.setLastname(updatedUser.getLastname());
        updatedUserDTO.setUsername(updatedUser.getUsername());
        updatedUserDTO.setPassword(updatedUser.getPassword());
        updatedUserDTO.setRoleId(updatedUser.getRole().getRoleId());

        return updatedUserDTO;
    }

    @Override
    public void deleteUser(UUID userId){
        userRepository.deleteById(userId);
    }
}
