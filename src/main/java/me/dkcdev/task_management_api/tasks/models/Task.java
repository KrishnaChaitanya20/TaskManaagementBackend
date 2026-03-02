package me.dkcdev.task_management_api.tasks.models;

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
import me.dkcdev.task_management_api.organizations.models.Organization;
import me.dkcdev.task_management_api.tasks.enums.Status;
import me.dkcdev.task_management_api.users.models.User;

@Entity(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID taskId;

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organizationId")
    private Organization owner;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User createdBy;

    @Column
    private Status status;
}
