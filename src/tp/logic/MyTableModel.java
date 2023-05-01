package tp.logic;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyTableModel(Object[][] rowData, String[] columnNames) {
		super(rowData, columnNames);
	}

	public MyTableModel(Object[] objects, int i) {
		super(objects, i);
	}

	@Override
	public boolean isCellEditable(int row, int colum) {
		return false;
	}

}
