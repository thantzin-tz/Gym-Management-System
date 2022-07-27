package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import services.EquipmentService;
import services.ScheduleMemberService;
import services.ScheduleService;
import utils.CurrentUserHolder;

import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;

public class MainForm {

	protected JFrame frameMain;
	private JScrollPane middleScrollPane;
	private MemberListForm memberListForm;
	private StaffListForm staffListForm;
	private WorkoutPlanForm workoutPlanForm;
	private WorkoutForm workoutForm;
	private ScheduleListForm scheduleListForm;
	private ScheduledMemberForm scheduledMemberForm;
	private ReportForm reportForm;
	private EquipmentListForm equipmentListForm;
	private JPanel panHome, panMemberList, panStaffList, panWorkoutPlan, panSchedule, panRegisterMember, panWorkout,
			panAttendance, panelHome;
	private JLabel lblAvailable, lblExpire, lblEquipment;
	private ScheduleMemberService scheduleMemberService;
	private ScheduleService scheduleService;
	private EquipmentService equipmentService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm mainForm = new MainForm();
					mainForm.frameMain.setUndecorated(true);
					mainForm.frameMain.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void countingNumber() {
		/// Count too close Expired Member
		scheduleMemberService = new ScheduleMemberService();
		int expireMember = scheduleMemberService.countExpireMember();
		lblExpire.setText(String.valueOf(expireMember));
		/// count available schedule
		scheduleService = new ScheduleService();
		int availableSchedule = scheduleService.countAvailableSchedule();
		lblAvailable.setText(String.valueOf(availableSchedule));
		/// Count Equipments
		equipmentService = new EquipmentService();
		int equip = equipmentService.countEquipment();
		lblEquipment.setText(String.valueOf(equip));
	}

	public MainForm() {
		initialize();
		countingNumber();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMain = new JFrame();
		frameMain.setBounds(0, 0, 1400, 800);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.getContentPane().setLayout(null);

		JPanel panelSideBar = new JPanel();
		panelSideBar.setBounds(0, 0, 230, 800);
		panelSideBar.setBackground(new Color(110, 133, 183));
		frameMain.getContentPane().add(panelSideBar);
		panelSideBar.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/Gym-club.png")));
		lblNewLabel.setBounds(12, 0, 206, 221);
		panelSideBar.add(lblNewLabel);

		panHome = new JPanel();
		panHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetSideMenuColor();
				panHome.setBackground(new Color(146, 169, 189));
				middleScrollPane.setViewportView(panelHome);
				countingNumber();
			}
		});

		panHome.setForeground(new Color(255, 255, 255));
		panHome.setBackground(new Color(146, 169, 189));
		panHome.setBounds(0, 233, 230, 50);
		panelSideBar.add(panHome);
		panHome.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Home");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
//		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1.setBounds(60, 10, 98, 30);
		panHome.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-gym-30.png")));
		lblNewLabel_2.setBounds(25, 0, 30, 50);
		panHome.add(lblNewLabel_2);

		panMemberList = new JPanel();
		panMemberList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				memberListForm = new MemberListForm();
				middleScrollPane.setViewportView(memberListForm);
				resetSideMenuColor();
				panMemberList.setBackground(new Color(146, 169, 189));

			}
		});
		panMemberList.setLayout(null);
		panMemberList.setForeground(Color.WHITE);
		panMemberList.setBackground(new Color(110, 133, 183));
		panMemberList.setBounds(0, 289, 230, 50);
		panelSideBar.add(panMemberList);

		JLabel lblNewLabel_1_1 = new JLabel("Member List");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(60, 10, 98, 30);
		panMemberList.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-member-25.png")));
		lblNewLabel_2_1.setBounds(25, 0, 30, 50);
		panMemberList.add(lblNewLabel_2_1);

		panStaffList = new JPanel();
		panStaffList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				staffListForm = new StaffListForm();
				middleScrollPane.setViewportView(staffListForm);
				resetSideMenuColor();
				panStaffList.setBackground(new Color(146, 169, 189));
			}
		});
