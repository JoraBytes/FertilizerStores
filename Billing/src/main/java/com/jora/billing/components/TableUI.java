package com.jora.billing.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicTableUI;

public class TableUI extends BasicTableUI {
	protected final String outputPattern = "dd-MMM-yyyy";

	private final JDefaultModelTable tblModel;

	private final JTable table;

	private final List<RowGroup> lstRowGroup;

	private final List<Column> lstColumn;

	public TableUI(JDefaultModelTable m_tablemodel, JTable m_table, List<RowGroup> lstRowGroup, List<Column> lstColumn) {
	    this.tblModel = m_tablemodel;
	    this.table = m_table;
	    this.lstRowGroup = lstRowGroup;
	    this.lstColumn = lstColumn;
	  }

	private int getMergeStartRow(int row, int column) {
		Object value = this.table.getValueAt(row, column);
		for (int r = row - 1; r >= 0;) {
			Object prev = this.table.getValueAt(r, column);
			if (value != null && value.equals(prev)) {
				row = r;
				r--;
			}
		}
		return row;
	}

	public void paint(Graphics g, JComponent c) {
		Rectangle clip = g.getClipBounds();
		int colCount = this.table.getColumnCount();
		int rowCount = this.table.getRowCount();
		for (int col = 0; col < colCount; col++) {
			Column colProps = this.lstColumn.get(col);
			int row = 0;
			while (row < rowCount) {
				JDefaultModelTable.Rowtype rowtype = this.tblModel.getRowType(row);
				int mergeCount = (colProps.isMergeable() && rowtype != JDefaultModelTable.Rowtype.GroupHeader
						&& colProps.getColumnWidth() > 0) ? computeMergeCount(row, col) : 1;
				if (rowtype != JDefaultModelTable.Rowtype.GroupHeader || col <= 0) {
					Rectangle firstCell = this.table.getCellRect(row, col, false);
					int totalHeight = firstCell.height;
					for (int k = 1; k < mergeCount; k++)
						totalHeight += this.table.getRowHeight(row + k);
					int totalColumnWidth = firstCell.width;
					if (rowtype == JDefaultModelTable.Rowtype.GroupHeader)
						totalColumnWidth = this.table.getColumnModel().getTotalColumnWidth();
					Rectangle blockRect = new Rectangle(firstCell.x, firstCell.y, totalColumnWidth, totalHeight);
					if (blockRect.intersects(clip))
						try {
							paintCell(g, blockRect, row, col, rowtype, mergeCount);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
				}
				row += mergeCount;
			}
		}
	}

	private int computeMergeCount(int row, int col) {
		Object base = this.table.getValueAt(row, col);
		int cnt = 1;
		int max = this.table.getRowCount();
		for (int r = row + 1; r < max;) {
			Object next = this.table.getValueAt(r, col);
			if (base != null && base.equals(next)) {
				cnt++;
				r++;
			}
		}
		return cnt;
	}

	private void paintCell(Graphics g, Rectangle cellRect, int row, int column, JDefaultModelTable.Rowtype rowType,
			int mergeCount) throws Exception {
		int startRow = (mergeCount > 1) ? getMergeStartRow(row, column) : row;
		if (this.table.isEditing() && this.table.getEditingRow() == startRow
				&& this.table.getEditingColumn() == column) {
			Component editor = this.table.getEditorComponent();
			if (editor != null) {
				editor.setBounds(cellRect);
				editor.validate();
			}
		} else {
			Column colProps = this.lstColumn.get(column);
			javax.swing.table.TableCellRenderer renderer = !colProps.isMergeable() ? this.table.getCellRenderer(row, column)
					: this.table.getDefaultRenderer(JTable.class);
			if (renderer != null) {
				Component component;
				if (rowType != JDefaultModelTable.Rowtype.Detail
						&& colProps.getDataKey().equalsIgnoreCase("boolean")) {
					component = new JLabel("");
				} else {
					component = this.table.prepareRenderer(renderer, row, column);
				}
				boolean hasFocusAndSelected = (this.table.getSelectedRow() == row
						&& this.table.getSelectedColumn() == column);
				int groupOrderLevel = this.tblModel.getGroupOrderLevel(row);
				setCellProperty(component, rowType, colProps, groupOrderLevel, hasFocusAndSelected, mergeCount);
				if (this.rendererPane != null) {
					this.rendererPane.add(component);
					this.rendererPane.paintComponent(g, component, this.table, cellRect.x, cellRect.y, cellRect.width,
							cellRect.height, true);
				}
			}
		}
	}

	private void setCellProperty(Component component, JDefaultModelTable.Rowtype rowType, Column colProps,
			int groupOrderLevel, boolean focused, int mergeCount) throws Exception {
		if (component instanceof JLabel) {
			JLabel label = (JLabel) component;
			label.setOpaque(true);
			label.setHorizontalAlignment(colProps.getCellAlignment());
			label.setVerticalAlignment(0);
			if (rowType == JDefaultModelTable.Rowtype.GroupHeader) {
				RowGroup g = this.lstRowGroup.get(0);
				g.setGrouporder(groupOrderLevel);
				if (g.getBackColor() == null) {
					switch (g.getGrouporder()) {
					case 0:
						label.setBackground(Color.decode("#cde5ff"));
						break;
					case 1:
						label.setBackground(Color.decode("#e3f0ff"));
						break;
					case 2:
						label.setBackground(Color.decode("#f3f5ff"));
						break;
					case 3:
						label.setBackground(Color.decode("#f3f9ff"));
						break;
					default:
						label.setBackground(Color.decode("#FAFFCB"));
						break;
					}
				} else {
					label.setBackground(g.getBackColor());
					label.setForeground(g.getForeColor());
				}
				label.setFont(g.getTextFont());
				Border border = BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY), BorderFactory
								.createEmptyBorder(0, (g.getGrouporder() <= 0) ? 5 : (25 * g.getGrouporder()), 0, 0));
				label.setBorder(border);
			} else {
				int totalHeight = this.table.getRowHeight() * mergeCount;
				int fontHeight = label.getFontMetrics(label.getFont()).getHeight();
				int pad = Math.max((totalHeight - fontHeight) / 2, 5);
				Border cellBorder = BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY);
				Border padding = BorderFactory.createEmptyBorder(pad, 5, pad, 5);
				label.setBorder(BorderFactory.createCompoundBorder(cellBorder, padding));
				if (rowType == JDefaultModelTable.Rowtype.GroupTotal) {
					RowGroup g = this.lstRowGroup.get(0);
					label.setBackground(g.getBackColor());
					label.setForeground(g.getForeColor());
					label.setFont(g.getTextFont());
				} else if (rowType == JDefaultModelTable.Rowtype.FooterTotal) {
					label.setFont(new Font("Calibri", 1, 12));
				}
				if (colProps.getDataType() == Date.class)
					label.setText(formatDate(label.getText()));
				if ((colProps.getDataType() == Double.class || colProps.getDataType() == double.class)
						&& colProps.getsysmbolreq()) {
					NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
					String txt = label.getText();
					if (!txt.isEmpty()) {
						double val = Double.parseDouble(txt);
						String formatted = nf.format(val).replace(nf.getCurrency().getSymbol(), "");
						label.setText(formatted);
					}
				}
			}
			label.setText("<html> <body> " + label.getText() + "  </body> </html>");
			label.setEnabled(true);
		} else if (component instanceof JCheckBox) {
			JCheckBox checkBox = (JCheckBox) component;
			if (rowType != JDefaultModelTable.Rowtype.Detail) {
				JLabel label = new JLabel("");
				label.setHorizontalAlignment(0);
				label.setOpaque(true);
				label.setBackground(Color.WHITE);
				label.setForeground(Color.BLACK);
				component = label;
			} else {
				checkBox.setOpaque(true);
				checkBox.setEnabled(true);
				checkBox.setHorizontalAlignment(0);
				checkBox.setBackground(Color.WHITE);
				Border cellBorder = BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY);
				Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
				checkBox.setBorder(BorderFactory.createCompoundBorder(cellBorder, padding));
			}
		} else {
			Border cellBorder = BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY);
			Border padding = BorderFactory.createEmptyBorder(0, 5, 0, 5);
			((JComponent) component).setBorder(BorderFactory.createCompoundBorder(cellBorder, padding));
		}
	}

	private String formatDate(String text) {
		if (isSameFormat(text))
			return text;
		List<String> patterns = Arrays.asList(new String[] { "dd-MMM-yyyy", "dd-MM-yyyy", "yyyy-MM-dd",
				"dd-MMM-yyyy hh:mm:ss a", "dd-MM-yyyy hh:mm:ss a", "dd-MM-yyyy hh:mm:ss.S", "dd-MM-yyyy HH:mm:ss.S",
				"dd-MM-yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S", "yyyy-MM-dd hh:mm:ss a", "yyyy-MM-dd hh:mm:ss.S a" });
		DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		for (String pat : patterns) {
			try {
				DateTimeFormatter inFmt = DateTimeFormatter.ofPattern(pat);
				LocalDate ld = LocalDate.parse(text, inFmt);
				return outFmt.format(ld);
			} catch (DateTimeParseException dateTimeParseException) {
			}
		}
		return text;
	}

	private boolean isSameFormat(String text) {
		try {
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDate ld = LocalDate.parse(text, fmt);
			return fmt.format(ld).equals(text);
		} catch (DateTimeParseException ex) {
			return false;
		}
	}
}
