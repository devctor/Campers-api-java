package com.backend.springproject.Camper.DTO;

import com.backend.springproject.Activity.ActivityDTO;
import lombok.Data;

import java.util.List;

public record CamperWithActivitiesResponse() {

    private static Long id;
    private static String name;
    private static Integer age;
    private static List<ActivityDTO> activities;

    public void setId(Long id) {
    }

    public void setName(String name) {
    }

    public void setAge(Number age) {
    }

    public void setActivities(List<ActivityDTO> collect) {
    }
}
