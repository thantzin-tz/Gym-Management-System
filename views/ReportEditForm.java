package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import models.Report;
import models.ScheduleMember;
import models.WorkoutPlan;
import services.ReportService;
import services.ScheduleMemberService;
import services.WorkoutPlanService;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;

public class ReportEditForm {

	protected JFrame frameReportEdit;
	private JTextField txtScheduleName;
	private JTextField txtSetTime;
	private JTextField txtLevel;
	private JTextField txtGender;
	private JTextField txtPlayHour;
	private JTextField txtRemark;
	private JTextField txtUpdateWeight;
	private JTextField txtUpdateHeight;
	private JTextField txtBMI;
	private JComboBox<String> comboMemberName;
	private JComboBox<String> comboWorkoutName;
	private List<ScheduleMember> scheduleMemberList = new ArrayList<>();
	private List<WorkoutPlan> workoutPlanList = new ArrayList<>();
	private Optional<ScheduleMember> selectedScheduleMember;
	private Optional<WorkoutPlan> selectedWorkoutPlan;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAbsent, rdbtnAttend;

	private ReportService reportService;
	private ScheduleMemberService scheduleMemberService;
	private WorkoutPlanService workoutPlanService;
	private Report report;
	private double bmi;
	private JDateChooser dateAttendance;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportEditForm window = new ReportEditForm();
					window.frameReportEdit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReportEditForm() {
		this.reportService = new ReportService();
		initialize();
		this.initializeDependency();
		loadScheduleMemberForComboBox();
		loadWorkoutPlanForComboBox();

	}

	public ReportEditForm(Report report) {
		this.report = report;
		initialize();
		this.initializeDependency();
		this.loadScheduleMemberForComboBox();
		this.loadWorkoutPlanForComboBox();
		comboMemberName.setSelectedItem(report.getScheduleMember().getMember().getName());
		comboWorkoutName.setSelectedItem(report.getWorkoutPlan().getWorkout().getWorkoutName());
		// calculateBMI();
	}

	public double calculateBMI() {

		double weight, height, result = 0;
		try {
			weight = Double.parseDouble(txtUpdateWeight.getText());
			height = Double.parseDouble(txtUpdateHeight.getText());
			result = weight / ((height * height) / 10000);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please, enter NUMBER only for Height and Weight");
			return 0;
		}
		if (result < 18.5) {
			txtBMI.setText((result + "\sUnderweight!"));

		} else if (result > 18.5 && result < 25.0) {
			txtBMI.setText(result + "\sNormal");

		} else if (result > 25.00) {
			txtBMI.setText(result + "\sOverweight!");

		}

		return result;
	}

	private void loadScheduleMemberForComboBox() {
		comboMemberName.addItem("-select-");
//		this.scheduleMemberList = this.scheduleMemberService.findAllScheduleMember();
//		this.scheduleMemberList.forEach(b -> comboMemberName.addItem(b.getMember().getName()));
		if (scheduleMemberList.contains(comboMemberName.getSelectedItem())) {
			this.scheduleMemberList = this.scheduleMemberService.findAllScheduleMember();
			scheduleMemberList.remove(comboMemberName.getSelectedIndex());
//			this.scheduleMemberList = this.scheduleMemberService.findAllScheduleMember();
			this.scheduleMemberList.forEach(b -> comboMemberName.addItem(b.getMember().getName()));
		} else {
			this.scheduleMemberList = this.scheduleMemberService.findAllScheduleMember();
			this.scheduleMemberList.forEach(b -> comboMemberName.addItem(b.getMember().getName()));
		}
	}

	private void loadWorkoutPlanForComboBox() {
		comboWorkoutName.addItem("-select-");
		this.workoutPlanList = this.workoutPlanService.findAllWorkoutPlans();
		this.workoutPlanList.forEach(b -> comboWorkoutName.addItem(b.getWorkout().getWorkoutName()));

	}

	private void initializeDependency() {
		this.reportService = new ReportService();
		this.scheduleMemberService = new ScheduleMemberService();
		this.workoutPlanService = new WorkoutPlanService();
	}

