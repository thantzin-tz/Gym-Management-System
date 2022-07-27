package shared.mapper;

import java.sql.ResultSet;

import models.Members;
import models.Report;
import models.Schedule;
import models.ScheduleMember;
import models.Workout;
import models.WorkoutPlan;

public class ReportMapper {

	public Report mapToReport(Report report, ResultSet rs) {
		try {
			report.setReport_id(rs.getInt("report_id"));
			report.setReportAttendance(rs.getBoolean("attendance"));
			report.setReportDate(rs.getString("reportDate"));
			report.setReportWeight(rs.getDouble("reportWeight"));
			report.setReportHeight(rs.getDouble("reportHeight"));
			report.setReportBMI(rs.getDouble("reportBMI"));
			report.setReportRemark(rs.getString("Remark"));
			report.setPlayHour(rs.getInt("playHour"));

			ScheduleMember scheduleMember = new ScheduleMember();
			scheduleMember.setScheduleMember_id(rs.getInt("scheduledMember_id"));

			Schedule schedule = new Schedule();
			schedule.setSchedule_id(rs.getInt("schedule_id"));
			schedule.setSchedule_name(rs.getString("schedule_name"));

			// schedule.setSchedule_name(rs.getString("schedule_name"));

			Members member = new Members();
			member.setMember_id(rs.getInt(("member_id")));
			member.setName(rs.getString("name"));

			WorkoutPlan workoutPlan = new WorkoutPlan();
			workoutPlan.setWorkoutplan_id(rs.getInt(("workoutPlan_id")));
			workoutPlan.setTimer(rs.getString("setTime"));
			workoutPlan.setLevel(rs.getString("level"));
			workoutPlan.setGender(rs.getString("gender"));

			// workoutPlan
			Workout workout = new Workout();
			workout.setWorkout_id(rs.getInt("workout_id"));
			workout.setWorkoutName(rs.getString("workout_name"));

			scheduleMember.setSchedule(schedule);
			scheduleMember.setMember(member);
			report.setScheduleMember(scheduleMember);

			workoutPlan.setWorkout(workout);
			report.setWorkoutPlan(workoutPlan);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return report;
	}

}
