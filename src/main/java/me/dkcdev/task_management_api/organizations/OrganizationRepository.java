package me.dkcdev.task_management_api.organizations;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dkcdev.task_management_api.organizations.models.Organization;

@Repository
public interface OrganizationRepository  extends JpaRepository<Organization,UUID> {
    Organization findByOrganizationName(String orgName);
}
