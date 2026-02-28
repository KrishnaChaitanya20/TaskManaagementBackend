package me.dkcdev.task_management_api.organizations.models;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dkcdev.task_management_api.tasks.models.Task;
import me.dkcdev.task_management_api.users.models.User;

@Entity(name = "organizations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID organizationId;

    @Column(unique = true)
    private String organizationName;

    @OneToMany(mappedBy = "organization", targetEntity = User.class)
    private List<User> users;

    @OneToMany(mappedBy = "owner", targetEntity = Task.class)
    private List<Task> tasks;

    @Column
    private String joinCode;

}
