package me.dkcdev.task_management_api.common;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.dkcdev.task_management_api.users.UserRepository;
import me.dkcdev.task_management_api.users.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepo;
    public UserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  userRepo.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("No user found");
        UserDetails userDetails =  new UserDetailsImpl(user);
        return userDetails;
    }
}
