package com.backend.springproject.Activity;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private  ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        Iterable<Activity> activitiesIterable = activityRepository.findAll();
        List<Activity> activities = new ArrayList<>();

        activitiesIterable.forEach(activities::add);

        if (activities.isEmpty()) {
            throw new EntityNotFoundException("No activities found.");
        }

        return activities;
    }

    public Activity createActivity(Activity activityDTO) {
        return activityRepository.save(activityDTO);
    }



}
