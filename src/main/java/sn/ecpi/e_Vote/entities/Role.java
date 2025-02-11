package sn.ecpi.e_Vote.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity (name = "_role")
@Getter @Setter

public class Role {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Column(nullable = false)
    private String roleName;
    @Column(nullable = false)
    private UUID userId; // Ajout de la propriété userId
}
