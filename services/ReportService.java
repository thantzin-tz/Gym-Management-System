package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import config.DBConfig;
import models.Report;
import shared.mapper.ReportMapper;

public class ReportService {
	private final DBConfig dbConfig;
	private final ReportMapper reportMapper;

	public ReportService() {
		this.dbConfig = new DBConfig();
		this.reportMapper = new ReportMapper();
	}

	public void saveReport(Report report) {
		try {
			LocalDateTime dtm = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = dtm.format(formatter);
			try (Statement st = this.dbConfig.getConnection().createStatement()) {
				String query = "Select * from report Where reportDate = '" + date + "' and scheduledMember_id = "
						+ report.getScheduleMember().getScheduleMember_id();

				ResultSet cnt = st.executeQuery(query);

				if (!cnt.isBeforeFirst()) {

					PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
							"Insert into report(attendance,reportDate,reportWeight,reportHeight,reportBMI,remark,scheduledMember_id,workoutPlan_id,playHour)"
									+ "values(?,?,?,?,?,?,?,?,?)");

					ps.setBoolean(1, report.isReportAttendance());
					ps.setString(2, report.getReportDate());
					ps.setDouble(3, report.getReportWeight());
					ps.setDouble(4, report.getReportHeight());
					ps.setDouble(5, report.getReportBMI());
					ps.setString(6, report.getReportRemark());
					ps.setInt(7, report.getScheduleMember().getScheduleMember_id());
					ps.setInt(8, report.getWorkoutPlan().getWorkoutplan_id());
					ps.setInt(9, report.getPlayHour());

					ps.executeUpdate();
					ps.close();
					JOptionPane.showMessageDialog(null, "Report is Added");
				} else {
					JOptionPane.showMessageDialog(null, "Already Exists");
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public void updateReport(String id, Report report) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement("UPDATE report SET attendance=?,"
					+ "reportDate=?,reportWeight=?,reportHeight=?,reportBMI=?,remark=?,scheduledMember_id=?,workoutPlan_id=?,playHour=? Where report_id=?");

			ps.setBoolean(1, report.isReportAttendance());
			ps.setString(2, report.getReportDate());
			ps.setDouble(3, report.getReportWeight());
			ps.setDouble(4, report.getReportHeight());
			ps.setDouble(5, report.getReportBMI());
			ps.setString(6, report.getReportRemark());
			ps.setInt(7, report.getScheduleMember().getScheduleMember_id());
			ps.setInt(8, report.getWorkoutPlan().getWorkoutplan_id());
			ps.setInt(9, report.getPlayHour());
			ps.setString(10, id);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Report");
			e.printStackTrace();
		}

	}

	public List<Report> findAllReport() {

		List<Report> reportList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM report\n" + "INNER JOIN scheduledmember\n"
					+ "ON scheduledmember.scheduledMember_id = report.scheduledMember_id\n "
					+ "INNER JOIN workoutPlan\n" + "ON workoutplan.workoutPlan_id = report.workoutPlan_id\n " +

					"INNER JOIN schedule\n" + "ON schedule.schedule_id = scheduledmember.schedule_id\n " +

					"INNER JOIN member\n" + "ON member.member_id = scheduledmember.member_id\n " +

					"INNER JOIN workout\n" + "ON workout.workout_id = workoutPlan.workout_id\n " + "order by report_id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				Report report = new Report();
				reportList.add(this.reportMapper.mapToReport(report, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportList;

	}

	public Report findById(String reportId) {
		Report report = new Report();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM report\n" + "INNER JOIN scheduledmember\n"
					+ "ON scheduledmember.scheduledMember_id = report.scheduledMember_id\n "
					+ "INNER JOIN workoutPlan\n" + "ON workoutplan.workoutPlan_id = report.workoutPlan_id\n "
					+ "INNER JOIN schedule\n" + "ON schedule.schedule_id = scheduledmember.schedule_id\n "
					+ "INNER JOIN member\n" + "ON member.member_id = scheduledmember.member_id\n "
					+ "INNER JOIN workout\n" + "ON workout.workout_id = workoutPlan.workout_id\n "
					+ "WHERE report_id = " + reportId + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				report = this.reportMapper.mapToReport(report, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return report;
	}

	public void deleteReport(String id, Report report) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from report Where report_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
