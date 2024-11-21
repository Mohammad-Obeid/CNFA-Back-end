package com.example.CNFABackend.Services;

import com.example.CNFABackend.Entities.*;
import com.example.CNFABackend.Reposititories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            user.setUsername(signup.getUserName());
            user.setPassword(encoder.encode(signup.getPassword()));
            userRepo.save(user);
            return MapToDTO(user);
        }
        return null;
    }


}
