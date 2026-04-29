package com.example.fitnesslog.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "workoutsession")
@Data
public class WorkoutSession {
    @Id
    private String id;

    private String exerciseName;

    private BigDecimal duration;

    private Integer sets;

    private Integer reps;

    private Date date;
}
