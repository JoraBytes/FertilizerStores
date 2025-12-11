package com.jora.billing.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class Column {
	private final Class<?> dataType;

	private final int cellAlignment;

	private final List<TableColumn> columns = new ArrayList<>();

	private final Boolean columnEditable;

	private String strColumnName = "";

	private int intWidth;

	private String dataKey = "";

	private String dataFormate = "";

	private Aggregators aggregators = null;

	private boolean commasymbol = false;

	private TableCellEditor defaultCellEditor;

	private ColumnFixed columnFixed = ColumnFixed.NO;

	private Boolean mergeColumn = Boolean.valueOf(false);

	public Column() {
	    this.strColumnName = "";
	    this.dataType = String.class;
	    this.intWidth = 50;
	    this.cellAlignment = 2;
	    this.columnEditable = Boolean.valueOf(false);
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName) {
	    this.strColumnName = columnName;
	    this.dataType = String.class;
	    this.intWidth = 50;
	    this.cellAlignment = 2;
	    this.columnEditable = Boolean.valueOf(false);
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, Class<?> dataType) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = 50;
	    this.cellAlignment = 2;
	    this.columnEditable = Boolean.valueOf(false);
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, Class<?> dataType, int width) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = 2;
	    this.columnEditable = Boolean.valueOf(false);
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, Class<?> dataType, int width, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = 2;
	    this.columnEditable = Boolean.valueOf(false);
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, Class<?> dataType, int width, int celAlignment) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, Aggregators m_Aggregators) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.aggregators = m_Aggregators;
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat, Aggregators m_Aggregators) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.aggregators = m_Aggregators;
	    this.dataFormate = dataFormat;
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat, Aggregators m_Aggregators, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.aggregators = m_Aggregators;
	    this.dataFormate = dataFormat;
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat, boolean blncommaseprater, Aggregators m_Aggregators) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.aggregators = m_Aggregators;
	    this.dataFormate = dataFormat;
	    this.commasymbol = blncommaseprater;
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat, boolean blncommaseprater, Aggregators m_Aggregators, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.aggregators = m_Aggregators;
	    this.dataFormate = dataFormat;
	    this.commasymbol = blncommaseprater;
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.dataFormate = dataFormat;
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.dataFormate = dataFormat;
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat, boolean blncommaseprater) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.dataFormate = dataFormat;
	    this.commasymbol = blncommaseprater;
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, String dataFormat, boolean blncommaseprater, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(false);
	    this.dataKey = dataKey;
	    this.dataFormate = dataFormat;
	    this.commasymbol = blncommaseprater;
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, Class<?> dataType, int width, int celAlignment, boolean columnEditable) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(columnEditable);
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, boolean columnEditable) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(columnEditable);
	    this.columnFixed = ColumnFixed.NO;
	    this.dataKey = dataKey;
	  }

	public Column(String columnName, Class<?> dataType, int width, int celAlignment, boolean columnEditable, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(columnEditable);
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, Class<?> dataType, int width, int celAlignment, boolean columnEditable, TableCellEditor defaultCellEditor) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(columnEditable);
	    this.defaultCellEditor = defaultCellEditor;
	    this.columnFixed = ColumnFixed.NO;
	  }

	public Column(String columnName, Class<?> dataType, int width, int celAlignment, boolean columnEditable, TableCellEditor defaultCellEditor, ColumnFixed columnFixed) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(columnEditable);
	    this.defaultCellEditor = defaultCellEditor;
	    this.columnFixed = columnFixed;
	  }

	public Column(String columnName, String dataKey, Class<?> dataType, int width, int celAlignment, boolean columnEditable, TableCellEditor editor) {
	    this.strColumnName = columnName;
	    this.dataType = dataType;
	    this.intWidth = width;
	    this.cellAlignment = celAlignment;
	    this.columnEditable = Boolean.valueOf(columnEditable);
	    this.dataKey = dataKey;
	    this.columnFixed = ColumnFixed.NO;
	    this.defaultCellEditor = editor;
	  }

	public Column mergeColreq(Boolean mergeColumn) {
		this.mergeColumn = mergeColumn;
		return this;
	}

	public String getDataFormat() {
		return this.dataFormate;
	}

	public boolean getsysmbolreq() {
		return this.commasymbol;
	}

	public void add(TableColumn column) {
		this.columns.add(column);
	}

	public String getColumnName() {
		return this.strColumnName;
	}

	public String getDataKey() {
		return this.dataKey;
	}

	public Class<?> getDataType() {
		return this.dataType;
	}

	public int getColumnWidth() {
		return this.intWidth;
	}

	public void setColumnWidth(int intWidth) {
		this.intWidth = intWidth;
	}

	public int getCellAlignment() {
		return this.cellAlignment;
	}

	public Boolean isColumnEditable() {
		return this.columnEditable;
	}

	public TableCellEditor getDefaultCellEditor() {
		return this.defaultCellEditor;
	}

	public void setDefaultCellEditor(TableCellEditor defaultCellEditor) {
		this.defaultCellEditor = defaultCellEditor;
	}

	public Aggregators getAggregators() {
		return this.aggregators;
	}

	public ColumnFixed getColumnFixed() {
		return this.columnFixed;
	}

	public void setColumnFixed(ColumnFixed columnFixed) {
		this.columnFixed = columnFixed;
	}

	public boolean isMergeable() {
		return this.mergeColumn.booleanValue();
	}

	public enum Aggregators {
		Sum, Runningbalance, GroupRunningbalance, GroupbyNameValue;
	}

	public enum ColumnFixed {
		YES, NO;
	}
}
