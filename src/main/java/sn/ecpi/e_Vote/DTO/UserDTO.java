package sn.ecpi.e_Vote.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter

public class UserDTO {

    private UUID userId;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String password;

    private UUID roleId;
}
