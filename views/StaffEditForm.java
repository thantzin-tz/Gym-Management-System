package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import models.Staff;
import models.UserRole;
import services.StaffService;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class StaffEditForm {

	protected JFrame frameEditStaff;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private JTextField txtSalary;
	private JTextField txtAge;
	private JComboBox<String> comboGender;
	private JButton btnSave, btnCancel, btnBack;
	private StaffService staffService;
	private Staff staff;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffEditForm window = new StaffEditForm();
					window.frameEditStaff.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffEditForm() {
		initialize();
		initializeDependency();
	}

	public StaffEditForm(Staff staff) {
		this.staff = staff;
		initialize();
		this.initializeDependency();
	}

	private void initializeDependency() {
		this.staffService = new StaffService();
	}

	private void resetFormData() {
		txtName.setText("");
		txtAge.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		comboGender.setSelectedIndex(0);
		txtSalary.setText("0.0");
		txtName.requestFocus();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEditStaff = new JFrame();
		frameEditStaff.setResizable(false);
		frameEditStaff.setTitle("Staff Edit Form");
		frameEditStaff.setBounds(450, 50, 591, 600);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		frameEditStaff.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Staff",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		panel_2.setBackground(new Color(230, 230, 250));
		panel_2.setBounds(37, 44, 368, 494);
		panel.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(27, 36, 95, 18);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Phone");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(27, 144, 95, 18);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Address");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(27, 195, 95, 18);
		panel_2.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Email");
		lblNewLabel_1_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(27, 255, 95, 18);
		panel_2.add(lblNewLabel_1_3);

		txtName = new JTextField(staff != null ? staff.getName() : "");
		txtName.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtName.setColumns(10);
		txtName.setBounds(153, 28, 175, 35);
		panel_2.add(txtName);

		txtPhone = new JTextField(staff != null ? staff.getPhone() : "");
		txtPhone.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtPhone.setColumns(10);
		txtPhone.setBounds(153, 136, 175, 35);
		panel_2.add(txtPhone);

		txtAddress = new JTextField(staff != null ? staff.getAddress() : "");
		txtAddress.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtAddress.setColumns(10);
		txtAddress.setBounds(153, 187, 175, 35);
		panel_2.add(txtAddress);

		txtEmail = new JTextField(staff != null ? staff.getEmail() : "");
		txtEmail.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(153, 247, 175, 35);
		panel_2.add(txtEmail);

		JLabel lblNewLabel_1_3_1 = new JLabel("Gender");
		lblNewLabel_1_3_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_1.setBounds(27, 305, 95, 18);
		panel_2.add(lblNewLabel_1_3_1);

		comboGender = new JComboBox<String>();
		comboGender.setBackground(new Color(230, 230, 250));
		comboGender.setModel(new DefaultComboBoxModel<String>(new String[] { "-Select-", "Male", "Female", "Other" }));
		comboGender.setFont(new Font("Calibri", Font.BOLD, 15));
		comboGender.setBounds(153, 299, 175, 35);
		if (staff != null) {
			comboGender.setSelectedItem(staff.getGender());
		} else {
			comboGender.setSelectedIndex(0);
		}
		panel_2.add(comboGender);

		JLabel lblNewLabel_1_3_2 = new JLabel("Salary");
		lblNewLabel_1_3_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_2.setBounds(27, 366, 95, 18);
		panel_2.add(lblNewLabel_1_3_2);

		txtSalary = new JTextField(staff != null ? String.valueOf(staff.getSalary()) : "");
		txtSalary.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSalary.setColumns(10);
		txtSalary.setBounds(153, 358, 175, 35);
		panel_2.add(txtSalary);

		JLabel lblNewLabel_1_4 = new JLabel("Age");
		lblNewLabel_1_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(27, 88, 95, 18);
		panel_2.add(lblNewLabel_1_4);

		txtAge = new JTextField(staff != null ? String.valueOf(staff.getAge()) : "");
		txtAge.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtAge.setColumns(10);
		txtAge.setBounds(153, 80, 175, 35);
		panel_2.add(txtAge);

		JLabel lblNewLabel_1_3_2_1 = new JLabel("Role");
		lblNewLabel_1_3_2_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_2_1.setBounds(27, 430, 95, 18);
		panel_2.add(lblNewLabel_1_3_2_1);

		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setSelected(true);
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBackground(new Color(230, 230, 250));
		rdbtnAdmin.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnAdmin.setBounds(153, 422, 75, 35);
		panel_2.add(rdbtnAdmin);

		JRadioButton rdbtnTrainer = new JRadioButton("Trainer");
		buttonGroup.add(rdbtnTrainer);
		rdbtnTrainer.setBackground(new Color(230, 230, 250));
		rdbtnTrainer.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnTrainer.setBounds(253, 421, 75, 36);
		panel_2.add(rdbtnTrainer);
		/// to select radio button when update
		if (staff != null) {
			if (staff.getRole().equals(UserRole.Admin)) {
				rdbtnAdmin.setSelected(true);
				;
			} else if (staff.getRole().equals(UserRole.Trainer)) {
				rdbtnTrainer.setSelected(true);
			}
		}

		btnSave = new JButton(staff != null ? "Update" : "Save");
		btnSave.setBackground(new Color(60, 179, 113));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (staff != null && staff.getStaff_id() != 0) {
					staff.setName(txtName.getText());
					try {
						staff.setAge(Integer.parseInt(txtAge.getText()));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Ener Number only for Age.");
						return;
					}
					staff.setPhone(txtPhone.getText());
					staff.setAddress(txtAddress.getText());
					staff.setEmail(txtEmail.getText());
					staff.setGender((String) comboGender.getSelectedItem());
					if (rdbtnAdmin.isSelected()) {
						staff.setRole(UserRole.Admin);
					} else {
						staff.setRole(UserRole.Trainer);
					}
					try {
						staff.setSalary(Double.parseDouble(txtSalary.getText()));
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Enter Number only for Salary");
						return;
					}

					if (!staff.getName().isEmpty() || !staff.getAddress().isBlank() || !staff.getEmail().isBlank()
							|| (!(staff.getAge() == 0) || !staff.getPhone().isEmpty() || !staff.getGender().isBlank()
									|| !(staff.getSalary() == 0.0))) {
						if (txtName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Name.");
							txtName.requestFocus(true);
							return;
						} else if (!(txtName.getText().trim().matches("[a-z A-Z]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Name.");
							txtName.requestFocus(true);
							return;
						} else if (txtAge.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please Enter Age");
							txtAge.requestFocus();
							return;
//						}else if(!(txtAge.getText().trim().matches("[0-9]+"))) {
//							JOptionPane.showMessageDialog(null, "Please Enter Integer value for Age");
//							txtAge.requestFocus();
//							return;
						} else if (txtPhone.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Phone.");
							txtPhone.requestFocus(true);
							return;
						} else if (!(txtPhone.getText().trim().matches("[0-9]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Integer value for Phone Number.");
							txtPhone.requestFocus(true);
							return;
						} else if (txtAddress.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Address.");
							txtAddress.requestFocus(true);
							return;
						}
						// else if(!(txtAddress.getText().matches("[a-z A-Z 0-9()-.,]+")))
						else if (!(txtAddress.getText().trim().matches("[^!@#$%^&*=+~?<>;:/]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Address.");
							txtAddress.requestFocus(true);
							return;
						} else if (txtEmail.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Email.");
							txtEmail.requestFocus(true);
							return;
						} else if (!(txtEmail.getText().trim().matches("^[A-Za-z0-9+_.-]+@(.+)$"))) {
							JOptionPane.showMessageDialog(null, "Make sure the correct email format(i.e. jsmith@gmail.com)");
							txtEmail.requestFocus(true);
							return;
						} else if (comboGender.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Gender (Male/Femele/Other)");
							return;
						} else if ((txtSalary.getText().trim().equals(""))) {
							JOptionPane.showMessageDialog(null, "Please enter Salary.");
							txtSalary.requestFocus(true);
							return;
						}

						else {
							try {
								staffService.updateStaff(String.valueOf(staff.getStaff_id()), staff);
							} catch (SQLIntegrityConstraintViolationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Member Updated.");
							staff = null;
							// Back to Member Form
							StaffListForm staffListForm = new StaffListForm();
							staffListForm.loadAllStaffs(Optional.empty());
							frameEditStaff.setVisible(false);
							resetFormData();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				} else {
					Staff staff = new Staff();
					staff.setName(txtName.getText());
					try {
						staff.setAge(Integer.parseInt(txtAge.getText()));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Ener Number only for Age.");
						return;
					}
					staff.setPhone(txtPhone.getText());
					staff.setAddress(txtAddress.getText());
					staff.setEmail(txtEmail.getText());
					staff.setGender((String) comboGender.getSelectedItem());
					if (rdbtnAdmin.isSelected()) {
						staff.setRole(UserRole.Admin);
					} else {
						staff.setRole(UserRole.Trainer);
					}
					try {
						staff.setSalary(Double.parseDouble(txtSalary.getText()));
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Enter Number only for Salary");
						return;
					}

					if (!staff.getName().isEmpty() || !staff.getAddress().isBlank() || !staff.getEmail().isBlank()
							|| (!(staff.getAge() == 0) || !staff.getPhone().isEmpty() || !staff.getGender().isBlank()
									|| !(staff.getSalary() == 0.0))) {
						if (txtName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Name.");
							txtName.requestFocus(true);
							return;
						} else if (!(txtName.getText().trim().matches("[a-z A-Z]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Name.");
							txtName.requestFocus(true);
							return;
						} else if (txtAge.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please Enter Age");
							txtAge.requestFocus();
							return;
//						}else if(!(txtAge.getText().trim().matches("[0-9]+"))) {
//							JOptionPane.showMessageDialog(null, "Please Enter Integer value for Age");
//							txtAge.requestFocus();
//							return;
						} else if (txtPhone.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Phone.");
							txtPhone.requestFocus(true);
							return;
						} else if (!(txtPhone.getText().trim().matches("[0-9]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Integer value for Phone Number.");
							txtPhone.requestFocus(true);
							return;
						} else if (txtAddress.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Staff's Address.");
							txtAddress.requestFocus(true);
							return;
						}
						// else if(!(txtAddress.getText().matches("[a-z A-Z 0-9()-.,]+")))
						else if (!(txtAddress.getText().trim().matches("[^!@#$%^&*=+~?<>;:/]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Address.");
							txtAddress.requestFocus(true);
							return;
						} else if (txtEmail.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Email.");
							txtEmail.requestFocus(true);
							return;
						} else if (!(txtEmail.getText().trim().matches("^[A-Za-z0-9+_.-]+@(.+)$"))) {
							JOptionPane.showMessageDialog(null, "Make sure you are using the correct format for an email address (i.e. jsmith@gmail.com)");
							txtEmail.requestFocus(true);
							return;
						} else if (comboGender.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Gender (Male/Femele/Other)");
							return;
						} else if ((txtSalary.getText().trim().equals(""))) {
							JOptionPane.showMessageDialog(null, "Please enter Salary.");
							txtSalary.requestFocus(true);
							return;
						} else {
							try {
								staffService.saveStaff(staff);
							} catch (SQLIntegrityConstraintViolationException e1) {
								JOptionPane.showMessageDialog(null, "Already Exists");
							//e1.printStackTrace();
							}
							resetFormData();
							JOptionPane.showMessageDialog(null, "A new Staff is added.");
							StaffListForm staffListForm = new StaffListForm();
							staffListForm.loadAllStaffs(Optional.empty());
							frameEditStaff.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				}

			}
		});
		btnSave.setBounds(438, 211, 100, 35);
		panel.add(btnSave);
		btnSave.setFont(new Font("Calibri", Font.BOLD, 15));

		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(220, 20, 60));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFormData();
			}
		});
		btnCancel.setBounds(438, 290, 100, 35);
		panel.add(btnCancel);
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 15));

		btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(110, 133, 183));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffListForm staffListForm = new StaffListForm();
				staffListForm.loadAllStaffs(Optional.empty());
				frameEditStaff.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBack.setBounds(438, 374, 100, 35);
		panel.add(btnBack);
	}
}
