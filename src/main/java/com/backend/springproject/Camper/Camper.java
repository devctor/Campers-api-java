package com.backend.springproject.Camper;

import com.backend.springproject.Signup.Signup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
//import org.springframework.data.annotation.Id;

import java.lang.annotation.Documented;
import java.util.Set;

@Data
@Entity
@Table
public class Camper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Invalid Name: Empty")
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private  String name;

    @Column(name = "age")
    private  Number age;

    @NotNull(message = "Username is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    @Column(name = "username")
    private  String username;

    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name = "password")
    private  String password;

    @OneToMany(mappedBy = "camper")
    private Set<Signup> signups;


    public Camper(Long id, String name, Number age, String username, String password, Set<Signup> signups) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.signups = signups;
    }

    public Camper(String name, Number age, String username, String password) {
    }

    public Camper(){}

}
