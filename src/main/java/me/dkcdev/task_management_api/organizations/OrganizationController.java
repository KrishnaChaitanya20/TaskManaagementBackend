package me.dkcdev.task_management_api.organizations;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dkcdev.task_management_api.organizations.dtos.OrganizationResponseDto;
import me.dkcdev.task_management_api.users.UserService;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    private OrganizationService orgService;
    // private UserService userSerivce;

    public OrganizationController(OrganizationService orgService, UserService userService) {
        this.orgService = orgService;
        // this.userSerivce = userService;
    }

    @GetMapping()
    public List<OrganizationResponseDto> getAllOrganizations() {
        // userSerivce.getAllUsers();
        return orgService.getAllOrganizations().stream().map(OrganizationResponseDto::fromOrganization).toList();
    }
    
}
