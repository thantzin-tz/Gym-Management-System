package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.Workout;
//import repositories.WorkoutPlanRepo;
import shared.mapper.WorkoutMapper;

public class WorkoutService {

	private final DBConfig dbConfig;
	private final WorkoutMapper workoutMapper;
	public WorkoutService() {
		this.dbConfig = new DBConfig();
		this.workoutMapper = new WorkoutMapper();
	}

	public void setWorkoutPlanService(WorkoutPlanService workoutPlanService) throws SQLIntegrityConstraintViolationException{
	}

	public void saveWorkout(Workout workout) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Insert into workout(workout_name,bodypart_name)values(?,?)");
			ps.setString(1, workout.getWorkoutName());
			ps.setString(2, workout.getBodypartName());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Already Exists");
		}
	}

	public Workout findWorkoutById(String workoutId) {
		Workout workout = new Workout();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {
//
//			String query = "SELECT * FROM workout\n" + "INNER JOIN bodyPart\n"
//					+ "ON bodyPart.bodyPart_id = workout.bodyPart_id\n" + "WHERE workout_id = " + workoutId + ";";

			String query = "SELECT * FROM workout WHERE workout_id = " + workoutId;
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				workout = this.workoutMapper.mapToWorkout(workout, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return workout;
	}

	public List<Workout> findAllWorkouts() {

		List<Workout> workoutList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

//			String query = "SELECT * FROM workout\n" + "INNER JOIN bodyPart\n"
//					+ "ON bodyPart.bodyPart_id = workout.bodyPart_id\n";
			String query = "SELECT * FROM workout";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Workout workout = new Workout();
				workoutList.add(this.workoutMapper.mapToWorkout(workout, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workoutList;

	}

	public void updateWorkout(String id, Workout workout) {
		try {
			System.out.println("ID ---------------> " + id);
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE workout SET workout_name=?, bodypart_name=? WHERE workout_id = ?");

			ps.setString(1, workout.getWorkoutName());
			ps.setString(2, workout.getBodypartName());
			ps.setString(3, id);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Workout");
			e.printStackTrace();
		}

	}
	
	public void deleteWorkout(String id, Workout workout) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from workout Where workout_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
