package com.backend.springproject.Camper.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CamperResponseDTO {

//    @NotNull(message = "Name is required")
//    @NotBlank(message = "Invalid Name: Empty")
//    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
//    private String name;
    private String id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 8, message = "Age must be at least 8")
    @Max(value = 18, message = "Age must be at most 18")
    private Integer age;
}
