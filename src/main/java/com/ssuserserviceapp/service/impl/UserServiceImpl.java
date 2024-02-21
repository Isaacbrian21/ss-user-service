package com.ssuserserviceapp.service.impl;

import com.ssuserserviceapp.entity.User;
import com.ssuserserviceapp.mapper.UserMapper;
import com.ssuserserviceapp.repository.UserRepository;
import com.ssuserserviceapp.request.UserRequest;
import com.ssuserserviceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public Optional<User> userById(Long id) {

        Optional<User> user = userRepository.findById(id);
        encoder.encode(user.get().getPassword());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        userList.stream().map(user -> encoder.encode(user.getPassword()));
        return userList;
    }

    @Override
    public User createUSer(UserRequest request) {
        User user = UserMapper.user(request);

        return userRepository.save(user);
    }

    @Override
    public User updateUserProperties(Long id, UserRequest request) {

        Optional<User> user = userRepository.findById(id);
        User userToBeSaved = null;
        if (user.isPresent()){
            user.get().setUserName(request.getUserName());
            user.get().setName(request.getName());
            user.get().setEmail(request.getEmail());
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    @Override
    public void updateUserPassword(String username, String currentPassword, String newPassword, Long id) {
    Optional<User> user = userRepository.findById(id);

    if (user == null) {
        throw new IllegalArgumentException("User not Found");
    }

    if (!encoder.matches(currentPassword, user.get().getPassword())) {
        throw new IllegalArgumentException("Incorrect current user password");
    }

    String hashedPassword = encoder.encode(newPassword);

    user.get().setPassword(hashedPassword);
    userRepository.save(user.get());
    }
}
