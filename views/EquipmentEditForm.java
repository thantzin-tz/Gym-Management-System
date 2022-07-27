package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import models.Equipment;
import models.Staff;
import services.EquipmentService;
import services.StaffService;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EquipmentEditForm {

	protected JFrame frameEditEquipment;
	private JTextField txtName;
	private JTextField txtCondition;

	private JComboBox<String> comboStaff;
	private JDateChooser txtLastDate;
	private JDateChooser txtNextDate;

	private JButton btnSave;
	private JButton btnCancel;

	private JButton btnBack_1;
	private JLabel lblInformation;

	private EquipmentService equipmentService;
	private Equipment equipment;

	private List<Staff> staffList;

	private StaffService staffService;

	Optional<Staff> selectedStaff;
	Optional<Equipment> selectedEquipment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentEditForm window = new EquipmentEditForm();
					window.frameEditEquipment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param member2
	 */
	public EquipmentEditForm() {
		initialize();
		this.initializeDependency();
		this.equipmentService = new EquipmentService();
		this.loadStaffForComboBox();
	}

	public EquipmentEditForm(Equipment equipment) {
		this.equipment = equipment;
		initialize();
		this.initializeDependency();
		loadStaffForComboBox();
		comboStaff.setSelectedItem(equipment.getStaff().getName());

	}

	private void initializeDependency() {
		this.equipmentService = new EquipmentService();
		this.staffService = new StaffService();
	}

	private void loadStaffForComboBox() {
		comboStaff.addItem("- select -");
		this.staffList = this.staffService.findComboAdmin();
		this.staffList.forEach(b -> comboStaff.addItem(b.getName()));
	}

	private void resetFormData() {
		txtName.setText("");
		txtCondition.setText("");
		txtName.requestFocus();
		/*
		 * Default Date
		 */
		Date date = new Date();
		txtNextDate.setDate(date);
		txtLastDate.setDate(date);

//		Default Combo Value
		comboStaff.setSelectedIndex(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEditEquipment = new JFrame();
		frameEditEquipment.setResizable(false);
		frameEditEquipment.setTitle("Equipment Edit Form");
		frameEditEquipment.setBounds(450, 50, 567, 550);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setForeground(Color.WHITE);
		frameEditEquipment.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Add Equipment", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(123, 104, 238)));
		panel_2.setBackground(new Color(230, 230, 250));
		panel_2.setBounds(29, 31, 481, 381);
		panel.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(41, 38, 95, 18);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Condition");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(41, 108, 95, 18);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_3 = new JLabel("Last Maintain Date");
		lblNewLabel_1_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(41, 184, 126, 18);
		panel_2.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Next Maintain Date");
		lblNewLabel_1_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(41, 252, 126, 18);
		panel_2.add(lblNewLabel_1_4);

		txtName = new JTextField(equipment != null ? equipment.getEquip_name() : "");
		txtName.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtName.setBackground(new Color(255, 255, 255));
		txtName.setColumns(10);
		txtName.setBounds(270, 30, 175, 35);
		panel_2.add(txtName);

		txtCondition = new JTextField(equipment != null ? String.valueOf(equipment.getEquip_condition()) : "");
		txtCondition.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtCondition.setBackground(new Color(255, 255, 255));
		txtCondition.setColumns(10);
		txtCondition.setBounds(270, 100, 175, 35);
		panel_2.add(txtCondition);

		if (equipment != null) {
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(equipment.getLast_maintainDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			txtLastDate = new JDateChooser();
			txtLastDate.setDate(date);
		} else {
			txtLastDate = new JDateChooser();
			/// set Default date value
			txtLastDate.setDate(new Date());
			// To Make JDateChooser cannot edit
			JTextFieldDateEditor editor = (JTextFieldDateEditor) txtLastDate.getDateEditor();
			editor.setEditable(false);
		}

		txtLastDate.setBounds(270, 170, 175, 35);
		panel_2.add(txtLastDate);

		if (equipment != null) {
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(equipment.getNext_maintainDate());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			txtNextDate = new JDateChooser();
			txtNextDate.setDate(date);
		} else {
			txtNextDate = new JDateChooser();
			/// set Default date value
			txtNextDate.setDate(new Date());
			// To Make JDateChooser cannot edit
			JTextFieldDateEditor editor = (JTextFieldDateEditor) txtNextDate.getDateEditor();
			editor.setEditable(false);
		}

		txtNextDate.setBounds(270, 240, 175, 35);
		panel_2.add(txtNextDate);

		JLabel lblNewLabel_1_4_1 = new JLabel("Staff Name");
		lblNewLabel_1_4_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_4_1.setBounds(41, 318, 95, 18);
		panel_2.add(lblNewLabel_1_4_1);

		comboStaff = new JComboBox<String>();
		if (equipment != null) {
			comboStaff.setSelectedItem(equipment.getStaff().getName());
		} else {
			comboStaff.setSelectedIndex(-1);
		}
		comboStaff.setBackground(new Color(230, 230, 250));
		comboStaff.setFont(new Font("Calibri", Font.BOLD, 15));
		comboStaff.setBounds(270, 310, 175, 35);
		panel_2.add(comboStaff);

		lblInformation = new JLabel("");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setForeground(new Color(0, 139, 139));
		lblInformation.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		lblInformation.setBounds(153, 504, 175, 29);
		panel_2.add(lblInformation);

		btnSave = new JButton(equipment != null ? "Update" : "Save");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(60, 179, 113));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (equipment != null && equipment.getEquipment_id() != 0) {
					equipment.setEquip_name(txtName.getText());
					equipment.setEquip_condition(txtCondition.getText());

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					equipment.setLast_maintainDate(dateFormat.format(txtLastDate.getDate()));
					equipment.setNext_maintainDate(dateFormat.format(txtNextDate.getDate()));

					selectedStaff = staffList.stream().filter(s -> {
						return s.getName().equals(comboStaff.getSelectedItem());
					}).findFirst();
					if (selectedStaff.isPresent()) {
						equipment.setStaff(selectedStaff.get());
					}

					if (selectedStaff.isPresent()) {
						if (comboStaff.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select a Staff Name");
							return;
						} else {
							//// Update
							equipmentService.updateEquipment(String.valueOf(equipment.getEquipment_id()), equipment);
							frameEditEquipment.setVisible(false);
							resetFormData();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please Select a Staff Name");
					}

				} else {
					equipment = new Equipment();
					if (!(txtName.getText().trim().isBlank() && comboStaff.getSelectedIndex() == 0
							&& txtCondition.getText().trim().isBlank())) {
						equipment.setEquip_name(txtName.getText());
						equipment.setEquip_condition(txtCondition.getText());

						SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						String d1 = sdformat.format(txtLastDate.getDate());
						String d2 = sdformat.format(txtNextDate.getDate());
						/// Start date and end date check
						if (d1.compareTo(d2) == 0) {
							JOptionPane.showMessageDialog(null, "Expire date must be greather than start date");
							return;
						} else {
							equipment.setLast_maintainDate(d1);
							equipment.setNext_maintainDate(d2);
						}

						selectedStaff = staffList.stream().filter(s -> s.getName().equals(comboStaff.getSelectedItem()))
								.findFirst();
						if (selectedStaff.isPresent()) {
							equipment.setStaff(selectedStaff.get());
						}

						if (selectedStaff.isPresent()) {
							if (comboStaff.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Please, Select a Staff Name");
								return;
							} else {
								///// Save
								equipmentService.saveEquipment(equipment);
								JOptionPane.showMessageDialog(null, "Success");
								resetFormData();
								frameEditEquipment.setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Please Select a Staff Name");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Please,fill required field.");
					}
				}

			}
		});
		btnSave.setBounds(410, 447, 100, 35);
		panel.add(btnSave);
		btnSave.setFont(new Font("Calibri", Font.BOLD, 15));

		btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(220, 20, 60));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
			}
		});
		btnCancel.setBounds(277, 447, 100, 35);
		panel.add(btnCancel);
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 15));

		btnBack_1 = new JButton("Back");
		btnBack_1.setForeground(new Color(255, 255, 255));
		btnBack_1.setBackground(new Color(110, 133, 183));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameEditEquipment.setVisible(false);
				resetFormData();
			}
		});
		btnBack_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBack_1.setBounds(142, 447, 100, 35);
		panel.add(btnBack_1);
	}
}
