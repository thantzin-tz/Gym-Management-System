package models;

public class Workout {

	private int workout_id;
	
	private String workoutName;
	
	private String bodypartName;
	
	public Workout(int id, String workoutName, String bodypartName) {
		this.workout_id = id;
		this.workoutName = workoutName;
		this.bodypartName = bodypartName;
	}

	public Workout() {
	}

	public int getWorkout_id() {
		return workout_id;
	}

	public void setWorkout_id(int workout_id) {
		this.workout_id = workout_id;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public String getBodypartName() {
		return bodypartName;
	}

	public void setBodypartName(String bodypartName) {
		this.bodypartName = bodypartName;
	}

	/// Wakaranai ////
	
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		Workout workout = (Workout) o;
//		return Objects.equals(name, workout.name);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(name);
//	}

}
