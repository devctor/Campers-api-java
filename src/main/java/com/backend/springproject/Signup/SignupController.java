package com.backend.springproject.Signup;

import com.backend.springproject.Signup.DTO.SignupDTO;
import com.backend.springproject.Signup.DTO.SignupRequestDTO;
import com.backend.springproject.Signup.DTO.SignupResponseDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private SignupService signupService;

    @Autowired
    private ModelMapper modelMapper;

    public SignupController() {
    }

    @PostMapping
    public ResponseEntity<SignupResponseDTO> createSignup(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        Signup createdSignup = signupService.addSignup(signupRequestDTO);
        SignupResponseDTO responseDTO = modelMapper.map(createdSignup, SignupResponseDTO.class);
        return ResponseEntity.ok().body(responseDTO);
    }
}

