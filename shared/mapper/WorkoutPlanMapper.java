package shared.mapper;

import java.sql.ResultSet;

import models.Workout;
import models.WorkoutPlan;
import services.WorkoutService;

public class WorkoutPlanMapper {
	public void setWorkoutRepo(WorkoutService workoutRepo) {
	}

	public WorkoutPlan mapToWorkout(WorkoutPlan workoutPlan, ResultSet rs) {
		try {
			workoutPlan.setWorkoutplan_id(rs.getInt("workoutPlan_id"));
			workoutPlan.setTimer(rs.getString("setTime"));
			workoutPlan.setGender(rs.getString("gender"));
			workoutPlan.setLevel(rs.getString("level"));
			workoutPlan.setDesc(rs.getString("description"));

			Workout workout = new Workout();
			workout.setWorkout_id(rs.getInt("workout_id"));
			workout.setWorkoutName(rs.getString("workout_name"));
			// workout.setType(rs.getString("workout_type"));

			workout.setBodypartName(rs.getString("bodypart_name"));
			workoutPlan.setWorkout(workout);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workoutPlan;
	}

}
