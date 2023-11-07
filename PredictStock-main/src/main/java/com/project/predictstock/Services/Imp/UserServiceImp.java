package com.project.predictstock.Services.Imp;

import com.project.predictstock.Config.Jwtservice;
import com.project.predictstock.DTO.*;
import com.project.predictstock.Entities.Subscription;
import com.project.predictstock.Entities.User;
import com.project.predictstock.Entities.Company;

import com.project.predictstock.Repositories.UserRepository;
import com.project.predictstock.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Jwtservice jwtservice;

    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        
        var user = User.builder()
                .name(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtservice.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        // this will fo everything and if password or email incorrect it will throw exception
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // everything correct so generate token
        User user = userRepository.findByEmail(request.getEmail());
        var jwtToken  = jwtservice.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public String DeleteUser(String id) {
        userRepository.deleteById(id);
        return "User with id"+id +"Deleted successfully ";

    }

    @Override
    public User UpdateUser(UpdateUserDto newUser, String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setRole(newUser.getRole());

            return userRepository.save(user);
        } else {

            return userOptional.get();
        }
    }

    @Override
    public User GetUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("User not found with Id: " + id);
        }
    }
    @Override
  public  Date getCurrentDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a"); // Use 'HH' for 24-hour format
        // Obtain the current date
        Date date = new Date();
        System.out.println(format.format(date));

        return date; // Return the current date
    }




}








