package com.backend.springproject.Camper;

import com.backend.springproject.Camper.Camper;
import com.backend.springproject.Camper.CamperRepository;
import com.backend.springproject.Camper.CamperService;
import com.backend.springproject.Camper.DTO.CamperDTO;
import com.backend.springproject.Camper.DTO.CamperRegisterDTO;
import com.backend.springproject.Camper.DTO.CamperResponseDTO;
import com.backend.springproject.SpringProjectApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = SpringProjectApplication.class) // Replace YourApplication with your main application class
@AutoConfigureMockMvc
public class CamperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CamperService camperService;

    @MockBean
    private CamperRepository camperRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        // Define any necessary mock behaviors before each test
    }

    @Test
    public void testGetAllCampers() throws Exception {
        // Mock the behavior of the CamperService to return a list of CamperResponseDTOs
        List<CamperResponseDTO> mockCampers = Collections.singletonList(new CamperResponseDTO());
        Mockito.when(camperService.getAllCampers()).thenReturn(mockCampers);

        mockMvc.perform(MockMvcRequestBuilders.get("/campers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockCampers.size()));
    }

    @Test
    public void testRegisterCamper() throws Exception {
        // Create a CamperRegisterDTO for the request
        CamperRegisterDTO camperRegisterDTO = new CamperRegisterDTO();
        camperRegisterDTO.setName("Test Camper");
        camperRegisterDTO.setAge(15); // Provide valid age
        camperRegisterDTO.setUsername("testuser");
        camperRegisterDTO.setPassword("password123");
        // Add other properties as needed

        // Mock the behavior of the CamperService to return a CamperResponseDTO
        CamperResponseDTO mockCamperResponse = new CamperResponseDTO();
        Mockito.when(camperService.addCamper(Mockito.any(Camper.class))).thenReturn(new Camper());

        // Perform a POST request with the CamperRegisterDTO as JSON
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/camper")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(camperRegisterDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // You can perform additional assertions on the response if needed
    }

    @Test
    public void testRegisterCamperWithInvalidData() throws Exception {
        // Create a CamperRegisterDTO with missing or invalid data
        CamperRegisterDTO invalidCamperDTO = new CamperRegisterDTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/camper")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCamperDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray());
        // Add assertions for specific error messages and fields as needed
    }

//    @Test
//    public void testGetCamperByIdWithValidId() throws Exception {
//        // Mock the behavior of the CamperService to return a CamperDTO
//        CamperDTO mockCamperDTO = new CamperDTO();
//        mockCamperDTO.setId(1L);
//        mockCamperDTO.setName("Test Camper");
//
//        // Specify the ID you want to test
//        Long testCamperId = 1L;
//
//        // Mock the service behavior to return the CamperDTO when the specified ID is requested
//        Mockito.when(camperService.getCamperById(testCamperId)).thenReturn(mockCamperDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/campers/{id}", testCamperId))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Camper"));
//        // Add more assertions as needed for other fields
//    }
}
