package com.backend.springproject.Signup;

import com.backend.springproject.Activity.Activity;
import com.backend.springproject.Activity.ActivityRepository;
import com.backend.springproject.Camper.Camper;
import com.backend.springproject.Camper.CamperRepository;
import com.backend.springproject.Signup.DTO.SignupDTO;
import com.backend.springproject.Signup.DTO.SignupRequestDTO;
import com.backend.springproject.Signup.DTO.SignupResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private CamperRepository camperRepository;
    @Autowired
    private ActivityRepository activityRepository;

    public Signup addSignup(SignupRequestDTO signupRequestDTO) {
        Camper camper = camperRepository.findById(signupRequestDTO.getCamperId())
                .orElseThrow(() -> new EntityNotFoundException("Camper not found with id: " + signupRequestDTO.getCamperId()));

        Activity activity = activityRepository.findById(signupRequestDTO.getActivityId())
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + signupRequestDTO.getActivityId()));

        Signup signup = new Signup();
        signup.setCamper(camper); // Set the Camper entity
        signup.setActivity(activity); // Set the Activity entity
        signup.setTime(signupRequestDTO.getTime());

        return signupRepository.save(signup);
    }

    public void deleteSignup(Long signupId) {
        Signup signup = signupRepository.findById(signupId)
                .orElseThrow(() -> new EntityNotFoundException("Signup not found"));

        signupRepository.delete(signup);
    }
}

