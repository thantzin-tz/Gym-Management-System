package models;

import java.util.Objects;

public class WorkoutPlan{
	private int workoutplan_id;
	private Workout workout;
	private String timer;
	private String gender;
	private String level;
	private String desc;

	public WorkoutPlan() {
	}

	public WorkoutPlan(int workoutplan_id, Workout workout, String timer, String gender, String level,String desc) {
		this.workoutplan_id = workoutplan_id;
		this.workout = workout;
		this.timer = timer;
		this.gender = gender;
		this.level = level;
		this.desc=desc;
	}
	
	public Workout getWorkout() {
		return workout;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	public int getWorkoutplan_id() {
		return workoutplan_id;
	}
	public void setWorkoutplan_id(int workoutplan_id) {
		this.workoutplan_id = workoutplan_id;
	}
	public String getTimer() {
		return timer;
	}
	public void setTimer(String timer) {
		this.timer = timer;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WorkoutPlan that = (WorkoutPlan) o;
		return Objects.equals(workout, that.workout);
	}

	@Override
	public int hashCode() {
		return Objects.hash(workout);
	}
	
}
