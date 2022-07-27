package views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Workout;
import services.WorkoutService;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class WorkoutForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtWorkoutName;
	private JTextField txtSearch;
	private WorkoutService workoutService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable tableWorkout;
	private List<Workout> originalWorkoutList = new ArrayList<>();
	private List<Workout> filterdWorkoutList = new ArrayList<>();
	private Workout workout;
	private JComboBox<String> comboBodyType;
	/**
	 * Create the panel.
	 */
	public WorkoutForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllWorkout(Optional.empty());
	}

	public WorkoutForm(Workout workout) {
		this.workout = workout;
		initialize();
		this.initializeDependency();
	}

	private void initializeDependency() {
		this.workoutService = new WorkoutService();
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Workout Name");
		dtm.addColumn("Body Part");
		this.tableWorkout.setModel(dtm);
		tableWorkout.setSelectionBackground(new Color(146, 169, 189));
		tableWorkout.setSelectionForeground(Color.white);
		tableWorkout.setRowHeight(30);
		tableWorkout.setBackground(Color.white);
		tableWorkout.setGridColor(new Color(110, 133, 183));
		tableWorkout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableWorkout.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}
	
	protected void loadAllWorkout(Optional<List<Workout>> optionalWorkout) {
		this.dtm = (DefaultTableModel) this.tableWorkout.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.originalWorkoutList = this.workoutService.findAllWorkouts();
		this.filterdWorkoutList = optionalWorkout.orElseGet(() -> originalWorkoutList);

		filterdWorkoutList.forEach(e -> {
			Object[] row = new Object[4];
			row[0] = e.getWorkout_id();
			row[1] = e.getWorkoutName();
			row[2] = e.getBodypartName();
			
			dtm.addRow(row);
		});

		this.tableWorkout.setModel(dtm);
	}

	void resetFormData() {
		txtWorkoutName.setText("");
		comboBodyType.setSelectedIndex(0);
		txtWorkoutName.requestFocus();
		txtSearch.setText("");
	}

	private void initialize() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Add Workout", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel.setBounds(56, 130, 454, 481);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Workout Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(61, 72, 116, 31);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Body Type");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(61, 149, 116, 31);
		panel.add(lblNewLabel_1);

		txtWorkoutName = new JTextField();
		txtWorkoutName.setBounds(234, 71, 171, 35);
		panel.add(txtWorkoutName);
		txtWorkoutName.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Workout workout = new Workout();
				workout.setWorkoutName(txtWorkoutName.getText());
				workout.setBodypartName((String)comboBodyType.getSelectedItem());
