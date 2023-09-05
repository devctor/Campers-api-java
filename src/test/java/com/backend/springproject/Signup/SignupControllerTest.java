package com.backend.springproject.Signup;
import com.backend.springproject.Activity.*;
import com.backend.springproject.Camper.Camper;
import com.backend.springproject.Camper.CamperRepository;
import com.backend.springproject.Signup.DTO.SignupRequestDTO;
import com.backend.springproject.Signup.DTO.SignupResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
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

    @Test
    public void testCreateSignup() throws Exception {
        // Mock data for request DTO
        SignupRequestDTO requestDTO = new SignupRequestDTO();
        requestDTO.setCamperId(1L);
        requestDTO.setActivityId(2L);
        requestDTO.setTime(9);

        // Mock data for entities
        Camper camper = new Camper();
        camper.setId(1L);

        Activity activity = new Activity();
        activity.setId(2L);

        // Mock the service
        Mockito.when(signupService.addSignup(Mockito.any(SignupRequestDTO.class))).thenReturn(createMockSignup(camper, activity));

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

    @Test
    public void testCreateSignupCamperNotFound() throws Exception {
        // Mock data for request DTO
        SignupRequestDTO requestDTO = new SignupRequestDTO();
        requestDTO.setCamperId(1L);
        requestDTO.setActivityId(2L);
        requestDTO.setTime(9);

        // Mock the service to throw EntityNotFoundException for Camper
        Mockito.when(signupService.addSignup(Mockito.any(SignupRequestDTO.class)))
                .thenThrow(new EntityNotFoundException("Camper not found with id: 1"));

        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/signups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateSignupActivityNotFound() throws Exception {
        // Mock data for request DTO
        SignupRequestDTO requestDTO = new SignupRequestDTO();
        requestDTO.setCamperId(1L);
        requestDTO.setActivityId(2L);
        requestDTO.setTime(9);

        // Mock the service to throw EntityNotFoundException for Activity
        Mockito.when(signupService.addSignup(Mockito.any(SignupRequestDTO.class)))
                .thenThrow(new EntityNotFoundException("Activity not found with id: 2"));

        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/signups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isNotFound());
    }

    private Signup createMockSignup(Camper camper, Activity activity) {
        Signup signup = new Signup();
        signup.setId(1L);
        signup.setCamper(camper);
        signup.setActivity(activity);
        signup.setTime(9);
        return signup;
    }
}
