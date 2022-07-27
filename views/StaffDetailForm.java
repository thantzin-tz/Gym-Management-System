package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Staff;
import services.StaffService;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;

public class StaffDetailForm {

	protected JFrame frameDetail;
	private Staff staff;
	private JLabel lblID,lblName,lblAge,lblPhone,lblAddress,lblEmail,lblGender,
	lblRole,lblUsername, lblSalary,lblStatus;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffDetailForm window = new StaffDetailForm();
//					window.frameDetail.setUndecorated(true);
					window.frameDetail.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffDetailForm() {
		initialize();
	}
	public StaffDetailForm(Staff staff) {
		this.staff = staff;
		initialize();
		this.initializeDependency();
	}
	
	private void initializeDependency() {
		new StaffService();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameDetail = new JFrame();
		frameDetail.setResizable(false);
		frameDetail.setTitle("About Staff");
		frameDetail.setBounds(450, 70, 663, 640);
		frameDetail.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		frameDetail.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(146, 169, 189));
		panel_1.setBounds(0, 0, 661, 104);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStffType = new JLabel(staff != null ? String.valueOf(staff.getRole()) : "");
		lblStffType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStffType.setForeground(new Color(255, 255, 255));
		lblStffType.setFont(new Font("Calibri", Font.BOLD, 17));
		lblStffType.setBounds(35, 55, 71, 32);
		panel_1.add(lblStffType);
		
