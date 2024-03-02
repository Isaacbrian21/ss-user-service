package com.ssuserserviceapp.service.impl;

import com.ssuserserviceapp.dto.UserDto;
import com.ssuserserviceapp.entity.User;
import com.ssuserserviceapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void userById() {
        //Arr
        User userToFind = new User(1L, "isaac", "userisaac", "12345", "isaas@gmail.com");
        Optional<User> userOptional = Optional.of(userToFind);

        when(userRepository.findById(userToFind.getId())).thenReturn(userOptional);

        //Act
        Optional<User> user = userService.userById(userToFind.getId());


        //Ass
        verify(userRepository, times(1)).findById(userToFind.getId());

        assertEquals(userOptional, user);
        assertTrue(user.isPresent());
        assertEquals(userToFind, user.get());
    }



    @Test
    void getAllUsers() {


    }

    @Test
    void createUSer() {
    }

    @Test
    void updateUserProperties() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUserPassword() {
    }
}