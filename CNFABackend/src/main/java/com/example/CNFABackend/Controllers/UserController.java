package com.example.CNFABackend.Controllers;


import com.example.CNFABackend.Entities.Login;
import com.example.CNFABackend.Entities.Signup;
import com.example.CNFABackend.Entities.User;
import com.example.CNFABackend.Entities.UserDTO;
import com.example.CNFABackend.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {

        Optional<UserDTO> user= Optional.ofNullable(userService.getUser(email));
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> Authenticate(@RequestBody Login login) {

        Optional<UserDTO> user= Optional.ofNullable(userService.Authenticate(login));
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }


    @PostMapping("")
    public ResponseEntity<UserDTO> Signup(@RequestBody Signup user) {
        Optional<UserDTO> user1= Optional.ofNullable(userService.Signup(user));
        return user1.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

}
