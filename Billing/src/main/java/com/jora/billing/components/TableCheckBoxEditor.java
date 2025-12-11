package com.jora.billing.components;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCheckBoxEditor extends DefaultCellEditor implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JCheckBox checkBox;

	public TableCheckBoxEditor() {
		super(new JCheckBox());
		this.checkBox = (JCheckBox) getComponent();
		this.checkBox.setHorizontalAlignment(0);
		this.checkBox.addKeyListener(this);
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value instanceof Boolean)
			this.checkBox.setSelected(((Boolean) value).booleanValue());
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

	public Object getCellEditorValue() {
		return Boolean.valueOf(this.checkBox.isSelected());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			boolean currentState = this.checkBox.isSelected();
			this.checkBox.setSelected(!currentState);
			stopCellEditing();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
