package com.ssuserserviceapp.request;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;

    private String userName;

    private String password;

    private String email;

}
