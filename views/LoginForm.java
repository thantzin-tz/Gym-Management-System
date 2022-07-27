package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import models.Staff;
import models.UserRole;
import services.AuthService;
import services.StaffService;
import utils.CurrentUserHolder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class LoginForm {

	protected JFrame frameLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private Staff staff;
	private StaffService staffService;
	private AuthService authService;
	protected JLabel lblLoginHere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm lognForm = new LoginForm();
					// disable default window frame
					lognForm.frameLogin.setUndecorated(true);
					lognForm.frameLogin.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
		initializeDependency();
	}

	private void initializeDependency() {
		this.staffService = new StaffService();
		this.authService = new AuthService();
	}

	public LoginForm(Staff staff) {
		this.staff = staff;
		initialize();
		initializeDependency();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.getContentPane().setBackground(new Color(230, 230, 250));
		frameLogin.setBackground(new Color(50, 205, 50));
		frameLogin.setBounds(350, 150, 706, 392);
		frameLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameLogin.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(110, 133, 183));
		panel.setBounds(0, 0, 345, 405);
		frameLogin.getContentPane().add(panel);
		panel.setLayout(null);

		lblLoginHere = new JLabel(staff != null ? "Please Register Here >>" : "Please Login Here >>");
		lblLoginHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginHere.setForeground(Color.WHITE);
		lblLoginHere.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		lblLoginHere.setBounds(74, 287, 199, 25);
		panel.add(lblLoginHere);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginForm.class.getResource("/views/images/LogoLogin.png")));
		lblNewLabel_1.setBounds(51, 0, 250, 277);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(392, 87, 97, 18);
		frameLogin.getContentPane().add(lblNewLabel);

		txtUsername = new JTextField();
		txtUsername.setBounds(392, 115, 260, 31);
		frameLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPassword.setBounds(392, 163, 97, 18);
		frameLogin.getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(392, 191, 260, 31);
		frameLogin.getContentPane().add(txtPassword);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(220, 20, 60));
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 14));
		btnCancel.setBounds(408, 270, 100, 32);
		frameLogin.getContentPane().add(btnCancel);

		JButton btnLogin = new JButton(staff != null ? "Register" : "Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//// Register ////
				if (staff != null && staff.getRole().equals(UserRole.Admin)) {
					staff.setUsername(txtUsername.getText());
					staff.setPassword(String.valueOf(txtPassword.getPassword()));
					if (!staff.getUsername().isBlank() && !staff.getPassword().isBlank()) {
						try {
							staffService.updateStaff(String.valueOf(staff.getStaff_id()), staff);
						} catch (SQLIntegrityConstraintViolationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						MainForm mainform = new MainForm();
						mainform.frameMain.setUndecorated(true);
						mainform.frameMain.setVisible(true);
						frameLogin.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Fill required fields");
					}
				} else {
					//// Login ////
					String username = txtUsername.getText();
					String password = String.valueOf(txtPassword.getPassword());

					if (!username.isBlank() && !password.isBlank()) {
						String loggedInUserId = authService.login(username, password);
						if (!loggedInUserId.isBlank()) {
							CurrentUserHolder.setLoggedInUser(staffService.findStaffById(loggedInUserId));
							JOptionPane.showMessageDialog(null, "Successfully Login");
							frameLogin.setVisible(false);
							MainForm mainform = new MainForm();
							mainform.frameMain.setUndecorated(true);
							mainform.frameMain.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Enter required Fields");
					}
				}

			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(60, 179, 113));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 14));
		btnLogin.setBounds(537, 270, 100, 32);
		frameLogin.getContentPane().add(btnLogin);

		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameLogin.setVisible(false);
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClose.setForeground(new Color(255, 0, 0));
		lblClose.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		lblClose.setBounds(650, 0, 51, 25);
		frameLogin.getContentPane().add(lblClose);

		JLabel lblLoginTitle = new JLabel(staff != null ? "Staff : " + staff.getName() : "LOGIN");
		lblLoginTitle.setForeground(new Color(110, 133, 183));
		lblLoginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginTitle.setFont(new Font("Calibri", Font.BOLD, 15));
		lblLoginTitle.setBounds(392, 35, 274, 29);
		frameLogin.getContentPane().add(lblLoginTitle);

		JCheckBox chkShowPassword = new JCheckBox("Show Password");
		chkShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkShowPassword.isSelected()) {
					txtPassword.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('*');
				}
			}
		});
		chkShowPassword.setBackground(new Color(230, 230, 250));
		chkShowPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		chkShowPassword.setBounds(392, 228, 121, 21);
		frameLogin.getContentPane().add(chkShowPassword);
	}
}
