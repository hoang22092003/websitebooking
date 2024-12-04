package com.websitebooking.service;

import com.websitebooking.model.Activity;
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

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getActivityById(Long id) {
        Optional<Activity> activity = activityRepository.findById(id);
        return activity.orElse(null); // hoặc throw Exception nếu muốn xử lý lỗi khi không tìm thấy
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public List<Activity> searchActivities(String location) {
        return activityRepository.findByLocationContainingAndIsAvailable(location, true);
    }
}