//		panStaffList.addMouseListener(new PanelButtonMouseAdapter(panStaffList));
		panStaffList.setLayout(null);
		panStaffList.setForeground(Color.WHITE);
		panStaffList.setBackground(new Color(110, 133, 183));
		panStaffList.setBounds(0, 345, 230, 50);
		panelSideBar.add(panStaffList);

		JLabel lblNewLabel_1_1_1 = new JLabel("Staff List");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(60, 10, 98, 30);
		panStaffList.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-staff-24.png")));
		lblNewLabel_2_1_1.setBounds(25, 0, 30, 50);
		panStaffList.add(lblNewLabel_2_1_1);

		panWorkoutPlan = new JPanel();
		panWorkoutPlan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				workoutPlanForm = new WorkoutPlanForm();
				middleScrollPane.setViewportView(workoutPlanForm);
				resetSideMenuColor();
				panWorkoutPlan.setBackground(new Color(146, 169, 189));
			}
		});
//		panWorkoutPlan.addMouseListener(new PanelButtonMouseAdapter(panWorkoutPlan));
		panWorkoutPlan.setLayout(null);
		panWorkoutPlan.setForeground(Color.WHITE);
		panWorkoutPlan.setBackground(new Color(110, 133, 183));
		panWorkoutPlan.setBounds(0, 460, 230, 50);
		panelSideBar.add(panWorkoutPlan);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Workout Plan");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(60, 10, 123, 30);
		panWorkoutPlan.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-what-i-do-25.png")));
		lblNewLabel_2_1_1_1.setBounds(25, 0, 30, 50);
		panWorkoutPlan.add(lblNewLabel_2_1_1_1);

		panSchedule = new JPanel();
		panSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scheduleListForm = new ScheduleListForm();
				middleScrollPane.setViewportView(scheduleListForm);
				resetSideMenuColor();
				panSchedule.setBackground(new Color(146, 169, 189));
			}
		});
//		panSchedule.addMouseListener(new PanelButtonMouseAdapter(panSchedule));
		panSchedule.setLayout(null);
		panSchedule.setForeground(Color.WHITE);
		panSchedule.setBackground(new Color(110, 133, 183));
		panSchedule.setBounds(0, 518, 230, 50);
		panelSideBar.add(panSchedule);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Schedule");
		lblNewLabel_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1_1_1_1.setBounds(60, 10, 98, 30);
		panSchedule.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1_1
				.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-schedule-25.png")));
		lblNewLabel_2_1_1_1_1.setBounds(25, 0, 30, 50);
		panSchedule.add(lblNewLabel_2_1_1_1_1);

		panRegisterMember = new JPanel();
		panRegisterMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scheduledMemberForm = new ScheduledMemberForm();
				middleScrollPane.setViewportView(scheduledMemberForm);
				resetSideMenuColor();
				panRegisterMember.setBackground(new Color(146, 169, 189));
			}
		});
