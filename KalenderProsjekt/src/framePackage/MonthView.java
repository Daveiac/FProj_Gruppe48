package framePackage;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonthView {

	private JPanel monthView;
	private JLabel lbluke;
	private JLabel lblmandag;
	private JLabel lbltirdag;
	private JLabel lblonsdag;
	private JLabel lbltorsdag;
	private JLabel lblfredag;
	private JLabel lbllørdag;
	private JLabel lblsøndag;
	

	public static void main(String[] args) {
		DayView dw = new DayView();
		JFrame frame = new JFrame("Day view test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(dw.getDayView());
		frame.setSize(600, 500);
		frame.setVisible(true);

	}

	public MonthView() {
		initialize();
	}

	private void initialize() {
		
	}

	public JPanel getMonthView() {
		return monthView;
	}

}
