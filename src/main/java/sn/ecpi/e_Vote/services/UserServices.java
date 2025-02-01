package sn.ecpi.e_Vote.services;

import sn.ecpi.e_Vote.DTO.UserDTO;

import java.util.UUID;

public interface UserServices {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(UUID userId);

    UserDTO updateUser(UUID userId, UserDTO userDTO);

    void deleteUser(UUID userId);
}
