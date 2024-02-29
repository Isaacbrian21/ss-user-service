package com.ssuserserviceapp.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "USER_ID")
    private Long id;

    @Column( name = "NAME")
    private String name;
    @Column( name = "USER_NAME")
    private String userName;

    @Column( name = "PASSWORD")
    private String password;

    @Column( name = "EMAIL")
    private String email;



}
