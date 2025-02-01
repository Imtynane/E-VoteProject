package sn.ecpi.e_Vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ecpi.e_Vote.entities.Role;
import java.util.UUID;

@Repository

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);

    Role findByUserId(UUID userId);
}