	void resetFormData() {
		// txtScheduleName.setText("");
		// comboSchedule.setSelectedIndex(0);
		// txtScheduleName.requestFocus();

		txtUpdateHeight.setText("");
		txtUpdateWeight.setText("");
		txtBMI.setText("");
		txtSetTime.setText("");
		txtRemark.setText("");
		txtPlayHour.setText("");

		/*
		 * Default Date
		 */
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameReportEdit = new JFrame();
		frameReportEdit.setResizable(false);
		frameReportEdit.getContentPane().setBackground(new Color(230, 230, 250));
		frameReportEdit.setBounds(240, 50, 965, 645);
		frameReportEdit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameReportEdit.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Add Report", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel.setBounds(31, 22, 890, 490);
		frameReportEdit.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(25, 25, 403, 133);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Member Name");
		lblNewLabel.setBounds(12, 17, 150, 35);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));

		JLabel lblScheduleName = new JLabel("Schedule Name");
		lblScheduleName.setFont(new Font("Calibri", Font.BOLD, 15));
		lblScheduleName.setBounds(12, 86, 150, 35);
		panel_1.add(lblScheduleName);

		comboMemberName = new JComboBox<String>();
		comboMemberName.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboMemberName.setBackground(new Color(230, 230, 250));
		comboMemberName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboMemberName.getSelectedIndex() != 0 && comboMemberName.getSelectedIndex() != -1) {
					selectedScheduleMember = scheduleMemberList.stream().filter(p -> {
						return p.getMember().getName().equals(comboMemberName.getSelectedItem());
					}).findFirst();
					txtScheduleName.setText(selectedScheduleMember.get().getSchedule().getSchedule_name());
				}
			}
		});

		comboMemberName.setBounds(215, 14, 170, 35);
		panel_1.add(comboMemberName);

		txtScheduleName = new JTextField();
		txtScheduleName.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtScheduleName.setBounds(215, 83, 170, 35);
		panel_1.add(txtScheduleName);
		txtScheduleName.setColumns(10);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1_1.setBackground(new Color(230, 230, 250));
		panel_1_1.setBounds(25, 179, 403, 290);
		panel.add(panel_1_1);

		JLabel lblNewLabel_1 = new JLabel("Workout Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 17, 150, 35);
		panel_1_1.add(lblNewLabel_1);

		JLabel lblScheduleName_1 = new JLabel("Set Times");
		lblScheduleName_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblScheduleName_1.setBounds(12, 86, 150, 35);
		panel_1_1.add(lblScheduleName_1);

		comboWorkoutName = new JComboBox<String>();
		comboWorkoutName.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboWorkoutName.setBackground(new Color(230, 230, 250));
		comboWorkoutName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboWorkoutName.getSelectedIndex() != 0 && comboWorkoutName.getSelectedIndex() != -1) {
					selectedWorkoutPlan = workoutPlanList.stream().filter(p -> {
						return p.getWorkout().getWorkoutName().equals(comboWorkoutName.getSelectedItem());
					}).findFirst();
					txtSetTime.setText(selectedWorkoutPlan.get().getTimer());
					txtLevel.setText(selectedWorkoutPlan.get().getLevel());
					txtGender.setText(selectedWorkoutPlan.get().getGender());
				}
			}
		});
		comboWorkoutName.setBounds(215, 14, 170, 35);
		panel_1_1.add(comboWorkoutName);

		txtSetTime = new JTextField();
		txtSetTime.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtSetTime.setColumns(10);
		txtSetTime.setBounds(215, 83, 170, 35);
		panel_1_1.add(txtSetTime);

		JLabel lblScheduleName_1_1 = new JLabel("Level");
		lblScheduleName_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblScheduleName_1_1.setBounds(12, 158, 150, 35);
		panel_1_1.add(lblScheduleName_1_1);

		JLabel lblScheduleName_1_1_1 = new JLabel("Gender");
		lblScheduleName_1_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblScheduleName_1_1_1.setBounds(12, 231, 150, 35);
		panel_1_1.add(lblScheduleName_1_1_1);

		txtLevel = new JTextField();
		txtLevel.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtLevel.setColumns(10);
		txtLevel.setBounds(215, 155, 170, 35);
		panel_1_1.add(txtLevel);

		txtGender = new JTextField();
		txtGender.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtGender.setColumns(10);
		txtGender.setBounds(215, 228, 170, 35);
		panel_1_1.add(txtGender);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1_1_1.setBackground(new Color(230, 230, 250));
		panel_1_1_1.setBounds(452, 25, 415, 444);
		panel.add(panel_1_1_1);

		JLabel lblPlayHour = new JLabel("Play Hours /day");
		lblPlayHour.setFont(new Font("Calibri", Font.BOLD, 15));
		lblPlayHour.setBounds(23, 321, 150, 35);
		panel_1_1_1.add(lblPlayHour);

		JLabel lblRemrk = new JLabel("Remark");
		lblRemrk.setFont(new Font("Calibri", Font.BOLD, 15));
		lblRemrk.setBounds(23, 386, 150, 35);
		panel_1_1_1.add(lblRemrk);

		txtPlayHour = new JTextField(report != null ? String.valueOf(report.getPlayHour()) : "");
		txtPlayHour.setText("0");
		txtPlayHour.setEditable(false);
		txtPlayHour.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtPlayHour.setColumns(10);
		txtPlayHour.setBounds(221, 318, 170, 35);
		panel_1_1_1.add(txtPlayHour);

		txtRemark = new JTextField(report != null ? report.getReportRemark() : "");
		txtRemark.setText("Absent");
		txtRemark.setEditable(false);
		txtRemark.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtRemark.setColumns(10);
		txtRemark.setBounds(221, 383, 170, 35);
		panel_1_1_1.add(txtRemark);

		JLabel lblNewLabel_1_1_1 = new JLabel("Attendance Date");
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(23, 23, 150, 35);
		panel_1_1_1.add(lblNewLabel_1_1_1);

