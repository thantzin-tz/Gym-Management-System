package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.ScheduleMember;
import services.ScheduleMemberService;
import utils.CurrentUserHolder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class ScheduleMemberPrintVoucher {

	protected JFrame frameDetail;
	private ScheduleMember scheduleMember;
	protected JPanel panelPrint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleMemberPrintVoucher window = new ScheduleMemberPrintVoucher();
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
	 * 
	 * @wbp.parser.entryPoint
	 */
	public ScheduleMemberPrintVoucher() {
		initialize();
	}

	public ScheduleMemberPrintVoucher(ScheduleMember scheduleMember) {
		this.scheduleMember = scheduleMember;
		initialize();
		this.initializeDependency();
	}

	private void initializeDependency() {
		new ScheduleMemberService();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameDetail = new JFrame();
		frameDetail.setBackground(new Color(238, 238, 238));
		frameDetail.setResizable(false);
		frameDetail.setTitle("About Voucher");
		frameDetail.setBounds(450, 20, 577, 800);
		frameDetail.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		panelPrint = new JPanel();
		panelPrint.setBackground(new Color(230, 230, 250));
		frameDetail.getContentPane().add(panelPrint, BorderLayout.CENTER);
		panelPrint.setLayout(null);

		JLabel lblMemberName = new JLabel("Member Name");
		lblMemberName.setForeground(new Color(147, 112, 219));
		lblMemberName.setBounds(80, 214, 122, 28);
		lblMemberName.setFont(new Font("Dialog", Font.BOLD, 15));
		panelPrint.add(lblMemberName);

		JLabel lblName = new JLabel(scheduleMember != null ? scheduleMember.getMember().getName() : "Name");
		lblName.setForeground(new Color(147, 112, 219));
		lblName.setBounds(353, 214, 180, 28);
		lblName.setFont(new Font("Dialog", Font.BOLD, 15));
		panelPrint.add(lblName);

		JLabel lbl4_1_3_1 = new JLabel(":");
		lbl4_1_3_1.setForeground(new Color(147, 112, 219));
		lbl4_1_3_1.setBounds(238, 157, 60, 28);
		lbl4_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_3_1.setFont(new Font("Dialog", Font.BOLD, 15));
		panelPrint.add(lbl4_1_3_1);

		JLabel lblPackName = new JLabel("Package Name ");
		lblPackName.setForeground(new Color(147, 112, 219));
		lblPackName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPackName.setBounds(80, 280, 122, 28);
		panelPrint.add(lblPackName);

		JLabel lbl4_1_3_1_1 = new JLabel(":");
		lbl4_1_3_1_1.setForeground(new Color(147, 112, 219));
		lbl4_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_3_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl4_1_3_1_1.setBounds(238, 214, 60, 28);
		panelPrint.add(lbl4_1_3_1_1);

		JLabel lblStartJoinDate = new JLabel("Start Join Date");
		lblStartJoinDate.setForeground(new Color(147, 112, 219));
		lblStartJoinDate.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStartJoinDate.setBounds(80, 335, 122, 28);
		panelPrint.add(lblStartJoinDate);

		JLabel lbl4_1_3_1_1_1 = new JLabel(":");
		lbl4_1_3_1_1_1.setForeground(new Color(147, 112, 219));
		lbl4_1_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_3_1_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl4_1_3_1_1_1.setBounds(238, 280, 60, 28);
		panelPrint.add(lbl4_1_3_1_1_1);

		JLabel lblLastDate = new JLabel("Last Date");
		lblLastDate.setForeground(new Color(147, 112, 219));
		lblLastDate.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLastDate.setBounds(80, 392, 122, 28);
		panelPrint.add(lblLastDate);

		JLabel lbl4_1_3_1_1_1_1 = new JLabel(":");
		lbl4_1_3_1_1_1_1.setForeground(new Color(147, 112, 219));
		lbl4_1_3_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_3_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl4_1_3_1_1_1_1.setBounds(238, 335, 60, 28);
		panelPrint.add(lbl4_1_3_1_1_1_1);

		JLabel lblFees = new JLabel("Fees");
		lblFees.setForeground(new Color(147, 112, 219));
		lblFees.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFees.setBounds(80, 463, 122, 28);
		panelPrint.add(lblFees);

		JLabel lbl4_1_3_1_1_1_1_1 = new JLabel(":");
		lbl4_1_3_1_1_1_1_1.setForeground(new Color(147, 112, 219));
		lbl4_1_3_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_3_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl4_1_3_1_1_1_1_1.setBounds(238, 392, 60, 28);
		panelPrint.add(lbl4_1_3_1_1_1_1_1);

		JLabel lblPackage = new JLabel(
				scheduleMember != null ? scheduleMember.getSchedule().getSchedule_name() : "Package Name");
		lblPackage.setForeground(new Color(147, 112, 219));
		lblPackage.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPackage.setBounds(353, 280, 180, 28);
		panelPrint.add(lblPackage);

		JLabel lblStartdate = new JLabel(scheduleMember != null ? scheduleMember.getScheduleJoinDate() : "Start Date");
		lblStartdate.setForeground(new Color(147, 112, 219));
		lblStartdate.setFont(new Font("Dialog", Font.BOLD, 15));
		lblStartdate.setBounds(353, 335, 180, 28);
		panelPrint.add(lblStartdate);

		JLabel lblLastdate = new JLabel(scheduleMember != null ? scheduleMember.getExpireDate() : "End Date");
		lblLastdate.setForeground(new Color(147, 112, 219));
		lblLastdate.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLastdate.setBounds(353, 392, 180, 28);
		panelPrint.add(lblLastdate);

		JLabel lblFee = new JLabel(scheduleMember != null ? String.valueOf(scheduleMember.getSchedule().getAmount()) : "Fees");
		lblFee.setForeground(new Color(147, 112, 219));
		lblFee.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFee.setBounds(353, 463, 180, 28);
		panelPrint.add(lblFee);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 561, 109);
		panel_1.setBackground(new Color(110, 133, 183));
		panelPrint.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblStaffDetail = new JLabel("WORLD-39 GYM");
		lblStaffDetail.setBackground(new Color(255, 255, 255));
		lblStaffDetail.setForeground(new Color(255, 255, 255));
		lblStaffDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaffDetail.setFont(new Font("Dialog", Font.BOLD, 20));
		lblStaffDetail.setBounds(179, 0, 184, 32);
		panel_1.add(lblStaffDetail);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 139, 139));
		separator.setBounds(0, 102, 661, 2);
		panel_1.add(separator);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(ScheduleMemberPrintVoucher.class.getResource("/views/images/Gym-Voucherlogo.png")));
		lblNewLabel.setBounds(12, 0, 80, 109);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Zaya Theikdi 2nd St., Hlaing Tsp, Yangon");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Courier New", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(116, 58, 335, 19);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("09-777645647, 09-876345421, 09-450234987 ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Courier New", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(117, 81, 345, 19);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("www.world39.com");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Courier New", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(104, 34, 335, 19);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(
				new ImageIcon(ScheduleMemberPrintVoucher.class.getResource("/views/images/Gym-Voucherlogo.png")));
		lblNewLabel_2.setBounds(469, 0, 80, 109);
		panel_1.add(lblNewLabel_2);

		JLabel lbl4_1_3_1_1_1_1_1_1 = new JLabel(":");
		lbl4_1_3_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4_1_3_1_1_1_1_1_1.setForeground(new Color(147, 112, 219));
		lbl4_1_3_1_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl4_1_3_1_1_1_1_1_1.setBounds(238, 463, 60, 28);
		panelPrint.add(lbl4_1_3_1_1_1_1_1_1);

		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setForeground(new Color(147, 112, 219));
		lblMemberId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMemberId.setBounds(80, 157, 122, 28);
		panelPrint.add(lblMemberId);

		JLabel lblId = new JLabel(
				scheduleMember != null ? String.valueOf(scheduleMember.getScheduleMember_id()) : "ID");
		lblId.setForeground(new Color(147, 112, 219));
		lblId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblId.setBounds(353, 157, 180, 28);
		panelPrint.add(lblId);
		
		JLabel lblApprovedBy = new JLabel("Approved By.");
		lblApprovedBy.setForeground(new Color(147, 112, 219));
		lblApprovedBy.setFont(new Font("Dialog", Font.BOLD, 15));
		lblApprovedBy.setBounds(285, 588, 122, 28);
		panelPrint.add(lblApprovedBy);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(408, 614, 141, 2);
		panelPrint.add(separator_1);
		
		JLabel lblAdmin = new JLabel(scheduleMember != null ? String.valueOf(CurrentUserHolder.getCurrentUser().getName()) : "AdminName");
		lblAdmin.setForeground(new Color(147, 112, 219));
		lblAdmin.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAdmin.setBounds(410, 588, 151, 28);
		panelPrint.add(lblAdmin);
	}
}
