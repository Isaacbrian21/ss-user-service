package com.ssuserserviceapp.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;

    private String title;

    private String description;

    private Date expirationDate;

    private TaskStatus status;
}
