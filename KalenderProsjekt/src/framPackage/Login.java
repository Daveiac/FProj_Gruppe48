package framPackage;

import javax.swing.*;

public class Login extends JPanel{
	
	private JPanel panel;
	private JLabel userLabel, passwordLabel;
	private JTextField userField, passwordField;
	private JButton button;
	
	public Login(){
		
		userLabel = new JLabel("Brukernavn");
		passwordLabel = new JLabel("Passord");
		userField = new JTextField(20);
		passwordField = new JTextField(20);
		button = new JButton("Logg inn");
	}
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("Logg inn");
		frame.getContentPane().add(new Login());
		frame.pack();
		frame.setVisible(true);
	}
}
