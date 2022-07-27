package views;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.Members;
import services.MemberService;

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

public class MemberListForm extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private Members member;
	private DefaultTableModel dtm = new DefaultTableModel();
	private MemberService memberService;
	private List<Members> originalMemberList = new ArrayList<>();
	private List<Members> memberList = new ArrayList<>();
	private JTable tableMember;
	private JScrollPane scrollPane;

	private void initializeDependency() {
		this.memberService = new MemberService();
	}

	/**
	 * Create the panel.
	 */
	public MemberListForm() {
		initialize();
		this.setTableDesign();
		initializeDependency();
		this.loadAllMembers(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Age");
		dtm.addColumn("Address");
		dtm.addColumn("Phone");
		dtm.addColumn("Date of Join");
		dtm.addColumn("Gender");
		dtm.addColumn("Weight");
		dtm.addColumn("Height");
		dtm.addColumn("BMI");
		this.tableMember.setModel(dtm);
		tableMember.setSelectionBackground(new Color(146, 169, 189));
		tableMember.setSelectionForeground(Color.white);
		tableMember.setRowHeight(30);
		tableMember.setBackground(Color.white);
		tableMember.setGridColor(new Color(110, 133, 183));
		tableMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JTableHeader tHeader = tableMember.getTableHeader();
		tHeader.setBackground(new Color(110, 133, 183));
		tHeader.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tHeader.setForeground(Color.white);
	}

	protected void loadAllMembers(Optional<List<Members>> optionalMembers) {
		this.dtm = (DefaultTableModel) this.tableMember.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.originalMemberList = this.memberService.findAllMembers();
		this.memberList = optionalMembers.orElseGet(() -> originalMemberList);

		memberList.forEach(e -> {
			Object[] row = new Object[10];
			row[0] = e.getMember_id();
			row[1] = e.getName();
			row[2] = e.getAge();
			row[3] = e.getAddress();
			row[4] = e.getPhone();
			row[5] = e.getDateOfJoin();
			row[6] = e.getGender();
			row[7] = e.getWeight();
			row[8] = e.getHeight();
			row[9] = e.getBmi_result();
			dtm.addRow(row);
		});

		this.tableMember.setModel(dtm);

	}

	private void initialize() {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search Members", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(147, 112, 219)));
		searchPanel.setBackground(new Color(230, 230, 250));
		searchPanel.setBounds(62, 116, 1037, 119);
		add(searchPanel);

		txtSearch = new JTextField();
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearch.getText().equals("Search By Name")) {
					txtSearch.setText("");
					txtSearch.setForeground(new Color(72, 61, 139));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearch.getText().equals("")) {
					txtSearch.setText("Search By Name");
					txtSearch.setForeground(new Color(72, 61, 139));
				}
			}
		});
		txtSearch.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtSearch.setForeground(new Color(72, 61, 139));
		txtSearch.setText("Search By Name");
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setColumns(10);
		txtSearch.setBounds(567, 45, 268, 40);
		searchPanel.add(txtSearch);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				loadAllMembers(Optional.of(originalMemberList.stream()
						.filter(t -> t.getName().toLowerCase().startsWith(keyword.toLowerCase()))
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

		tableMember = new JTable();
		scrollPane.setViewportView(tableMember);
		scrollPane.setBackground(new Color(230, 230, 250));
		scrollPane.setForeground(Color.white);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (member != null) {
					int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete Member",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						memberService.deleteMember(String.valueOf(member.getMember_id()), member);
						loadAllMembers(Optional.empty());
						member = null;
						JOptionPane.showMessageDialog(null, "Deleted");
					} else if (result == JOptionPane.NO_OPTION) {
						loadAllMembers(Optional.empty());
						member = null;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please, select a member.");
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
				MemberEditForm memberEidtForm = new MemberEditForm();
				memberEidtForm.frameEditMember.setVisible(true);
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
				if (member != null) {
					MemberEditForm memberEditForm = new MemberEditForm(member);
					memberEditForm.frameEditMember.setVisible(true);
					loadAllMembers(Optional.empty());
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
				loadAllMembers(Optional.empty());
			}
		});
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBounds(520, 645, 120, 40);
		add(btnRefresh);
		btnRefresh.setBackground(new Color(110, 133, 183));
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lblTitle = new JLabel("Member List");
		lblTitle.setForeground(new Color(72, 61, 139));
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(406, 40, 340, 47);
		add(lblTitle);

		this.tableMember.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tableMember.getSelectionModel().isSelectionEmpty()) {
				String id = tableMember.getValueAt(tableMember.getSelectedRow(), 0).toString();
				member = memberService.findMemberById(id);
			}
		});

	}
}
