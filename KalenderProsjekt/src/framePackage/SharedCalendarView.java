package framePackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Person;

public class SharedCalendarView {
	private JPanel sharedCPanel;
	private JComboBox<String> personBox;
	private JButton addButton;
	private ButtonGroup buttonGroup;
	private Person person;
	private JPanel checkBoxPanel;

	
	public SharedCalendarView(){
		initialize();
//		person = new Person();
	}
	
	
	private void initialize(){
		sharedCPanel = new JPanel(new GridBagLayout());
		sharedCPanel.setPreferredSize(new Dimension(250, 200));
		sharedCPanel.setVisible(true);
		sharedCPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		GridBagConstraints c = new GridBagConstraints();
		
		personBox = new JComboBox<String>();
		personBox.addItem("Shimin Sun");
		personBox.addItem("andre folk");
		personBox.setSelectedItem(null);
		c.gridx = 0;
		c.gridy = 0;
		sharedCPanel.add(personBox, c);
		
		addButton = new JButton("Legg til");
		c.gridx = 1;
		c.gridy = 0;
		sharedCPanel.add(addButton,c);
		
		checkBoxPanel = new JPanel(new GridBagLayout());
		final GridBagConstraints cbpCon = new GridBagConstraints();
		cbpCon.gridx = 0;
		cbpCon.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
		scrollPane.setPreferredSize(new Dimension(150, 150));
		c.gridx = 0;
		c.gridy = 1;
		sharedCPanel.add(scrollPane, c);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(personBox.getSelectedItem() != null){
					JCheckBox checkbox = new JCheckBox(personBox.getSelectedItem().toString());
					personBox.removeItem(personBox.getSelectedItem());
					checkBoxPanel.add(checkbox,cbpCon);
					cbpCon.gridy += 1;
				}
			}
		});
	}
	
	public JPanel getPanel(){
		return sharedCPanel;
	}
}
