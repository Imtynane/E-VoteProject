package sn.ecpi.e_Vote.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter

public class RoleDTO {
    private UUID roleId;

    private String roleName;
}
