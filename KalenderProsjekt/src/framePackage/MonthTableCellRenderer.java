package framePackage;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
class MonthTableCellRenderer extends DefaultTableCellRenderer {
	private static final LineBorder lineBorder = new LineBorder(Color.black);;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		@SuppressWarnings("unchecked")
		JList<String> list = (JList<String>) value;
		if (list != null) {
			if (hasFocus) {
				list.setBorder(lineBorder);
			} else {
				if (list != null)
					list.setBorder(null);
			}
		}
		return list;
	}
}