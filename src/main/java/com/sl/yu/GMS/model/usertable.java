package com.sl.yu.GMS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class usertable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_ID")
    private String userID;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "ROLE")
    private String role;
}
