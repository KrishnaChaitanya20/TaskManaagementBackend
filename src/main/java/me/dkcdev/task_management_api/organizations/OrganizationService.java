package me.dkcdev.task_management_api.organizations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dkcdev.task_management_api.organizations.models.Organization;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository orgRepo;
    
    public Organization createOrganization(String name){
        Organization organization = new Organization();
        organization.setOrganizationName(name);
        organization.setJoinCode(name); //TODO: Update this to generate a random string
        return orgRepo.save(organization);
    }

    public Organization getOrganizationById(UUID id){
        return orgRepo.findById(id).orElse(null);
    }

    public List<Organization> getAllOrganizations(){
        return orgRepo.findAll();
    }

    // public Organization updateOrganization(Organization updatedOrg){
    //     return orgRepo.save(updatedOrg);
    // }

    public void deleteOrganization(UUID id){
        orgRepo.deleteById(id);
    }

    public Organization findByName(String orgName) {
       return orgRepo.findByOrganizationName(orgName);
    }

}
