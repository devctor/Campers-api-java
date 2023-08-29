package com.backend.springproject.Signup;

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
