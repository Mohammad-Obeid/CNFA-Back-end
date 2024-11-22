package com.example.CNFABackend.Controllers;


import com.example.CNFABackend.Entities.*;
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
    //new
    @PatchMapping("/employee/{userName}")
    public ResponseEntity<Boolean> MakeNewAdmin(@PathVariable String userName) {
        Optional<Boolean> result= Optional.ofNullable(userService.MakeNewAdmin(userName));
        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    @DeleteMapping("admin/{userName}")
    public ResponseEntity<Boolean> DeleteAdmin(@PathVariable String userName) {
        Optional<Boolean> result= Optional.ofNullable(userService.DeleteUserAccount(userName));
        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    @GetMapping("/page/{pageNum}")
    public ResponseEntity<List<UserDTO>> getAllEmployees(@PathVariable int pageNum) {
        Optional<List<UserDTO>> employees= Optional.ofNullable(userService.getAllEmployees(pageNum));
        return employees.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    @GetMapping("/page")
    public int getNumOfPages(
    ){
        int num= userService.getNumOfPagesForEmployyes();
        return num;
    }

    @GetMapping("/admin/page/{pageNum}")
    public ResponseEntity<List<UserDTO>> getAllAdmins(@PathVariable int pageNum) {
        Optional<List<UserDTO>> employees= Optional.ofNullable(userService.getAllAdmins(pageNum));
        return employees.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    @GetMapping("/admin/page")
    public int getNumOfPagesforAdmins(
    ){
        int num= userService.getNumOfPagesForAdmins();
        return num;
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserDTO>> getEmployeesStartingWithName(@PathVariable("name") String name) {

        Optional<List<UserDTO>> farmer= Optional.ofNullable(userService.getEmployeesStartingWithName(name));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }


    @PatchMapping("/admin/{userName}")
    public ResponseEntity<Boolean> removeAdmin(@PathVariable String userName) {
        Optional<Boolean> result= Optional.ofNullable(userService.removeAdmin(userName));
        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }


    @GetMapping("/admin/search/{name}")
    public ResponseEntity<List<UserDTO>> getAdminsStartingWithName(@PathVariable("name") String name) {

        Optional<List<UserDTO>> farmer= Optional.ofNullable(userService.getAdminsStartingWithName(name));
        return farmer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

}
