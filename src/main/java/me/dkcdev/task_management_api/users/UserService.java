package me.dkcdev.task_management_api.users;

import java.util.List;

import org.springframework.stereotype.Service;

import me.dkcdev.task_management_api.auth.emuns.Roles;
import me.dkcdev.task_management_api.organizations.models.Organization;
import me.dkcdev.task_management_api.users.models.User;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String userName, String password, Organization org, Roles role) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUserName(userName);
        newUser.setOrganization(org);
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
