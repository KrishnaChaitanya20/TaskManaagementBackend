package me.dkcdev.task_management_api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import me.dkcdev.task_management_api.auth.emuns.Roles;
import me.dkcdev.task_management_api.organizations.OrganizationService;
import me.dkcdev.task_management_api.organizations.models.Organization;
import me.dkcdev.task_management_api.users.UserService;
import me.dkcdev.task_management_api.users.models.User;

@Service
public class AuthService {
    @Autowired
    private OrganizationService orgService;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Transactional
    public String registerOrg(String organizationName, String email, String name, String password) {
        if(this.orgService.findByName(organizationName) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Organization with this name already exists");
        }
        if(this.userService.findByEmail(email) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
        }

        Organization org = this.orgService.createOrganization(organizationName);
        User user = this.userService.createUser(email, name, password, org, Roles.ADMIN);

        return jwtService.generateToken(user);
    }

    public String register(String name, String email, String joinToken, String password, Roles role) {
        Organization org = this.orgService.findByName(joinToken);
        if( org == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization with this name does not exist");
        }
        if(this.userService.findByEmail(email) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
        }

        User user = this.userService.createUser(email, name, password, org, role);

        return jwtService.generateToken(user);
    }
}
