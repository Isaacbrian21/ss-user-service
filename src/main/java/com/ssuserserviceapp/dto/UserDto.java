package com.ssuserserviceapp.dto;

import com.ssuserserviceapp.entity.Task;


import java.util.List;

public class UserDto {

    private Long id;

    private String name;
    private String userName;

    private String password;

    private String email;

    private List<Task> assossiatedTasks;
}
