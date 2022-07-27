package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import models.Schedule;
import models.Staff;
import services.ScheduleService;
import services.StaffService;

import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ScheduleEditForm {

	protected JFrame frameScheduleEdit;
	private JTextField txtScheduleName;
	private JTextField txtFees;
	JComboBox<String> comboTrainerName, comboPlayTime, comboTypeOfBody, comboGender;
	private Schedule schedule;
	private ScheduleService scheduleService;
	private StaffService staffService;
	private List<Staff> staffList;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtAvailableMember;
	// private Optional<Staff> selectedStaff;
	// private List<Schedule> scheduleList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleEditForm window = new ScheduleEditForm();
					window.frameScheduleEdit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScheduleEditForm() {
		this.scheduleService = new ScheduleService();
		initialize();
		initializeDependency();
		this.loadStaffForComboBox();

	}

	public ScheduleEditForm(Schedule schedule) {
		this.schedule = schedule;
		this.scheduleService = new ScheduleService();
		initialize();
		this.initializeDependency();
		this.loadStaffForComboBox();
		comboTrainerName.setSelectedItem(schedule.getStaff().getName());
	}

	private void initializeDependency() {
		this.scheduleService = new ScheduleService();
		this.staffService = new StaffService();
	}

	private void resetFormData() {
		txtScheduleName.setText("");
		txtFees.setText("");
		comboTrainerName.setSelectedIndex(0);
		comboPlayTime.setSelectedIndex(0);
		comboTypeOfBody.setSelectedIndex(0);
		comboGender.setSelectedIndex(0);
		txtAvailableMember.setText("");

	}

	private void loadStaffForComboBox() {
		comboTrainerName.addItem("- select -");
		this.staffList = this.staffService.findComboTrainer();
		this.staffList.forEach(b -> comboTrainerName.addItem(b.getName()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameScheduleEdit = new JFrame();
		frameScheduleEdit.setTitle("Schedule");
		frameScheduleEdit.getContentPane().setBackground(new Color(230, 230, 250));
		frameScheduleEdit.setBounds(350, 30, 673, 690);
		frameScheduleEdit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameScheduleEdit.setResizable(false);
		frameScheduleEdit.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Add Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(46, 42, 446, 579);
		frameScheduleEdit.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Schedule Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(39, 32, 149, 32);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Trainer Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(39, 158, 149, 32);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Play Time");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_2.setBounds(39, 229, 149, 32);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Type of Body");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_3.setBounds(39, 303, 149, 32);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_4.setBounds(39, 380, 149, 32);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Fees");
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_5.setBounds(39, 445, 149, 32);
		panel.add(lblNewLabel_5);

		txtScheduleName = new JTextField(schedule != null ? schedule.getSchedule_name() : "");
		txtScheduleName.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtScheduleName.setBounds(237, 31, 170, 35);
		panel.add(txtScheduleName);
		txtScheduleName.setColumns(10);

		txtFees = new JTextField(schedule != null ? String.valueOf(schedule.getAmount()) : "");
		txtFees.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtFees.setColumns(10);
		txtFees.setBounds(237, 444, 170, 35);
		panel.add(txtFees);

		comboTrainerName = new JComboBox<String>();
		comboTrainerName.setFont(new Font("Calibri", Font.PLAIN, 15));
		comboTrainerName.setBackground(new Color(230, 230, 250));
		comboTrainerName.setBounds(237, 157, 170, 35);
		panel.add(comboTrainerName);
//		comboTrainerName.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Optional<Staff> selectedStaff = staffList.stream()
//						.filter(s -> s.getName().equals(comboTrainerName.getSelectedItem())).findFirst();
//				schedule.setStaff(selectedStaff.orElse(null));

//			}
//		});

		comboPlayTime = new JComboBox<String>();
		comboPlayTime.setFont(new Font("Calibri", Font.PLAIN, 15));
		comboPlayTime.setModel(new DefaultComboBoxModel<String>(
				new String[] { "- Select -", "1 Hour", "2 Hours", "3 Hours", "4 Hours", "5 Hours" }));
		comboPlayTime.setBackground(new Color(230, 230, 250));
		comboPlayTime.setBounds(237, 228, 170, 35);

		if (schedule != null) {
			comboPlayTime.setSelectedItem(schedule.getTime());
		} else {
			comboPlayTime.setSelectedIndex(0);
		}
		panel.add(comboPlayTime);

		comboTypeOfBody = new JComboBox<String>();
		comboTypeOfBody.setFont(new Font("Calibri", Font.PLAIN, 15));
		comboTypeOfBody.setModel(new DefaultComboBoxModel<String>(
				new String[] { "- Select -", "Normal", "Over Weight", "Under Weight" }));
		comboTypeOfBody.setBackground(new Color(230, 230, 250));
		comboTypeOfBody.setBounds(237, 302, 170, 35);

		if (schedule != null) {
			comboTypeOfBody.setSelectedItem(schedule.getTypeOfBody());
		} else {
			comboTypeOfBody.setSelectedIndex(0);
		}
		panel.add(comboTypeOfBody);

		comboGender = new JComboBox<String>();
		comboGender.setFont(new Font("Calibri", Font.PLAIN, 15));
		comboGender.setModel(new DefaultComboBoxModel<String>(new String[] { "- Select -", "Male", "Female" }));
		comboGender.setBackground(new Color(230, 230, 250));
		comboGender.setBounds(237, 379, 170, 35);

		if (schedule != null) {
			comboGender.setSelectedItem(schedule.getGender());
		} else {
			comboGender.setSelectedIndex(0);
		}
		panel.add(comboGender);

		JLabel lblNewLabel_6 = new JLabel("Schedule Type");
		lblNewLabel_6.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_6.setBounds(39, 97, 149, 32);
		panel.add(lblNewLabel_6);

		/// When selected radio button
		JRadioButton rdbtnOridinary = new JRadioButton("Ordinary");
		rdbtnOridinary.setSelected(true);
		rdbtnOridinary.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});

		buttonGroup.add(rdbtnOridinary);
		rdbtnOridinary.setBackground(new Color(230, 230, 250));
		rdbtnOridinary.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnOridinary.setBounds(237, 100, 82, 26);
		panel.add(rdbtnOridinary);

		/// When selected radio button
		JRadioButton rdbtnSpecial = new JRadioButton("Special");
		rdbtnSpecial.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});

		buttonGroup.add(rdbtnSpecial);
		rdbtnSpecial.setBackground(new Color(230, 230, 250));
		rdbtnSpecial.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnSpecial.setBounds(340, 100, 67, 26);
		panel.add(rdbtnSpecial);

		JLabel lblNewLabel_5_1 = new JLabel("Available Members");
		lblNewLabel_5_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_5_1.setBounds(39, 516, 149, 32);
		panel.add(lblNewLabel_5_1);

		txtAvailableMember = new JTextField(schedule != null ? String.valueOf(schedule.getAvailabeMember()) : "");
		txtAvailableMember.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtAvailableMember.setColumns(10);
		txtAvailableMember.setBounds(237, 515, 170, 35);
		panel.add(txtAvailableMember);

		/// to select radio button when update
		if (schedule != null) {
			if (schedule.getSchedule_type().equals("Special")) {
				rdbtnSpecial.setSelected(true);
			} else if (schedule.getSchedule_type().equals("Ordinary")) {
				rdbtnOridinary.setSelected(true);
			}
		}

		JButton btnSave = new JButton(schedule != null ? "Update" : "Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (schedule != null && schedule.getSchedule_id() != 0) {
					schedule.setSchedule_name(txtScheduleName.getText());
					if (rdbtnOridinary.isSelected()) {
						String keyword = "Ordinary";
						schedule.setSchedule_type(keyword);
						int availableMember = Integer.parseInt(txtAvailableMember.getText());

						if (availableMember > 20) {
							schedule.setAvailabeMember(Integer.parseInt(txtAvailableMember.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Ordinary Member must be Grather than 20!");
							return;
						}
						double fees = Double.parseDouble(txtFees.getText());
						System.out.println(fees);
						if (fees <= 50000.0) {
							if (fees >= 30000.0) {
								schedule.setAmount(Double.parseDouble(txtFees.getText()));
							} else {
								JOptionPane.showMessageDialog(null, "Fee must be greather than 30000");
								return;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Fee must be Less than 50000");
							return;
						}

					} else if (rdbtnSpecial.isSelected()) {
						String keyword = "Special";
						schedule.setSchedule_type(keyword);
						int availableMember = Integer.parseInt(txtAvailableMember.getText());
						double fees = Double.parseDouble(txtFees.getText());
						if (availableMember <= 20) {
							schedule.setAvailabeMember(Integer.parseInt(txtAvailableMember.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Special Member must be 20 or Less!");
							return;
						}
						if (fees >= 50000.0) {
							schedule.setAmount(Double.parseDouble(txtFees.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Fee must be grather than 50000");
							return;
						}
					}
					Optional<Staff> selectedStaff = staffList.stream()
							.filter(s -> s.getName().equals(comboTrainerName.getSelectedItem())).findFirst();
					schedule.setStaff(selectedStaff.orElse(null));
					schedule.setTime(String.valueOf(comboPlayTime.getSelectedItem()));
					schedule.setTypeOfBody(String.valueOf(comboTypeOfBody.getSelectedItem()));
					schedule.setGender((String) comboGender.getSelectedItem());
					try {
						schedule.setAmount(Double.parseDouble(txtFees.getText()));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Enter Number only for Fees");
						return;
					}
					try {
						schedule.setAvailabeMember(Integer.parseInt(txtAvailableMember.getText()));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Enter Number only for Available Members");
						return;
					}
					if (selectedStaff.isPresent()) {
						if (!schedule.getSchedule_name().isBlank() && !schedule.getSchedule_type().isBlank()
								&& !schedule.getStaff().getName().isBlank() && !schedule.getTime().isBlank()
								&& !schedule.getTypeOfBody().isBlank() && !schedule.getGender().isBlank()
								&& !(schedule.getAmount() == 0.0) && !(schedule.getAvailabeMember() == 0)) {
							if (txtScheduleName.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Schedule Name.");
								txtScheduleName.requestFocus(true);
								return;
							} else if (!(txtScheduleName.getText().trim().matches("[a-z A-Z]+"))) {
								JOptionPane.showMessageDialog(null, "Please enter Schedule Name Correctly.");
								txtScheduleName.requestFocus(true);
								return;
							} else if (comboTrainerName.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Trainer Name");
								return;
							} else if (comboPlayTime.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Play Time");
								return;
							} else if (comboTypeOfBody.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Type of Body");
								return;
							} else if (comboGender.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Gender");
								return;
							} else if (txtFees.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Fees.");
								txtFees.requestFocus(true);
								return;
							} else if (txtAvailableMember.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Available Members.");
								txtAvailableMember.requestFocus(true);
								return;
							} else {
								/// Update Schedule
								scheduleService.updateSchedule(String.valueOf(schedule.getSchedule_id()), schedule);
								JOptionPane.showMessageDialog(null, "Schedule Updated.");
								schedule = null;
								// Back to Member Form
								ScheduleListForm scheduleListForm = new ScheduleListForm();
								scheduleListForm.loadAllSchedules(Optional.empty());
								frameScheduleEdit.setVisible(false);
								resetFormData();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Enter Required Field");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please Choose Trainer Name");
					}

				} else {
					Schedule schedule = new Schedule();
					schedule.setSchedule_name(txtScheduleName.getText());
					if (rdbtnOridinary.isSelected()) {
						String keyword = "Ordinary";
						schedule.setSchedule_type(keyword);
						int availableMember = Integer.parseInt(txtAvailableMember.getText());

						if (availableMember > 20) {
							schedule.setAvailabeMember(Integer.parseInt(txtAvailableMember.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Ordinary Member must be Greater than 20!");
						}
						double fees = Double.parseDouble(txtFees.getText());
						System.out.println(fees);
						if (fees <= 50000.0) {
							if (fees >= 30000.0) {
								schedule.setAmount(Double.parseDouble(txtFees.getText()));
							} else {
								JOptionPane.showMessageDialog(null, "Fee must be greater than 30000");
								return;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Fee must be Less than 50000");
							return;
						}

					} else if (rdbtnSpecial.isSelected()) {
						String keyword = "Special";
						schedule.setSchedule_type(keyword);
						int availableMember = Integer.parseInt(txtAvailableMember.getText());
						double fees = Double.parseDouble(txtFees.getText());
						if (availableMember <= 20) {
							schedule.setAvailabeMember(Integer.parseInt(txtAvailableMember.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Special Member must be 20 or Less!");
							return;
						}
						if (fees >= 50000.0) {
							schedule.setAmount(Double.parseDouble(txtFees.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Fee must be greater than 50000");
							return;
						}
					}
					Optional<Staff> selectedStaff = staffList.stream()
							.filter(s -> s.getName().equals(comboTrainerName.getSelectedItem())).findFirst();
					schedule.setStaff(selectedStaff.orElse(null));
					schedule.setTime(String.valueOf(comboPlayTime.getSelectedItem()));
					schedule.setTypeOfBody(String.valueOf(comboTypeOfBody.getSelectedItem()));
					schedule.setGender((String) comboGender.getSelectedItem());
					try {
						schedule.setAmount(Double.parseDouble(txtFees.getText()));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Enter Number only for Fees");
						return;
					}
					try {
						schedule.setAvailabeMember(Integer.parseInt(txtAvailableMember.getText()));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Enter Number only for Available Members");
						return;
					}
					if (selectedStaff.isPresent()) {
						if (!schedule.getSchedule_name().isBlank() && !schedule.getSchedule_type().isBlank()
								&& !schedule.getStaff().getName().isBlank() && !schedule.getTime().isBlank()
								&& !schedule.getTypeOfBody().isBlank() && !schedule.getGender().isBlank()
								&& !(schedule.getAmount() == 0.0) && !(schedule.getAvailabeMember() == 0)) {
							if (txtScheduleName.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Schedule Name.");
								txtScheduleName.requestFocus(true);
								return;
							} else if (!(txtScheduleName.getText().trim().matches("[a-z A-Z]+"))) {
								JOptionPane.showMessageDialog(null, "Please enter Schedule Name Correctly.");
								txtScheduleName.requestFocus(true);
								return;
							} else if (comboTrainerName.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Trainer Name");
								return;
							} else if (comboPlayTime.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Play Time");
								return;
							} else if (comboTypeOfBody.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Type of Body");
								return;
							} else if (comboGender.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select Gender");
								return;
							} else if (txtFees.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Fees.");
								txtFees.requestFocus(true);
								return;
							} else if (txtAvailableMember.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter Available Members.");
								txtAvailableMember.requestFocus(true);
								return;
							} else {
								/// Save Schedule
								scheduleService.saveSchedule(schedule);
								resetFormData();
								JOptionPane.showMessageDialog(null, "A new Schedule is added.");
								ScheduleListForm scheduleListForm = new ScheduleListForm();
								scheduleListForm.loadAllSchedules(Optional.empty());
								frameScheduleEdit.setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Enter Required Field");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please Choose Trainer Name");
					}

				}
			}
		});
		btnSave.setBackground(new Color(60, 179, 113));
		btnSave.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSave.setBounds(528, 218, 99, 35);
		frameScheduleEdit.getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(220, 20, 60));
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 15));
		btnCancel.setBounds(528, 294, 99, 35);
		frameScheduleEdit.getContentPane().add(btnCancel);

		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(110, 133, 183));
		btnBack.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBack.setBounds(528, 371, 99, 35);
		frameScheduleEdit.getContentPane().add(btnBack);
	}
}
