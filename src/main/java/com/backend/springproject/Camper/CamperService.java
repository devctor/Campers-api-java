package com.backend.springproject.Camper;

import com.backend.springproject.Camper.DTO.CamperResponseDTO;
import com.backend.springproject.utils.MapperUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

}
