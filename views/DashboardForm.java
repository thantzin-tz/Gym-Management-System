package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ScheduledMemberForm scheduledMemberForm;
	private JScrollPane middleScrollPane;

	/**
	 * Create the panel.
	 */
	public DashboardForm() {
		setForeground(new Color(0, 0, 0));

		setBackground(new Color(230, 230, 250));
		setLayout(null);
		setBounds(230, 45, 1170, 755);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scheduledMemberForm = new ScheduledMemberForm();
				middleScrollPane.setViewportView(scheduledMemberForm);
				
			}
		});
		lblNewLabel.setBounds(484, 538, 50, 13);
		add(lblNewLabel);
		
	}
}
