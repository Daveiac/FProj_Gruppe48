package framePackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Meeting;
import data.MeetingRoom;
import data.Notification;
import data.Person;
import data.Team;

public class NotiPanelView extends JPanel{
	
	private JFrame frame;
	private JPanel varselPanel;
	private JLabel lblWarning;
	private JList warningList;
	private DefaultListModel listModel;
	private List<Notification> notifications;
	private AppointmentOverView appointOverView;
	
	public NotiPanelView(){
		notifications = new ArrayList<Notification>();
		initialize();
	}
	
	private void initialize(){
		varselPanel = new JPanel(new GridBagLayout());
		varselPanel.setPreferredSize(new Dimension(250, 300));
		varselPanel.setVisible(true);
		varselPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		GridBagConstraints c = new GridBagConstraints();
		
		lblWarning = new JLabel("Varsel");
		lblWarning.setFont(new Font(lblWarning.getFont().getName(),lblWarning.getFont().getStyle(), 20 ));
		c.gridx = 0;
		c.gridy = 0;
		varselPanel.add(lblWarning, c);
		
		listModel = new DefaultListModel();
		warningList = new JList<Notification>(listModel);
		warningList.setFixedCellWidth(15);
		warningList.setCellRenderer(new NotiViewRender());
		JScrollPane scrollPane = new JScrollPane(warningList);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(200, 250));
		c.gridx = 0;
		c.gridy = 1;
		varselPanel.add(scrollPane, c);
		warningList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
//				appointOverView = new AppointmentOverView(meeting, creator, notification);
//				bruke calendarmodel her
				appointOverView.getPanel();
			}
		}); 
		
	}
	
	
	public JPanel getPanel(){
		return varselPanel;
	}
	
}
