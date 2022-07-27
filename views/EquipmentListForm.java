package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Equipment;
import services.EquipmentService;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipmentListForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JTable tableEquipment;
	private DefaultTableModel dtm = new DefaultTableModel();
	private EquipmentService equipmentService;
	private List<Equipment> equipmentList = new ArrayList<>();
	private List<Equipment> filteredEquipmentList = new ArrayList<>();
	private Equipment equipment;

	private void initializeDependency() {
		this.equipmentService = new EquipmentService();
	}

	public EquipmentListForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllEquipments(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("Equipment Id");
		dtm.addColumn("Equipment Name");
		dtm.addColumn("Equipment Condition");
		dtm.addColumn("Last Maintainance Date");
		dtm.addColumn("Next Maintainance Date");
		dtm.addColumn("Staff Name");

		this.tableEquipment.setModel(dtm);
		tableEquipment.setSelectionBackground(new Color(146, 169, 189));
		tableEquipment.setSelectionForeground(Color.white);
		tableEquipment.setRowHeight(30);
		tableEquipment.setBackground(Color.white);
		tableEquipment.setGridColor(new Color(110, 133, 183));
		tableEquipment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableEquipment.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}

	private void loadAllEquipments(Optional<List<Equipment>> optionalEquipment) {
		this.dtm = (DefaultTableModel) this.tableEquipment.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.equipmentList = this.equipmentService.findAllEquipment();

		this.filteredEquipmentList = optionalEquipment.orElseGet(() -> this.equipmentList).stream()
				.collect(Collectors.toList());

		this.filteredEquipmentList.forEach(pd -> {
			Object[] row = new Object[7];
			row[0] = pd.getEquipment_id();
			row[1] = pd.getEquip_name();
			row[2] = pd.getEquip_condition();
			row[3] = pd.getLast_maintainDate();
			row[4] = pd.getNext_maintainDate();
			row[5] = pd.getStaff().getName();

			dtm.addRow(row);
		});

		this.tableEquipment.setModel(dtm);
	}

	/**
	 * Create the panel.
	 */
	private void initialize() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JLabel lblWorkoutList = new JLabel("Equipment List");
		lblWorkoutList.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutList.setForeground(new Color(72, 61, 139));
		lblWorkoutList.setFont(new Font("Dialog", Font.BOLD, 22));
		lblWorkoutList.setBounds(422, 51, 340, 47);
		add(lblWorkoutList);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Search Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(123, 104, 238)));
		searchPanel.setBackground(new Color(230, 230, 250));
		searchPanel.setBounds(79, 134, 965, 119);
		add(searchPanel);

		txtSearch = new JTextField("Search By Equipment");
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search By Equipment")) {
					txtSearch.setText("");
					txtSearch.setForeground(new Color(32, 178, 170));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().equals("")) {
					txtSearch.setText("Search By Equipment");
					txtSearch.setForeground(new Color(32, 178, 170));
				}
			}
		});
		txtSearch.setForeground(new Color(123, 104, 238));
		txtSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setBounds(468, 45, 268, 40);
		searchPanel.add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				loadAllEquipments(
						Optional.of(
								equipmentList.stream()
										.filter(b -> b.getEquip_name().toLowerCase(Locale.ROOT)
												.startsWith(keyword.toLowerCase(Locale.ROOT)))
										.collect(Collectors.toList())));
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch.setBackground(new Color(110, 133, 183));
		btnSearch.setBounds(785, 45, 120, 40);
		searchPanel.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(new Color(224, 255, 255));
		scrollPane.setBounds(79, 285, 965, 328);
		add(scrollPane);

		tableEquipment = new JTable();
		scrollPane.setViewportView(tableEquipment);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllEquipments(Optional.empty());
				txtSearch.setText("");
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setBounds(469, 641, 120, 40);
		add(btnRefresh);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equipment != null) {
					int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Equipment",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						equipmentService.deleteEquipment(String.valueOf(equipment.getEquipment_id()), equipment);
						loadAllEquipments(Optional.empty());
						equipment = null;
						JOptionPane.showMessageDialog(null, "Deleted");
					} else if (result == JOptionPane.NO_OPTION) {
						loadAllEquipments(Optional.empty());
						equipment = null;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please, select a Equipment.");
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDelete.setBackground(new Color(220, 20, 60));
		btnDelete.setBounds(626, 641, 120, 40);
		add(btnDelete);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipmentEditForm equipmentEditForm = new EquipmentEditForm();
				equipmentEditForm.frameEditEquipment.setVisible(true);
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAdd.setBackground(new Color(60, 179, 113));
		btnAdd.setBounds(774, 641, 120, 40);
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equipment != null) {
					EquipmentEditForm equipmentEditForm = new EquipmentEditForm(equipment);
					equipmentEditForm.frameEditEquipment.setVisible(true);
					loadAllEquipments(Optional.empty());
				} else {
					JOptionPane.showMessageDialog(null, "Select a Equipment!");
				}
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(110, 133, 183));
		btnUpdate.setBounds(924, 641, 120, 40);
		add(btnUpdate);

		this.tableEquipment.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableEquipment.getSelectionModel().isSelectionEmpty()) {
				String equipmentId = tableEquipment.getValueAt(tableEquipment.getSelectedRow(), 0).toString();
				equipment = equipmentService.findEquipmentById(equipmentId);
				// comboStaff.setSelectedItem(equipment.getStaff().getName());
			}
		});
	}
}