		JLabel lblStaffName = new JLabel(staff != null ? staff.getName() : "");
		lblStaffName.setForeground(new Color(255, 255, 255));
		lblStaffName.setFont(new Font("Calibri", Font.BOLD, 17));
		lblStaffName.setBounds(166, 55, 174, 32);
		panel_1.add(lblStaffName);
		
		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 17));
		lblNewLabel_2.setBounds(104, 55, 50, 32);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblStaffDetail = new JLabel("Staff Details");
		lblStaffDetail.setBackground(new Color(255, 255, 255));
		lblStaffDetail.setForeground(new Color(255, 255, 255));
		lblStaffDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaffDetail.setFont(new Font("Dialog", Font.BOLD, 20));
		lblStaffDetail.setBounds(237, 25, 184, 32);
		panel_1.add(lblStaffDetail);
		
		JLabel lbl = new JLabel("ID");
		lbl.setForeground(new Color(255, 255, 255));
		lbl.setFont(new Font("Calibri", Font.BOLD, 17));
		lbl.setBounds(491, 55, 31, 32);
		panel_1.add(lbl);
		
		JLabel lblNewLabel_2_6 = new JLabel(":");
		lblNewLabel_2_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6.setFont(new Font("Calibri", Font.BOLD, 17));
		lblNewLabel_2_6.setBounds(521, 55, 31, 32);
		panel_1.add(lblNewLabel_2_6);
		
		JLabel lblStaffId = new JLabel(staff != null ? String.valueOf(staff.getStaff_id()) : "");
		lblStaffId.setForeground(new Color(255, 255, 255));
		lblStaffId.setFont(new Font("Calibri", Font.BOLD, 17));
		lblStaffId.setBounds(564, 55, 63, 32);
		panel_1.add(lblStaffId);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 139, 139));
		separator.setBounds(0, 102, 661, 2);
		panel_1.add(separator);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameDetail.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(536, 541, 91, 32);
		panel.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Personal Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel_2.setBackground(new Color(230, 230, 250));
		panel_2.setBounds(31, 127, 314, 220);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		lblID = new JLabel(staff != null ? String.valueOf(staff.getStaff_id()) : "Staff's ID");
		lblID.setBounds(170, 23, 107, 28);
		panel_2.add(lblID);
		lblID.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl1 = new JLabel("ID");
		lbl1.setBounds(26, 23, 65, 28);
		panel_2.add(lbl1);
		lbl1.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl2 = new JLabel("Name");
		lbl2.setBounds(26, 69, 107, 28);
		panel_2.add(lbl2);
		lbl2.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblName = new JLabel(staff != null ? staff.getName() : "Staff's Name");
		lblName.setBounds(170, 69, 120, 28);
		panel_2.add(lblName);
		lblName.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl3 = new JLabel("Age");
		lbl3.setBounds(26, 123, 107, 28);
		panel_2.add(lbl3);
		lbl3.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblAge = new JLabel(staff != null ? String.valueOf(staff.getAge()) : "Staff's Age");
		lblAge.setBounds(170, 123, 107, 28);
		panel_2.add(lblAge);
		lblAge.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl7 = new JLabel("Gender");
		lbl7.setBounds(26, 170, 107, 28);
		panel_2.add(lbl7);
		lbl7.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblGender = new JLabel(staff != null ? staff.getGender() : "Gender Type");
		lblGender.setBounds(170, 170, 117, 28);
		panel_2.add(lblGender);
		lblGender.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl4_1_3 = new JLabel(":");
		lbl4_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_3.setBounds(98, 23, 60, 28);
		panel_2.add(lbl4_1_3);
		
		JLabel lbl4_1_4 = new JLabel(":");
		lbl4_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_4.setBounds(98, 69, 60, 28);
		panel_2.add(lbl4_1_4);
		
		JLabel lbl4_1_5 = new JLabel(":");
		lbl4_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_5.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_5.setBounds(98, 123, 60, 28);
		panel_2.add(lbl4_1_5);
		
		JLabel lbl4_1_6 = new JLabel(":");
		lbl4_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_6.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_6.setBounds(98, 170, 60, 28);
		panel_2.add(lbl4_1_6);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Contact Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel_3.setBackground(new Color(230, 230, 250));
		panel_3.setBounds(31, 357, 598, 165);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lbl4 = new JLabel("Phone");
		lbl4.setBounds(115, 21, 107, 28);
		panel_3.add(lbl4);
		lbl4.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblPhone = new JLabel(staff != null ? staff.getPhone() : "Staff's Phone");
		lblPhone.setBounds(340, 21, 199, 28);
		panel_3.add(lblPhone);
		lblPhone.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl5 = new JLabel("Address");
		lbl5.setBounds(115, 62, 107, 28);
		panel_3.add(lbl5);
		lbl5.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblAddress = new JLabel(staff != null ? staff.getAddress() : "Staff's Address");
		lblAddress.setBounds(340, 62, 199, 28);
		panel_3.add(lblAddress);
		lblAddress.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl6 = new JLabel("Email");
		lbl6.setBounds(115, 114, 107, 28);
		panel_3.add(lbl6);
		lbl6.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblEmail = new JLabel(staff != null ? staff.getEmail() : "Staff's Email");
		lblEmail.setBounds(340, 114, 139, 28);
		panel_3.add(lblEmail);
		lblEmail.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl4_1 = new JLabel(":");
		lbl4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1.setBounds(230, 21, 60, 28);
		panel_3.add(lbl4_1);
		
		JLabel lbl4_1_1 = new JLabel(":");
		lbl4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_1.setBounds(230, 62, 60, 28);
		panel_3.add(lbl4_1_1);
		
		JLabel lbl4_1_2 = new JLabel(":");
		lbl4_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_2.setBounds(230, 114, 60, 28);
		panel_3.add(lbl4_1_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Other Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel_4.setBackground(new Color(230, 230, 250));
		panel_4.setBounds(357, 127, 272, 220);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lbl9 = new JLabel("UserName");
		lbl9.setBounds(22, 32, 107, 28);
		panel_4.add(lbl9);
		lbl9.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblUsername = new JLabel(staff != null ? staff.getUsername() : "Username");
		lblUsername.setBounds(142, 32, 107, 28);
		panel_4.add(lblUsername);
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl8 = new JLabel("Role");
		lbl8.setBounds(22, 70, 107, 28);
		panel_4.add(lbl8);
		lbl8.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblRole = new JLabel(staff != null ? String.valueOf(staff.getRole()) : "Role");
		lblRole.setBounds(142, 70, 107, 28);
		panel_4.add(lblRole);
		lblRole.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl10 = new JLabel("Salary");
		lbl10.setBounds(22, 119, 107, 28);
		panel_4.add(lbl10);
		lbl10.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblSalary = new JLabel(staff != null ? String.valueOf(staff.getSalary()) : "Salary");
		lblSalary.setBounds(142, 119, 107, 28);
		panel_4.add(lblSalary);
		lblSalary.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl11 = new JLabel("Status");
		lbl11.setBounds(22, 168, 107, 28);
		panel_4.add(lbl11);
		lbl11.setFont(new Font("Calibri", Font.BOLD, 15));
		
		lblStatus = new JLabel(staff != null ? (staff.isActive() ? "Active" : "Inactive") : "Status");
		lblStatus.setBounds(142, 168, 107, 28);
		panel_4.add(lblStatus);
		lblStatus.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lbl4_1_7 = new JLabel(":");
		lbl4_1_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_7.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_7.setBounds(86, 32, 60, 28);
		panel_4.add(lbl4_1_7);
		
		JLabel lbl4_1_8 = new JLabel(":");
		lbl4_1_8.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_8.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_8.setBounds(86, 70, 60, 28);
		panel_4.add(lbl4_1_8);
		
		JLabel lbl4_1_9 = new JLabel(":");
		lbl4_1_9.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_9.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_9.setBounds(86, 119, 60, 28);
		panel_4.add(lbl4_1_9);
		
		JLabel lbl4_1_10 = new JLabel(":");
		lbl4_1_10.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_10.setFont(new Font("Calibri", Font.BOLD, 15));
		lbl4_1_10.setBounds(86, 168, 60, 28);
		panel_4.add(lbl4_1_10);
			}
}
