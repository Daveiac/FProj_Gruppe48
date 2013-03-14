package framePackage;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonTableCellEditor extends DefaultCellEditor {

	public ButtonTableCellEditor(JCheckBox checkBox) {
		super(checkBox);
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		
		return table;
		
	}

}
