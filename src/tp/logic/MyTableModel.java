package tp.logic;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	public MyTableModel(Object[][] rowData, String[] columnNames) {
		super(rowData, columnNames);
	}

	@Override
	public boolean isCellEditable(int row, int colum) {
		return false;
	}

}
