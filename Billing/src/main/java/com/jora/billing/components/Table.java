package com.jora.billing.components;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.plaf.TableUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.jora.billing.common.CommonValues;

public class Table extends JTable implements KeyListener {
	private static final long serialVersionUID = 1L;

	private List<Integer> lstFixedTableColumn;

	private boolean blnfooterdata = false;

	private List<Column> listColumn = new ArrayList<>();

	private List<RowGroup> lstRowGroup = new ArrayList<>();

	private JDefaultModelTable tableModel;

	private JScrollPane scrollPane;

	private boolean rowSorterReq = false, focusOnRight = false, focusToEdit = false;

	private boolean skipRepaint = true;

	private FooterTable footertable;

	private Map<String, Object> Totalmap = null;

	private String reportHeading = "";

	private String reportName = "";

	private Boolean showHorizontalLines = Boolean.valueOf(true);

	private Boolean showVerticalLines = Boolean.valueOf(true);

	private Boolean showGroupHeaders = Boolean.valueOf(true);

	private boolean groupselectreq = false;

	private Color gridColor;

	public Table() {
		initialize(this.listColumn);
		addKeyListener(this);
	}

	public Table(Boolean showHorizontalLines, Boolean showVerticalLines, Color gridColor) {
		initialize(this.listColumn);
		this.showHorizontalLines = showHorizontalLines;
		this.showVerticalLines = showVerticalLines;
		this.gridColor = gridColor;
		addKeyListener(this);
	}

	public Table(TableModel arg0) {
		super(arg0);
		initialize(this.listColumn);
		addKeyListener(this);
	}

	public Table(int arg0, int arg1) {
		super(arg0, arg1);
		initialize(this.listColumn);
		addKeyListener(this);
	}

	public Table(Object[][] arg0, Object[] arg1) {
		super(arg0, arg1);
		initialize(this.listColumn);
		addKeyListener(this);
	}

	public Table(TableModel arg0, TableColumnModel arg1) {
		super(arg0, arg1);
		initialize(this.listColumn);
		addKeyListener(this);
	}

	public Table(TableModel arg0, TableColumnModel arg1, ListSelectionModel arg2) {
		super(arg0, arg1, arg2);
		initialize(this.listColumn);
		addKeyListener(this);
	}

	public Table(List<Column> listColumnProperty, boolean footerdata) {
		this.listColumn = listColumnProperty;
		this.blnfooterdata = footerdata;
		initialize(listColumnProperty);
		addKeyListener(this);
	}

	public Table(List<Column> listColumnProperty) {
		this.listColumn = listColumnProperty;
		initialize(listColumnProperty);
		addKeyListener(this);
	}

	public List<Column> getListColumn() {
		return this.listColumn;
	}

	public FooterTable getfootertable() {
		return this.footertable;
	}

	public List<Column> getcolumns() {
		return this.listColumn;
	}

	public void setGroupselectreq(boolean groupselectreq) {
		this.groupselectreq = groupselectreq;
		if (this.tableModel != null)
			this.tableModel.setGroupselectreq(groupselectreq);
	}

