package com.backend.springproject.Camper;
import com.backend.springproject.Camper.DTO.CamperRegisterDTO;
import com.backend.springproject.Camper.DTO.CamperResponseDTO;
import com.backend.springproject.Camper.DTO.CamperWithActivitiesResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class CamperController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CamperService camperService;
    @Autowired
    private CamperRepository camperRepository;

    @GetMapping("/camper")
    public  String index() { return "camper /GET";}

    @GetMapping("/campers")
    public ResponseEntity<List<CamperResponseDTO>> getAllCampers() {
        List<CamperResponseDTO> allCampers = camperService.getAllCampers();
        if (allCampers.isEmpty()) {
            throw new EntityNotFoundException("No campers found.");
        }
        return  ResponseEntity.ok().body(allCampers);
    }

    @PostMapping("/camper")
    public ResponseEntity<CamperResponseDTO> registerCamper(@Valid @RequestBody CamperRegisterDTO camperRegisterDTO){
        try {

        Camper camperRequest = modelMapper.map(camperRegisterDTO, Camper.class);
        Camper  _camper = camperService.addCamper(camperRequest);
        CamperResponseDTO camperResponse = modelMapper.map(_camper, CamperResponseDTO.class);

            return ResponseEntity.ok().body(camperResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/camper/{id}")
    public ResponseEntity<CamperWithActivitiesResponse> getCamperWithActivities(@PathVariable Long id) {
        CamperWithActivitiesResponse camperWithActivities = camperService.getCamperWithActivities(id);
        if (camperWithActivities != null) {
            return ResponseEntity.ok(camperWithActivities);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if camper is not found
        }
    }
}
