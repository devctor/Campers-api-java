package com.backend.springproject.Activity;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        List<ActivityDTO> activityDTOs = activities.stream()
                .map(activity -> modelMapper.map(activity, ActivityDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(activityDTOs);
    }

    @PostMapping("/activity")
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO activityDTO) {
        Activity activityRequest = modelMapper.map(activityDTO, Activity.class);
        Activity _activity = activityService.createActivity(activityRequest);
        ActivityDTO activityResponse = modelMapper.map(_activity, ActivityDTO.class);

        return ResponseEntity.ok().body(activityResponse);

    }
}
