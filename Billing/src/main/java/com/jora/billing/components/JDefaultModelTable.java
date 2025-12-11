package com.jora.billing.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class JDefaultModelTable extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	private final List<String> listColumnName;

	private final transient List<List<Object>> listData;

	private final List<Class<?>> listClass;

	private final List<Boolean> listEditable;

	private final List<List<Color>> listColors;

	private final List<List<Color>> listForeColors;

	private final List<List<Rowtype>> listrowType;

	private final List<Integer> listGroupOrderLevel = new ArrayList<>();

	private final List<RowGroup> lstRowGroup;

	private boolean groupselectreq;

	public JDefaultModelTable() {
		this.listColumnName = new ArrayList<>();
		this.listClass = new ArrayList<>();
		this.listData = new ArrayList<>();
		this.listEditable = new ArrayList<>();
		this.listColors = new ArrayList<>();
		this.listForeColors = new ArrayList<>();
		this.listrowType = new ArrayList<>();
		this.lstRowGroup = new ArrayList<>();
	}

	public JDefaultModelTable(List<String> listColumns) {
		this.listColumnName = listColumns;
		this.listClass = new ArrayList<>();
		this.listData = new ArrayList<>();
		this.listEditable = new ArrayList<>();
		this.listColors = new ArrayList<>();
		this.listForeColors = new ArrayList<>();
		this.listrowType = new ArrayList<>();
		this.lstRowGroup = new ArrayList<>();
	}

	public JDefaultModelTable(List<String> listColumns, List<Class<?>> listClass) {
		this.listColumnName = listColumns;
		this.listClass = listClass;
		this.listData = new ArrayList<>();
		this.listEditable = new ArrayList<>();
		this.listColors = new ArrayList<>();
		this.listForeColors = new ArrayList<>();
		this.listrowType = new ArrayList<>();
		this.lstRowGroup = new ArrayList<>();
	}

	public JDefaultModelTable(List<String> listColumns, List<Class<?>> listClass, List<Boolean> listColumnEditable) {
		this.listColumnName = listColumns;
		this.listClass = listClass;
		this.listData = new ArrayList<>();
		this.listEditable = listColumnEditable;
		this.listColors = new ArrayList<>();
		this.listForeColors = new ArrayList<>();
		this.listrowType = new ArrayList<>();
		this.lstRowGroup = new ArrayList<>();
	}

	public JDefaultModelTable(List<String> listColumns, List<Class<?>> listClass, List<Boolean> listColumnEditable,
			List<RowGroup> lstRowGroup) {
		this.listColumnName = listColumns;
		this.listClass = listClass;
		this.listData = new ArrayList<>();
		this.listEditable = listColumnEditable;
		this.listColors = new ArrayList<>();
		this.listForeColors = new ArrayList<>();
		this.listrowType = new ArrayList<>();
		this.lstRowGroup = lstRowGroup;
	}

	public JDefaultModelTable(List<String> listColumns, List<Class<?>> listClass, List<Boolean> listColumnEditable,
			List<RowGroup> lstRowGroup, boolean groupselectreq) {
		this.listColumnName = listColumns;
		this.listClass = listClass;
		this.listData = new ArrayList<>();
		this.listEditable = listColumnEditable;
		this.listColors = new ArrayList<>();
		this.listForeColors = new ArrayList<>();
		this.listrowType = new ArrayList<>();
		this.lstRowGroup = lstRowGroup;
		this.groupselectreq = groupselectreq;
	}

	public void setGroupselectreq(boolean groupselectreq) {
		this.groupselectreq = groupselectreq;
	}

	public void addRow(List<Object> listObject, Rowtype m_Rowtype) {
		if (listObject == null)
			return;
		this.listData.add(listObject);
		List<Color> lstC = new ArrayList<>();
		List<Color> lstFC = new ArrayList<>();
		List<Rowtype> lstRowType = new ArrayList<>();
		for (int i = 0; i < listObject.size(); i++) {
			lstC.add(Color.WHITE);
			lstFC.add(Color.BLACK);
			lstRowType.add(m_Rowtype);
		}
		this.listColors.add(lstC);
		this.listForeColors.add(lstFC);
		this.listrowType.add(lstRowType);
		this.listGroupOrderLevel.add(Integer.valueOf(-1));
		fireTableRowsInserted(this.listData.size() - 1, this.listData.size() - 1);
	}

	public void addRow(List<Object> listObject, Rowtype m_Rowtype, int headerGroupLevel) {
		if (listObject == null)
			return;
		this.listData.add(listObject);
		List<Color> lstC = new ArrayList<>();
		List<Color> lstFC = new ArrayList<>();
		List<Rowtype> lstRowType = new ArrayList<>();
		for (int i = 0; i < listObject.size(); i++) {
			lstC.add(Color.WHITE);
			lstFC.add(Color.BLACK);
			lstRowType.add(m_Rowtype);
		}
		this.listColors.add(lstC);
		this.listForeColors.add(lstFC);
		this.listrowType.add(lstRowType);
		this.listGroupOrderLevel.add(Integer.valueOf(headerGroupLevel));
		fireTableRowsInserted(this.listData.size() - 1, this.listData.size() - 1);
	}

	public void addRow(List<Object> listObject) {
		if (listObject == null)
			return;
		this.listData.add(listObject);
		List<Color> lstC = new ArrayList<>();
		List<Color> lstFC = new ArrayList<>();
		for (int i = 0; i < listObject.size(); i++) {
			lstC.add(Color.WHITE);
			lstFC.add(Color.BLACK);
		}
		this.listColors.add(lstC);
		this.listForeColors.add(lstFC);
		this.listGroupOrderLevel.add(Integer.valueOf(0));
		fireTableRowsInserted(this.listData.size() - 1, this.listData.size() - 1);
	}

	public void replaceRow(List<Object> listObject, int addRowIndex) {
		if (listObject == null)
			return;
		this.listData.set(addRowIndex, listObject);
		List<Color> lstC = new ArrayList<>();
		List<Color> lstFC = new ArrayList<>();
		for (int i = 0; i < listObject.size(); i++) {
			lstC.add(Color.WHITE);
			lstFC.add(Color.BLACK);
		}
		this.listColors.add(lstC);
		this.listForeColors.add(lstFC);
		fireTableRowsInserted(this.listData.size() - 1, this.listData.size() - 1);
	}

	public void addColumn(Object columnName) {
		this.listClass.add(String.class);
		this.listColumnName.add(String.valueOf(columnName));
	}

	public void addColumn(String columnName) {
		this.listClass.add(String.class);
		this.listColumnName.add(columnName);
	}

	public void addColumn(String strColumnName, Class<?> oClass) {
		this.listColumnName.add(strColumnName);
		this.listClass.add(oClass);
	}

	public void addColumn(List<String> listColumn) {
		for (String column : listColumn)
			addColumn(column);
	}

	public void setColumn(List<String> listColumn) {
		this.listColumnName.clear();
		this.listClass.clear();
		for (String column : listColumn)
			addColumn(column);
	}

	public int getColumnCount() {
		if (this.listColumnName == null)
			return 0;
		return this.listColumnName.size();
	}

	public String getColumnName(int column) {
		return this.listColumnName.get(column);
	}

	public int getRowCount() {
		if (this.listData == null)
			return 0;
		return this.listData.size();
	}

	public Object getValueAt(int row, int column) {
		if (this.listData.isEmpty())
			return null;
		return ((List) this.listData.get(row)).get(column);
	}

	public boolean isCellEditable(int row, int column) {
		if (this.listEditable.isEmpty())
			return false;
		return ((Boolean) this.listEditable.get(column)).booleanValue();
	}

	public void setValueAt(Object aValue, int row, int column) {
		if (this.listData.isEmpty() || this.listData.size() <= row || column >= ((List) this.listData.get(row)).size())
			return;
		if (this.groupselectreq && aValue instanceof Boolean) {
			int startRow = -1;
			int endRow = -1;
			int i;
			for (i = 0; i < this.listrowType.size(); i++) {
				if (((List<Rowtype>) this.listrowType.get(i)).get(0) == Rowtype.GroupHeader) {
					startRow = i;
				} else if ((((List<Rowtype>) this.listrowType.get(i)).get(0) == Rowtype.GroupTotal
						|| i == this.listrowType.size() - 1) && i > startRow && row <= i) {
					endRow = i;
					break;
				}
			}
			if (startRow != -1 && endRow != -1)
				for (i = startRow + 1; i <= endRow; i++) {
					if (((List<Rowtype>) this.listrowType.get(i)).get(0) == Rowtype.Detail) {
						((List<Object>) this.listData.get(i)).set(column, aValue);
						fireTableCellUpdated(i, column);
					}
				}
		} else {
			((List<Object>) this.listData.get(row)).set(column, aValue);
			fireTableCellUpdated(row, column);
		}
	}

	public Class<?> getColumnClass(int columnIndex) {
		return this.listClass.get(columnIndex);
	}

	public void removeRow(int arg0) {
		if (this.listData.isEmpty())
			return;
		this.listData.remove(arg0);
		this.listColors.remove(arg0);
		this.listForeColors.remove(arg0);
		fireTableRowsDeleted(0, this.listData.size());
		fireTableDataChanged();
	}

	public void setCellForeColor(int row, int column, Color color) {
		if (this.listForeColors.isEmpty())
			return;
		((List<Color>) this.listForeColors.get(row)).add(column, color);
	}

	public void removeAll() {
		if (this.listData.isEmpty())
			return;
		this.listData.clear();
		this.listColors.clear();
		this.listForeColors.clear();
		this.listrowType.clear();
		fireTableRowsDeleted(0, this.listData.size());
		fireTableDataChanged();
	}

	public List<List<Object>> getRows() {
		return this.listData;
	}

	public List<Object> getRow(int row) {
		if (this.listData.isEmpty())
			return null;
		return this.listData.get(row);
	}

	public Color getCellColor(int row, int column) {
		if (this.listColors.isEmpty())
			return null;
		return ((List<Color>) this.listColors.get(row)).get(column);
	}

	public Rowtype getRowType(int row) {
		if (this.listrowType.isEmpty() || row >= this.listrowType.size())
			return null;
		return ((List<Rowtype>) this.listrowType.get(row)).get(0);
	}

	public Color getCellForeColor(int row, int column) {
		if (this.listForeColors.isEmpty())
			return null;
		return ((List<Color>) this.listForeColors.get(row)).get(column);
	}

	public void setRowColor(int row, Color color) {
		List<Color> lstC = this.listColors.get(row);
		if (lstC == null)
			return;
		for (int i = 0; i < lstC.size(); i++)
			lstC.set(i, color);
		this.listColors.set(row, lstC);
		fireTableRowsUpdated(row, row);
	}

	public void setRowForeColor(int row, Color color) {
		List<Color> lstC = this.listForeColors.get(row);
		if (lstC == null)
			return;
		for (int i = 0; i < lstC.size(); i++)
			lstC.set(i, color);
		this.listForeColors.set(row, lstC);
		fireTableRowsUpdated(row, row);
	}

	public void setCellColor(int row, int column, Color color) {
		if (this.listColors.isEmpty())
			return;
		if (this.listColors.get(row) == null)
			return;
		((List<Color>) this.listColors.get(row)).set(column, color);
		fireTableCellUpdated(row, column);
	}

	public List<Map<String, Object>> getRowsWithName() {
		if (this.listData.isEmpty())
			return null;
		List<Map<String, Object>> listMap = new ArrayList<>();
		for (List<Object> liData : this.listData) {
			Map<String, Object> data = new LinkedHashMap<>();
			for (int i = 0; i < liData.size(); i++)
				data.put(this.listColumnName.get(i), liData.get(i));
			listMap.add(data);
		}
		return listMap;
	}

	public Map<String, Object> getRowWithName(int row) {
		if (this.listData.isEmpty())
			return null;
		Map<String, Object> data = new LinkedHashMap<>();
		List<Object> ldata = this.listData.get(row);
		for (int i = 0; i < ldata.size(); i++)
			data.put(this.listColumnName.get(i), ldata.get(i));
		return data;
	}

	public List<List<Object>> getListData() {
		return this.listData;
	}

	public int getGroupOrderLevel(int row) {
		if (row < 0 || row >= this.listGroupOrderLevel.size())
			return 0;
		return ((Integer) this.listGroupOrderLevel.get(row)).intValue();
	}

	public enum Rowtype {
		GroupHeader, Detail, GroupTotal, FooterTotal;
	}
}
