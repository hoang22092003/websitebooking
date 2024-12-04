package com.websitebooking.repository;

import com.websitebooking.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByLocationContainingAndIsAvailable(String location, Boolean isAvailable);
}
