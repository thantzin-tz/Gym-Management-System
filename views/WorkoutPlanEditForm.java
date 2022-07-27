package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import models.Workout;
import models.WorkoutPlan;
import services.WorkoutPlanService;
import services.WorkoutService;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;

public class WorkoutPlanEditForm {

	protected JFrame workoutPlanEditframe;
	private JTextField txtBodyPart;
	private JTextField txtSetTimer;
	private WorkoutPlan workoutPlan;
	private List<Workout> workoutList = new ArrayList<>();
	private JComboBox<String> comboLevel;
	private JComboBox<String> comboWorkout = new JComboBox<>();
	private JComboBox<String> comboGender;
	private JTextArea txtAreaDesc;
	private WorkoutPlanService workoutPlanService;
	private WorkoutService workoutService;
	private Optional<Workout> selectedWorkout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkoutPlanEditForm window = new WorkoutPlanEditForm();
					window.workoutPlanEditframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void resetFormData() {
		comboWorkout.setSelectedIndex(0);
		txtSetTimer.setText("");
		comboGender.setSelectedIndex(0);
		comboLevel.setSelectedIndex(0);
		txtAreaDesc.setText("");

	}

	/**
	 * Create the application.
	 */
	public WorkoutPlanEditForm() {
		initialize();
		initializeDependency();
		this.loadWorkoutForComboBox();
	}

	public WorkoutPlanEditForm(WorkoutPlan workoutPlan) {
		this.workoutPlan = workoutPlan;
		initialize();
		initializeDependency();
		this.loadWorkoutForComboBox();
		comboWorkout.setSelectedItem(workoutPlan.getWorkout().getWorkoutName());
	}

	private void initializeDependency() {
		this.workoutPlanService = new WorkoutPlanService();
		this.workoutService = new WorkoutService();
		try {
			this.workoutService.setWorkoutPlanService(this.workoutPlanService);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Susjdfj");
		}

	}

