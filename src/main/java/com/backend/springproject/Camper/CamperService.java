package com.backend.springproject.Camper;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamperService {
    @Autowired
    private CamperRepository camperRepository;

    public List<Camper> getAllCampers(){
        return camperRepository.findAll();
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
