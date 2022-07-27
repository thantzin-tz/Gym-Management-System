package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import config.DBConfig;
import models.WorkoutPlan;
import shared.mapper.WorkoutPlanMapper;

public class WorkoutPlanService {

	private final DBConfig dbConfig;
	private final WorkoutPlanMapper workoutPlanMapper;

//	 private final WorkoutPlanService workoutPlanService;

	public WorkoutPlanService() {
		this.dbConfig = new DBConfig();
		this.workoutPlanMapper = new WorkoutPlanMapper();
		new WorkoutService();
	}

	public List<WorkoutPlan> findAllWorkoutPlans() {

		List<WorkoutPlan> workoutList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			// String query = "SELECT * FROM workoutPlan";

			String query = "SELECT * FROM workoutPlan\n" + "INNER JOIN workout\n"
					+ "ON workout.workout_id = workoutPlan.workout_id\n " + "order by workoutPlan_id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				WorkoutPlan workoutPlan = new WorkoutPlan();
				workoutList.add(this.workoutPlanMapper.mapToWorkout(workoutPlan, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workoutList;

	}

	public void saveWorkout(WorkoutPlan wkPlan) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
				.prepareStatement("Insert into workoutPlan(setTime,gender,level,description,workout_id)values(?,?,?,?,?)");
			
			ps.setString(1, wkPlan.getTimer());
			ps.setString(2,  wkPlan.getGender());
			ps.setString(3, wkPlan.getLevel());
			ps.setString(4, wkPlan.getDesc());	
			ps.setInt(5, wkPlan.getWorkout().getWorkout_id());
			ps.executeUpdate();
			ps.close();
		/*
               // Update Product Quantity and Price (by raising 10 %)
               Product storedProduct = productService.findById(pd.getProduct().getId() + "");
               storedProduct.setPrice(((pd.getPrice() / 10) + pd.getPrice()));
               storedProduct.setQuantity(storedProduct.getQuantity() + pd.getQuantity());
               productService.updateProduct(String.valueOf(storedProduct.getId()), storedProduct);*/
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, "Already Exists");
			e.printStackTrace();
		}
	}
	public List<WorkoutPlan> findWorkoutPlanListByWorkoutId(String workoutId) {
		List<WorkoutPlan> workoutPlanList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			// String query = "SELECT * FROM workoutPlan";

			String query = "SELECT * FROM WorkoutPlan WHERE workout_id = " + workoutId + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				WorkoutPlan workoutPlan = new WorkoutPlan();
				workoutPlanList.add(this.workoutPlanMapper.mapToWorkout(workoutPlan, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workoutPlanList;

	}

	public WorkoutPlan findById(String workoutPlanId) {
		WorkoutPlan workoutPlan = new WorkoutPlan();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM workoutPlan\n" + "INNER JOIN workout\n"
					+ "ON workout.workout_id = workoutPlan.workout_id\n" + "WHERE workoutPlan_id = " + workoutPlanId
					+ ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				workoutPlan = this.workoutPlanMapper.mapToWorkout(workoutPlan, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return workoutPlan;
	}

	public void updateWorkoutPlan(String id, WorkoutPlan workoutPlan) {
		try {
			// System.out.println("ID ---------------> "+id);
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"UPDATE workoutPlan SET  workout_id=?,setTime=?,gender=?,level=? , description=? WHERE workoutPlan_id =?");
			ps.setInt(1, workoutPlan.getWorkout().getWorkout_id());
			ps.setString(2, workoutPlan.getTimer());
			ps.setString(3, workoutPlan.getGender());
			ps.setString(4, workoutPlan.getLevel());
			ps.setString(5, workoutPlan.getDesc());

			/*
			 * ps.setString(1, member.getTimer()); ps.setString(2, member.getDesc());
			 */
			ps.setString(6, id);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Workout Plan");
			e.printStackTrace();
		}

	}

	public void deleteWorkoutPlan(String id, WorkoutPlan workoutPlan) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from workoutPlan Where workoutPlan_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
