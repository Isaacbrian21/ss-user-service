package com.ssuserserviceapp.service;

import com.ssuserserviceapp.dto.UserDto;
import com.ssuserserviceapp.entity.User;
import com.ssuserserviceapp.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface UserService {


    public Optional<User> userById(Long id);

    public List<UserDto> getAllUsers();

    public User createUSer(UserRequest request);


    public User updateUserProperties(Long id, UserRequest request);


    public void deleteUser(Long id);


    void updateUserPassword(String username, String currentPassword, String newPassword, Long id);
}