//		dateAttendance.setDateFormatString("yyyy-MM-dd");

		if (report != null) {
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(report.getReportDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			dateAttendance = new JDateChooser();
			dateAttendance.setDate(date);
		} else {
			dateAttendance = new JDateChooser();
			/// set Default date value
			dateAttendance.setDate(new Date());
		}
		dateAttendance.setBounds(220, 23, 170, 35);
		panel_1_1_1.add(dateAttendance);

		JLabel lblNewLabel_1_1_2 = new JLabel("Attendance");
		lblNewLabel_1_1_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(23, 74, 150, 35);
		panel_1_1_1.add(lblNewLabel_1_1_2);

		rdbtnAttend = new JRadioButton("Attend");
		rdbtnAttend.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnAttend.isSelected()) {
					txtUpdateWeight.setEditable(true);
					txtUpdateHeight.setEditable(true);
					txtPlayHour.setEditable(true);
					txtRemark.setEditable(true);
					txtUpdateWeight.setText(report != null ? String.valueOf(report.getReportWeight()) : "");
					txtUpdateHeight.setText(report != null ? String.valueOf(report.getReportHeight()) : "");
					txtBMI.setText(report != null ? String.valueOf(report.getReportBMI()) : "");
					txtPlayHour.setText(report != null ? String.valueOf(report.getPlayHour()) : "");
					txtRemark.setText(report != null ? report.getReportRemark() : "");
				}
			}
		});

		buttonGroup.add(rdbtnAttend);
		rdbtnAttend.setBackground(new Color(230, 230, 250));
		rdbtnAttend.setFont(new Font("Calibri", Font.BOLD, 15));
		rdbtnAttend.setBounds(220, 81, 68, 21);
		panel_1_1_1.add(rdbtnAttend);

		rdbtnAbsent = new JRadioButton("Absent");
		rdbtnAbsent.setSelected(true);
		rdbtnAbsent.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnAbsent.isSelected()) {
					txtUpdateWeight.setEditable(false);
					txtUpdateHeight.setEditable(false);
					txtBMI.setEditable(false);
					txtPlayHour.setEditable(false);
					txtRemark.setEditable(false);
					txtUpdateWeight.setText("0.0");
					txtUpdateHeight.setText("0.0");
					txtBMI.setText("0.0");
					txtPlayHour.setText("0");
					txtRemark.setText("Absent");
				}
			}
		});
		buttonGroup.add(rdbtnAbsent);
		rdbtnAbsent.setBackground(new Color(230, 230, 250));
		rdbtnAbsent.setFont(new Font("Calibri", Font.BOLD, 15));
		rdbtnAbsent.setBounds(316, 81, 75, 21);
		panel_1_1_1.add(rdbtnAbsent);

		JLabel lblWeight = new JLabel("Update Weight");
		lblWeight.setFont(new Font("Calibri", Font.BOLD, 15));
		lblWeight.setBounds(23, 122, 150, 35);
		panel_1_1_1.add(lblWeight);

		txtUpdateWeight = new JTextField(report != null ? String.valueOf(report.getReportWeight()) : "");
		txtUpdateWeight.setText("0.0");
		txtUpdateWeight.setEditable(false);
		txtUpdateWeight.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtUpdateWeight.setColumns(10);
		txtUpdateWeight.setBounds(220, 119, 170, 35);
		panel_1_1_1.add(txtUpdateWeight);

		txtUpdateHeight = new JTextField(report != null ? String.valueOf(report.getReportHeight()) : "");
		txtUpdateHeight.setText("0.0");
		txtUpdateHeight.setEditable(false);
		txtUpdateHeight.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtUpdateHeight.setColumns(10);
		txtUpdateHeight.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Format Decimal Place
					DecimalFormat df = new DecimalFormat("0.00");
					bmi = calculateBMI();
					txtBMI.setText(String.valueOf(df.format(bmi)));
				}
			}
		});
		txtUpdateHeight.setBounds(221, 181, 170, 35);
		panel_1_1_1.add(txtUpdateHeight);

		JLabel lblHeight = new JLabel("Update Height");
		lblHeight.setFont(new Font("Calibri", Font.BOLD, 15));
		lblHeight.setBounds(23, 184, 150, 35);
		panel_1_1_1.add(lblHeight);

		txtBMI = new JTextField(report != null ? String.valueOf(report.getReportBMI()) : "");
		txtBMI.setText("0.0");
		txtBMI.setEditable(false);
		txtBMI.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtBMI.setColumns(10);
		txtBMI.setBounds(221, 250, 170, 35);
		panel_1_1_1.add(txtBMI);

		JLabel lblBMI = new JLabel("BMI");
		lblBMI.setFont(new Font("Calibri", Font.BOLD, 15));
		lblBMI.setBounds(23, 253, 150, 35);
		panel_1_1_1.add(lblBMI);

		JButton btnSave = new JButton(report != null ? "Update" : "Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (report != null && report.getReport_id() != 0) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					report.setReportDate(dateFormat.format(dateAttendance.getDate()));

					selectedScheduleMember = scheduleMemberList.stream().filter(p -> {
						return p.getMember().getName().equals(comboMemberName.getSelectedItem());
					}).findFirst();
					if (selectedScheduleMember.isPresent()) {
						report.setScheduleMember(selectedScheduleMember.get());
					}

					selectedWorkoutPlan = workoutPlanList.stream().filter(p -> {
						System.out.print(p.getTimer());
						return p.getWorkout().getWorkoutName().equals(comboWorkoutName.getSelectedItem());
					}).findFirst();
					if (selectedWorkoutPlan.isPresent()) {
						report.setWorkoutPlan(selectedWorkoutPlan.get());
					}
					report.setPlayHour(Integer.parseInt(txtPlayHour.getText()));
					report.setReportBMI(Double.parseDouble(txtBMI.getText()));
					report.setReportHeight(Double.parseDouble(txtUpdateHeight.getText()));
					report.setReportWeight(Double.parseDouble(txtUpdateWeight.getText()));
					report.setReportRemark(txtRemark.getText());

					if (selectedScheduleMember.isPresent()) {
						if (txtScheduleName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please");
							return;
						} else {
							reportService.updateReport(String.valueOf(report.getReport_id()), report);
							JOptionPane.showMessageDialog(null, "Report Updated.");
							report = null;
							// Back to Member Form
							StaffListForm staffListForm = new StaffListForm();
							staffListForm.loadAllStaffs(Optional.empty());
							frameReportEdit.setVisible(false);
							resetFormData();
						}
					}
				} else {
					Report report = new Report();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					report.setReportDate(dateFormat.format(dateAttendance.getDate()));

					selectedScheduleMember = scheduleMemberList.stream().filter(p -> {
						return p.getMember().getName().equals(comboMemberName.getSelectedItem());
					}).findFirst();
					if (selectedScheduleMember.isPresent()) {
						report.setScheduleMember(selectedScheduleMember.get());
					}

					selectedWorkoutPlan = workoutPlanList.stream().filter(p -> {
						System.out.print(p.getTimer());
						return p.getWorkout().getWorkoutName().equals(comboWorkoutName.getSelectedItem());
					}).findFirst();
					if (selectedWorkoutPlan.isPresent()) {
						report.setWorkoutPlan(selectedWorkoutPlan.get());
					}
					// report.setWorkoutPlan(selectedWorkoutPlan.orElseGet(null));
					report.setPlayHour(Integer.parseInt(txtPlayHour.getText()));
					report.setReportBMI(Double.parseDouble(txtBMI.getText()));
					report.setReportHeight(Double.parseDouble(txtUpdateHeight.getText()));
					report.setReportWeight(Double.parseDouble(txtUpdateWeight.getText()));
					report.setReportRemark(txtRemark.getText());

					if (selectedScheduleMember.isPresent() && selectedWorkoutPlan.isPresent()) {
						if (txtScheduleName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please Enter Schedule Name");
							return;
						} else {
							reportService.saveReport(report);
							report = null;
							resetFormData();
							frameReportEdit.setVisible(false);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Please Select Member and Workout Name");
					}
				}

			}
		});
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(60, 179, 113));
		btnSave.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSave.setBounds(769, 539, 100, 35);
		frameReportEdit.getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 15));
		btnCancel.setBackground(new Color(220, 20, 60));
		btnCancel.setBounds(615, 539, 100, 35);
		frameReportEdit.getContentPane().add(btnCancel);

		/// to select radio button when update
		if (report != null) {
			if (report.getReportRemark().equals("Absent")) {
				rdbtnAbsent.setSelected(true);
			} else {
				rdbtnAttend.setSelected(true);
			}
		}
	}
}
