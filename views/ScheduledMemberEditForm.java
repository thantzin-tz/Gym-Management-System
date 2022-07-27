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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import models.Members;
import models.Schedule;
import models.ScheduleMember;
import services.MemberService;
import services.ScheduleMemberService;
import services.ScheduleService;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class ScheduledMemberEditForm {

	protected JFrame frameScheduledMemberEdit;
	private JTextField txtPlayHour;
	private JTextField txtTypeOfBody;
	private JTextField txtGender;
	private JTextField txtFee;
	private JDateChooser dateChooser;
	private JDateChooser endDate;
	private JComboBox<String> comboMember;
	private JComboBox<String> comboSchedule;
	private ScheduleMemberService scheduleMemberService;
	private MemberService memberService;
	private ScheduleService scheduleService;
	private ScheduleMember scheduleMember;
	private List<Schedule> scheduleList = new ArrayList<>();
	private List<Members> memberList = new ArrayList<>();

	private Optional<Schedule> selectedSchedule;
	private Optional<Members> selectedMember;
	private int availableMember;
	private int scheduleId;
	Schedule schedule = new Schedule();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduledMemberEditForm window = new ScheduledMemberEditForm();
					window.frameScheduledMemberEdit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScheduledMemberEditForm() {
		initialize();
		this.initializeDependency();
		// Load Combo Box Data
		this.loadScheduleForComboBox();
		this.loadMemberForComboBox();
	}

	public ScheduledMemberEditForm(ScheduleMember scheduleMember) {
		initialize();
		this.scheduleMember = scheduleMember;
		this.initializeDependency();

		this.loadScheduleForComboBox();
		this.loadMemberForComboBox();
		comboSchedule.setSelectedItem(scheduleMember.getSchedule().getSchedule_name());
		comboMember.setSelectedItem(scheduleMember.getMember().getName());
	}

	private void loadScheduleForComboBox() {
		comboSchedule.addItem("- select -");
		this.scheduleList = this.scheduleService.findAllSchedule();
		this.scheduleList.forEach(b -> comboSchedule.addItem(b.getSchedule_name()));
	}

	private void loadMemberForComboBox() {
		comboMember.addItem("- select -");
		this.memberList = this.memberService.findAllMembers();
		this.memberList.forEach(b -> comboMember.addItem(b.getName()));
	}

	private void initializeDependency() {
		this.scheduleService = new ScheduleService();
		this.memberService = new MemberService();
		this.scheduleMemberService = new ScheduleMemberService();
	}

	void resetFormData() {
		// txtScheduleName.setText("");
		comboSchedule.setSelectedIndex(0);
		// txtScheduleName.requestFocus();

		txtFee.setText("");
		/*
		 * Default Date
		 */
	}

	public int calculateDays() {
		long dateMili;
		Date expireDate = endDate.getDate();
		Date startDate = dateChooser.getDate();

		Date today = new Date();
		if (today.compareTo(startDate) > 0) {
			dateMili = Math.abs(expireDate.getTime() - today.getTime());
		} else {
			dateMili = Math.abs(expireDate.getTime() - startDate.getTime());
		}
		int diff = (int) TimeUnit.DAYS.convert(dateMili, TimeUnit.MILLISECONDS);

		return diff;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameScheduledMemberEdit = new JFrame();
		frameScheduledMemberEdit.setResizable(false);
		frameScheduledMemberEdit.getContentPane().setBackground(new Color(230, 230, 250));
		frameScheduledMemberEdit.setBounds(400, 50, 690, 651);
		frameScheduledMemberEdit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameScheduledMemberEdit.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Register Members to Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(38, 33, 470, 553);
		frameScheduledMemberEdit.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Member Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(45, 29, 170, 35);
		panel.add(lblNewLabel);

		JLabel lblScheduleName = new JLabel("Schedule Name");
		lblScheduleName.setFont(new Font("Calibri", Font.BOLD, 15));
		lblScheduleName.setBounds(45, 96, 170, 35);
		panel.add(lblScheduleName);

		JLabel lblPlayHours = new JLabel("Play Hours");
		lblPlayHours.setFont(new Font("Calibri", Font.BOLD, 15));
		lblPlayHours.setBounds(45, 160, 170, 35);
		panel.add(lblPlayHours);

		JLabel lblTypeOfBody = new JLabel("Type of Body");
		lblTypeOfBody.setFont(new Font("Calibri", Font.BOLD, 15));
		lblTypeOfBody.setBounds(45, 223, 170, 35);
		panel.add(lblTypeOfBody);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Calibri", Font.BOLD, 15));
		lblGender.setBounds(45, 280, 170, 35);
		panel.add(lblGender);

		JLabel lblFees = new JLabel("Fees");
		lblFees.setFont(new Font("Calibri", Font.BOLD, 15));
		lblFees.setBounds(45, 343, 170, 35);
		panel.add(lblFees);

		JLabel lblDuration = new JLabel("Start Date");
		lblDuration.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDuration.setBounds(45, 413, 170, 35);
		panel.add(lblDuration);

		JLabel lblStartJoinDate = new JLabel("End Date");
		lblStartJoinDate.setFont(new Font("Calibri", Font.BOLD, 15));
		lblStartJoinDate.setBounds(45, 473, 170, 35);
		panel.add(lblStartJoinDate);

		comboMember = new JComboBox<String>();
		comboMember.setBackground(new Color(230, 230, 250));
		comboMember.setBounds(252, 26, 170, 35);
		panel.add(comboMember);

		comboSchedule = new JComboBox<String>();
		comboSchedule.setEnabled(true);
		comboSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSchedule = scheduleList.stream().filter(p -> {
					return p.getSchedule_name().trim().equals(String.valueOf(comboSchedule.getSelectedItem()).trim());
				}).findFirst();

				if (selectedSchedule.isPresent()) {
					txtTypeOfBody.setText(selectedSchedule.get().getTypeOfBody());
					txtPlayHour.setText(selectedSchedule.get().getTime());
					txtGender.setText(selectedSchedule.get().getGender());
					txtFee.setText(String.valueOf((selectedSchedule.get().getAmount())));
					availableMember = selectedSchedule.get().getAvailabeMember();
					scheduleId = selectedSchedule.get().getSchedule_id();
				}

			}
		});
		comboSchedule.setBackground(new Color(230, 230, 250));
		comboSchedule.setBounds(252, 93, 170, 35);
		panel.add(comboSchedule);

		txtPlayHour = new JTextField();
		txtPlayHour.setEditable(false);
		txtPlayHour.setBounds(252, 157, 170, 35);
		panel.add(txtPlayHour);
		txtPlayHour.setColumns(10);

		txtTypeOfBody = new JTextField();
		txtTypeOfBody.setEditable(false);
		txtTypeOfBody.setColumns(10);
		txtTypeOfBody.setBounds(252, 220, 170, 35);
		panel.add(txtTypeOfBody);

		txtGender = new JTextField();
		txtGender.setEditable(false);
		txtGender.setColumns(10);
		txtGender.setBounds(252, 277, 170, 35);
		panel.add(txtGender);

		txtFee = new JTextField();
		txtFee.setEditable(false);
		txtFee.setColumns(10);
		txtFee.setBounds(252, 340, 170, 35);
		panel.add(txtFee);

		if (scheduleMember != null) {
			Date date = new Date();
			Date date1 = new Date();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(scheduleMember.getScheduleJoinDate());
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(scheduleMember.getExpireDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			dateChooser = new JDateChooser();
			dateChooser.setDate(date);
			endDate = new JDateChooser();
			endDate.setDate(date1);
		} else {
			dateChooser = new JDateChooser();
			endDate = new JDateChooser();
			/// set Default date value
			dateChooser.setDate(new Date());
			endDate.setDate(new Date());
		}

		dateChooser.setBackground(new Color(230, 230, 250));
		dateChooser.setBounds(252, 410, 170, 35);
		panel.add(dateChooser);

		endDate.setBackground(new Color(230, 230, 250));
		endDate.setBounds(252, 473, 170, 35);
		panel.add(endDate);

		// To Make JDateChooser cannot edit
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		JTextFieldDateEditor editor1 = (JTextFieldDateEditor) endDate.getDateEditor();
		editor1.setEditable(false);

		JButton btnSave = new JButton(scheduleMember != null ? "Update" : "Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (scheduleMember != null && scheduleMember.getScheduleMember_id() != 0) {

					selectedMember = memberList.stream().filter(p -> {
						return p.getName().equals(String.valueOf(comboMember.getSelectedItem()).trim());
					}).findFirst();
					if (selectedMember.isPresent()) {
						scheduleMember.setMember(selectedMember.orElseGet(null));
					}

					selectedSchedule = scheduleList.stream().filter(p -> {
						return p.getSchedule_name().trim()
								.equals(String.valueOf(comboSchedule.getSelectedItem()).trim());
					}).findFirst();

					if (selectedSchedule.isPresent()) {
						scheduleMember.setSchedule(selectedSchedule.orElse(schedule));
					}

					Date d1 = dateChooser.getDate();
					Date d2 = endDate.getDate();

					/// Start date and end date check
					if (d1.compareTo(d2) == 0 || d1.compareTo(d2) > 0) {
						JOptionPane.showMessageDialog(null, "Expire date must be greater than start date");
						return;
					} else {
						SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						scheduleMember.setScheduleJoinDate(sdformat.format(d1));
						scheduleMember.setExpireDate(sdformat.format(d2));
					}

					if ((selectedSchedule.isPresent()) && (selectedMember.isPresent())) {
						if (comboMember.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Member Name");
							return;
						} else if (comboSchedule.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Schedule");
							return;
						} else {
							if (availableMember > 0) {
								int dayLeft = calculateDays();
								scheduleMember.setDayLeft(dayLeft);
								scheduleMemberService.updateScheduleMember(
										String.valueOf(scheduleMember.getScheduleMember_id()), scheduleMember);
								availableMember -= 1;
								schedule.setAvailabeMember(availableMember);
								scheduleService.updateAvailableMember(String.valueOf(scheduleId), schedule);

								JOptionPane.showMessageDialog(null, "ScheduledMember List is Updated.");
								scheduleMember = null;
								// Back to Member Form
								frameScheduledMemberEdit.setVisible(false);
								resetFormData();
							} else {
								JOptionPane.showMessageDialog(null, "This Schedule is Full of Members!");
								resetFormData();
							}
						}

					} else {
						JOptionPane.showMessageDialog(null, "Please Select Member and Schedule's Name");
					}

				} else {
					/// Save
					comboMember.setEnabled(true);
					comboMember.setEditable(true);
					comboSchedule.setEnabled(true);
					comboSchedule.setEditable(true);
					ScheduleMember scheduleMember = new ScheduleMember();
					selectedMember = memberList.stream().filter(p -> {
						return p.getName().equals(String.valueOf(comboMember.getSelectedItem()).trim());
					}).findFirst();
					if (selectedMember.isPresent()) {
						scheduleMember.setMember(selectedMember.orElseGet(null));
					}
					selectedSchedule = scheduleList.stream().filter(p -> {
						return p.getSchedule_name().trim()
								.equals(String.valueOf(comboSchedule.getSelectedItem()).trim());
					}).findFirst();
					if (selectedSchedule.isPresent()) {
						scheduleMember.setSchedule(selectedSchedule.orElse(schedule));
					}

					int dayLeft = calculateDays();
					scheduleMember.setDayLeft(dayLeft);

					Date d1 = dateChooser.getDate();
					Date d2 = endDate.getDate();

					/// Start date and end date check
					if (d1.compareTo(d2) == 0 || d1.compareTo(d2) > 0) {
						JOptionPane.showMessageDialog(null, "Expire date must be greather than start date");
						return;
					} else {
						SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						scheduleMember.setScheduleJoinDate(sdformat.format(d1));
						scheduleMember.setExpireDate(sdformat.format(d2));
					}

					if (selectedMember.isPresent() && selectedSchedule.isPresent()) {
						if (comboMember.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Member Name");
							return;
						} else if (comboSchedule.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Schedule");
							return;
						}
//						} else if (d1.compareTo(d2) == 0 || d1.compareTo(d2) > 0) {
//							JOptionPane.showMessageDialog(null, "Expire date must be greather than start date");
//							return;
//						} 
							else {
							/// Save ScheduledMember
							/// available Member decrease
							if (availableMember > 0) {
								availableMember -= 1;
								schedule.setAvailabeMember(availableMember);
								scheduleService.updateAvailableMember(String.valueOf(scheduleId), schedule);

								/// Save Process
								scheduleMemberService.saveScheduleMember(scheduleMember);
								JOptionPane.showMessageDialog(null, "Member is Registered.");
								scheduleMember = null;
								// Back to Member Form
								frameScheduledMemberEdit.setVisible(false);
								resetFormData();
							} else {
								JOptionPane.showMessageDialog(null, "This Schedule is Full of Members!");
								resetFormData();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please Select Member and Schedule's Name");
					}

				}
			}
		});

		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSave.setBackground(new Color(60, 179, 113));
		btnSave.setBounds(538, 182, 100, 35);
		frameScheduledMemberEdit.getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(220, 20, 60));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 15));
		btnCancel.setBounds(538, 267, 100, 35);
		frameScheduledMemberEdit.getContentPane().add(btnCancel);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBackground(new Color(110, 133, 183));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBack.setBounds(538, 357, 100, 35);
		frameScheduledMemberEdit.getContentPane().add(btnBack);
	}
}