	public void initialize(List<Column> listColumnProperty) {
		this.lstFixedTableColumn = new ArrayList<>();
		this.listColumn = listColumnProperty;
		List<Class<?>> listDataType = new ArrayList<>();
		List<String> listColumnName = new ArrayList<>();
		List<Boolean> listColumnEditable = new ArrayList<>();
		for (Column jColumn : this.listColumn) {
			listDataType.add(jColumn.getDataType());
			if (jColumn.getDataKey().equalsIgnoreCase("")) {
				listColumnName.add(jColumn.getColumnName());
			} else {
				listColumnName.add(jColumn.getDataKey());
			}
			listColumnEditable.add(jColumn.isColumnEditable());
		}
		this.tableModel = new JDefaultModelTable(listColumnName, listDataType, listColumnEditable, getLstRowGroup());
		this.tableModel.setGroupselectreq(this.groupselectreq);
		setModel(this.tableModel);
		setFillsViewportHeight(true);
		setShowGrid(true);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (Column o : this.listColumn) {
			int index = this.listColumn.indexOf(o);
			TableColumn tableColumn = getColumnModel().getColumn(index);
			if (o.getDataType() == Boolean.class) {
				getColumnModel().getColumn(index).setCellEditor(new TableCheckBoxEditor());
			} else if (null != o.getDefaultCellEditor()) {
				getColumnModel().getColumn(index).setCellEditor(o.getDefaultCellEditor());
			}
			if (!o.getDataKey().equalsIgnoreCase("")) {
				tableColumn.setIdentifier(o.getDataKey());
				tableColumn.setHeaderValue(o.getColumnName());
			}
			if (o.getColumnFixed() == Column.ColumnFixed.YES)
				this.lstFixedTableColumn.add(Integer.valueOf(index));
			if (o.getColumnWidth() == 0)
				tableColumn.setMinWidth(o.getColumnWidth());
			if (o.getColumnWidth() == 0)
				tableColumn.setMinWidth(o.getColumnWidth());
			tableColumn.setPreferredWidth(o.getColumnWidth());
			TableCellRenderer cellRenderer = new TableCellRenderer(gridColor, this.showVerticalLines,
					this.showHorizontalLines);
			cellRenderer.setHorizontalAlignment(o.getCellAlignment());
			if (o.getDataType() != Boolean.class)
				tableColumn.setCellRenderer(cellRenderer);
			if (this.blnfooterdata)
				this.footertable = new FooterTable(this.listColumn);
			setFixedTableColumns(this.lstFixedTableColumn);
		}
//		SkipHiddenColumnNav.install(this);
	}

	public List<Integer> getFixedTableColumns() {
		return this.lstFixedTableColumn;
	}

	public void setFixedTableColumns(List<Integer> lstFixedColumn) {
		this.lstFixedTableColumn = lstFixedColumn;
	}

	public void setDataSource(List<RowGroup> lstRowGroup, List<Map<String, Object>> lstData, String reportHeading,
			String ReportName) throws Exception {
		try {
			if (this.blnfooterdata)
				this.footertable.clear();
			this.reportHeading = reportHeading;
			this.reportName = ReportName;
			setLstRowGroup(lstRowGroup);
		} catch (Exception e) {
			throw e;
		}
	}

//	private void processGroupLevel(List<Map<String, Object>> currentData, String[] groupNames, int currentGroupLevel,
//			Map<String, Object> lastGroupRunningmap, Map<String, Object> lastRunningmap) throws Exception {
//		if (currentGroupLevel >= groupNames.length) {
//			for (Map<String, Object> row : currentData) {
//				Map<String, Map<String, Object>> runningResult = getRuningvalues(row, lastGroupRunningmap,
//						lastRunningmap);
//				if (runningResult.containsKey("Grouprunning"))
//					lastGroupRunningmap.putAll(runningResult.get("Grouprunning"));
//				if (runningResult.containsKey("Running"))
//					lastRunningmap.putAll(runningResult.get("Running"));
//				addRow(new ArrayList(row.values()), JDefaultModelTable.Rowtype.Detail);
//			}
//			return;
//		}
//		String currentGroupColumn = groupNames[currentGroupLevel];
//		Map<Object, List<Map<String, Object>>> groupedData = (Map<Object, List<Map<String, Object>>>) currentData
//				.stream().collect(Collectors.groupingBy(row -> row.get(currentGroupColumn), LinkedHashMap::new,
//						Collectors.toList()));
//		for (Object groupKey : groupedData.keySet()) {
//			List<Map<String, Object>> groupList = groupedData.get(groupKey);
//			List<Object> groupHeader = new ArrayList(Collections.nCopies(getColumnCount(), ""));
//			groupHeader.set(0, groupKey);
//			if (this.showGroupHeaders.booleanValue())
//				addRow(groupHeader, JDefaultModelTable.Rowtype.GroupHeader, currentGroupLevel);
//			processGroupLevel(groupList, groupNames, currentGroupLevel + 1, lastGroupRunningmap, lastRunningmap);
//			if (groupList.size() > 1)
//				getGroupTotalvalues(new String[] { currentGroupColumn }, groupList, lastGroupRunningmap);
//		}
//	}

