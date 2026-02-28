package me.dkcdev.task_management_api.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import me.dkcdev.task_management_api.auth.dtos.RegisterDto;
import me.dkcdev.task_management_api.auth.dtos.RegisterOrgDto;
import me.dkcdev.task_management_api.auth.emuns.Roles;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired 
    AuthService authService;

    @PostMapping("/register-org")
    public ResponseEntity<Map<String,String>> registerOrg(@RequestBody RegisterOrgDto body){
        String token;
        try {
            token = authService.registerOrg(body.orgName, body.email, body.userName, body.password);
        } catch (ResponseStatusException e) {
            // return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage())
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("reason",e.getReason()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token",token));
    }

     @PostMapping("/register")
     public ResponseEntity<Map<String,String>> register(@RequestBody RegisterDto body){
        System.out.println(body);
        String token;
        Roles role;
        try {
            role = body.role() == null ? Roles.VIEWER : body.role();
            token = authService.register(body.name(), body.email(), body.joinToken(), body.password(), role);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(Map.of("reason", e.getReason()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
     }
}
