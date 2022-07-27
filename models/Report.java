package models;

import java.util.Objects;

//import views.ScheduleMemberAdd;

public class Report {

	private int report_id;
	private boolean reportAttendance;
	private String reportDate;
	private Double reportWeight;
	private Double reportHeight;
	private Double reportBMI;
	private String reportRemark;
	private ScheduleMember scheduleMember;
	private WorkoutPlan workoutPlan;
	private int playHour;

	public Report() {

	}

	public Report(int report_id, boolean reportAttendance, String reportDate, Double reportWeight, Double reportHeight,
			Double reportBMI, String reportRemark, ScheduleMember scheduleMember, WorkoutPlan workoutPlan,
			int playHour) {
		super();
		this.report_id = report_id;
		this.reportAttendance = reportAttendance;
		this.reportDate = reportDate;
		this.reportWeight = reportWeight;
		this.reportHeight = reportHeight;
		this.reportBMI = reportBMI;
		this.reportRemark = reportRemark;
		this.scheduleMember = scheduleMember;
		this.workoutPlan = workoutPlan;
		this.playHour = playHour;
	}

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public boolean isReportAttendance() {
		return reportAttendance;
	}

	public void setReportAttendance(boolean reportAttendance) {
		this.reportAttendance = reportAttendance;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public Double getReportWeight() {
		return reportWeight;
	}

	public void setReportWeight(Double reportWeight) {
		this.reportWeight = reportWeight;
	}

	public Double getReportHeight() {
		return reportHeight;
	}

	public void setReportHeight(Double reportHeight) {
		this.reportHeight = reportHeight;
	}

	public Double getReportBMI() {
		return reportBMI;
	}

	public void setReportBMI(Double reportBMI) {
		this.reportBMI = reportBMI;
	}

	public String getReportRemark() {
		return reportRemark;
	}

	public void setReportRemark(String reportRemark) {
		this.reportRemark = reportRemark;
	}

	public ScheduleMember getScheduleMember() {
		return scheduleMember;
	}

	public void setScheduleMember(ScheduleMember scheduleMember) {
		this.scheduleMember = scheduleMember;
	}

	public WorkoutPlan getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(WorkoutPlan workoutPlan) {
		this.workoutPlan = workoutPlan;
	}

	public int getPlayHour() {
		return playHour;
	}

	public void setPlayHour(int playHour) {
		this.playHour = playHour;
	}

	public Schedule orElseGet(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Report that = (Report) o;
		if (scheduleMember == that.scheduleMember)
			return Objects.equals(scheduleMember, that.scheduleMember);
		else
			return Objects.equals(workoutPlan, that.workoutPlan);

	}

	@Override
	public int hashCode() {
		if (scheduleMember != null)
			return Objects.hash(scheduleMember);
		else
			return Objects.hash(workoutPlan);
	}

}