	private List<Object> getAggeratorvalue(String groupColumn, List<Map<String, Object>> list,
			Map<String, Object> runninglastmap) throws Exception {
		try {
			List<Object> lstAggeratorData = new ArrayList();
			for (Column col : this.listColumn) {
				Object data = null;
				if (col.getAggregators() != null)
					if (col.getDataType() == Double.class || col.getDataType().toString().equalsIgnoreCase("double")) {
						double value = 0.0D;
						if (col.getAggregators() == Column.Aggregators.Sum) {
							value = list.stream().mapToDouble(s -> (s.get(col.getDataKey()) == null) ? 0.0D
									: Double.parseDouble(String.valueOf(s.get(col.getDataKey())))).sum();
						} else if (col.getAggregators() == Column.Aggregators.Runningbalance) {
							value = (runninglastmap != null && runninglastmap.containsKey(col.getDataKey()))
									? Double.parseDouble(String.valueOf(runninglastmap.get(col.getDataKey())))
									: 0.0D;
						} else if (col.getAggregators() == Column.Aggregators.GroupRunningbalance) {
							value = (runninglastmap != null && runninglastmap.containsKey(col.getDataKey()))
									? Double.parseDouble(String.valueOf(runninglastmap.get(col.getDataKey())))
									: 0.0D;
						}
						if (!col.getDataFormat().equalsIgnoreCase("")) {
							data = String.format(col.getDataFormat(), new Object[] { Double.valueOf(value) });
						} else {
							data = Double.valueOf(value);
						}
					} else if (col.getDataType() == Integer.class
							|| col.getDataType().toString().equalsIgnoreCase("int")) {
						if (col.getAggregators() == Column.Aggregators.Sum) {
							int value = list.stream().mapToInt(s -> (s.get(col.getDataKey()) == null) ? 0
									: Integer.parseInt(s.get(col.getDataKey()).toString())).sum();
							data = Integer.valueOf(value);
						} else if (col.getAggregators() == Column.Aggregators.Runningbalance) {
							data = Integer
									.valueOf((runninglastmap != null && runninglastmap.containsKey(col.getDataKey()))
											? Integer.parseInt(runninglastmap.get(col.getDataKey()).toString())
											: 0);
						} else if (col.getAggregators() == Column.Aggregators.GroupRunningbalance) {
							data = Integer
									.valueOf((runninglastmap != null && runninglastmap.containsKey(col.getDataKey()))
											? Integer.parseInt(runninglastmap.get(col.getDataKey()).toString())
											: 0);
						}
					} else if (col.getDataType() == String.class) {
						if (groupColumn.equalsIgnoreCase("")) {
							data = " Total ";
						} else {
							Object actualGroupKeyValue = list.isEmpty() ? null : ((Map) list.get(0)).get(groupColumn);
							data = ((actualGroupKeyValue != null) ? actualGroupKeyValue.toString() : "") + " Total ";
						}
					}
				lstAggeratorData.add(data);
			}
			return lstAggeratorData;
		} catch (Exception e) {
			throw e;
		}
	}

