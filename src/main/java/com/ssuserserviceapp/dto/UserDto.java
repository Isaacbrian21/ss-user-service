package com.ssuserserviceapp.dto;

import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;
    private String userName;

    private String email;

   // private List<Task> assossiatedTasks;

}
