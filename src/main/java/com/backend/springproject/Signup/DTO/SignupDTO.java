package com.backend.springproject.Signup.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SignupDTO {
    private Long id;

    @NotNull(message = "Camper ID is required")
    private Long camperId;

    @NotNull(message = "Activity ID is required")
    private Long activityId;

    @Positive(message = "Time must be a positive number")
    private Integer time;
}
