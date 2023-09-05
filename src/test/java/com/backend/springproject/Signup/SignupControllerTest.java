package com.backend.springproject.Signup;
import com.backend.springproject.Activity.*;
import com.backend.springproject.Camper.Camper;
import com.backend.springproject.Camper.CamperRepository;
import com.backend.springproject.Signup.DTO.SignupRequestDTO;
import com.backend.springproject.Signup.DTO.SignupResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SignupController.class)
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SignupService signupService;

    @MockBean
    private CamperRepository camperRepository;

    @MockBean
    private ActivityRepository activityRepository;

    @BeforeEach
    public void setUp() {
        // Mock Camper and Activity entities
        Camper camper = new Camper();
        camper.setId(1L);

        Activity activity = new Activity();
        activity.setId(2L);

        Mockito.when(camperRepository.findById(1L)).thenReturn(java.util.Optional.of(camper));
        Mockito.when(activityRepository.findById(2L)).thenReturn(java.util.Optional.of(activity));
    }

    @Test
    public void testCreateSignup() throws Exception {
        // Mock data
        SignupRequestDTO requestDTO = new SignupRequestDTO();
        requestDTO.setCamperId(1L);
        requestDTO.setActivityId(2L);
        requestDTO.setTime(9);

        SignupResponseDTO signup = new SignupResponseDTO();
        signup.setId(1L);
        signup.setCamperId(1L);
        signup.setActivityId(2L);
        signup.setTime(9);

        // Mock the service
        Mockito.when(signupService.addSignup(Mockito.any(SignupRequestDTO.class))).thenReturn(signup);

        // Perform POST request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.camperId").value(1))
                .andExpect(jsonPath("$.activityId").value(2))
                .andExpect(jsonPath("$.time").value(9))
                .andReturn();

        // Verify that the service method was called
        Mockito.verify(signupService, Mockito.times(1)).addSignup(Mockito.any(SignupRequestDTO.class));
    }
}
