package views;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Schedule;
import services.ScheduleService;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ScheduleListForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private Schedule schedule;
	private DefaultTableModel dtm = new DefaultTableModel();
	private ScheduleService scheduleService;
	private List<Schedule> originalScheduleList = new ArrayList<>();
	private List<Schedule> scheduleList = new ArrayList<>();
	private JTable tableSchedule;
	private JScrollPane scrollPane;

	private void initializeDependency() {
		this.scheduleService = new ScheduleService();
	}

	/**
	 * Create the panel.
	 */
	public ScheduleListForm() {
		initialize();
		this.setTableDesign();
		initializeDependency();
		this.loadAllSchedules(Optional.empty());
	}
	public ScheduleListForm(Schedule schedule) {
		initialize();
		this.setTableDesign();
		initializeDependency();
		this.loadAllSchedules(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Schedule Name");
		dtm.addColumn("Schedule Type");
		dtm.addColumn("Trainer Name");
		dtm.addColumn("Play Time");
		dtm.addColumn("Type of Body");
		dtm.addColumn("Gender");
		dtm.addColumn("Fees");
		dtm.addColumn("Available Members");

		this.tableSchedule.setModel(dtm);
		tableSchedule.setSelectionBackground(new Color(146, 169, 189));
		tableSchedule.setSelectionForeground(Color.white);
		tableSchedule.setRowHeight(30);
		tableSchedule.setBackground(Color.white);
		tableSchedule.setGridColor(new Color(110, 133, 183));
		tableSchedule.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableSchedule.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}

	protected void loadAllSchedules(Optional<List<Schedule>> optionalSchedule) {
		this.dtm = (DefaultTableModel) this.tableSchedule.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.originalScheduleList = this.scheduleService.findAllSchedule();
		this.scheduleList = optionalSchedule.orElseGet(() -> originalScheduleList);

		scheduleList.forEach(s -> {
			Object[] row = new Object[9];
			row[0] = s.getSchedule_id();
			row[1] = s.getSchedule_name();
			row[2] = s.getSchedule_type();
			row[3] = s.getStaff().getName();
			row[4] = s.getTime();
			row[5] = s.getTypeOfBody();
			row[6] = s.getGender();
			row[7] = s.getAmount();
			row[8] = s.getAvailabeMember();
			dtm.addRow(row);
		});

		this.tableSchedule.setModel(dtm);

	}

	private void initialize() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Search Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(147, 112, 219)));
		searchPanel.setBackground(new Color(230, 230, 250));
		searchPanel.setBounds(62, 116, 1037, 119);
		add(searchPanel);

		txtSearch = new JTextField("Search By Schedule Name");
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search By Schedule Name")) {
					txtSearch.setText("");
					txtSearch.setForeground(new Color(65, 105, 225));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().equals("")) {
					txtSearch.setText("Search By Schedule Name");
					txtSearch.setForeground(new Color(65, 105, 225));
				}
			}
		});
		txtSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSearch.setForeground(new Color(65, 105, 225));
		txtSearch.setText("Search By Schedule Name");
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setColumns(10);
		txtSearch.setBounds(567, 45, 268, 40);
		searchPanel.add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				loadAllSchedules(Optional.of(originalScheduleList.stream()
						.filter(t -> t.getSchedule_name().toLowerCase().startsWith(keyword.toLowerCase()))
						.collect(Collectors.toList())));
			}
		});
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(110, 133, 183));
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch.setBounds(876, 45, 120, 40);
		searchPanel.add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 272, 1037, 326);
		add(scrollPane);

		tableSchedule = new JTable();
		scrollPane.setViewportView(tableSchedule);
		scrollPane.setBackground(new Color(230, 230, 250));
		scrollPane.setForeground(Color.white);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (schedule != null) {
					int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Schedule",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						scheduleService.deleteSchedule(String.valueOf(schedule.getSchedule_id()), schedule);
						loadAllSchedules(Optional.empty());
						schedule = null;
						JOptionPane.showMessageDialog(null, "Deleted");
					} else if (result == JOptionPane.NO_OPTION) {
						loadAllSchedules(Optional.empty());
						schedule = null;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please, select a Schedule.");
				}
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(178, 34, 34));
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDelete.setBounds(678, 645, 120, 40);
		add(btnDelete);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// Add Schedule Edit Form
				ScheduleEditForm scheduleEidtForm = new ScheduleEditForm();
				scheduleEidtForm.frameScheduleEdit.setVisible(true);
			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(60, 179, 113));
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAdd.setBounds(829, 645, 120, 40);
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (schedule != null) {
					/// Add Schedule Edit Form
					ScheduleEditForm scheduleEditForm = new ScheduleEditForm(schedule);
					scheduleEditForm.frameScheduleEdit.setVisible(true);
					loadAllSchedules(Optional.empty());
				} else {
					JOptionPane.showMessageDialog(null, "Select a member!");
				}
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(110, 133, 183));
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate.setBounds(979, 645, 120, 40);
		add(btnUpdate);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllSchedules(Optional.empty());
			}
		});
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBounds(520, 645, 120, 40);
		add(btnRefresh);
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));

		JLabel lblTitle = new JLabel("Schedule List");
		lblTitle.setForeground(new Color(72, 61, 139));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(406, 40, 340, 47);
		add(lblTitle);

		this.tableSchedule.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableSchedule.getSelectionModel().isSelectionEmpty()) {
				String id = tableSchedule.getValueAt(tableSchedule.getSelectedRow(), 0).toString();
				schedule = scheduleService.findScheduleById(id);
			}
		});

	}
}
