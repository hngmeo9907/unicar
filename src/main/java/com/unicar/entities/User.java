package com.unicar.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "updated_date")
    private Date updatedDate;

    public User() {
    }

    public User(Integer id, String username, String password, String mobile, Date updatedDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.updatedDate = updatedDate;
    }
}
