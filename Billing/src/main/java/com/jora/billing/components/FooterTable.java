package com.jora.billing.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class FooterTable extends JTable implements KeyListener {

	private static final long serialVersionUID = 1L;

	private List<Column> listColumn = new ArrayList<>();

	private List<RowGroup> lstRowGroup = new ArrayList<>();

	private JDefaultModelTable tableModel;

	private JScrollPane scrollPane;

	private boolean rowSorterReq = false;

	private boolean focusOnRight = false;

	private boolean focusToEdit = false;

	private final boolean skipRepaint = true;

	public FooterTable(List<Column> listColumnProperty) {
		this.listColumn = listColumnProperty;
		initialize(this.listColumn);
		addKeyListener(this);
	}

	public JDefaultModelTable getTableModel() {
		return this.tableModel;
	}

	public void initialize(List<Column> listColumnProperty) {
		this.listColumn = listColumnProperty;
		List<Class<?>> listDataType = new ArrayList<>();
		List<String> listColumnName = new ArrayList<>();
		List<Boolean> listColumnEditable = new ArrayList<>();
		for (Column jColumn : this.listColumn) {
			listDataType.add(jColumn.getDataType());
			listColumnName.add(jColumn.getColumnName());
			listColumnEditable.add(jColumn.isColumnEditable());
		}
		this.tableModel = new JDefaultModelTable(listColumnName, listDataType, listColumnEditable);
		setModel(this.tableModel);
		setFillsViewportHeight(true);
		setShowGrid(true);
		setAutoResizeMode(0);
		setRowSelectionAllowed(false);
		setCellSelectionEnabled(true);
		getTableHeader().setVisible(false);
		getTableHeader().setPreferredSize(new Dimension(getColumnModel().getTotalColumnWidth(), 0));
		for (Column o : this.listColumn) {
			TableColumn tableColumn = getColumnModel().getColumn(this.listColumn.indexOf(o));
			if (null != o.getDefaultCellEditor())
				getColumnModel().getColumn(this.listColumn.indexOf(o)).setCellEditor(o.getDefaultCellEditor());
			if (!o.getDataKey().equalsIgnoreCase("")) {
				tableColumn.setHeaderValue(o.getDataKey());
				tableColumn.setIdentifier(o.getColumnName());
			}
			if (o.getColumnWidth() == 0)
				tableColumn.setMinWidth(o.getColumnWidth());
			tableColumn.setPreferredWidth(o.getColumnWidth());
			TableCellRenderer cellRenderer = new TableCellRenderer(getGridColor(),
					Boolean.valueOf(this.showVerticalLines), Boolean.valueOf(this.showHorizontalLines));
			cellRenderer.setHorizontalAlignment(o.getCellAlignment());
			if (o.getDataType() != Boolean.class)
				tableColumn.setCellRenderer(cellRenderer);
		}
	}

//	protected JTableHeader createDefaultTableHeader() {
//		return new JilabaGroupableTableHeader(this.columnModel);
//	}

	public void setColumn(List<Column> listColumn) {
		this.listColumn = listColumn;
		initialize(this.listColumn);
	}

	public void addRow(List<Object> listData) {
		this.tableModel.addRow(listData);
	}

	public void addRow(List<Object> listData, JDefaultModelTable.Rowtype m_Rowtype) {
		this.tableModel.addRow(listData, m_Rowtype);
	}

	public void replaceRow(List<Object> listData, int addRowIndex) {
		this.tableModel.replaceRow(listData, addRowIndex);
	}

	public void setValueAt(Object value, int row, int column) {
		this.tableModel.setValueAt(value, row, column);
	}

	public void setValueAt(Object value, int row, String strColumnName) {
		int column = this.tableModel.findColumn(strColumnName);
		this.tableModel.setValueAt(value, row, column);
	}

	public void removeRow(int row) {
		this.tableModel.removeRow(row);
	}

	public void clear() {
		this.tableModel.removeAll();
	}

	public Object getValueAt(int row, int column) {
		return this.tableModel.getValueAt(row, column);
	}

	public Object getValueAt(int arg0, String strColumnName) {
		int column = this.tableModel.findColumn(strColumnName);
		return this.tableModel.getValueAt(arg0, column);
	}

	public List<List<Object>> getRows() {
		return this.tableModel.getRows();
	}

	public List<Object> getRow(int row) {
		return this.tableModel.getRow(row);
	}

	public List<Map<String, Object>> getRowsWithName() {
		return this.tableModel.getRowsWithName();
	}

	public Map<String, Object> getRowWithName(int row) {
		return this.tableModel.getRowWithName(row);
	}

	public void setRowColor(int row, Color color) {
		this.tableModel.setRowColor(row, color);
	}

	public void setRowForeColor(int row, Color color) {
		this.tableModel.setRowForeColor(row, color);
	}

	public void setCellColor(int row, int column, Color color) {
		this.tableModel.setCellColor(row, column, color);
	}

	public void resizeColumns() {
		double sumCol = 0.0D;
		for (Column col : this.listColumn)
			sumCol += col.getColumnWidth();
		int width = (getWidth() == 0) ? getParent().getParent().getWidth() : getWidth();
		if (width == 0)
			return;
		double widthvarience = width / sumCol;
		for (Column col : this.listColumn) {
			double colWidth = col.getColumnWidth() * widthvarience;
			col.setColumnWidth((int) colWidth);
		}
		initialize(this.listColumn);
	}

	public void resizeColumns(double widthRatio) {
		double sumCol = 0.0D;
		for (Column col : this.listColumn)
			sumCol += col.getColumnWidth();
		int width = (getWidth() == 0) ? getParent().getParent().getWidth() : getWidth();
		width = (int) (width * widthRatio);
		if (width == 0)
			return;
		double widthvarience = width / sumCol;
		for (Column col : this.listColumn) {
			double colWidth = col.getColumnWidth() * widthvarience;
			col.setColumnWidth((int) colWidth);
		}
		initialize(this.listColumn);
	}

	public JScrollPane getScrollPane() {
		return this.scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public boolean isRowSorterReq() {
		return this.rowSorterReq;
	}

	public void setRowSorterReq(boolean rowSorterReq) {
		if (this.listColumn == null || this.listColumn.size() == 0)
			this.rowSorterReq = false;
		this.rowSorterReq = rowSorterReq;
		if (isRowSorterReq()) {
//			JilabaRowSorter customRowSorter = new JilabaRowSorter(this.tableModel);
//			setRowSorter(customRowSorter);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (isFocusToEdit()) {
				if (isEditing())
					getEditorComponent().requestFocusInWindow();
				e.consume();
			}
			if (isFocusOnRight()) {
				int nextColumnIndex = 0;
				if (getSelectedColumn() < getColumnCount())
					nextColumnIndex = getSelectedColumn() + 1;
				changeSelection(getSelectedRow(), nextColumnIndex, false, false);
			}
			e.consume();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean isFocusOnRight() {
		return this.focusOnRight;
	}

	public void setFocusOnRight(boolean focusOnRight) {
		this.focusOnRight = focusOnRight;
	}

	public boolean isFocusToEdit() {
		return this.focusToEdit;
	}

	public void setFocusToEdit(boolean focusToEdit) {
		this.focusToEdit = focusToEdit;
	}

	public List<RowGroup> getLstRowGroup() {
		return this.lstRowGroup;
	}

	public void setLstRowGroup(List<RowGroup> lstRowGroup) {
		this.lstRowGroup = lstRowGroup;
	}

	public List<Column> getcolumns() {
		return this.listColumn;
	}

}
