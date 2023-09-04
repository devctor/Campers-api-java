package com.backend.springproject.Camper.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CamperDTO {
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Invalid Name: Empty")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 8, message = "Age must be at least 8")
    @Max(value = 18, message = "Age must be at most 18")
    private Integer age;

    @NotNull(message = "Username is required")
    private String username;
    private String password;

//    public CamperDTO(List<CamperResponseDTO> camperResponseDTOList) {
//
//    }

//    public CamperDTO(String name, Integer age, String username) {
//        this.name = name;
//        this.age = age;
//        this.username = username;
//        this.password = password;
//    }

}
