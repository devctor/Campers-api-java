package com.backend.springproject.Activity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ActivityDTO {
    private Long id;

    @NotNull(message = "Activity name is required")
    private String name;

    @Positive(message = "Difficulty must be a positive number")
    private Integer difficulty;
}
