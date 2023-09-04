package com.backend.springproject.Activity;
import com.backend.springproject.Signup.Signup;
import com.backend.springproject.Signup.SignupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private  ActivityRepository activityRepository;

    @Autowired
    private SignupRepository signupRepository;

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

    public void deleteActivity(Long activityId) {
        // Find the Activity by ID
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found"));

        // Delete associated Signups
        List<Signup> signups = activity.getSignups();
        signupRepository.deleteAll(signups);

        // Delete the Activity itself
        activityRepository.delete(activity);
    }


}
