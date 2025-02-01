package sn.ecpi.e_Vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ecpi.e_Vote.entities.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID>{

    User findByUsername(String username);

    List<User> findByRoleName(String roleName);

   /* @Query("SELECT COUNT(u) FROM User u")
    long countUsers();*/
}
