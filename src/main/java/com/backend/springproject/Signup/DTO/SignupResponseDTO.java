package com.backend.springproject.Signup.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupResponseDTO {
    private Long id;
    private Long camperId;
    private Long activityId;
    private Integer time;

}


