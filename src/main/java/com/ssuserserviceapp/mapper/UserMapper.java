package com.ssuserserviceapp.mapper;

import com.ssuserserviceapp.entity.User;
import com.ssuserserviceapp.request.UserRequest;

public class UserMapper {

    public static User user(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .userName(request.getUserName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }

    public static User userToInsertTask(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .userName(request.getUserName())
                .password(request.getPassword())
                .email(request.getEmail())
//                .assossiatedTasks(request.getTaskList())
                .build();
    }

}
