package com.example.CNFABackend.Services;

import com.example.CNFABackend.Entities.*;
import com.example.CNFABackend.Reposititories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepository) {
        this.userRepo = userRepository;
    }


    private UserDTO MapToDTO(User user){
        UserDTO userD = new UserDTO();
        userD.setEmail(user.getEmail());
        userD.setUsername(user.getUsername());
        userD.setRule(String.valueOf(user.getRule()));
        return userD;
    }

    private User MapToUser(UserDTO userD){
        User user = new User();
        user.setEmail(userD.getEmail());
        user.setUsername(userD.getUsername());
        user.setRule(String.valueOf(userD.getRule()));
        return user;
    }




    public UserDTO getUser(String email) {
        if(email.contains("@") && email.contains(".")) {
            Optional<User> user = Optional.ofNullable(userRepo.findByEmail(email));
            return user.map(this::MapToDTO).orElse(null);
        }
        Optional<User> user = Optional.ofNullable(userRepo.findByUsername(email));
        return user.map(this::MapToDTO).orElse(null);
    }

    private User getUserpr(String email) {
        if(email.contains("@") && email.contains(".")) {
            Optional<User> user = Optional.ofNullable(userRepo.findByEmail(email));
            return user.orElse(null);
        }
        Optional<User> user = Optional.ofNullable(userRepo.findByUsername(email));
        return user.orElse(null);

    }

    public UserDTO Authenticate(Login login) {
        User user = getUserpr(login.getEmail());
        if(user!=null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(login.getPassword(), user.getPassword())) {
                return MapToDTO(user);
            }
            return null;
        }
        return null;
    }

    public UserDTO Signup(Signup signup) {
        User userch = getUserpr(signup.getEmail());
        if(userch!=null) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if(signup.getPassword().equals(signup.getConfirmPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setEmail(signup.getEmail());
            user.setUsername(signup.getUserName().toLowerCase());
            user.setPassword(encoder.encode(signup.getPassword()));
            userRepo.save(user);
            return MapToDTO(user);
        }
        return null;
    }

    public List<UserDTO> getAllEmployees(int pageNum) {
        Page<User> emps = userRepo.findAllByRule("Employee", PageRequest.of(pageNum, 5));
        return emps.getContent().stream().map(this::MapToDTO).collect(Collectors.toList());
    }

    public int getNumOfPagesForEmployyes() {
        long totalProducts = userRepo.countByRule("Employee");
        return (int) Math.ceil((double) totalProducts / 5);
    }

    public Boolean DeleteUserAccount(String userName) {
        Optional<User> user = Optional.ofNullable(userRepo.findByEmail(userName));
        if(user.isEmpty())return false;
        userRepo.delete(user.get());
        return true;
    }

    public Boolean MakeNewAdmin(String userName) {
        Optional<User> user = Optional.ofNullable(userRepo.findByEmail(userName));
        if(user.isEmpty())return false;
        user.get().setRule("ADMIN");
        userRepo.save(user.get());
        return true;
    }


    //Admin Service //

    public List<UserDTO> getAllAdmins(int pageNum) {
        Page<User> emps = userRepo.findAllByRule("ADMIN", PageRequest.of(pageNum, 5));
        return emps.getContent().stream().map(this::MapToDTO).collect(Collectors.toList());
    }
    public int getNumOfPagesForAdmins() {
        long totalProducts = userRepo.countByRule("ADMIN");
        return (int) Math.ceil((double) totalProducts / 5);
    }

    public List<UserDTO> getEmployeesStartingWithName(String name) {
        Optional<List<User>> farmers = userRepo.findUsersByUsernameStartingWithAndRule(name.toLowerCase(),"Employee");
        return farmers.orElse(Collections.emptyList()) // Use an empty list if not present
                .stream() // Stream over the list, not the Optional
                .map(this::MapToDTO)
                .collect(Collectors.toList());
    }

    public Boolean removeAdmin(String userName) {
        Optional<User> user = Optional.ofNullable(userRepo.findByEmail(userName));
        if(user.isEmpty())return false;
        user.get().setRule("Employee");
        userRepo.save(user.get());
        return true;
    }

    public List<UserDTO> getAdminsStartingWithName(String name) {
        Optional<List<User>> farmers = userRepo.findUsersByUsernameStartingWithAndRule(name.toLowerCase(),"ADMIN");
        return farmers.orElse(Collections.emptyList()) // Use an empty list if not present
                .stream() // Stream over the list, not the Optional
                .map(this::MapToDTO)
                .collect(Collectors.toList());
    }
}
