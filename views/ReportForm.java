package views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Report;
import services.ReportService;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class ReportForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JTable tableReport;
	private ReportService reportService;
	private List<Report> reportList = new ArrayList<>();
	private DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	private Report report;
	private JDateChooser searchDate;

	/*
	 * private List<Schedule> scheduleList = new ArrayList<>(); private
	 * List<Members> memberList = new ArrayList<>();
	 */
	private List<Report> filteredReportList = new ArrayList<>();

	private void initializeDependency() {
		this.reportService = new ReportService();
	}

	public ReportForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllReportDetails(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("Report ID ");
		dtm.addColumn("Member Name");
		dtm.addColumn("Schedule Name");
		dtm.addColumn("Workout Name");
		dtm.addColumn("Set Time");
		dtm.addColumn("Level");
		dtm.addColumn("Gender");
		dtm.addColumn("Attendance Date");
		dtm.addColumn("Weight");
		dtm.addColumn("Height");
		dtm.addColumn("BMI ");
		dtm.addColumn("Play Hour");
		dtm.addColumn("Remark");

		this.tableReport.setModel(dtm);
		tableReport.setSelectionBackground(new Color(146, 169, 189));
		tableReport.setSelectionForeground(Color.white);
		tableReport.setRowHeight(30);
		tableReport.setBackground(Color.white);
		tableReport.setGridColor(new Color(110, 133, 183));
		tableReport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableReport.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}

	private void loadAllReportDetails(Optional<List<Report>> optionalWorkouts) {
		this.dtm = (DefaultTableModel) this.tableReport.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();
		this.reportList = this.reportService.findAllReport();
		this.filteredReportList = optionalWorkouts.orElseGet(() -> this.reportList).stream()
				.collect(Collectors.toList());
		this.filteredReportList.forEach(pd -> {

			Object[] row = new Object[13];
//			System.out.print("hi" +  pd.getWorkoutPlan().getLevel());
			row[0] = pd.getReport_id();
			row[1] = pd.getScheduleMember().getMember().getName();
			row[2] = pd.getScheduleMember().getSchedule().getSchedule_name();
			row[3] = pd.getWorkoutPlan().getWorkout().getWorkoutName();
			row[4] = pd.getWorkoutPlan().getTimer();
			row[5] = pd.getWorkoutPlan().getLevel();
			row[6] = pd.getWorkoutPlan().getGender();
			row[7] = pd.getReportDate();
			row[8] = pd.getReportWeight();
			row[9] = pd.getReportHeight();
			row[10] = pd.getReportBMI();
			row[11] = pd.getPlayHour();
			row[12] = pd.getReportRemark();

			dtm.addRow(row);
		});

		this.tableReport.setModel(dtm);
	}

	/**
	 * Create the panel.
	 */
	private void initialize() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JLabel lblReportList = new JLabel("Attendance List");
		lblReportList.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportList.setForeground(new Color(72, 61, 139));
		lblReportList.setFont(new Font("Dialog", Font.BOLD, 22));
		lblReportList.setBounds(386, 34, 340, 47);
		add(lblReportList);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Search Member By Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(147, 112, 219)));
		searchPanel.setBackground(new Color(230, 230, 250));
		searchPanel.setBounds(555, 110, 524, 119);
		add(searchPanel);

		txtSearch = new JTextField("Search By Member Name");
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
		txtSearch.setForeground(new Color(72, 61, 139));
		txtSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setBounds(51, 43, 226, 40);
		searchPanel.add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String keyword = txtSearch.getText();

				loadAllReportDetails(
						Optional.of(reportList
								.stream().filter(b -> b.getScheduleMember().getMember().getName()
										.toLowerCase(Locale.ROOT).startsWith(keyword.toLowerCase(Locale.ROOT)))
								.collect(Collectors.toList())));
			}

		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch.setBackground(new Color(110, 133, 183));
		btnSearch.setBounds(365, 43, 120, 40);
		searchPanel.add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(new Color(230, 230, 250));
		scrollPane.setBounds(42, 266, 1037, 326);
		add(scrollPane);

		tableReport = new JTable();
		scrollPane.setViewportView(tableReport);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllReportDetails(Optional.empty());
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setBounds(500, 634, 120, 40);
		add(btnRefresh);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Staff",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					reportService.deleteReport(String.valueOf(report.getReport_id()), report);
					loadAllReportDetails(Optional.empty());
					report = null;
					JOptionPane.showMessageDialog(null, "Deleted");
				} else if (result == JOptionPane.NO_OPTION) {
					loadAllReportDetails(Optional.empty());
					report = null;
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDelete.setBackground(new Color(178, 34, 34));
		btnDelete.setBounds(658, 634, 120, 40);
		add(btnDelete);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportEditForm window = new ReportEditForm();
				window.frameReportEdit.setVisible(true);
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAdd.setBackground(new Color(60, 179, 113));
		btnAdd.setBounds(809, 634, 120, 40);
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (report != null) {
					ReportEditForm reportEditForm = new ReportEditForm(report);
					reportEditForm.frameReportEdit.setVisible(true);
					loadAllReportDetails(Optional.empty());
				} else {
					JOptionPane.showMessageDialog(null, "Select a member!");
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(110, 133, 183));
		btnUpdate.setBounds(959, 634, 120, 40);
		add(btnUpdate);

		JPanel searchPanel_1 = new JPanel();
		searchPanel_1.setLayout(null);
		searchPanel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search Member By Date", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(147, 112, 219)));
		searchPanel_1.setBackground(new Color(230, 230, 250));
		searchPanel_1.setBounds(42, 110, 482, 119);
		add(searchPanel_1);

		searchDate = new JDateChooser();
		searchDate.setFont(new Font("Calibri", Font.BOLD, 15));
		searchDate.setBackground(new Color(230, 230, 250));
		searchDate.setDateFormatString("yyyy-MM-dd");
		// To Make JDateChooser cannot edit
		searchDate.setDate(new Date());
		JTextFieldDateEditor editor = (JTextFieldDateEditor) searchDate.getDateEditor();
		editor.setEditable(false);
		searchDate.setBounds(49, 40, 205, 40);
		searchPanel_1.add(searchDate);

		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.setBounds(334, 40, 120, 40);
		searchPanel_1.add(btnSearch_1);
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(searchDate.getDate());
				loadAllReportDetails(Optional.of(
						reportList.stream().filter(b -> b.getReportDate().equals(date)).collect(Collectors.toList())));

			}
		});
		btnSearch_1.setForeground(Color.WHITE);
		btnSearch_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch_1.setBackground(new Color(110, 133, 183));

		this.tableReport.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableReport.getSelectionModel().isSelectionEmpty()) {
				String reportId = tableReport.getValueAt(tableReport.getSelectedRow(), 0).toString();
				report = reportService.findById(reportId);



			}

		});

	}
}
