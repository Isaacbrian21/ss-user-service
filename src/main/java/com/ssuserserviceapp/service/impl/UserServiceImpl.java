package com.ssuserserviceapp.service.impl;


import com.ssuserserviceapp.dto.PaswordUpdateDTO;
import com.ssuserserviceapp.dto.UserDto;
import com.ssuserserviceapp.entity.User;
import com.ssuserserviceapp.mapper.UserMapper;
import com.ssuserserviceapp.repository.UserRepository;
import com.ssuserserviceapp.request.UserRequest;
import com.ssuserserviceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public Optional<User> userById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String encryptedPassword = encoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
        }

        return userOptional;
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
//       return userList.stream()
//               .map(user -> new UserDto(user.getId(), user.getName(), user.getUserName(), user.getEmail(),
//                       user.getAssossiatedTasks())).collect(Collectors.toList());
        return userList.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getUserName(), user.getEmail())).collect(Collectors.toList());
    }


    @Override
    public User createUSer(UserRequest request) {
        User user = UserMapper.user(request);
        String newPassword = request.getPassword();
        String hashedPassword = encoder.encode(newPassword);
        user.setPassword(hashedPassword);
       User userTosave = userRepository.save(user);

        return userTosave;
    }

    @Override
    public User updateUserProperties(Long id, UserRequest request) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().setUserName(request.getUserName());
            user.get().setName(request.getName());
            user.get().setEmail(request.getEmail());
        }
        return userRepository.save(user.get());
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    @Override
    public User updateUserPassword(PaswordUpdateDTO updateDTO, Long id) {
    Optional<User> user = userRepository.findById(id);

    if (!user.isPresent()) {
        throw new IllegalArgumentException("User not Found!");
    }

    if (!encoder.matches(updateDTO.getCurrentPassword(), user.get().getPassword())) {
        log.info("Senha antiga lançada pelo usuário para ser verificado: " + updateDTO.getCurrentPassword());
        log.info("Senha atual do usuário: " + user.get().getPassword());

        throw new IllegalArgumentException("Incorrect current user password!");
    }


    String hashedNewPassword = encoder.encode(updateDTO.getNewPassword());

    if (encoder.matches(updateDTO.getNewPassword(), user.get().getPassword())) {
        throw new IllegalArgumentException("New password cannot be equals old password");
    }

    log.info("Pasword updated successfully");

    User userToSsave = userRepository.save(user.get());

    return userToSsave;
    }
}
