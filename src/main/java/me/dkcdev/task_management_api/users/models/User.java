package me.dkcdev.task_management_api.users.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dkcdev.task_management_api.auth.emuns.Roles;
import me.dkcdev.task_management_api.organizations.models.Organization;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID userId;

    @Column
    private String userName;
    
    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organizationId")
    private Organization organization;

    @Column
    private Roles role;
}
