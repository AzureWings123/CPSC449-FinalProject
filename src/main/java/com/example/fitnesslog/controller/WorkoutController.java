package com.example.fitnesslog.controller;

import com.example.fitnesslog.entity.WorkoutSession;
import com.example.fitnesslog.repository.WorkoutSessionRepository;
import com.example.fitnesslog.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutSessionRepository workoutSessionRepository;

    public WorkoutController(WorkoutService workoutService, WorkoutSessionRepository workoutSessionRepository) {
        this.workoutService = workoutService;
        this.workoutSessionRepository = workoutSessionRepository;
    }

    @PostMapping
    public ResponseEntity<WorkoutSession> createWorkout(@RequestBody WorkoutSession workout) {
        WorkoutSession created = workoutService.createWorkout(workout);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutSession>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSession> getWorkoutById(@PathVariable String id) {
        if (!workoutSessionRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(workoutService.getWorkoutById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSession> updateWorkout(@PathVariable String id,
                                                        @RequestBody WorkoutSession workout) {

        if (!workoutSessionRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(workoutService.updateWorkout(id, workout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable String id) {
        if (!workoutSessionRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
}