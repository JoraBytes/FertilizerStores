package com.jora.billing.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

public class ComboBox<E> extends JComboBox<E> {

	private static final long serialVersionUID = 1L;

	private final JTextComponent txtEditor;

	private List<Item> listItem = new ArrayList<>();

	private Color color = Color.WHITE;

	public ComboBox() {
		this.txtEditor = (JTextComponent) getEditor().getEditorComponent();
		this.txtEditor.setDocument(new ComboBoxSearch<>(this));
	}

	public void setEditorEditable(boolean bln) {
		if (this.txtEditor != null)
			this.txtEditor.setEditable(bln);
	}

	public ComboBox(E[] arg0) {
		super(arg0);
		this.txtEditor = (JTextComponent) getEditor().getEditorComponent();
		this.txtEditor.setDocument(new ComboBoxSearch<>(this));
	}

	public ComboBox(ComboBoxModel<E> arg0) {
		super(arg0);
		this.txtEditor = (JTextComponent) getEditor().getEditorComponent();
		this.txtEditor.setDocument(new ComboBoxSearch<>(this));
	}

	public void setNonEditableColor(Color color) {
		setRenderer(new MyRenderer(color));
		setColor(color);
	}

	public void setEditorBackground(Color color) {
		if (this.txtEditor != null) {
			this.txtEditor.setBackground(color);
			this.txtEditor.setOpaque(true);
		}
	}

	public void removeArrowButton() {
		Component[] components = getComponents();
		for (Component component : components) {
			if (component instanceof javax.swing.AbstractButton) {
				remove(component);
			} else if (component instanceof JTextComponent) {
				((JTextComponent) component).setBorder(BorderFactory.createEmptyBorder());
			} else if (component instanceof JComponent) {
				((JComponent) component).setBorder(BorderFactory.createEmptyBorder());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void setListItem(List<Item> item) {
		removeAllItems();
		this.listItem.clear();
		this.listItem = item;
		if (this.listItem != null)
			for (Item it : this.listItem)
				addItem((E) it.getKey());
	}

	public List<Item> getListItem() {
		return this.listItem;
	}

	@SuppressWarnings("unchecked")
	public void addListItem(Item item) {
		if (this.listItem == null || this.listItem.isEmpty())
			this.listItem = new ArrayList<>();
		if (item != null) {
			this.listItem.add(item);
			addItem((E) item.getKey());
		}
	}

	public Item getListItem(int position) throws ArrayIndexOutOfBoundsException {
		try {
			if (this.listItem == null || this.listItem.isEmpty())
				return null;
			return this.listItem.get(position);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}

	public Object getSelectedItemValue() throws ArrayIndexOutOfBoundsException {
		try {
			if (this.listItem == null || this.listItem.isEmpty())
				return null;
			if (getSelectedIndex() < 0)
				return null;
			return ((Item) this.listItem.get(getSelectedIndex())).getValue();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}

	public boolean setSelectedItemValue(Object selectedValue) throws ArrayIndexOutOfBoundsException {
		try {
			if (this.listItem == null || this.listItem.isEmpty())
				return false;
			for (Item item : this.listItem) {
				if (!item.getValue().equals(selectedValue))
					continue;
				setSelectedIndex(this.listItem.indexOf(item));
				return true;
			}
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}

	public void removeAllItems() {
		super.removeAllItems();
		this.listItem.clear();
	}

	public void removeItem(Object arg0) {
		super.removeItem(arg0);
		this.listItem.remove(arg0);
	}

	public void removeItemAt(int arg0) {
		super.removeItemAt(arg0);
		this.listItem.remove(arg0);
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	class MyRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;

		private Color color = Color.WHITE;

		public MyRenderer(Color color) {
			this.color = color;
		}

		public void setBorder(Border border) {
			setBorder(BorderFactory.createEmptyBorder());
		}

		public void setBackground(Color bg) {
			super.setBackground(this.color);
		}

		public Insets insets() {
			return new Insets(0, 0, 0, 0);
		}
	}
}