//		panRegisterMember.addMouseListener(new PanelButtonMouseAdapter(panRegisterMember));
		panRegisterMember.setLayout(null);
		panRegisterMember.setForeground(Color.WHITE);
		panRegisterMember.setBackground(new Color(110, 133, 183));
		panRegisterMember.setBounds(0, 576, 230, 50);
		panelSideBar.add(panRegisterMember);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Register Members");
		lblNewLabel_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1_1_1_1_1.setBounds(60, 10, 151, 30);
		panRegisterMember.add(lblNewLabel_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("");
		lblNewLabel_2_1_1_1_1_1
				.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-edit-user-25.png")));
		lblNewLabel_2_1_1_1_1_1.setBounds(25, 0, 30, 50);
		panRegisterMember.add(lblNewLabel_2_1_1_1_1_1);

		panWorkout = new JPanel();
		panWorkout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				workoutForm = new WorkoutForm();
				middleScrollPane.setViewportView(workoutForm);
				resetSideMenuColor();
				panWorkout.setBackground(new Color(146, 169, 189));
			}
		});
		panWorkout.setLayout(null);
		panWorkout.setForeground(Color.WHITE);
		panWorkout.setBackground(new Color(110, 133, 183));
		panWorkout.setBounds(0, 402, 230, 50);
		panelSideBar.add(panWorkout);

		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Workout ");
		lblNewLabel_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_2.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1_1_1_2.setBounds(60, 10, 123, 30);
		panWorkout.add(lblNewLabel_1_1_1_1_2);

		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("");
		lblNewLabel_2_1_1_1_2.setBounds(25, 0, 30, 50);
		panWorkout.add(lblNewLabel_2_1_1_1_2);

		JLabel lblNewLabel_2_1_1_1_3 = new JLabel("");
		lblNewLabel_2_1_1_1_3
				.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-deadlift-25.png")));
		lblNewLabel_2_1_1_1_3.setBounds(25, 0, 30, 50);
		panWorkout.add(lblNewLabel_2_1_1_1_3);

		panAttendance = new JPanel();
		panAttendance.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				reportForm = new ReportForm();
				middleScrollPane.setViewportView(reportForm);
				resetSideMenuColor();
				panAttendance.setBackground(new Color(146, 169, 189));
			}
		});
		panAttendance.setLayout(null);
		panAttendance.setForeground(Color.WHITE);
		panAttendance.setBackground(new Color(110, 133, 183));
		panAttendance.setBounds(0, 634, 230, 50);
		panelSideBar.add(panAttendance);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Attendance");
		lblNewLabel_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(60, 10, 151, 30);
		panAttendance.add(lblNewLabel_1_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_2 = new JLabel("");
		lblNewLabel_2_1_1_1_1_1_2
				.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-attendance-30.png")));
		lblNewLabel_2_1_1_1_1_1_2.setBounds(25, 0, 30, 50);
		panAttendance.add(lblNewLabel_2_1_1_1_1_1_2);

		middleScrollPane = new JScrollPane();
		middleScrollPane.setBounds(230, 44, 1160, 760);
		frameMain.getContentPane().add(middleScrollPane);

		panelHome = new JPanel();
		panelHome.setBackground(new Color(230, 230, 250));
		middleScrollPane.setViewportView(panelHome);
		panelHome.setLayout(null);

		JLabel lblWorld = new JLabel("WORLD-39  GYM");
		lblWorld.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorld.setForeground(new Color(72, 61, 139));
		lblWorld.setFont(new Font("Cambria", Font.BOLD, 37));
		lblWorld.setBounds(393, 53, 340, 47);
		panelHome.add(lblWorld);

		JPanel panelExpire = new JPanel();
		panelExpire.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scheduledMemberForm = new ScheduledMemberForm();
				middleScrollPane.setViewportView(scheduledMemberForm);
				resetSideMenuColor();
				panRegisterMember.setBackground(new Color(146, 169, 189));
			}
		});
		panelExpire.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelExpire.setLayout(null);
		panelExpire.setBackground(new Color(255, 165, 0));
		panelExpire.setBounds(113, 205, 250, 120);
		panelHome.add(panelExpire);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(126, 0, 4, 120);
		panelExpire.add(separator);

		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-expired-100.png")));
		lblNewLabel_1_2.setBounds(7, 0, 119, 120);
		panelExpire.add(lblNewLabel_1_2);

		lblExpire = new JLabel("20");
		lblExpire.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpire.setForeground(Color.WHITE);
		lblExpire.setFont(new Font("Dialog", Font.BOLD, 25));
		lblExpire.setBounds(146, 22, 75, 35);
		panelExpire.add(lblExpire);

		JLabel lblMembers = new JLabel("Members");
		lblMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembers.setForeground(Color.WHITE);
		lblMembers.setFont(new Font("Dialog", Font.BOLD, 17));
		lblMembers.setBounds(126, 67, 124, 35);
		panelExpire.add(lblMembers);

		JLabel lblNewLabel_4 = new JLabel("Almost Expire Members");
		lblNewLabel_4.setForeground(new Color(148, 0, 211));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_4.setBounds(119, 335, 250, 40);
		panelHome.add(lblNewLabel_4);

		JPanel panelAvailble = new JPanel();
		panelAvailble.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scheduleListForm = new ScheduleListForm();
				middleScrollPane.setViewportView(scheduleListForm);
				resetSideMenuColor();
				panSchedule.setBackground(new Color(146, 169, 189));
			}
		});
		panelAvailble.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelAvailble.setLayout(null);
		panelAvailble.setBackground(new Color(60, 179, 113));
		panelAvailble.setBounds(463, 205, 250, 120);
		panelHome.add(panelAvailble);

		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2
				.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-view-schedule-100.png")));
		lblNewLabel_2_2.setBounds(0, 0, 114, 120);
		panelAvailble.add(lblNewLabel_2_2);

		lblAvailable = new JLabel("20");
		lblAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailable.setForeground(Color.WHITE);
		lblAvailable.setFont(new Font("Dialog", Font.BOLD, 25));
		lblAvailable.setBounds(150, 27, 75, 35);
		panelAvailble.add(lblAvailable);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(127, 0, 4, 120);
		panelAvailble.add(separator_1);

		JLabel lblSchedules = new JLabel("Schedules");
		lblSchedules.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchedules.setForeground(Color.WHITE);
		lblSchedules.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSchedules.setBounds(126, 66, 124, 35);
		panelAvailble.add(lblSchedules);

		JLabel lblNewLabel_4_1 = new JLabel("Available Schedule");
		lblNewLabel_4_1.setForeground(new Color(148, 0, 211));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_4_1.setBounds(463, 335, 250, 40);
		panelHome.add(lblNewLabel_4_1);

		JPanel panelEquip = new JPanel();
		panelEquip.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				equipmentListForm = new EquipmentListForm();
				middleScrollPane.setViewportView(equipmentListForm);
				resetSideMenuColor();
			}
		});
		panelEquip.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelEquip.setLayout(null);
		panelEquip.setBackground(new Color(110, 133, 183));
		panelEquip.setBounds(813, 205, 250, 120);
		panelHome.add(panelEquip);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-data-setting-100.png")));
		lblNewLabel_3_1.setBounds(0, 0, 112, 120);
		panelEquip.add(lblNewLabel_3_1);

		lblEquipment = new JLabel("20");
		lblEquipment.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipment.setForeground(Color.WHITE);
		lblEquipment.setFont(new Font("Dialog", Font.BOLD, 25));
		lblEquipment.setBounds(151, 22, 75, 35);
		panelEquip.add(lblEquipment);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(128, 0, 4, 120);
		panelEquip.add(separator_2);

		JLabel lblMachines = new JLabel("Machines");
		lblMachines.setHorizontalAlignment(SwingConstants.CENTER);
		lblMachines.setForeground(Color.WHITE);
		lblMachines.setFont(new Font("Dialog", Font.BOLD, 17));
		lblMachines.setBounds(126, 64, 124, 35);
		panelEquip.add(lblMachines);

		JLabel lblNewLabel_4_2 = new JLabel("Equipment");
		lblNewLabel_4_2.setForeground(new Color(153, 50, 204));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_4_2.setBounds(813, 335, 250, 40);
		panelHome.add(lblNewLabel_4_2);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/gymCutout.png")));
		lblNewLabel_6.setBounds(205, 396, 712, 282);
		panelHome.add(lblNewLabel_6);
		
		JLabel lblLogin = new JLabel("LoginUser");
		lblLogin.setForeground(new Color(75, 0, 130));
		lblLogin.setBounds(935, 0, 139, 40);
		panelHome.add(lblLogin);
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 17));
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setText("Login");
		lblLogin.setText(String.valueOf(CurrentUserHolder.getCurrentUser().getName()));
		
		JLabel lblLogin_1 = new JLabel("");
		lblLogin_1.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-male-user-35 (1).png")));
		lblLogin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLogin_1.setBounds(1088, 0, 40, 40);
		panelHome.add(lblLogin_1);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(0, 0, 1400, 45);
		panelHeader.setBackground(new Color(146, 169, 189));
		frameMain.getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

		JLabel lblLogout = new JLabel("");
		lblLogout.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogout.setForeground(new Color(0, 0, 255));
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure, you want to close?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		lblLogout.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-power-button-40.png")));
		lblLogout.setBounds(1312, 0, 44, 45);
		panelHeader.add(lblLogout);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameMain.setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon(MainForm.class.getResource("/views/images/icons8-minimize-window-37.png")));
		lblNewLabel_3.setBounds(1273, 0, 37, 45);
		panelHeader.add(lblNewLabel_3);
	}

	public void resetSideMenuColor() {
		panHome.setBackground(new Color(110, 133, 183));
		panMemberList.setBackground(new Color(110, 133, 183));
		panStaffList.setBackground(new Color(110, 133, 183));
		panWorkoutPlan.setBackground(new Color(110, 133, 183));
		panSchedule.setBackground(new Color(110, 133, 183));
		panRegisterMember.setBackground(new Color(110, 133, 183));
		panWorkout.setBackground(new Color(110, 133, 183));
		panAttendance.setBackground(new Color(110, 133, 183));

	}
}
