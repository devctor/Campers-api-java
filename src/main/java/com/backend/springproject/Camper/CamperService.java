package com.backend.springproject.Camper;

import com.backend.springproject.Activity.ActivityDTO;
import com.backend.springproject.Camper.DTO.CamperDTO;
import com.backend.springproject.Camper.DTO.CamperResponseDTO;
import com.backend.springproject.Camper.DTO.CamperWithActivitiesResponse;
import com.backend.springproject.Exception.ResourceNotFoundException;
import com.backend.springproject.utils.MapperUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CamperService {
    @Autowired
    private CamperRepository camperRepository;
    @Autowired
    private ModelMapper modelMapper;
//    private Function<? super Camper,?> CamperDTOMapper;

    public List<CamperResponseDTO> getAllCampers(){
        return MapperUtil.mapList(camperRepository.findAll(), CamperResponseDTO.class);
    }

    public Camper addCamper(@Valid Camper camper) {
//        try {
        return  camperRepository.save(camper);
/*
            CamperDTO _camper =  camperRepository
                    .save(
                            camper.getName(), camper.getAge(), camper.getUsername(), camper.getPassword());
            return new ResponseEntity<RegisterCamperDTO>(_camper, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


*/
    }
    public CamperWithActivitiesResponse getCamperWithActivities(Long id) {
        Camper camper = camperRepository.findById(id).orElse(null); // Fetch the camper by ID

        if (camper == null) {
            throw new ResourceNotFoundException("Camper not found"); // Camper not found, you can handle this as needed
        }
            return mapCamperToResponse(camper);

    }

    private CamperWithActivitiesResponse mapCamperToResponse(Camper camper) {
        CamperWithActivitiesResponse response = new CamperWithActivitiesResponse();
        response.setId(camper.getId());
        response.setName(camper.getName());
        response.setAge(camper.getAge());
        response.setActivities(camper.getActivities().stream()
                .map(activity -> modelMapper.map(activity, ActivityDTO.class))
                .collect(Collectors.toList()));
        return response;
    }

}
