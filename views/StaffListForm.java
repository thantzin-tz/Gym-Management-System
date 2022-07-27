package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Staff;
import models.UserRole;
import services.StaffService;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StaffListForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private StaffService staffService;
	private Staff staff;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Staff> originalStaffList = new ArrayList<>();
	private List<Staff> staffList = new ArrayList<>();
	private JTable tableStaff;
	private JPopupMenu popupMenu;
	private JMenuItem menuItemDetail;
	private JMenuItem menuItemRetire;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAdmin, rdbtnTrainer, rdbtnAll;

	private void initializeDependency() {
		this.staffService = new StaffService();
	}

	/**
	 * Create the application.
	 */
	public StaffListForm() {
		initialize();
		this.setTableDesign();
		initializeDependency();
		this.loadAllStaffs(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Age");
		dtm.addColumn("Gender");
		dtm.addColumn("Username");
		dtm.addColumn("Role");
		dtm.addColumn("Status");
		this.tableStaff.setModel(dtm);

		tableStaff.setSelectionBackground(new Color(146, 169, 189));
		tableStaff.setSelectionForeground(Color.white);
		tableStaff.setRowHeight(30);
		tableStaff.setBackground(Color.white);
		tableStaff.setGridColor(new Color(110, 133, 183));
		tableStaff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableStaff.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}

	protected void loadAllStaffs(Optional<List<Staff>> optionalStaffs) {
		this.dtm = (DefaultTableModel) this.tableStaff.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.originalStaffList = this.staffService.findAllStaffs();
		this.staffList = optionalStaffs.orElseGet(() -> originalStaffList);

		staffList.forEach(e -> {
			Object[] row = new Object[7];
			row[0] = e.getStaff_id();
			row[1] = e.getName();
			row[2] = e.getAge();
			row[3] = e.getGender();
			row[4] = e.getUsername();
			row[5] = e.getRole();
			row[6] = e.isActive() ? "Active" : "Resigned";
			dtm.addRow(row);
		});

		this.tableStaff.setModel(dtm);
	}

	/**
	 * Create the panel.
	 */
	private void initialize() {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllStaffs(Optional.empty());
				txtSearch.setText("");
				rdbtnAll.setSelected(true);
			}
		});
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));
		btnRefresh.setBounds(477, 643, 120, 40);
		add(btnRefresh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(75, 272, 995, 328);
		add(scrollPane);

		tableStaff = new JTable();
		scrollPane.setViewportView(tableStaff);

		// Add Popup Menu
		popupMenu = new JPopupMenu();
		menuItemDetail = new JMenuItem("Detail");
		menuItemDetail.setBackground(new Color(224, 255, 255));
		popupMenu.add(menuItemDetail);
		popupMenu.setBorder(getBorder());
		menuItemRetire = new JMenuItem("Resign");
		menuItemRetire.setBackground(new Color(224, 255, 255));
		popupMenu.add(menuItemRetire);

		// sets the popup menu for the table
		tableStaff.setComponentPopupMenu(popupMenu);
		menuItemDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (staff != null) {
					StaffDetailForm window = new StaffDetailForm(staff);
					window.frameDetail.setVisible(true);
					loadAllStaffs(Optional.empty());
				} else {
					JOptionPane.showMessageDialog(null, "Select a Staff!");
				}
			}
		});
		menuItemRetire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (staff != null) {
					// delete password and username
					staff.setUsername("");
					staff.setPassword("");
					if (staff.isActive()) {

						int result = JOptionPane.showConfirmDialog(null,
								"Sure? You want to resign " + staff.getName() + " ?", "Resign Staff",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							staffService.retireStaff(String.valueOf(staff.getStaff_id()), staff);
							loadAllStaffs(Optional.empty());
							staff = null;
						} else if (result == JOptionPane.NO_OPTION) {
							loadAllStaffs(Optional.empty());
							staff = null;
						}

					} else {
//						staffService.restoreStaff(String.valueOf(staff.getStaff_id()),staff);

						int result = JOptionPane.showConfirmDialog(null,
								"Sure? You want to re-employe " + staff.getName() + " ?", "Re-employ Staff",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							staffService.restoreStaff(String.valueOf(staff.getStaff_id()), staff);
							loadAllStaffs(Optional.empty());
							staff = null;
						} else if (result == JOptionPane.NO_OPTION) {
							loadAllStaffs(Optional.empty());
							staff = null;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select a Staff!");
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Yes/No Confirm Message
				int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Staff",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					staffService.deleteStaff(String.valueOf(staff.getStaff_id()), staff);
					loadAllStaffs(Optional.empty());
					staff = null;
					JOptionPane.showMessageDialog(null, "Deleted");
				} else if (result == JOptionPane.NO_OPTION) {
					loadAllStaffs(Optional.empty());
					staff = null;
				}

			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(178, 34, 34));
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 15));
		btnDelete.setBounds(636, 643, 120, 40);
		add(btnDelete);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffEditForm staffEditForm = new StaffEditForm();
				staffEditForm.frameEditStaff.setVisible(true);
			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(60, 179, 113));
		btnAdd.setFont(new Font("Calibri", Font.BOLD, 15));
		btnAdd.setBounds(799, 643, 120, 40);
		add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (staff != null) {
					StaffEditForm staffEditForm = new StaffEditForm(staff);
					staffEditForm.frameEditStaff.setVisible(true);
					loadAllStaffs(Optional.empty());
				} else {
					JOptionPane.showMessageDialog(null, "Select a member!");
				}
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(110, 133, 183));
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 15));
		btnUpdate.setBounds(950, 643, 120, 40);
		add(btnUpdate);

		JPanel searchPanel = new JPanel();
		searchPanel.setForeground(Color.BLACK);
		searchPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Search Staff /Trainer By Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(72, 61, 139)));
		searchPanel.setBackground(new Color(230, 230, 250));
		searchPanel.setBounds(607, 114, 463, 119);
		add(searchPanel);
		searchPanel.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(123, 104, 238));
		// Add Placeholder in Search Box
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search By Name")) {
					txtSearch.setText("");
					txtSearch.setForeground(new Color(32, 178, 170));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().equals("")) {
					txtSearch.setText("Search By Name");
					txtSearch.setForeground(new Color(32, 178, 170));
				}
			}
		});
		txtSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSearch.setText("Search By Name");
		txtSearch.setBounds(47, 44, 196, 40);
		txtSearch.setToolTipText("Search By Name");
		txtSearch.setColumns(10);
		searchPanel.add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				loadAllStaffs(Optional.of(originalStaffList.stream()
						.filter(t -> t.getName().toLowerCase().startsWith(keyword.toLowerCase()))
						.collect(Collectors.toList())));
			}
		});
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(110, 133, 183));
		btnSearch.setBounds(320, 44, 106, 40);
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 15));
		searchPanel.add(btnSearch);

		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (staff != null && staff.getRole().equals(UserRole.Trainer)) {
					JOptionPane.showMessageDialog(null, "ADMIN only can Create Account!");
				} else if (staff != null && staff.getRole().equals(UserRole.Admin)) {
					if (staff.getUsername() == null) {
						LoginForm loginForm = new LoginForm(staff);
						loginForm.frameLogin.setUndecorated(true);
						loginForm.frameLogin.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null,
								"'" + staff.getName() + "'" + " is Already created Account!");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Plsease Choose a Staff");
				}
			}
		});
		btnCreateAccount.setForeground(new Color(255, 255, 255));
		btnCreateAccount.setBackground(new Color(60, 179, 113));
		btnCreateAccount.setFont(new Font("Calibri", Font.BOLD, 14));
		btnCreateAccount.setBounds(276, 643, 167, 40);
		add(btnCreateAccount);

		JPanel searchPanel_1 = new JPanel();
		searchPanel_1.setLayout(null);
		searchPanel_1.setForeground(Color.BLACK);
		searchPanel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Search By Role", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(123, 104, 238)));
		searchPanel_1.setBackground(new Color(230, 230, 250));
		searchPanel_1.setBounds(75, 114, 478, 119);
		add(searchPanel_1);

		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAdmin.isSelected()) {
					String keyword = "Admin";
					loadAllStaffs(Optional
							.of(originalStaffList.stream().filter(t -> String.valueOf(t.getRole()).startsWith(keyword))
									.collect(Collectors.toList())));
				} else if (rdbtnTrainer.isSelected()) {
					String keyword = "Trainer";
					loadAllStaffs(Optional
							.of(originalStaffList.stream().filter(t -> String.valueOf(t.getRole()).startsWith(keyword))
									.collect(Collectors.toList())));
				} else {
					loadAllStaffs(Optional.empty());
				}
			}
		});
		btnSearch_1.setForeground(Color.WHITE);
		btnSearch_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSearch_1.setBackground(new Color(110, 133, 183));
		btnSearch_1.setBounds(326, 41, 106, 40);
		searchPanel_1.add(btnSearch_1);

		rdbtnAdmin = new JRadioButton("Admin");
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBackground(new Color(230, 230, 250));
		rdbtnAdmin.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnAdmin.setBounds(33, 51, 64, 21);
		searchPanel_1.add(rdbtnAdmin);

		rdbtnTrainer = new JRadioButton("Trainer");
		buttonGroup.add(rdbtnTrainer);
		rdbtnTrainer.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnTrainer.setBackground(new Color(230, 230, 250));
		rdbtnTrainer.setBounds(124, 51, 72, 21);
		searchPanel_1.add(rdbtnTrainer);

		rdbtnAll = new JRadioButton("All");
		buttonGroup.add(rdbtnAll);
		rdbtnAll.setFont(new Font("Calibri", Font.BOLD, 14));
		rdbtnAll.setBackground(new Color(230, 230, 250));
		rdbtnAll.setBounds(220, 51, 55, 21);
		rdbtnAll.setSelected(true);
		searchPanel_1.add(rdbtnAll);

		JLabel lblStaffList = new JLabel("Staff List");
		lblStaffList.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaffList.setForeground(new Color(72, 61, 139));
		lblStaffList.setFont(new Font("Dialog", Font.BOLD, 22));
		lblStaffList.setBounds(428, 32, 340, 47);
		add(lblStaffList);

		this.tableStaff.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableStaff.getSelectionModel().isSelectionEmpty()) {
				String id = tableStaff.getValueAt(tableStaff.getSelectedRow(), 0).toString();
				staff = staffService.findStaffById(id);
			}
		});

	}
}
