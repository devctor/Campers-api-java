package com.backend.springproject.Activity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Activity name is required")
    @Size(min = 2, max = 100, message = "Activity name must be between 2 and 100 characters")
    @Column(name = "activity_name", nullable = false, length = 100)
    private String name;

    private  Number difficulty;

    public Activity(Long id, String name, Number difficulty) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
    }
    public Activity(String name, Number difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public Activity() {}
}
