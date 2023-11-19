package com.project.predictstock.Services;

import com.project.predictstock.DTO.AuthenticationResponse;
import com.project.predictstock.DTO.LoginRequest;
import com.project.predictstock.DTO.RegisterRequest;
import com.project.predictstock.DTO.UpdateUserDto;
import com.project.predictstock.Entities.User;

import java.util.List;

public interface UserService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(LoginRequest request);

    String DeleteUser (String id);

    User UpdateUser(UpdateUserDto newUser , String id);

    User GetUserById(String id);
    List<User> GetAllUsers();
}
