package com.backend.springproject.Activity;

import com.backend.springproject.Signup.Signup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Signup> signups;
//    private List<Signup> activities;

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

    public List<Signup> getSignups() {
        return signups;
    }

    public void setSignups(List<Signup> signups) {
        this.signups = signups;
    }


//    public void setActivities(List<Signup> activities) {
//        this.activities = activities;
//    }
}
