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

import models.Members;
import services.MemberService;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MemberEditForm {

	protected JFrame frameEditMember;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtWeight;
	private JTextField txtHeight;
	private JTextField txtBMI;
	private JComboBox<String> comboGender;
	private JDateChooser txtDate;
	private JButton btnSave;
	private JButton btnCancel;
	private MemberService memberService;
	private Members member;
	private double bmi;
	private JButton btnBack_1;
	private JLabel lblInformation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberEditForm window = new MemberEditForm();
					window.frameEditMember.setVisible(true);
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
	public MemberEditForm() {
		initialize();
		this.memberService = new MemberService();
	}

	public MemberEditForm(Members member) {
		this.member = member;
		initialize();
		this.initializeDependency();
//		calculateBMI();
	}

	private void initializeDependency() {
		this.memberService = new MemberService();
	}

	private void resetFormData() {
		txtName.setText("");
		txtAge.setText("");
		txtAddress.setText("");
		txtPhone.setText("");
		txtName.requestFocus();
		/*
		 * Default Date
		 */
		Date date = new Date();
		txtDate.setDate(date);
		txtWeight.setText("");
		txtHeight.setText("");
		txtBMI.setText("");
//		Default Combo Value
		comboGender.setSelectedIndex(0);

	}

	public double calculateBMI(){

		double weight, height, result = 0;
		try {
			weight = Double.parseDouble(txtWeight.getText());
			height = Double.parseDouble(txtHeight.getText());
			result = weight / ((height * height) / 10000);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please, enter NUMBER only for Height and Weight");
			return 0;
		}
		if (result < 18.5) {
			lblInformation.setText("Underweight!");
			lblInformation.setForeground(Color.red);
		} else if (result > 18.5 && result < 25.0) {
			lblInformation.setText("Normal");
			lblInformation.setForeground(new Color(0, 139, 139));
		} else if (result > 25.00) {
			lblInformation.setText("Overweight!");
			lblInformation.setForeground(Color.red);
		}
		System.out.println(result);
		return result;

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEditMember = new JFrame();
		frameEditMember.setResizable(false);
		frameEditMember.setTitle("Member Edit Form");
		frameEditMember.setBounds(450, 50, 567, 650);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setForeground(Color.WHITE);
		frameEditMember.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Member", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(123, 104, 238)));
		panel_2.setBackground(new Color(230, 230, 250));
		panel_2.setBounds(29, 31, 371, 543);
		panel.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(27, 28, 95, 18);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Age");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(27, 79, 95, 18);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Address");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(27, 135, 95, 18);
		panel_2.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Phone");
		lblNewLabel_1_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(27, 193, 95, 18);
		panel_2.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Date of Join");
		lblNewLabel_1_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(27, 255, 95, 18);
		panel_2.add(lblNewLabel_1_4);

		txtName = new JTextField(member != null ? member.getName() : "");
		txtName.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtName.setBackground(new Color(255, 255, 255));
		txtName.setColumns(10);
		txtName.setBounds(153, 20, 175, 35);
		panel_2.add(txtName);

		txtAge = new JTextField(member != null ? String.valueOf(member.getAge()) : "");
		txtAge.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtAge.setBackground(new Color(255, 255, 255));
		txtAge.setColumns(10);
		txtAge.setBounds(153, 68, 175, 35);
		panel_2.add(txtAge);

		txtAddress = new JTextField(member != null ? member.getAddress() : "");
		txtAddress.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtAddress.setColumns(10);
		txtAddress.setBounds(153, 127, 175, 35);
		panel_2.add(txtAddress);

		txtPhone = new JTextField(member != null ? member.getPhone() : "");
		txtPhone.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtPhone.setColumns(10);
		txtPhone.setBounds(153, 185, 175, 35);
		panel_2.add(txtPhone);
		if (member != null) {
			Date date = new Date();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(member.getDateOfJoin());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			txtDate = new JDateChooser();
			txtDate.setDate(date);
		} else {
				txtDate = new JDateChooser();
				/// set Default date value
				txtDate.setDate(new Date());
				// To Make JDateChooser cannot edit
				JTextFieldDateEditor editor = (JTextFieldDateEditor) txtDate.getDateEditor();
				editor.setEditable(false);
		}
		
		txtDate.setBounds(153, 243, 175, 35);
		panel_2.add(txtDate);

		JLabel lblNewLabel_1_4_1 = new JLabel("Gender");
		lblNewLabel_1_4_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_4_1.setBounds(27, 301, 63, 18);
		panel_2.add(lblNewLabel_1_4_1);

		comboGender = new JComboBox<String>();
		comboGender.setBackground(new Color(230, 230, 250));
		comboGender.addItem("-Select-");
		comboGender.addItem("Male");
		comboGender.addItem("Female");
		comboGender.addItem("Other");
		if (member != null) {
			comboGender.setSelectedItem(member.getGender());
		} else {
			comboGender.setSelectedIndex(0);
		}

		comboGender.setFont(new Font("Calibri", Font.BOLD, 15));
		comboGender.setBounds(153, 293, 175, 35);
		panel_2.add(comboGender);

		JLabel lblNewLabel_1_3_1 = new JLabel("Weight(kg)");
		lblNewLabel_1_3_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_1.setBounds(27, 361, 95, 18);
		panel_2.add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_3_2 = new JLabel("Height(cm)");
		lblNewLabel_1_3_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_2.setBounds(27, 412, 95, 18);
		panel_2.add(lblNewLabel_1_3_2);

		JLabel lblNewLabel_1_3_3 = new JLabel("BMI Result");
		lblNewLabel_1_3_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1_3_3.setBounds(27, 467, 95, 18);
		panel_2.add(lblNewLabel_1_3_3);

		txtWeight = new JTextField(member != null ? String.valueOf(member.getWeight()) : "");
		txtWeight.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtWeight.setColumns(10);
		txtWeight.setBounds(153, 353, 175, 35);
		panel_2.add(txtWeight);

		txtHeight = new JTextField(member != null ? String.valueOf(member.getHeight()) : "");
		txtHeight.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtHeight.setColumns(10);
		txtHeight.setBounds(153, 404, 175, 35);
		panel_2.add(txtHeight);

		// calculate bmi when enter key
		txtHeight.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Format Decimal Place
					DecimalFormat df = new DecimalFormat("0.00");
					bmi = calculateBMI();
					txtBMI.setText(String.valueOf(df.format(bmi)));
				}
			}
		});

		txtBMI = new JTextField(member != null ? String.valueOf(member.getBmi_result()) : "");
		txtBMI.setFont(new Font("Calibri", Font.PLAIN, 15));
