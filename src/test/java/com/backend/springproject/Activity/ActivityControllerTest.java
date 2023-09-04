package com.backend.springproject.Activity;

import com.backend.springproject.Activity.Activity;
import com.backend.springproject.Activity.ActivityController;
import com.backend.springproject.Activity.ActivityDTO;
import com.backend.springproject.Activity.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActivityController.class)
public class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActivityService activityService;

    @Test
    public void testGetAllActivities() throws Exception {
        // Mock data
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1L, "Activity 1", 2));
        activities.add(new Activity(2L, "Activity 2", 3));

        // Mock the service
        Mockito.when(activityService.getAllActivities()).thenReturn(activities);

        // Perform GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/activities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Validate response JSON
        List<ActivityDTO> expectedActivityDTOs = activities.stream()
                .map(activity -> modelMapper.map(activity, ActivityDTO.class))
                .collect(Collectors.toList());
        String expectedJsonResponse = objectMapper.writeValueAsString(expectedActivityDTOs);
        String actualJsonResponse = result.getResponse().getContentAsString();

        // You can further assert the JSON response as needed
        assertThat(actualJsonResponse).isEqualTo(expectedJsonResponse);

        // Verify that the service method was called
        Mockito.verify(activityService, Mockito.times(1)).getAllActivities();
    }

    @Test
    public void testCreateActivity() throws Exception {
        // Mock data
        ActivityDTO requestDTO = new ActivityDTO();
        requestDTO.setName("Activity 1");
        requestDTO.setDifficulty(2);

        Activity createdActivity = new Activity(1L, "Activity 1", 2);

        // Mock the service
        Mockito.when(activityService.createActivity(Mockito.any(Activity.class))).thenReturn(createdActivity);

        // Perform POST request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/activity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Activity 1"))
                .andExpect(jsonPath("$.difficulty").value(2))
                .andReturn();

        // Verify that the service method was called
        Mockito.verify(activityService, Mockito.times(1)).createActivity(Mockito.any(Activity.class));
    }

    @Test
    public void testDeleteActivity() throws Exception {
        Long activityId = 1L;

        // Mock the service
        Mockito.doNothing().when(activityService).deleteActivity(Mockito.eq(activityId));

        // Perform DELETE request
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/activity/{id}", activityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify that the service method was called
        Mockito.verify(activityService, Mockito.times(1)).deleteActivity(Mockito.eq(activityId));
    }

    // You can add more test methods for other controller endpoints
}
