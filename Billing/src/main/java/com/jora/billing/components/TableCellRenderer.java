package com.jora.billing.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	Color gridColor;

	Boolean verline;

	Boolean horline;

	Map<Object, Map<Object, Color>> map;

	public TableCellRenderer(Color color, Boolean verticalline, Boolean horizontalline) {
	    this.gridColor = color;
	    this.verline = verticalline;
	    this.horline = horizontalline;
	  }

	public TableCellRenderer(JTable table, Map<Object, Map<Object, Color>> m_map) {
	    this.map = m_map;
	    if (this.map == null)
	      return; 
	    if (table == null)
	      return; 
	    for (Object string : this.map.keySet())
	      table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex(string)).setCellRenderer(this); 
	  }

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JDefaultModelTable jilabaDefaultModelTable = (JDefaultModelTable) table.getModel();
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (jilabaDefaultModelTable.getCellColor(row, column) != null && !isSelected)
			component.setBackground(jilabaDefaultModelTable.getCellColor(row, column));
		if (jilabaDefaultModelTable.getCellForeColor(row, column) != null && !isSelected)
			component.setForeground(jilabaDefaultModelTable.getCellForeColor(row, column));
		if (null == this.verline && null == this.horline) {
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, this.gridColor));
		} else {
			if (this.verline.booleanValue() && this.horline.booleanValue())
				setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, this.gridColor));
			if (this.verline.booleanValue() && !this.horline.booleanValue())
				setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, this.gridColor));
			if (this.horline.booleanValue() && !this.verline.booleanValue())
				setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, this.gridColor));
		}
		if (this.map == null) {
			component.setFocusable(true);
			return component;
		}
		if (value == null)
			return component;
		if (this.map.containsKey(table.getColumnName(column))) {
			Map<Object, Color> mapColor = this.map.get(table.getColumnName(column));
			if (mapColor.containsKey(value.toString().toLowerCase().replaceAll("\\s+", ""))) {
				component.setForeground(mapColor.get(value.toString().toLowerCase().replaceAll("\\s+", "")));
				Font boldFont = component.getFont().deriveFont(1);
				component.setFont(boldFont);
				if (mapColor.containsKey(value.toString().toLowerCase().replaceAll("\\s+", "") + "rowcolor"))
					((Table) table).setRowColor(row,
							mapColor.get(value.toString().toLowerCase().replaceAll("\\s+", "") + "rowcolor"));
				if (mapColor.containsKey(value.toString().toLowerCase().replaceAll("\\s+", "") + "rowforecolor"))
					((Table) table).setRowForeColor(row,
							mapColor.get(value.toString().toLowerCase().replaceAll("\\s+", "") + "rowforecolor"));
			} else {
				component.setForeground(Color.black);
			}
		}
		return component;
	}
}
