package sn.ecpi.e_Vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ecpi.e_Vote.entities.Role;

import java.util.List;
import java.util.UUID;

@Repository

public interface RoleRepository extends JpaRepository<Role, UUID> {

    List<Role> findByRoleName(String roleName); // Modification de la méthode pour renvoyer une liste de rôles

    Role findByUserId(UUID userId);

    Role findByRoleId(UUID roleId);
}
