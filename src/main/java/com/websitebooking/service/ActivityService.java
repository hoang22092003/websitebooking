package com.websitebooking.service;

import com.websitebooking.entity.Activity;
import com.websitebooking.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id)
                .map(activity -> {
                    activity.setName(updatedActivity.getName());
                    activity.setDescription(updatedActivity.getDescription());
                    activity.setLocation(updatedActivity.getLocation());
                    activity.setDate(updatedActivity.getDate());
                    activity.setPrice(updatedActivity.getPrice());
                    activity.setImageUrl(updatedActivity.getImageUrl());
                    return activityRepository.save(activity);
                })
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
