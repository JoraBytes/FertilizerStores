package com.jora.billing.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableHeaderCellrender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private Color bg;
	private Color fg;
	private Font font;

	public TableHeaderCellrender(Color bg, Color fg, Font font) {
		this.bg = bg;
		this.fg = fg;
		this.font = font;

		setOpaque(true);
		setHorizontalAlignment(CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		setBackground(bg);
		setForeground(fg);
		setFont(font);

		return this;
	}

}