	private void getGroupTotalvalues(String[] GroupNames, List<Map<String, Object>> list, Map<String, Object> lastmap)
			throws Exception {
		try {
			if (!list.isEmpty() && GroupNames.length > 0) {
				String currentGroupColumnForTotal = GroupNames[0];
				List<Object> lstAggeratorData = getAggeratorvalue(currentGroupColumnForTotal, list, lastmap);
				addRow(lstAggeratorData, JDefaultModelTable.Rowtype.GroupTotal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void getfooterValues(String[] GroupNames, List<Map<String, Object>> list, Map<String, Object> lastmap)
			throws Exception {
		try {
			List<Object> lstAggeratorData = getAggeratorvalue("", list, lastmap);
			if (footertable != null) {
				footertable.addRow(lstAggeratorData, JDefaultModelTable.Rowtype.FooterTotal);
			} else {
				System.err.println("WARNING: Footertable is null. Cannot add footer values.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

//	private Map<String, Map<String, Object>> getRuningvalues(Map<String, Object> map, Map<String, Object> lastgroupmap,
//			Map<String, Object> lastmap) throws Exception {
//		try {
//			Map<String, Map<String, Object>> RunningObject = new LinkedHashMap<>();
//			Map<String, Object> currentGroupRunningMap = (lastgroupmap != null) ? new LinkedHashMap<>(lastgroupmap)
//					: new LinkedHashMap<>();
//			Map<String, Object> currentRunningMap = (lastmap != null) ? new LinkedHashMap<>(lastmap)
//					: new LinkedHashMap<>();
//			Map<String, Object> currentTotalMap = (this.Totalmap != null) ? new LinkedHashMap<>(this.Totalmap)
//					: new LinkedHashMap<>();
//			List<Column> aggGroupColumn = (List<Column>) this.listColumn.stream()
//					.filter(s -> (s.getAggregators() == Column.Aggregators.GroupRunningbalance
//							|| s.getAggregators() == Column.Aggregators.Runningbalance))
//					.sorted(Comparator.comparing(Column::getDataKey)).collect(Collectors.toList());
//			for (Column col : aggGroupColumn) {
//				Object cellValue = map.get(col.getDataKey());
//				double numericCellValue = 0.0D;
//				int integerCellValue = 0;
//				if (col.getDataType() == Double.class && cellValue != null) {
//					numericCellValue = Double.parseDouble(cellValue.toString());
//				} else if (col.getDataType() == Integer.class && cellValue != null) {
//					integerCellValue = Integer.parseInt(cellValue.toString());
//				}
//				if (col.getDataType() == Double.class) {
//					double prevRunning = (currentGroupRunningMap.getOrDefault(col.getDataKey(),
//							(V) Double.valueOf(0.0D)) instanceof Number)
//									? ((Number) currentGroupRunningMap.getOrDefault(col.getDataKey(),
//											Double.valueOf(0.0D))).doubleValue()
//									: 0.0D;
//					double prevTotal = (currentTotalMap.getOrDefault(col.getDataKey(),
//							(V) Double.valueOf(0.0D)) instanceof Number)
//									? ((Number) currentTotalMap.getOrDefault(col.getDataKey(), Double.valueOf(0.0D)))
//											.doubleValue()
//									: 0.0D;
//					double newRunning = prevRunning + numericCellValue;
//					double newTotal = prevTotal + numericCellValue;
//					currentGroupRunningMap.put(col.getDataKey(), Double.valueOf(newRunning));
//					currentTotalMap.put(col.getDataKey(), Double.valueOf(newTotal));
//					continue;
//				}
//				if (col.getDataType() == Integer.class) {
//					int prevRunning = (currentGroupRunningMap.getOrDefault(col.getDataKey(),
//							(V) Integer.valueOf(0)) instanceof Number)
//									? ((Number) currentGroupRunningMap.getOrDefault(col.getDataKey(),
//											Double.valueOf(0.0D))).intValue()
//									: 0;
//					int prevTotal = (currentTotalMap.getOrDefault(col.getDataKey(),
//							(V) Integer.valueOf(0)) instanceof Number)
//									? ((Number) currentTotalMap.getOrDefault(col.getDataKey(), Double.valueOf(0.0D)))
//											.intValue()
//									: 0;
//					int newRunning = prevRunning + integerCellValue;
//					int newTotal = prevTotal + integerCellValue;
//					currentGroupRunningMap.put(col.getDataKey(), Integer.valueOf(newRunning));
//					currentTotalMap.put(col.getDataKey(), Integer.valueOf(newTotal));
//				}
//			}
//			RunningObject.put("Grouprunning", new LinkedHashMap<>(currentGroupRunningMap));
//			RunningObject.put("Running", new LinkedHashMap<>(currentRunningMap));
//			RunningObject.put("FooterTotal", new LinkedHashMap<>(currentTotalMap));
//			this.Totalmap.putAll(currentTotalMap);
//			return RunningObject;
//		} catch (Exception e) {
//			throw e;
//		}
//	}

	public void setUI(TableUI ui) {
		if (!this.skipRepaint) {
			super.setUI(ui);
			repaint();
		}
	}

//	protected JTableHeader createDefaultTableHeader() {
//		return new JilabaGroupableTableHeader(this.columnModel);
//	}

	public void setColumn(List<Column> listColumn) {
		this.listColumn = listColumn;
		initialize(this.listColumn);
	}

	public void setColumn(List<Column> listColumn, Boolean blnfooterdata) {
		this.blnfooterdata = blnfooterdata.booleanValue();
		this.listColumn = listColumn;
		initialize(this.listColumn);
	}

	public void addRow(List<Object> listData) {
		this.tableModel.addRow(listData);
	}

	public void addRow(List<Object> listData, JDefaultModelTable.Rowtype m_Rowtype) {
		this.tableModel.addRow(listData, m_Rowtype);
	}

	public void addRow(List<Object> listData, JDefaultModelTable.Rowtype m_Rowtype, int headerGroupLevel) {
		this.tableModel.addRow(listData, m_Rowtype, headerGroupLevel);
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
		if (this.footertable != null)
			this.footertable.getTableModel().removeAll();
	}

	private void posting() throws Exception {
//		JPostingDialog jPostingDialog = new JPostingDialog(this, this.reportHeading, this.reportName, this.reportName);
//		jPostingDialog.setOpername("");
//		jPostingDialog.setVisible(true);
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

	public void setCellForeColor(int row, int column, Color color) {
		this.tableModel.setCellForeColor(row, column, color);
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
//		if (this.listColumn == null || this.listColumn.size() == 0)
//			this.rowSorterReq = false;
//		this.rowSorterReq = rowSorterReq;
//		if (isRowSorterReq()) {
//			JilabaRowSorter customRowSorter = new JilabaRowSorter(this.tableModel);
//			setRowSorter(customRowSorter);
//		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
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
			} else if (e.getKeyCode() == KeyEvent.VK_F4 && !this.reportHeading.equalsIgnoreCase("")) {
				posting();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
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

//	public void processDataSource(List<?> originalList, List<RowGroup> lstRowGroup, String reportHeading,
//			String reportName, Optional<List<JilabaSortColumn>> sortColumns) throws Exception {
//		try {
//			if (this.blnfooterdata)
//				this.footertable.clear();
//			this.reportHeading = reportHeading;
//			this.reportName = reportName;
//			setLstRowGroup(Optional.<List<RowGroup>>ofNullable(lstRowGroup).orElseGet(ArrayList::new));
//			List<Map<String, Object>> lstMapData = convertDataToListOfMaps(originalList);
//			List<Column> jilcols = getcolumns();
//			List<Map<String, Object>> lstData = (List<Map<String, Object>>) lstMapData.stream().map(dataMap -> {
//				Map<String, Object> orderedMap = new LinkedHashMap<>();
//				for (Column column : jilcols) {
//					String key = column.getDataKey();
//					if (dataMap.containsKey(key)) {
//						orderedMap.put(key, dataMap.get(key));
//						continue;
//					}
//					if (key.equalsIgnoreCase("model")) {
//						orderedMap.put(key, dataMap);
//						continue;
//					}
//					if (column.getDataType() == Integer.class || column.getDataType() == int.class) {
//						orderedMap.put(key, Integer.valueOf(0));
//						continue;
//					}
//					if (column.getDataType() == Double.class || column.getDataType() == double.class) {
//						orderedMap.put(key, "0.00");
//						continue;
//					}
//					if (column.getDataType() == Boolean.class || column.getDataType() == boolean.class) {
//						orderedMap.put(key, Boolean.valueOf(false));
//						continue;
//					}
//					orderedMap.put(key, "");
//				}
//				return orderedMap;
//			}).collect(Collectors.toList());
//			if (sortColumns.isPresent() && !((List) sortColumns.get()).isEmpty()) {
//				Comparator<Map<String, Object>> comparator = null;
//				for (JilabaSortColumn sortCol : sortColumns.get()) {
//					String column = sortCol.getColumnName();
//					Optional<Column> jilabaColumnOptional = jilcols.stream().filter(c -> c.getDataKey().equals(column))
//							.findFirst();
//					if (jilabaColumnOptional.isPresent()) {
//						Class<?> columnDataType = ((Column) jilabaColumnOptional.get()).getDataType();
//						Comparator<Map<String, Object>> currentComparator = (o1, o2) -> {
//							Object v1 = o1.get(column);
//							Object v2 = o2.get(column);
//							return (v1 == null && v2 == null) ? 0
//									: ((v1 == null) ? 1
//											: ((v2 == null) ? -1
//													: (columnDataType.equals(Integer.class)
//															? Integer.valueOf(v1.toString())
//																	.compareTo(Integer.valueOf(v2.toString()))
//															: (columnDataType.equals(Double.class)
//																	? Double.valueOf(v1.toString())
//																			.compareTo(Double.valueOf(v2.toString()))
//																	: ((v1 instanceof Comparable)
//																			? ((Comparable<Object>) v1).compareTo(v2)
//																			: v1.toString()
//																					.compareTo(v2.toString()))))));
//						};
//						if (!sortCol.isOrderAscending())
//							currentComparator = currentComparator.reversed();
//						if (comparator == null) {
//							comparator = currentComparator;
//							continue;
//						}
//						comparator = comparator.thenComparing(currentComparator);
//						continue;
//					}
//					System.err.println("WARNING: OrderBy column '" + column
//							+ "' not found. This column will be skipped for sorting.");
//				}
//				if (comparator != null)
//					lstData.sort(comparator);
//			}
//			String[] GroupNames = (String[]) getLstRowGroup().stream().map(RowGroup::GetgroupRowcolumnName)
//					.toArray(x$0 -> new String[x$0]);
//			Map<String, Object> lastGroupRunningmap = new LinkedHashMap<>();
//			Map<String, Object> lastRunningmap = new LinkedHashMap<>();
//			this.Totalmap = new LinkedHashMap<>();
//			if (GroupNames.length > 0) {
//				processGroupLevel(lstData, GroupNames, 0, lastGroupRunningmap, lastRunningmap);
//			} else {
//				for (Map<String, Object> row : lstData) {
//					Map<String, Map<String, Object>> runningResult = getRuningvalues(row, lastGroupRunningmap,
//							lastRunningmap);
//					lastGroupRunningmap.putAll(runningResult.get("Grouprunning"));
//					lastRunningmap.putAll(runningResult.get("Running"));
//					addRow(new LinkedList(row.values()), JDefaultModelTable.Rowtype.Detail);
//				}
//			}
//			if (this.blnfooterdata) {
//				this.footertable.clear();
//				getfooterValues(GroupNames, lstData, this.Totalmap);
//			}
//			this.skipRepaint = false;
//			setUI(new TableUI(this.tableModel, this, getLstRowGroup(), getcolumns()));
//			if (this.blnfooterdata)
//				this.footertable.setUI(new TableUI(this.footertable.getTableModel(), this.footertable,
//						this.footertable.getLstRowGroup(), getcolumns()));
//			this.skipRepaint = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}

//	private List<Map<String, Object>> convertDataToListOfMaps(List<?> data) {
//		if (data == null || data.isEmpty())
//			return Collections.emptyList();
//		if (data.get(0) instanceof Map)
//			return (List) data;
//		ObjectMapper mapper = new ObjectMapper();
//		return (List<Map<String, Object>>) mapper.convertValue(data, new TypeReference<List<Map<String, Object>>>() {
//
//		});
//	}

	public void setShowGroupHeaders(Boolean showGroupHeaders) {
		this.showGroupHeaders = showGroupHeaders;
	}

	public void disableFlatLafHeaderHover() {
		JTableHeader header = getTableHeader();

		header.setOpaque(true);

		header.putClientProperty("TableHeader.hoverable", Boolean.FALSE);
		header.putClientProperty("TableHeader.enableActions", Boolean.FALSE);

		Color bg = CommonValues.getBORDER_COLOR() != null ? CommonValues.getBORDER_COLOR()
				: UIManager.getColor("TableHeader.background");

		header.putClientProperty("TableHeader.cellHoverBackground", bg);
		header.putClientProperty("TableHeader.cellPressedBackground", bg);
	}

}
