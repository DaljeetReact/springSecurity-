package com.singh.blogapp.service;

import com.singh.blogapp.dto.RegisterRequest;
import com.singh.blogapp.model.User;
import com.singh.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public void signup(RegisterRequest registerRequest) {
      User data = new User();
      data.setEmail(registerRequest.getEmail());
      data.setPassword(registerRequest.getPassword());
      data.setUsername(registerRequest.getUsername());

      this.userRepository.save(data);
    }

    public List<User> AllUsers(){
       return this.userRepository.findAll();
    }
}
