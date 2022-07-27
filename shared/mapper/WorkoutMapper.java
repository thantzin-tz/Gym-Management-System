package shared.mapper;

import java.sql.ResultSet;

import models.Workout;


public class WorkoutMapper {
	public Workout mapToWorkout(Workout workout, ResultSet rs) {
        try {
        	workout.setWorkout_id(rs.getInt("workout_id"));
        	workout.setWorkoutName(rs.getString("workout_name"));
        	workout.setBodypartName(rs.getString("bodypart_name"));
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workout;
}
	
}