//		txtBMI.setText(String.valueOf(bmi));
		txtBMI.setEditable(false);

		txtBMI.setColumns(10);
		txtBMI.setBounds(153, 459, 175, 35);
		panel_2.add(txtBMI);

		lblInformation = new JLabel("");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setForeground(new Color(0, 139, 139));
		lblInformation.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		lblInformation.setBounds(153, 504, 175, 29);
		panel_2.add(lblInformation);

		btnSave = new JButton(member != null ? "Update" : "Save");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(60, 179, 113));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (member != null && member.getMember_id() != 0) {
					member.setName(txtName.getText());
					try {
						member.setAge(Integer.parseInt(txtAge.getText()));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please Ente Number Only for Age");
						return;
					}
					try {
						member.setWeight(Double.parseDouble(txtWeight.getText()));
						member.setHeight(Double.parseDouble(txtHeight.getText()));
						member.setBmi_result(Double.parseDouble(txtBMI.getText()));
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "Please Enter NUMBER only for Weight and Height");
						return;
					}
					member.setAddress(txtAddress.getText());
					member.setPhone(txtPhone.getText());
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					member.setDateOfJoin(dateFormat.format(txtDate.getDate()));
					member.setGender((String) comboGender.getSelectedItem());

					if (!member.getName().isEmpty() || !member.getAddress().isBlank()
							|| (!(member.getAge() == 0) || !member.getPhone().isEmpty()
									|| (member.getDateOfJoin() != null))
							|| !(String.valueOf(member.getWeight())).isEmpty()
							|| !(String.valueOf(member.getHeight())).isEmpty()
							|| !(String.valueOf(member.getBmi_result())).isEmpty()) {

						if (txtName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Name.");
							txtName.requestFocus(true);
							return;
						} else if (!(txtName.getText().trim().matches("[a-z A-Z]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter String value.");
							txtName.requestFocus(true);
							return;
						} else if (txtAge.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please Member's Enter Age");
							txtAge.requestFocus();
							return;
						} else if (txtAddress.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Address.");
							txtAddress.requestFocus(true);
							return;
						}
						// else if(!(txtAddress.getText().matches("[a-z A-Z 0-9()-.,]+")))
						else if (!(txtAddress.getText().trim().matches("[^!@#$%^&*=+~?<>;:/]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Address.");
							txtAddress.requestFocus(true);
							return;
						} else if (txtPhone.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Phone.");
							txtPhone.requestFocus(true);
							return;
						} else if (!(txtPhone.getText().trim().matches("[0-9]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Number for Phone Number.");
							txtPhone.requestFocus(true);
							return;
						}else if(comboGender.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Gender (Male/Femele/Other)");
							return;
						}
						else if (txtWeight.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Weight.");
							txtPhone.requestFocus(true);
							return;
						}else if (txtHeight.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Height.");
							txtPhone.requestFocus(true);
							return;
						} else {
							memberService.updateMember(String.valueOf(member.getMember_id()), member);
							JOptionPane.showMessageDialog(null, "Member Updated.");
							// load all data
							MemberListForm mbl = new MemberListForm();
							mbl.loadAllMembers(Optional.empty());
							member = null;
							resetFormData();
							frameEditMember.setVisible(false);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
						return;
					}
				} else {
					System.out.println(txtDate.getDate());
					Members member = new Members();
					member.setName(txtName.getText());
					try {
						member.setAge(Integer.parseInt(txtAge.getText()));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please Ente Number Only for Age");
						return;
					}
					try {
						member.setWeight(Double.parseDouble(txtWeight.getText()));
						member.setHeight(Double.parseDouble(txtHeight.getText()));
						member.setBmi_result(Double.parseDouble(txtBMI.getText()));
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "Please Enter NUMBER only for Weight and Height");
						return;
					}
					member.setAddress(txtAddress.getText());
					member.setPhone(txtPhone.getText());
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					member.setDateOfJoin(dateFormat.format(txtDate.getDate()));
					member.setGender((String) comboGender.getSelectedItem());

					if (!member.getName().isEmpty() || !member.getAddress().isBlank()
							|| (!(member.getAge() == 0) || !member.getPhone().isEmpty()
									|| (member.getDateOfJoin() != null))
							|| !(String.valueOf(member.getWeight())).isEmpty()
							|| !(String.valueOf(member.getHeight())).isEmpty()
							|| !(String.valueOf(member.getBmi_result())).isEmpty()) {

						if (txtName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Name.");
							txtName.requestFocus(true);
							return;
						} else if (!(txtName.getText().trim().matches("[a-z A-Z]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Mmeber's Name.");
							txtName.requestFocus(true);
							return;
						} else if (txtAge.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please Enter Age");
							txtAge.requestFocus();
							return;
						}
//						}else if(10 < (Integer.parseInt((txtAge.getText())) < 75 )){
//							JOptionPane.showMessageDialog(null, "Please Enter Integer value for Age");
//							txtAge.requestFocus();
//							return;
//						}
						else if (txtAddress.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Address.");
							txtAddress.requestFocus(true);
							return;
						}
						// else if(!(txtAddress.getText().matches("[a-z A-Z 0-9()-.,]+")))
						else if (!(txtAddress.getText().trim().matches("[^!@#$%^&*=+~?<>;:/]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Address.");
							txtAddress.requestFocus(true);
							return;
						} else if (txtPhone.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Phone.");
							txtPhone.requestFocus(true);
							return;
						} else if (!(txtPhone.getText().trim().matches("[0-9]+"))) {
							JOptionPane.showMessageDialog(null, "Please enter Number for Phone Number.");
							txtPhone.requestFocus(true);
							return;
						}else if(comboGender.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Please, Select Gender (Male/Femele/Other)");
							return;
						}
						else if (txtWeight.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Weight.");
							txtPhone.requestFocus(true);
							return;
						} else if (txtHeight.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Height.");
							txtPhone.requestFocus(true);
							return;
						} else if (txtBMI.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter Member's Height.");
							txtPhone.requestFocus(true);
							return;
						} else {
							memberService.saveMember(member);
							JOptionPane.showMessageDialog(null, "Member added.");
							MemberListForm mbl = new MemberListForm();
							mbl.loadAllMembers(Optional.empty());
							member = null;
							resetFormData();
							frameEditMember.setVisible(false);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
						return;
					}
				}

			}
		});
		btnSave.setBounds(424, 189, 100, 35);
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
		btnCancel.setBounds(424, 268, 100, 35);
		panel.add(btnCancel);
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 15));

		btnBack_1 = new JButton("Back");
		btnBack_1.setForeground(new Color(255, 255, 255));
		btnBack_1.setBackground(new Color(110, 133, 183));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameEditMember.setVisible(false);
				resetFormData();
			}
		});
		btnBack_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnBack_1.setBounds(424, 342, 100, 35);
		panel.add(btnBack_1);
	}
}
