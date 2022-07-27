package views;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Schedule;
import models.ScheduleMember;
import services.MemberService;
import services.ScheduleMemberService;
import services.ScheduleService;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ScheduledMemberForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JTable tableScheduledMember;
	private ScheduleMemberService scheduleMemberService;
	private ScheduleMember scheduleMember;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<ScheduleMember> originalScheduleMemberList = new ArrayList<>();
	private List<ScheduleMember> scheduleMemberList = new ArrayList<>();
	//private Schedule schedule;
	private ScheduleService scheduleService;
	private int availableMember;
	private int scheduleId;
	private JPopupMenu popupMenu;
	private JMenuItem regMemberMenuItem, printMenuItem;
	private Long stdatemili;
	private Date startDate, endDate;

	private void initializeDependency() {
		this.scheduleMemberService = new ScheduleMemberService();
		this.scheduleService = new ScheduleService();
		new MemberService();
		//schedule = new Schedule();
	}

	/**
	 * Create the panel.
	 */
	public ScheduledMemberForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllScheduleMembers(Optional.empty());
	}
	public ScheduledMemberForm(ScheduleMember scheduleMember) {
		//this.scheduleMember = scheduleMember;
		
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllScheduleMembers(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("Schedule Member Id");
		dtm.addColumn("Member Name");
		dtm.addColumn("Schedule Name");
		dtm.addColumn("Type of Body");
		dtm.addColumn("Gender");
		dtm.addColumn("Fees");
		dtm.addColumn("Play Hours");
		dtm.addColumn("Start Join Date");
		dtm.addColumn("End Date");
		dtm.addColumn("Day Left");

		this.tableScheduledMember.setModel(dtm);
		tableScheduledMember.setSelectionBackground(new Color(146, 169, 189));
		tableScheduledMember.setSelectionForeground(Color.white);
		tableScheduledMember.setRowHeight(30);
		tableScheduledMember.setBackground(Color.white);
		tableScheduledMember.setGridColor(new Color(110, 133, 183));
		tableScheduledMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableScheduledMember.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}

	protected void loadAllScheduleMembers(Optional<List<ScheduleMember>> optionalScheduleMember) {
		this.dtm = (DefaultTableModel) this.tableScheduledMember.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.originalScheduleMemberList = this.scheduleMemberService.findAllScheduleMembers();
		this.scheduleMemberList = optionalScheduleMember.orElseGet(() -> originalScheduleMemberList);

		scheduleMemberList.forEach(pd -> {
			Object[] row = new Object[10];
			row[0] = pd.getScheduleMember_id();
			row[1] = pd.getMember().getName();
			row[2] = pd.getSchedule().getSchedule_name();
			row[3] = pd.getSchedule().getTypeOfBody();
			row[4] = pd.getSchedule().getGender();
			row[5] = pd.getSchedule().getAmount();
			row[6] = pd.getSchedule().getTime();
			row[7] = pd.getScheduleJoinDate();
			row[8] = pd.getExpireDate();
			/// To Calculate Day Left Daily ////
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd").parse(pd.getScheduleJoinDate());
				endDate = new SimpleDateFormat("yyyy-MM-dd").parse(pd.getExpireDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date today = new Date();
			if (today.compareTo(startDate) > 0) {
				stdatemili = (endDate.getTime() - today.getTime());
			} else {
				stdatemili = (endDate.getTime() - startDate.getTime());
			}
			int diff = (int) TimeUnit.DAYS.convert(stdatemili, TimeUnit.MILLISECONDS);
			pd.setDayLeft(diff);
			row[9] = pd.getDayLeft();
			//// Update in Database
			scheduleMemberService.updateScheduleMemberDayLeft(String.valueOf(pd.getScheduleMember_id()),pd);
			///// Delete Expire Member
			if(diff < 1) {
				scheduleMemberService.deleteScheduleMemberExipired(pd);
				
				Schedule schedule = pd.getSchedule();
				availableMember= schedule.getAvailabeMember();
				availableMember += 1;
				schedule.setAvailabeMember(availableMember);
				scheduleService.updateAvailableMember(String.valueOf(pd.getSchedule().getSchedule_id()), schedule);
				
			}
			dtm.addRow(row);
		});

		this.tableScheduledMember.setModel(dtm);

	}

	private void initialize() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JLabel lblTitle = new JLabel("Scheduled Member List");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(72, 61, 139));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitle.setBounds(404, 32, 340, 47);
		add(lblTitle);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBorder(new TitledBorder(

				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),

				"Search Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(147, 112, 219)));
		searchPanel.setBackground(new Color(230, 230, 250));
		searchPanel.setBounds(79, 113, 1010, 119);
		add(searchPanel);

		txtSearch = new JTextField();
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search By Member Name")) {
					txtSearch.setText("");
					txtSearch.setForeground(new Color(72, 61, 139));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().equals("")) {
					txtSearch.setText("Search By Member Name");
					txtSearch.setForeground(new Color(72, 61, 139));
				}
			}
		});
		txtSearch.setText("Search By Member Name");
		txtSearch.setForeground(new Color(65, 105, 225));
		txtSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setBounds(527, 45, 268, 40);
		searchPanel.add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				loadAllScheduleMembers(Optional.of(originalScheduleMemberList.stream()
						.filter(t -> t.getMember().getName().toLowerCase().startsWith(keyword.toLowerCase()))
						.collect(Collectors.toList())));
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch.setBackground(new Color(110, 133, 183));
		btnSearch.setBounds(844, 45, 120, 40);
		searchPanel.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 279, 1004, 319);
		add(scrollPane);

		tableScheduledMember = new JTable();
		scrollPane.setViewportView(tableScheduledMember);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///// Available Member of schedule
				if (scheduleMember != null) {
					ScheduledMemberEditForm sMemberEditForm = new ScheduledMemberEditForm(scheduleMember);
					sMemberEditForm.frameScheduledMemberEdit.setVisible(true);
					sMemberEditForm.calculateDays();
					loadAllScheduleMembers(Optional.empty());
				} else {
					JOptionPane.showMessageDialog(null, "Select a member!");
				}
			}
		});

		// Add Popup Menu
		popupMenu = new JPopupMenu();
		regMemberMenuItem = new JMenuItem("Detail");
		regMemberMenuItem.setBackground(new Color(224, 255, 255));
		popupMenu.add(regMemberMenuItem);
		popupMenu.setBorder(getBorder());
		
		printMenuItem = new JMenuItem("Print");
		printMenuItem.setBackground(new Color(224, 255, 255));
		popupMenu.add(printMenuItem);
		popupMenu.setBorder(getBorder());

		// sets the popup menu for the table
		tableScheduledMember.setComponentPopupMenu(popupMenu);
		regMemberMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (scheduleMember != null) {
					ScheduleMemberPrintVoucher regPrintVoucher = new ScheduleMemberPrintVoucher(scheduleMember);
//					window.frameDetail.setUndecorated(true);
					regPrintVoucher.frameDetail.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Select a Member!");
				}
			}
		});
		
		printMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (scheduleMember != null) {
					/// Print as a PDF
					PrinterJob job = PrinterJob.getPrinterJob();
					job.setJobName("Print Voucher");
					
					job.setPrintable(new Printable() {
						
						@Override
						public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException {
							pf.setOrientation(PageFormat.PORTRAIT);
							if(pageNum > 0) {
								return Printable.NO_SUCH_PAGE;
							}
							Graphics2D g2 = (Graphics2D)pg;
							g2.translate(pf.getImageableX(), pf.getImageableY());
							g2.scale(1, 1);
							ScheduleMemberPrintVoucher window = new ScheduleMemberPrintVoucher(scheduleMember);
							window.frameDetail.setVisible(true);
							window.frameDetail.print(g2);
							window.frameDetail.setVisible(false);
							return Printable.PAGE_EXISTS;
						}
					});
					
					boolean ok = job.printDialog();
					if(ok) {
						try {
							job.print();
						}catch(PrinterException ex){
							ex.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a Member!");
				}
			}
		});

		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(110, 133, 183));
		btnUpdate.setBounds(969, 639, 120, 40);
		add(btnUpdate);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduledMemberEditForm sMemberEditForm = new ScheduledMemberEditForm();
				sMemberEditForm.frameScheduledMemberEdit.setVisible(true);
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAdd.setBackground(new Color(60, 179, 113));
		btnAdd.setBounds(805, 639, 120, 40);
		add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Register Member",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {

					/// Update Schedule Available Member
					
					System.out.println(scheduleMember);
					
					Schedule schedule = scheduleMember.getSchedule();
					availableMember= schedule.getAvailabeMember();
					availableMember += 1;
					schedule.setAvailabeMember(availableMember);
					scheduleService.updateAvailableMember(String.valueOf(scheduleId), schedule);
					
					//// Delete Schedule Member
					scheduleMemberService.deleteScheduleMember(String.valueOf(scheduleMember.getScheduleMember_id()),
							scheduleMember);
					
					loadAllScheduleMembers(Optional.empty());
					scheduleMember = null;
					JOptionPane.showMessageDialog(null, "Deleted");
				} else if (result == JOptionPane.NO_OPTION) {
					loadAllScheduleMembers(Optional.empty());
					scheduleMember = null;
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDelete.setBackground(new Color(178, 34, 34));
		btnDelete.setBounds(639, 639, 120, 40);
		add(btnDelete);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllScheduleMembers(Optional.empty());
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setBounds(472, 639, 120, 40);
		add(btnRefresh);

		this.tableScheduledMember.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableScheduledMember.getSelectionModel().isSelectionEmpty()) {
				String scheduleMemberId = tableScheduledMember.getValueAt(tableScheduledMember.getSelectedRow(), 0)
						.toString();
				scheduleMember = scheduleMemberService.findById(scheduleMemberId);
				/// Get Id
				scheduleId = scheduleMember.getSchedule().getSchedule_id();
				/// Get Schedule
//				schedule = scheduleService.findAvailableMemberById(scheduleId);
				/// Get Available Members
//				availableMember = schedule.getAvailabeMember();

			}

		});

	}
}
