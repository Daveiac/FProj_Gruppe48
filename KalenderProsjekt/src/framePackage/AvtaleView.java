package framePackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AvtaleView extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//Alle labels
	private JLabel overskrift = new JLabel("Avtale for                  ");
	private JLabel tittel = new JLabel("Tittel:                                    ");
	private JLabel start = new JLabel("Starttid: ");
	private JLabel slutt = new JLabel("Sluttid: ");
	private JLabel sted = new JLabel("Sted: ");
	private JLabel moteRom = new JLabel("Møterom: ");
	private JLabel deltaker = new JLabel("Deltaker: ");
	private JLabel beskrivelse = new JLabel("Beskrivelse: ");
	private JLabel alarm = new JLabel("Alarm: ");
	private JLabel tidspunktAlarm = new JLabel("Tidspunkt for alarm: ");
	
	//Lagar alternativ for TidsComboBoxane
	String[] hour = new String[] {"00","01","02","03","04","05","06","07","08","09","10"
			,"11","12","13","14","15","16","17","18","19","20","21","22","23"};
	String[] min = new String[] {"00","05","10","15","20","25","30","35","40","45","50","55"};
	
	//Alle tekstfelt og ComboBoxar
	private JTextField tittelComponent = new JTextField();
	private JComboBox<String> startHourComponent = new JComboBox<String>(hour);
	private JComboBox<String> startMinComponent = new JComboBox<String>(min);
	private JComboBox<String> sluttHourComponent = new JComboBox<String>(hour);
	private JComboBox<String> sluttMinComponent = new JComboBox<String>(min);
	private JTextField stedComponent = new JTextField();
	private JComboBox<String> moteRomComponent = new JComboBox<String>();
	private JComboBox<String> deltakerComponent = new JComboBox<String>();
	private JTextArea beskrivelseComponent = new JTextArea();
	private JComboBox<String> tidspunktAlarmHourComponent = new JComboBox<String>(hour);
	private JComboBox<String> tidspunktAlarmMinComponent = new JComboBox<String>(min);	
	private JList<data.Person> deltakerListe = new JList<data.Person>();
	final DefaultListModel<data.Person> listModel = new DefaultListModel<data.Person>();
	private JCheckBox alarmComponent = new JCheckBox();
	
	//Buttons
	private JButton leggTilDeltakerKnapp = new JButton("Legg til");
	private JButton slettDeltakerKnapp = new JButton("Slett");
	private JButton opprettKnapp = new JButton("Opprett avtale");
	private JButton endreKnapp = new JButton("Endre avtale");
	private JButton slettKnapp = new JButton("Slett avtale");
	
	public AvtaleView(){
		
		//beskrivelseComponent
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		beskrivelseComponent.setLineWrap(true);
		beskrivelseComponent.setWrapStyleWord(true);
		beskrivelseComponent.setColumns(8);
		beskrivelseComponent.setRows(8);
		scrollPane.setViewportView(beskrivelseComponent);
		
		deltakerListe.setPreferredSize( new Dimension(200,200) );
		
		//Tilpassar storleiken på tekstfelta
		tittelComponent.setPreferredSize(new Dimension(300,20));
		startHourComponent.setPreferredSize(new Dimension(100,20));
		startMinComponent.setPreferredSize(new Dimension(100,20));
		sluttHourComponent.setPreferredSize(new Dimension(100,20));
		sluttMinComponent.setPreferredSize(new Dimension(100,20));
		stedComponent.setPreferredSize(new Dimension(100,20));
		beskrivelseComponent.setPreferredSize(new Dimension(200,200));
		tidspunktAlarmHourComponent.setPreferredSize(new Dimension(100,20));
		tidspunktAlarmMinComponent.setPreferredSize(new Dimension(100,20));
		
		//Legg til deltakere i deltakerlista
		leggTilDeltakerKnapp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				listModel.addElement( new data.Person("Per",5,"sd","sd","sd","sd") );
			}
		});
		deltakerListe = new JList<data.Person>( listModel );
		
		//Lagar layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		
		GridBagConstraints d = new GridBagConstraints();
		d.anchor = GridBagConstraints.PAGE_END;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(overskrift, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		this.add(tittel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 5;
		this.add(tittelComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        this.add(start, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(startHourComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(startMinComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(slutt, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(sluttHourComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 3;
        c.gridwidth = 1;
        this.add(sluttMinComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        this.add(sted, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 5;
        this.add(stedComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        this.add(moteRom, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 5;
        this.add(moteRomComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        this.add(deltaker, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 5;
        this.add(deltakerComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 7;
        c.gridy = 6;
        c.gridwidth = 5;
        this.add(leggTilDeltakerKnapp, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 7;
        this.add(deltaker, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 7;
        c.gridwidth = 5;
        this.add(new JScrollPane(deltakerListe), c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 7;
        c.gridy = 7;
        this.add(slettDeltakerKnapp, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 10;
        this.add(beskrivelse, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 10;
        c.gridwidth = 5;
        this.add(scrollPane, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 16;
        this.add(alarm, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 16;
        this.add(alarmComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 17;
        this.add(tidspunktAlarm, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 17;
        c.gridwidth = 1;
        this.add(tidspunktAlarmHourComponent, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 17;
        c.gridwidth = 1;
        this.add(tidspunktAlarmMinComponent, c);
        
        d.fill = GridBagConstraints.VERTICAL;
        d.gridx = 0;
        d.gridy = 18;
        d.gridwidth = 1;
        this.add(opprettKnapp, d);
        
        d.fill = GridBagConstraints.VERTICAL;
        d.gridx = 1;
        d.gridy = 18;
        d.gridwidth = 1;
        this.add(endreKnapp, d);
        
        d.fill = GridBagConstraints.VERTICAL;
        d.gridx = 2;
        d.gridy = 18;
        d.gridwidth = 5;
        this.add(slettKnapp, d);
        
	}

	/*
	//keyListener
	class tekstHoyrar implements KeyListener {
		public void keyPressed(KeyEvent e) {
		}
		
		public void keyReleased(KeyEvent e) {
			//Gjer det umogeleg for brukaren og skrive inn ugyldige tidspunkt
			if(startHourComponent.getText() > 0 && startHourComponent.getText() <= 23){
				startHourComponent.setText( startHourComponent.getText() );
			}
			if(startMinComponent.getText() > 0 && startMinComponent.getText() <= 59){
				startMinComponent.setText( startMinComponent.getText() );
			}
			if(sluttHourComponent.getText() > 0 && sluttHourComponent.getText() <= 23){
				sluttHourComponent.setText( sluttHourComponent.getText() );
			}
			if(sluttMinComponent.getText() > 0 && sluttMinComponent.getText() <= 59){
				sluttMinComponent.setText( sluttMinComponent.getText() );
			}
			if(tidspunktAlarmHourComponent.getText() > 0 && tidspunktAlarmHourComponent.getText() <= 23){
				tidspunktAlarmHourComponent.setText( tidspunktAlarmHourComponent.getText() );
			}
			if(tidspunktAlarmMinComponent.getText() > 0 && tidspunktAlarmMinComponent.getText() <= 59){
				tidspunktAlarmMinComponent.setText( tidspunktAlarmMinComponent.getText() );
			}
		}
	}
	*/
	public static void main(String[] args){
		JFrame frame = new JFrame("Avtale");
		frame.setPreferredSize(new Dimension(550, 700));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new AvtaleView());
		frame.pack();
		frame.setVisible(true);
	}

}