//				workout.setBodypartName(txtBodyType.getText());
				if (!workout.getWorkoutName().isBlank() && !workout.getBodypartName().isBlank()) {
					if (txtWorkoutName.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter Workout Name.");
						txtWorkoutName.requestFocus(true);
						return;
					} else if (!(txtWorkoutName.getText().trim().matches("[a-z A-Z]+"))) {
						JOptionPane.showMessageDialog(null, "Please enter Workout Name Correctly.");
						txtWorkoutName.requestFocus(true);
						return;
					}else if(comboBodyType.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "Please, Select Body Part Name!");
						return;
					} else {
						workoutService.saveWorkout(workout);
						resetFormData();
						loadAllWorkout(Optional.empty());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Check Required Field");
				}
			}
		});
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAdd.setBackground(new Color(60, 179, 113));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBounds(277, 248, 128, 35);
		panel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != workout && workout.getWorkout_id() != 0) {

					workout.setWorkoutName(txtWorkoutName.getText());
					workout.setBodypartName((String)comboBodyType.getSelectedItem());

					if (!workout.getWorkoutName().isBlank() && !workout.getBodypartName().isBlank()) {
						if (txtWorkoutName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Workout Name.");
							txtWorkoutName.requestFocus(true);
							return;
						} else if (!(txtWorkoutName.getText().trim().matches("[a-z A-Z]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Workout Name Correctly.");
							txtWorkoutName.requestFocus(true);
							return;
						} else if(comboBodyType.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Body Part Name!");
							return;
						} else {
							workoutService.updateWorkout(String.valueOf(workout.getWorkout_id()), workout);
							resetFormData();
							loadAllWorkout(Optional.empty());
						}	

					} else {
						JOptionPane.showMessageDialog(null, "Check Required Field");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please Select a row");
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(110, 133, 183));
		btnUpdate.setBounds(277, 320, 128, 35);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Workout",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					workoutService.deleteWorkout(String.valueOf(workout.getWorkout_id()), workout);
					loadAllWorkout(Optional.empty());
					workout = null;
					JOptionPane.showMessageDialog(null, "Deleted");
					resetFormData();
				} else if (result == JOptionPane.NO_OPTION) {
					loadAllWorkout(Optional.empty());
					workout = null;
					resetFormData();
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDelete.setBackground(new Color(220, 20, 60));
		btnDelete.setBounds(61, 248, 128, 35);
		panel.add(btnDelete);

		JButton btnUpdate_1_1 = new JButton("Cancel");
		btnUpdate_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
			}
		});
		btnUpdate_1_1.setForeground(Color.WHITE);
		btnUpdate_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate_1_1.setBackground(new Color(110, 133, 183));
		btnUpdate_1_1.setBounds(61, 320, 128, 35);
		panel.add(btnUpdate_1_1);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllWorkout(Optional.empty());
				resetFormData();
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setBounds(277, 392, 128, 35);
		panel.add(btnRefresh);
		
		comboBodyType = new JComboBox<String>();
		comboBodyType.setBackground(new Color(230, 230, 250));
		comboBodyType.setFont(new Font("Calibri", Font.PLAIN, 15));
		comboBodyType.setModel(new DefaultComboBoxModel<String>(new String[] {"- Select -", "Chest", "Back", "Shoulder", "Leg", "Biceps", "Calf", "Arms", "Knees"}));
		comboBodyType.setBounds(234, 151, 171, 35);
		panel.add(comboBodyType);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(557, 190, 539, 421);
		add(scrollPane);

		tableWorkout = new JTable();
		scrollPane.setViewportView(tableWorkout);

		txtSearch = new JTextField("Search By Workout Name");
		txtSearch.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			if (txtSearch.getText().equals("Search By Workout Name")) {
				txtSearch.setText("");
				txtSearch.setForeground(new Color(72, 61, 139));
			}
		}
		@Override
		public void focusLost(FocusEvent e) {
			if (txtSearch.getText().equals("")) {
				txtSearch.setText("Search By Workout Name");
				txtSearch.setForeground(new Color(72, 61, 139));
			}
		}
	});
		txtSearch.setColumns(10);
		txtSearch.setText("Search By Workout Name");
		txtSearch.setForeground(new Color(72, 61, 139));
		txtSearch.setBounds(772, 122, 175, 35);
		add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();

				loadAllWorkout(Optional.of(originalWorkoutList.stream().filter(
						b -> b.getWorkoutName().toLowerCase(Locale.ROOT).startsWith(keyword.toLowerCase(Locale.ROOT)))
						.collect(Collectors.toList())));
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch.setBackground(new Color(110,133,183));
		btnSearch.setBounds(996, 122, 100, 35);
		add(btnSearch);
		
		JLabel lblWorkoutList = new JLabel("Workout List");
		lblWorkoutList.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutList.setForeground(new Color(72, 61, 139));
		lblWorkoutList.setFont(new Font("Dialog", Font.BOLD, 22));
		lblWorkoutList.setBounds(449, 32, 340, 47);
		add(lblWorkoutList);

		this.tableWorkout.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableWorkout.getSelectionModel().isSelectionEmpty()) {
				String id = tableWorkout.getValueAt(tableWorkout.getSelectedRow(), 0).toString();

				workout = workoutService.findWorkoutById(id);

				txtWorkoutName.setText(workout.getWorkoutName());
				comboBodyType.setSelectedItem(workout.getBodypartName());

			}
		});
	}
}
