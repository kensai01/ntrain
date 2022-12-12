package com.nitetrain.service.dto;

import java.util.List;

public class WorkoutsDTO {

    String title;
    String description;
    List<WorkoutStepDTO> workoutSteps;
    String scaling;
    String beginnerDescription;
    List<WorkoutStepDTO> beginnerSteps;

    Integer time;
    String videoId;
    String intermediateDescription;
    List<WorkoutStepDTO> intermidateSteps;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WorkoutStepDTO> getWorkoutSteps() {
        return workoutSteps;
    }

    public void setWorkoutSteps(List<WorkoutStepDTO> workoutSteps) {
        this.workoutSteps = workoutSteps;
    }

    public String getScaling() {
        return scaling;
    }

    public void setScaling(String scaling) {
        this.scaling = scaling;
    }

    public String getBeginnerDescription() {
        return beginnerDescription;
    }

    public void setBeginnerDescription(String beginnerDescription) {
        this.beginnerDescription = beginnerDescription;
    }

    public List<WorkoutStepDTO> getBeginnerSteps() {
        return beginnerSteps;
    }

    public void setBeginnerSteps(List<WorkoutStepDTO> beginnerSteps) {
        this.beginnerSteps = beginnerSteps;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getIntermediateDescription() {
        return intermediateDescription;
    }

    public void setIntermediateDescription(String intermediateDescription) {
        this.intermediateDescription = intermediateDescription;
    }

    public List<WorkoutStepDTO> getIntermidateSteps() {
        return intermidateSteps;
    }

    public void setIntermidateSteps(List<WorkoutStepDTO> intermidateSteps) {
        this.intermidateSteps = intermidateSteps;
    }
}
