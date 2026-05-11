package com.example.fitnesslog.service;

import com.example.fitnesslog.entity.WorkoutSession;
import com.example.fitnesslog.exception.ResourceNotFoundException;
import com.example.fitnesslog.repository.WorkoutSessionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutSessionRepository workoutRepository;

    public WorkoutService(WorkoutSessionRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Helper method to get the authenticated user's ID from the SecurityContext
    private String getAuthenticatedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.toString();
    }

    // Create a new workout session
    public WorkoutSession createWorkout(WorkoutSession new_workout) {
        String userId = getAuthenticatedUserId();
        new_workout.setUserId(userId);
        return workoutRepository.save(new_workout);
    }

    // Get all the workouts belonging to the authenticated user
    public List<WorkoutSession> getAllWorkouts() {
        String userId = getAuthenticatedUserId();
        return workoutRepository.findByUserId(userId);
    }

    // Get workout by ID
    public WorkoutSession getWorkoutById(String id) {
        String userId = getAuthenticatedUserId();

        WorkoutSession current_workout = workoutRepository.findById(id).orElse(null);

        if (current_workout == null) {
            throw new ResourceNotFoundException("workout with id" + id +" not found");
        }

        if (!current_workout.getUserId().equals(userId)) {
            throw new IllegalArgumentException("workout with id" + id +" belongs to another user");
        }

        return current_workout;
    }

    // Update a workout
    public WorkoutSession updateWorkout(String id, WorkoutSession updated_workout) {
        String userId = getAuthenticatedUserId();

        WorkoutSession current_workout = workoutRepository.findById(id).orElse(null);

        if (current_workout == null) {
            throw new ResourceNotFoundException("workout with id" + id +" not found");
        }

        if (!current_workout.getUserId().equals(userId)) {
            throw new IllegalArgumentException("workout with id" + id +" belongs to another user");
        }

        current_workout.setExerciseName(updated_workout.getExerciseName());
        current_workout.setDuration(updated_workout.getDuration());
        current_workout.setSets(updated_workout.getSets());
        current_workout.setReps(updated_workout.getReps());
        current_workout.setDate(updated_workout.getDate());

        return workoutRepository.save(current_workout);
    }

    // Delete a workout
    public void deleteWorkout(String id) {
        String userId = getAuthenticatedUserId();

        WorkoutSession current_workout = workoutRepository.findById(id).orElse(null);

        if (current_workout == null) {
            throw new ResourceNotFoundException("workout with id" + id +" not found");
        }

        if (!current_workout.getUserId().equals(userId)) {
            throw new IllegalArgumentException("workout with id" + id +" belongs to another user.");
        }

        workoutRepository.delete(current_workout);
    }
}
