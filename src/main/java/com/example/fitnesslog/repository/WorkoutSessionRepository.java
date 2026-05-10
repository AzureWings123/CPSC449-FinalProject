package com.example.fitnesslog.repository;

import com.example.fitnesslog.entity.WorkoutSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkoutSessionRepository extends MongoRepository<WorkoutSession, String> {
    List<WorkoutSession> findByUserId(String userId);
}