	private void loadWorkoutForComboBox() {
		this.comboWorkout.addItem("- Select -");
		workoutList = this.workoutService.findAllWorkouts();
		workoutList.forEach(s -> this.comboWorkout.addItem(s.getWorkoutName()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		workoutPlanEditframe = new JFrame();
		workoutPlanEditframe.setResizable(false);
		workoutPlanEditframe.setBounds(400, 100, 571, 590);
		workoutPlanEditframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		workoutPlanEditframe.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(0, 0, 559, 568);
		workoutPlanEditframe.getContentPane().add(panel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Add Member", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(147, 112, 219)));
		panel_2.setBackground(new Color(230, 230, 250));
		panel_2.setBounds(32, 29, 488, 441);
		panel.add(panel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Body Part");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(45, 94, 95, 18);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_3 = new JLabel("Set Timer");
		lblNewLabel_1_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(45, 149, 95, 18);
		panel_2.add(lblNewLabel_1_3);

		txtBodyPart = new JTextField("");
		txtBodyPart.setEditable(false);
		txtBodyPart.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtBodyPart.setColumns(10);
		txtBodyPart.setBackground(Color.WHITE);
		txtBodyPart.setBounds(271, 86, 175, 35);
		panel_2.add(txtBodyPart);

		txtSetTimer = new JTextField(workoutPlan != null ? workoutPlan.getTimer() : "");
		txtSetTimer.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSetTimer.setColumns(10);
		txtSetTimer.setBounds(271, 141, 175, 35);
		panel_2.add(txtSetTimer);

		JLabel lblNewLabel_1_4_1 = new JLabel("Workout");
		lblNewLabel_1_4_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_4_1.setBounds(43, 34, 63, 18);
		panel_2.add(lblNewLabel_1_4_1);

		comboWorkout = new JComboBox<String>();
		comboWorkout.setFont(new Font("Calibri", Font.BOLD, 15));
		comboWorkout.setBackground(new Color(230, 230, 250));
		comboWorkout.setBounds(271, 26, 175, 35);
		if (workoutPlan != null) {
			comboWorkout.setSelectedItem(workoutPlan.getWorkout().getWorkoutName());
		} else {
			comboWorkout.setSelectedIndex(-1);
		}
		panel_2.add(comboWorkout);
		// selected combo
		comboWorkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedWorkout = workoutList.stream()
						.filter(w -> w.getWorkoutName().equals(comboWorkout.getSelectedItem())).findFirst();
				txtBodyPart.setText(selectedWorkout.map(bodyName -> bodyName.getBodypartName()).orElse(""));

			}
		});

		JLabel lblNewLabel_1_3_1 = new JLabel("Gender");
		lblNewLabel_1_3_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_1.setBounds(45, 210, 95, 18);
		panel_2.add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_3_2 = new JLabel("Description");
		lblNewLabel_1_3_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_2.setBounds(45, 333, 95, 18);
		panel_2.add(lblNewLabel_1_3_2);

		JLabel lblInformation = new JLabel("");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setForeground(new Color(0, 139, 139));
		lblInformation.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		lblInformation.setBounds(153, 504, 175, 29);
		panel_2.add(lblInformation);

		comboGender = new JComboBox<String>();
		comboGender.setModel(new DefaultComboBoxModel<String>(new String[] { "- Select -", "Male", "Female" }));
		comboGender.setFont(new Font("Calibri", Font.BOLD, 15));
		comboGender.setBackground(new Color(230, 230, 250));
		comboGender.setBounds(271, 202, 175, 35);
		if (workoutPlan != null) {
			comboGender.setSelectedItem(workoutPlan.getGender());
		} else {
			comboGender.setSelectedIndex(0);
		}
		panel_2.add(comboGender);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("Level");
		lblNewLabel_1_3_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_1_1.setBounds(45, 271, 95, 18);
		panel_2.add(lblNewLabel_1_3_1_1);

		comboLevel = new JComboBox<String>();
		comboLevel.setModel(
				new DefaultComboBoxModel<String>(new String[] { "- Select -", "Beginner", "Intermediate", "Advance" }));
		comboLevel.setFont(new Font("Calibri", Font.BOLD, 15));
		comboLevel.setBackground(new Color(230, 230, 250));
		comboLevel.setBounds(271, 263, 175, 35);
		if (workoutPlan != null) {
			comboLevel.setSelectedItem(workoutPlan.getLevel());
		} else {
			comboLevel.setSelectedIndex(0);
		}
		panel_2.add(comboLevel);

		txtAreaDesc = new JTextArea(workoutPlan != null ? workoutPlan.getDesc() : "");
		txtAreaDesc.setBounds(271, 328, 175, 75);
		panel_2.add(txtAreaDesc);

		JButton btnSave = new JButton(workoutPlan != null ? "Update" : "Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != workoutPlan && workoutPlan.getWorkoutplan_id() != 0) {
					workoutPlan.setTimer(txtSetTimer.getText());
					workoutPlan.setGender(String.valueOf(comboGender.getSelectedItem()));
					workoutPlan.setLevel(String.valueOf(comboLevel.getSelectedItem()));
					workoutPlan.setDesc(txtAreaDesc.getText());
					selectedWorkout = workoutList.stream()
							.filter(w -> w.getWorkoutName().equals(comboWorkout.getSelectedItem())).findFirst();
					workoutPlan.setWorkout(selectedWorkout.orElse(null));
					if (selectedWorkout.isPresent()) {
						if (!workoutPlan.getWorkout().getWorkoutName().isEmpty()
								&& !workoutPlan.getWorkout().getBodypartName().isBlank()
								&& !workoutPlan.getTimer().isBlank() && !workoutPlan.getGender().isBlank()
								&& !workoutPlan.getLevel().isBlank() && !workoutPlan.getDesc().isBlank()) {
							if (comboWorkout.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Workout");
								return;
							} else if (txtSetTimer.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter no. of times");
								txtSetTimer.requestFocus(true);
								return;
							} else if (!(txtSetTimer.getText().trim().matches("[0-9]+"))) {
								JOptionPane.showMessageDialog(null, "Please enter Number only for No. of Times.");
								txtSetTimer.requestFocus(true);
								return;
							} else if (comboGender.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Gender (Male/Femele/Other)");
								return;
							} else if (comboLevel.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Level");
								return;
							} else if (txtAreaDesc.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Description");
								txtAreaDesc.requestFocus(true);
								return;
							} else {
								workoutPlanService.updateWorkoutPlan(String.valueOf(workoutPlan.getWorkoutplan_id()),
										workoutPlan);
								JOptionPane.showMessageDialog(null, "Workout Plan is Updated.");
								resetFormData();
								workoutPlanEditframe.setVisible(false);
							}

						} else {
							JOptionPane.showMessageDialog(null, "Check Required Field");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please Choose Workout Plan");
					}

				} else {
					WorkoutPlan workoutPlan = new WorkoutPlan();
					workoutPlan.setTimer(txtSetTimer.getText());
					workoutPlan.setGender(String.valueOf(comboGender.getSelectedItem()));
					workoutPlan.setLevel(String.valueOf(comboLevel.getSelectedItem()));
					workoutPlan.setDesc(txtAreaDesc.getText());
					selectedWorkout = workoutList.stream()
							.filter(w -> w.getWorkoutName().equals(comboWorkout.getSelectedItem())).findFirst();
					workoutPlan.setWorkout(selectedWorkout.orElse(null));
					if (selectedWorkout.isPresent()) {
						if (!(workoutPlan.getWorkout().getWorkoutName().isBlank())
								&& !(workoutPlan.getWorkout().getBodypartName().isBlank())
								&& !workoutPlan.getTimer().isBlank() && !workoutPlan.getGender().isBlank()
								&& !workoutPlan.getLevel().isBlank() && !workoutPlan.getDesc().isBlank()) {
							if (comboWorkout.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Workout");
								return;
							} else if (txtSetTimer.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter no. of times");
								txtSetTimer.requestFocus(true);
								return;
							} else if (!(txtSetTimer.getText().trim().matches("[a-z A-Z 0-9()-.,]+"))) {
								JOptionPane.showMessageDialog(null, "Please enter Number only for No. of Times.");
								txtSetTimer.requestFocus(true);
								return;
							} else if (comboGender.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Gender (Male/Femele/Other)");
								return;
							} else if (comboLevel.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Level");
								return;
							} else if (txtAreaDesc.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Description");
								txtAreaDesc.requestFocus(true);
								return;
							} else {
								workoutPlanService.saveWorkout(workoutPlan);
								JOptionPane.showMessageDialog(null, "A new Workout Plan is added");
								resetFormData();
								workoutPlanEditframe.setVisible(false);
							}

						} else {
							JOptionPane.showMessageDialog(null, "Check Required Field");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please Choose Workout Plan");
					}

				}

			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSave.setBackground(new Color(60, 179, 113));
		btnSave.setBounds(420, 496, 100, 35);
		panel.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 15));
		btnCancel.setBackground(new Color(220, 20, 60));
		btnCancel.setBounds(261, 496, 100, 35);
		panel.add(btnCancel);
	}
}
