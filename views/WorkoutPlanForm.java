package views;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.WorkoutPlan;
import services.WorkoutPlanService;
import services.WorkoutService;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WorkoutPlanForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private WorkoutPlanService workoutPlanService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable tableWorkoutPlan;
	private JScrollPane scrollPane;
	private List<WorkoutPlan> workoutPlanList = new ArrayList<>();
	private List<WorkoutPlan> filteredworkoutPlanList = new ArrayList<>();
	private WorkoutPlan workoutPlan;

	private void initializeDependency() {
		this.workoutPlanService = new WorkoutPlanService();
		new WorkoutService();
	}

	/**
	 * Create the panel.
	 */
	public WorkoutPlanForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllWorkoutDetails(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("Workout Id");
		dtm.addColumn("Workout Name");
		dtm.addColumn("Body Part");
		dtm.addColumn("Set Times");
		dtm.addColumn("Gender");
		dtm.addColumn("Workout Level");
		dtm.addColumn("Description");
		this.tableWorkoutPlan.setModel(dtm);
		tableWorkoutPlan.setSelectionBackground(new Color(146, 169, 189));
		tableWorkoutPlan.setSelectionForeground(Color.white);
		tableWorkoutPlan.setRowHeight(30);
		tableWorkoutPlan.setBackground(Color.white);
		tableWorkoutPlan.setGridColor(new Color(110, 133, 183));
		tableWorkoutPlan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableWorkoutPlan.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}

	private void loadAllWorkoutDetails(Optional<List<WorkoutPlan>> optionalWorkouts) {
		this.dtm = (DefaultTableModel) this.tableWorkoutPlan.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.workoutPlanList = this.workoutPlanService.findAllWorkoutPlans();

		this.filteredworkoutPlanList = optionalWorkouts.orElseGet(() -> this.workoutPlanList).stream()
				.collect(Collectors.toList());

		this.filteredworkoutPlanList.forEach(pd -> {
			Object[] row = new Object[7];
			row[0] = pd.getWorkoutplan_id();
			row[1] = pd.getWorkout().getWorkoutName();
			row[2] = pd.getWorkout().getBodypartName();
			row[3] = pd.getTimer();
			row[4] = pd.getGender();
			row[5] = pd.getLevel();
			row[6] = pd.getDesc();

			dtm.addRow(row);
		});

		this.tableWorkoutPlan.setModel(dtm);
	}

	private void initialize() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Search Workout Plan", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(123, 104, 238)));
		searchPanel.setBackground(new Color(230, 230, 250));
		searchPanel.setBounds(48, 115, 1051, 119);
		add(searchPanel);

		txtSearch = new JTextField("Search By Workout Name");
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search By Workout Name")) {
					txtSearch.setText("");
					txtSearch.setForeground(new Color(123, 104, 238));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().equals("")) {
					txtSearch.setText("Search By Workout Name");
					txtSearch.setForeground(new Color(123, 104, 238));
				}
			}
		});
		txtSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSearch.setText("Search By Workout Name");
		txtSearch.setForeground(new Color(123, 104, 238));
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setColumns(10);
		txtSearch.setBounds(567, 45, 268, 40);
		searchPanel.add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();

				loadAllWorkoutDetails(
						Optional.of(
								workoutPlanList.stream()
										.filter(b -> b.getWorkout().getWorkoutName().toLowerCase(Locale.ROOT)
												.startsWith(keyword.toLowerCase(Locale.ROOT)))
										.collect(Collectors.toList())));
			}

		});
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(110, 133, 183));
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch.setBounds(876, 45, 120, 40);
		searchPanel.add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 264, 1051, 328);
		add(scrollPane);

		tableWorkoutPlan = new JTable();
		scrollPane.setViewportView(tableWorkoutPlan);
		scrollPane.setBackground(new Color(224, 255, 255));
		scrollPane.setForeground(Color.white);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Workout Plan",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					workoutPlanService.deleteWorkoutPlan(String.valueOf(workoutPlan.getWorkoutplan_id()), workoutPlan);
					loadAllWorkoutDetails(Optional.empty());
					workoutPlan = null;
					JOptionPane.showMessageDialog(null, "Deleted");
				} else if (result == JOptionPane.NO_OPTION) {
					loadAllWorkoutDetails(Optional.empty());
					workoutPlan = null;
				}
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(220, 20, 60));
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDelete.setBounds(654, 638, 120, 40);
		add(btnDelete);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkoutPlanEditForm workoutPlanEditForm = new WorkoutPlanEditForm();
				workoutPlanEditForm.workoutPlanEditframe.setVisible(true);
			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(60, 179, 113));
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAdd.setBounds(821, 638, 120, 40);
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (workoutPlan != null) {
					WorkoutPlanEditForm workoutPlanEditForm = new WorkoutPlanEditForm(workoutPlan);
					workoutPlanEditForm.workoutPlanEditframe.setVisible(true);
					loadAllWorkoutDetails(Optional.empty());
				} else {
					JOptionPane.showMessageDialog(null, "Select a Workout Plan!");
				}
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(110, 133, 183));
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate.setBounds(979, 638, 120, 40);
		add(btnUpdate);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllWorkoutDetails(Optional.empty());
				txtSearch.setText("");
			}
		});
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBounds(494, 638, 120, 40);
		add(btnRefresh);
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));

		JLabel lblWorkoutList = new JLabel("Workout Plan List");
		lblWorkoutList.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutList.setForeground(new Color(72, 61, 139));
		lblWorkoutList.setFont(new Font("Dialog", Font.BOLD, 22));
		lblWorkoutList.setBounds(416, 32, 340, 47);
		add(lblWorkoutList);

		this.tableWorkoutPlan.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableWorkoutPlan.getSelectionModel().isSelectionEmpty()) {

				String workoutPlanId = tableWorkoutPlan.getValueAt(tableWorkoutPlan.getSelectedRow(), 0).toString();

				workoutPlan = workoutPlanService.findById(workoutPlanId);
			}
		});

	}
}
