package com.nitetrain.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nitetrain.domain.BeginnerWorkout;
import com.nitetrain.domain.IntermediateWorkout;
import com.nitetrain.domain.WorkoutStep;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DTO for the {@link com.nitetrain.domain.Workout} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WorkoutDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @Min(value = 0)
    @Max(value = 180)
    private Integer time;

    private String videoId;

    private String scaling;

    private Set<WorkoutStep> workoutSteps = new HashSet<>();

    private BeginnerWorkout beginnerWorkout;

    private IntermediateWorkout intermediateWorkout;

    public BeginnerWorkout getBeginnerWorkout() {
        return beginnerWorkout;
    }

    public void setBeginnerWorkout(BeginnerWorkout beginnerWorkout) {
        this.beginnerWorkout = beginnerWorkout;
    }

    public IntermediateWorkout getIntermediateWorkout() {
        return intermediateWorkout;
    }

    public void setIntermediateWorkout(IntermediateWorkout intermediateWorkout) {
        this.intermediateWorkout = intermediateWorkout;
    }

    public void setWorkoutSteps(Set<WorkoutStep> workoutSteps) {
        this.workoutSteps = workoutSteps;
    }

    public Set<WorkoutStep> getWorkoutSteps() {
        return workoutSteps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getScaling() {
        return scaling;
    }

    public void setScaling(String scaling) {
        this.scaling = scaling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkoutDTO)) {
            return false;
        }

        WorkoutDTO workoutDTO = (WorkoutDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workoutDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkoutDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", time=" + getTime() +
            ", videoId='" + getVideoId() + "'" +
            ", scaling='" + getScaling() + "'" +
            "}";
    }
}
