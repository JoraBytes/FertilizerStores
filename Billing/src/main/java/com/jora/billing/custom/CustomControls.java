package com.jora.billing.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.border.Border;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.components.Button;
import com.jora.billing.components.CheckList;
import com.jora.billing.components.Column;
import com.jora.billing.components.ComboBox;
import com.jora.billing.components.Item;
import com.jora.billing.components.JTextFieldEnum;
import com.jora.billing.components.Menu;
import com.jora.billing.components.MenuItem;
import com.jora.billing.components.PasswordField;
import com.jora.billing.components.Spinner;
import com.jora.billing.components.Table;
import com.jora.billing.components.TextField;

public class CustomControls {
	public static ClassLoader classLoader = CustomControls.class.getClassLoader();

	private static JScrollPane scrollPane;

	public static BufferedImage getBufferImage(String imgpath) throws Exception {
		BufferedImage bufimg = null;
		try {
			if (null == classLoader.getResource(imgpath))
				return bufimg;
			bufimg = ImageIO.read(classLoader.getResource(imgpath));
		} catch (Exception e) {
			throw new Exception(ErrorHandler.errorMessage(e));
		}
		return bufimg;
	}

	public static JLabel createLabel(String text, String iconPath, int alignment, Font font, Color foreColor)
			throws Exception {
		JLabel lbl;
		if (!iconPath.isEmpty() && null != getBufferImage(iconPath)) {
			lbl = new JLabel(new ImageIcon(getBufferImage(iconPath)));
		} else {
			lbl = new JLabel(text);
		}
		lbl.setFont(font);
		lbl.setForeground(foreColor);
		lbl.setHorizontalAlignment(alignment);
		lbl.setOpaque(false);
		lbl.setVisible(true);
		return lbl;
	}

	public static JLabel createLabel(JLabel lbl, String text, String iconPath, int alignment, Font font)
			throws Exception {
		if (!iconPath.isEmpty() && null != getBufferImage(iconPath)) {
			lbl = new JLabel(new ImageIcon(getBufferImage(iconPath)));
		} else {
			lbl = new JLabel(text);
		}
		lbl.setHorizontalAlignment(alignment);
		lbl.setVerticalAlignment(0);
		lbl.setOpaque(false);
		lbl.setVisible(true);
		lbl.setFont(font);
		return lbl;
	}

	public static JLabel createLabel(String text, String iconPath, int alignment) throws Exception {
		JLabel lbl;
		if (!iconPath.isEmpty() && null != getBufferImage(iconPath)) {
			lbl = new JLabel(new ImageIcon(getBufferImage(iconPath)));
		} else {
			lbl = new JLabel(text);
		}
		lbl.setOpaque(false);
		lbl.setHorizontalAlignment(alignment);
		lbl.setVisible(true);
		return lbl;
	}

	public static PasswordField createPasswordField(PasswordField txtField, int maxLength, String toolTipText)
			throws Exception {
		txtField = new PasswordField();
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		txtField.setVisible(true);
		txtField.setOpaque(false);
		return txtField;
	}

	public static TextField createTextField(TextField txtField, int maxLength,
			JTextFieldEnum.TextInputType textInputType, String toolTipText) throws Exception {
		txtField = new TextField();
		txtField.setTextType(textInputType);
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		setAlignment(txtField, textInputType);
		txtField.setOpaque(false);
		txtField.setVisible(true);
		return txtField;
	}

	public static TextField createTextField(TextField txtField, int maxLength,
			JTextFieldEnum.TextInputType textInputType, String toolTipText, boolean borderReq) throws Exception {
		txtField = new TextField();
		txtField.setTextType(textInputType);
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		txtField.setFocusColor(Color.WHITE);
		txtField.setVisible(true);
		if (borderReq) {
//			txtField.setBorder(new RoundBorder(8, Color.decode("#C6D1D8")));
//			txtField.setOpaque(false);
		}
		setAlignment(txtField, textInputType);
		return txtField;
	}

	public static TextField createTextField(TextField txtField, int maxLength,
			JTextFieldEnum.TextInputType textInputType, String toolTipText, Font font,
			JTextFieldEnum.NumericDigits numericDigits) throws Exception {
		txtField = new TextField();
		txtField.putClientProperty("TextComponent.arc", Integer.valueOf(120));
		txtField.setTextType(textInputType);
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		txtField.setFocusColor(Color.WHITE);
		txtField.setOpaque(false);
		txtField.setFont(font);
		setAlignment(txtField, textInputType);
		txtField.setNumericDigits(numericDigits);
		txtField.setVisible(true);
		return txtField;
	}

	public static TextField createTextField(TextField txtField, int maxLength,
			JTextFieldEnum.TextInputType textInputType) throws Exception {
		txtField = new TextField();
		txtField.setTextType(textInputType);
		txtField.setMaxLength(maxLength);
		txtField.setFocusColor(Color.WHITE);
		txtField.setOpaque(false);
		txtField.setVisible(true);
		setAlignment(txtField, textInputType);
		return txtField;
	}

	private static void setAlignment(TextField txTextField, JTextFieldEnum.TextInputType textInputType) {
		if (textInputType == textInputType.NUMBER || textInputType == textInputType.NUMERIC) {
			txTextField.setHorizontalAlignment(4);
		} else {
			txTextField.setHorizontalAlignment(2);
		}
	}

	public static TextField createTextField(TextField txtField, JTextFieldEnum.TextInputCase textCase, int maxLength,
			JTextFieldEnum.TextInputType textInputType, String toolTipText) throws Exception {
		txtField = new TextField();
		txtField.setTextCase(textCase);
		txtField.setTextType(textInputType);
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		txtField.setFocusColor(Color.WHITE);
		txtField.setOpaque(false);
		txtField.setVisible(true);
		setAlignment(txtField, textInputType);
		return txtField;
	}

	public static TextField createTextField(TextField txtField, JTextFieldEnum.TextInputCase textCase, int maxLength,
			JTextFieldEnum.TextInputType textInputType, String toolTipText, boolean borderReq) throws Exception {
		txtField = new TextField();
		txtField.setTextCase(textCase);
		txtField.setTextType(textInputType);
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		txtField.setFocusColor(Color.WHITE);
		txtField.setOpaque(false);
		txtField.setVisible(true);
//		if (borderReq)
//			txtField.setBorder(new RoundBorder(8, Color.decode("#C6D1D8")));
		setAlignment(txtField, textInputType);
		return txtField;
	}

	public static TextField createTextField(TextField txtField, int maxLength,
			JTextFieldEnum.TextInputType textInputType, JTextFieldEnum.NumericDigits numericDigits, String toolTipText)
			throws Exception {
		txtField = new TextField();
		txtField.setTextCase(JTextFieldEnum.TextInputCase.SYSTEM);
		txtField.setTextType(textInputType);
		txtField.setNumericDigits(numericDigits);
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		txtField.setHorizontalAlignment(4);
		txtField.setFocusColor(Color.WHITE);
		txtField.setOpaque(false);
		txtField.setVisible(true);
		setAlignment(txtField, textInputType);
		return txtField;
	}

	public static TextField createTextField(TextField txtField, JTextFieldEnum.TextInputCase textCase, int maxLength,
			JTextFieldEnum.TextInputType textInputType, JTextFieldEnum.NumericDigits numericDigits, String toolTipText)
			throws Exception {
		txtField = new TextField();
		txtField.setTextCase(textCase);
		txtField.setTextType(textInputType);
		txtField.setNumericDigits(numericDigits);
		txtField.setMaxLength(maxLength);
		txtField.setToolTipText(toolTipText);
		txtField.setHorizontalAlignment(4);
		txtField.setFocusColor(Color.WHITE);
		txtField.setOpaque(false);
		txtField.setVisible(true);
		setAlignment(txtField, textInputType);
		return txtField;
	}

	public static Button createButton(Button button, String butText, Color bgColor, Color fgColor) throws Exception {
		button = new Button(butText);
		button.setOpaque(true);
		button.setBackground(bgColor);
		button.setForeground(fgColor);
		button.setVisible(true);
		return button;
	}

	public static Button createButton(Button button, String butImagePath, String disButImgPath, String butText,
			Color bgColor, Color fgColor) throws Exception {
		if (null != getBufferImage(butImagePath)) {
			button = new Button(new ImageIcon(getBufferImage(butImagePath)));
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.setOpaque(true);
			button.setBackground(bgColor);
			button.setForeground(fgColor);
			button.setBorderPainted(false);
		} else {
			button = new Button(butText);
		}
		if (null != getBufferImage(disButImgPath))
			button.setDisabledIcon(new ImageIcon(getBufferImage(disButImgPath)));
		return button;
	}

	public static Button createButton(Button button, String butImagePath, String disButImgPath, String butText)
			throws Exception {
		if (null != getBufferImage(butImagePath)) {
			button = new Button(new ImageIcon(getBufferImage(butImagePath)));
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.setOpaque(true);
			button.setContentAreaFilled(false);
			button.setBorderPainted(true);
		} else {
			button = new Button(butText);
		}
		if (null != getBufferImage(disButImgPath))
			button.setDisabledIcon(new ImageIcon(getBufferImage(disButImgPath)));
		return button;
	}

	public static Menu createMenu(Menu menu, String text, String name, String tooltip, String iconPath, int iconTextGap,
			int shortCutKey, int verticalTextPos, int horizonalTextPos) throws Exception {
		menu = new Menu(text);
		BufferedImage bufferedImage = getBufferImage(iconPath);
		if (null != bufferedImage)
			menu.setIcon(new ImageIcon(bufferedImage));
		menu.setIconTextGap(iconTextGap);
		menu.setMnemonic(shortCutKey);
		menu.setVerticalTextPosition(verticalTextPos);
		menu.setHorizontalTextPosition(horizonalTextPos);
		menu.setName(name);
		menu.setToolTipText(tooltip);
		return menu;
	}

	public static Menu createMenu(Menu menu, String text, String menuname, String tooltip, String iconPath,
			int iconTextGap, int shortCutKey) throws Exception {
		menu = new Menu(text);
		BufferedImage bufferedImage = getBufferImage(iconPath);
		if (null != bufferedImage)
			menu.setIcon(new ImageIcon(bufferedImage));
		menu.setIconTextGap(iconTextGap);
		menu.setMnemonic(shortCutKey);
		menu.setName(menuname);
		menu.setToolTipText(tooltip);
		return menu;
	}

	public static Menu createMenu(Menu menu, String text, String menuname, String tooltip, String iconPath)
			throws Exception {
		menu = new Menu(text);
		BufferedImage bufferedImage = getBufferImage(iconPath);
		if (null != bufferedImage)
			menu.setIcon(new ImageIcon(bufferedImage));
		menu.setName(menuname);
		menu.setToolTipText(tooltip);
		return menu;
	}

	public static Menu createMenu(Menu menu, String menuText, String menuName, String iconPath, String formToLoad,
			JMenuEnum.FormType formType, int mnemonicKeyCode) throws Exception {
		menu = new Menu(menuText);
		BufferedImage bufferedImage = getBufferImage(iconPath);
		if (null != bufferedImage)
			menu.setIcon(new ImageIcon(bufferedImage));
		menu.setName(menuName);
		menu.setVerticalTextPosition(3);
		menu.setHorizontalTextPosition(0);
		menu.setFormToLoad(formToLoad);
		menu.setFormType(formType);
		menu.setMnemonic(mnemonicKeyCode);
		return menu;
	}

	public static MenuItem createMenuItem(MenuItem menuItem, String text, String menuname, String iconPath,
			String formToLoad, JMenuEnum.FormType formType) throws Exception {
		menuItem = new MenuItem(text);
		menuItem.setName(menuname);
		menuItem.setFormToLoad(formToLoad);
		if (!iconPath.isEmpty()) {
			BufferedImage bufferedImage = getBufferImage(iconPath);
			if (null != bufferedImage) {
				ImageIcon imageIcon = new ImageIcon(bufferedImage);
				if (imageIcon != null)
					menuItem.setIcon(imageIcon);
			}
		}
		menuItem.setFormType(formType);
		return menuItem;
	}

	public static ComboBox<Item> createComboBox(ComboBox<Item> cmbBox, boolean edit, Font font, boolean roundBorderReq,
			String borderColor) throws Exception {
		cmbBox = new ComboBox();
		cmbBox.setOpaque(true);
		cmbBox.putClientProperty("Component.arc", Integer.valueOf(1111));
		cmbBox.setEditable(edit);
		cmbBox.setFont(font);
		return cmbBox;
	}

	public static void controlEnableDisable(Component[] components, boolean enable) throws Exception {
		try {
			for (Component component : components) {
				if (component instanceof JPanel) {
					controlEnableDisable(((JPanel) component).getComponents(), enable);
				} else if (component instanceof JTabbedPane) {
					controlEnableDisable(((JTabbedPane) component).getComponents(), enable);
				} else if (component instanceof ComboBox) {
					component.setEnabled(enable);
				} else {
					if ("jscrollpane".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase())) {
						JScrollPane comp = (JScrollPane) component;
						for (Component jsPaneComponent : comp.getComponents()) {
							switch (jsPaneComponent.getClass().getSimpleName().toLowerCase()) {
							case "jpanel":
								controlEnableDisable(((JPanel) jsPaneComponent).getComponents(), enable);
								break;
							case "checklist":
								((CheckList) jsPaneComponent).setEnabled(enable);
								break;
							case "table":
								((Table) jsPaneComponent).setEnabled(enable);
								break;
							case "jviewport":
								controlEnableDisable((JViewport) jsPaneComponent, enable);
								break;
							}
						}
					}
					for (Component component2 : ((Container) component).getComponents()) {
						for (Component component3 : ((Container) component2).getComponents())
							component3.setEnabled(enable);
					}
					component.setEnabled(enable);
				}
			}
		} catch (Exception e) {
			throw new Exception(ErrorHandler.errorMessage(e));
		}
	}

	private static void controlEnableDisable(JViewport comp, boolean enable) throws Exception {
		for (Component component : comp.getComponents()) {
			switch (component.getClass().getSimpleName().toLowerCase()) {
			case "jpanel":
				controlEnableDisable(((JPanel) component).getComponents(), enable);
				break;
			case "checklist":
				((CheckList) component).setEnabled(enable);
				break;
			case "table":
				((Table) component).setEnabled(enable);
				break;
			case "jtextarea":
				((JTextArea) component).setEnabled(enable);
				break;
//			case "jilabatextarea":
//				((JilabaTextArea) component).setEnabled(enable);
//				break;
			case "jtextpane":
				((JTextPane) component).setEnabled(enable);
				break;
			}
		}
	}

	public static void clear(JPanel panel) {
		Date date = new Date();
		for (Component comp : panel.getComponents()) {
			Component[] tapComps;
			switch (comp.getClass().getSimpleName().toLowerCase()) {
			case "passwordfield":
				((PasswordField) comp).setText("");
				break;
			case "textfield":
				((TextField) comp).setText("");
				break;
			case "spinner":
				((Spinner) comp).setValue(date);
				break;
			case "combobox":
				if ((ComboBox) comp != null)
					resetComboSelectedIndex((ComboBox) comp);
				break;
			case "jcheckbox":
				((JCheckBox) comp).setSelected(false);
				break;
			case "table":
				((Table) comp).clear();
				break;
			case "jpanel":
				clear((JPanel) comp);
				break;
			case "jscrollpane":
				clearScrolPane((JScrollPane) comp);
				break;
			case "jtabbedpane":
				tapComps = ((JTabbedPane) comp).getComponents();
				for (Component component : tapComps) {
					if ("jpanel".equalsIgnoreCase(component.getClass().getSimpleName()))
						clear((JPanel) component);
				}
				break;
			case "jlabel":
				for (Component comp1 : ((JLabel) comp).getComponents()) {
					if ("jscrollpane".equalsIgnoreCase(comp1.getClass().getSimpleName()))
						clearScrolPane((JScrollPane) comp1);
					if ("jpanel".equalsIgnoreCase(comp1.getClass().getSimpleName()))
						clear((JPanel) comp1);
				}
				break;
			}
		}
	}

	private static void clearScrolPane(JScrollPane comp) {
		for (Component component : comp.getComponents()) {
			switch (component.getClass().getSimpleName().toLowerCase()) {
			case "jpanel":
				clear((JPanel) component);
				break;
//			case "checklist":
//				((CheckList) component).setListData((Object[]) new com.jilaba.control.CheckableItem[0]);
//				break;
			case "table":
				((Table) component).clear();
				break;
//			case "jilabalist":
//				((JilabaList) component).removeAllItems();
//				break;
			case "jviewport":
				clearViewPort((JViewport) component);
				break;
			}
		}
	}

	private static void clearViewPort(JViewport comp) {
		for (Component component : comp.getComponents()) {
			switch (component.getClass().getSimpleName().toLowerCase()) {
			case "jpanel":
				clear((JPanel) component);
				break;
//			case "checklist":
//				((CheckList) component).setListData((Object[]) new Item[0]);
//				break;
			case "table":
				((Table) component).clear();
				break;
			case "jtextarea":
				((JTextArea) component).setText("");
				break;
//			case "jilabatextarea":
//				((JilabaTextArea) component).setText("");
//				break;
			case "jtextpane":
				((JTextPane) component).setText("");
				break;
//			case "jilabalist":
//				((JilabaList) component).removeAllItems();
//				break;
			}
		}
	}

	private static void resetComboSelectedIndex(ComboBox<?> comp) {
		comp.removeAllItems();
	}

	public static int getWidthOrHeightFromPer(double componentWidth, double percent) {
		return (int) (componentWidth * percent / 100.0D);
	}

	public static Rectangle getRectangle(int panelWidth, int panelHeight, double xposPer, double yposPer,
			double widthPer, double heightPer) {
		return new Rectangle((int) (panelWidth * xposPer) / 100, (int) (panelHeight * yposPer) / 100,
				(int) (panelWidth * widthPer) / 100, (int) (panelHeight * heightPer) / 100);
	}

	public static int verticalGap(JPanel panel, Component cmp, double increaseYper) {
		return cmp.getY() + cmp.getHeight() + (int) (panel.getHeight() * increaseYper / 100.0D);
	}

	public static int horizontalGap(JPanel panel, Component cmp, double increaseXper) {
		return cmp.getX() + cmp.getWidth() + (int) (panel.getWidth() * increaseXper / 100.0D);
	}

	public static void addListeners(JPanel panelMain, JInternalFrame internalFrame) {
		for (Component comp : panelMain.getComponents()) {
			Component[] tapComps;
			switch (comp.getClass().getSimpleName().toLowerCase()) {
			case "jtabbedpane":
				tapComps = ((JTabbedPane) comp).getComponents();
				for (Component component : tapComps) {
					if ("jpanel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addListeners((JPanel) component, internalFrame);
				}
				break;
			case "passwordfield":
				((PasswordField) comp).addKeyListener((KeyListener) internalFrame);
				((PasswordField) comp).setFocusTraversalKeysEnabled(false);
				break;
			case "textfield":
				((TextField) comp).addKeyListener((KeyListener) internalFrame);
				((TextField) comp).setFocusTraversalKeysEnabled(false);
				break;
			case "spinner":
				((Spinner) comp).getEditor().getComponent(0).addKeyListener((KeyListener) internalFrame);
				((Spinner) comp).getEditor().getComponent(0).setFocusTraversalKeysEnabled(false);
				break;
			case "combobox":
				if ((ComboBox) comp != null) {
					ComboBox<?> cmb = (ComboBox) comp;
					if (cmb.isEditable()) {
						cmb.getEditor().getEditorComponent().addKeyListener((KeyListener) internalFrame);
						cmb.getEditor().getEditorComponent().setFocusTraversalKeysEnabled(false);
						break;
					}
					cmb.addKeyListener((KeyListener) internalFrame);
					cmb.setFocusTraversalKeysEnabled(false);
				}
				break;
			case "jcheckbox":
				if ((JCheckBox) comp != null) {
					JCheckBox chk = (JCheckBox) comp;
					chk.addKeyListener((KeyListener) internalFrame);
					chk.addActionListener((ActionListener) internalFrame);
					chk.setFocusTraversalKeysEnabled(false);
				}
				break;
			case "table":
				((Table) comp).addKeyListener((KeyListener) internalFrame);
				((Table) comp).setFocusTraversalKeysEnabled(false);
				break;
			case "jbutton":
				((JButton) comp).addActionListener((ActionListener) internalFrame);
				((JButton) comp).setFocusTraversalKeysEnabled(false);
				break;
//			case "jilabalist":
//				((JilabaList) comp).addKeyListener((KeyListener) internalFrame);
//				break;
			case "jpanel":
				addListeners((JPanel) comp, internalFrame);
				break;
			case "jscrollpane":
				addListenersToScrolPaneComponent((JScrollPane) comp, internalFrame);
				break;
			case "jilababutton":
				((Button) comp).addActionListener((ActionListener) internalFrame);
				((Button) comp).setFocusTraversalKeysEnabled(false);
				break;
			}
		}
	}

	public static void addListeners(JPanel panelMain, JFrame frame) {
		for (Component comp : panelMain.getComponents()) {
			Component[] tapComps;
			switch (comp.getClass().getSimpleName().toLowerCase()) {
			case "jtabbedpane":
				tapComps = ((JTabbedPane) comp).getComponents();
				for (Component component : tapComps) {
					if ("jpanel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addListeners((JPanel) component, frame);
				}
				break;
			case "jlabel":
				for (Component component : ((JLabel) comp).getComponents()) {
					if ("jlabel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addLabelListeners((JLabel) component, frame);
					if ("jpanel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addListeners((JPanel) component, frame);
				}
				break;
			case "passwordfield":
				comp.addKeyListener((KeyListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "textfield":
				comp.addKeyListener((KeyListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "spinner":
				((Spinner) comp).getEditor().getComponent(0).addKeyListener((KeyListener) frame);
				((Spinner) comp).getEditor().getComponent(0).setFocusTraversalKeysEnabled(false);
				break;
			case "combobox":
				if (comp != null) {
					ComboBox<?> cmb = (ComboBox) comp;
					if (cmb.isEditable()) {
						cmb.getEditor().getEditorComponent().addKeyListener((KeyListener) frame);
						cmb.getEditor().getEditorComponent().setFocusTraversalKeysEnabled(false);
						break;
					}
					cmb.addKeyListener((KeyListener) frame);
					cmb.setFocusTraversalKeysEnabled(false);
				}
				break;
			case "jcheckbox":
				comp.addKeyListener((KeyListener) frame);
				((JCheckBox) comp).addActionListener((ActionListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "table":
				comp.addKeyListener((KeyListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "jbutton":
				((JButton) comp).addActionListener((ActionListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
//			case "jilabalist":
//				comp.addKeyListener((KeyListener) frame);
//				break;
			case "jpanel":
				addListeners((JPanel) comp, frame);
				break;
			case "button":
				((Button) comp).addActionListener((ActionListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			}
		}
	}

	public static void addLabelListeners(JLabel panelMain, JFrame frame) {
		for (Component comp : panelMain.getComponents()) {
			Component[] tapComps;
			switch (comp.getClass().getSimpleName().toLowerCase()) {
			case "jtabbedpane":
				tapComps = ((JTabbedPane) comp).getComponents();
				for (Component component : tapComps) {
					if ("jlabel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addLabelListeners((JLabel) component, frame);
				}
				break;
			case "jlabel":
				for (Component component : ((JLabel) comp).getComponents()) {
					if ("jlabel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addLabelListeners((JLabel) component, frame);
					if ("jpanel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addListeners((JPanel) component, frame);
				}
				break;
			case "passwordfield":
				comp.addKeyListener((KeyListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "textfield":
				comp.addKeyListener((KeyListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "spinner":
				((Spinner) comp).getEditor().getComponent(0).addKeyListener((KeyListener) frame);
				((Spinner) comp).getEditor().getComponent(0).setFocusTraversalKeysEnabled(false);
				break;
			case "combobox":
				if (comp != null) {
					ComboBox<?> cmb = (ComboBox) comp;
					if (cmb.isEditable()) {
						cmb.getEditor().getEditorComponent().addKeyListener((KeyListener) frame);
						cmb.getEditor().getEditorComponent().setFocusTraversalKeysEnabled(false);
						break;
					}
					cmb.addKeyListener((KeyListener) frame);
					cmb.setFocusTraversalKeysEnabled(false);
				}
				break;
			case "jcheckbox":
				comp.addKeyListener((KeyListener) frame);
				((JCheckBox) comp).addActionListener((ActionListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "table":
				comp.addKeyListener((KeyListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			case "jbutton":
				((JButton) comp).addActionListener((ActionListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
//			case "jilabalist":
//				comp.addKeyListener((KeyListener) frame);
//				break;
			case "jpanel":
				addListeners((JPanel) comp, frame);
				break;
			case "button":
				((Button) comp).addActionListener((ActionListener) frame);
				comp.setFocusTraversalKeysEnabled(false);
				break;
			}
		}
	}

	public static void addListeners(JPanel panelMain, JDialog jDialog) {
		for (Component comp : panelMain.getComponents()) {
			Component[] tapComps;
			switch (comp.getClass().getSimpleName().toLowerCase()) {
			case "jtabbedpane":
				tapComps = ((JTabbedPane) comp).getComponents();
				for (Component component : tapComps) {
					if ("jpanel".equalsIgnoreCase(component.getClass().getSimpleName().toLowerCase()))
						addListeners((JPanel) component, jDialog);
				}
				break;
			case "passwordfield":
				((PasswordField) comp).addKeyListener((KeyListener) jDialog);
				break;
			case "textfield":
				((TextField) comp).addKeyListener((KeyListener) jDialog);
				break;
			case "spinner":
				((Spinner) comp).getEditor().getComponent(0).addKeyListener((KeyListener) jDialog);
				break;
			case "combobox":
				if ((ComboBox) comp != null) {
					ComboBox<?> cmb = (ComboBox) comp;
					if (cmb.isEditable()) {
						cmb.getEditor().getEditorComponent().addKeyListener((KeyListener) jDialog);
						break;
					}
					cmb.addKeyListener((KeyListener) jDialog);
				}
				break;
			case "jcheckbox":
				((JCheckBox) comp).addKeyListener((KeyListener) jDialog);
				((JCheckBox) comp).addActionListener((ActionListener) jDialog);
				break;
			case "table":
				((Table) comp).addKeyListener((KeyListener) jDialog);
				break;
			case "jbutton":
				((JButton) comp).addActionListener((ActionListener) jDialog);
				break;
			case "jialababutton":
				((JButton) comp).addActionListener((ActionListener) jDialog);
				break;
			case "jscrollpane":
				addListenersToScrolPaneComponent((JScrollPane) comp, jDialog);
				break;
			}
		}
	}

	private static void addListenersToScrolPaneComponent(JScrollPane comp, JDialog jDialog) {
		for (Component component : comp.getComponents()) {
			switch (component.getClass().getSimpleName().toLowerCase()) {
			case "jpanel":
				addListeners((JPanel) component, jDialog);
				break;
			case "checklist":
				((CheckList) component).addKeyListener((KeyListener) jDialog);
				((CheckList) component).setFocusTraversalKeysEnabled(false);
				break;
			case "table":
				((Table) component).addKeyListener((KeyListener) jDialog);
				((Table) component).setFocusTraversalKeysEnabled(false);
				break;
//			case "jilabalist":
//				((JilabaList) component).addKeyListener((KeyListener) jDialog);
//				break;
			case "jviewport":
				addListenersToViewPortComponents((JViewport) component, jDialog);
				break;
			}
		}
	}

	private static void addListenersToViewPortComponents(JViewport comp, JDialog jDialog) {
		for (Component component : comp.getComponents()) {
			switch (component.getClass().getSimpleName().toLowerCase()) {
			case "jpanel":
				addListeners((JPanel) component, jDialog);
				break;
			case "checklist":
				((CheckList) component).addKeyListener((KeyListener) jDialog);
				((CheckList) component).setFocusTraversalKeysEnabled(false);
				break;
			case "table":
				((Table) component).addKeyListener((KeyListener) jDialog);
				((Table) component).setFocusTraversalKeysEnabled(false);
				break;
//			case "jilabalist":
//				((JilabaList) component).addKeyListener((KeyListener) jDialog);
//				break;
			}
		}
	}

	private static void addListenersToScrolPaneComponent(JScrollPane comp, JInternalFrame frm) {
		for (Component component : comp.getComponents()) {
			switch (component.getClass().getSimpleName().toLowerCase()) {
			case "jpanel":
				addListeners((JPanel) component, frm);
				break;
			case "checklist":
				((CheckList) component).addKeyListener((KeyListener) frm);
				((CheckList) component).setFocusTraversalKeysEnabled(false);
				break;
			case "table":
				((Table) component).addKeyListener((KeyListener) frm);
				((Table) component).setFocusTraversalKeysEnabled(false);
				break;
//			case "jilabalist":
//				((JilabaList) component).addKeyListener((KeyListener) frm);
//				break;
			case "jviewport":
				addListenersToViewPortComponents((JViewport) component, frm);
				break;
			}
		}
	}

	private static void addListenersToViewPortComponents(JViewport comp, JInternalFrame frm) {
		for (Component component : comp.getComponents()) {
			switch (component.getClass().getSimpleName().toLowerCase()) {
			case "jpanel":
				addListeners((JPanel) component, frm);
				break;
			case "checklist":
				((CheckList) component).addKeyListener((KeyListener) frm);
				((CheckList) component).setFocusTraversalKeysEnabled(false);
				break;
			case "table":
				((Table) component).addKeyListener((KeyListener) frm);
				((Table) component).setFocusTraversalKeysEnabled(false);
				break;
//			case "jilabalist":
//				((JilabaList) component).addKeyListener((KeyListener) frm);
//				break;
			}
		}
	}

	public static boolean cmbEditorvalidCheck(ComboBox<?> cmb) {
		if (cmb.getEditor().getItem().equals(""))
			return false;
		if (cmb.getEditor().getItem().equals("/"))
			return false;
		if (cmb.getSelectedIndex() < 0)
			return false;
		return true;
	}

	public static boolean cmbEditorvalidCheck(JComboBox<?> cmb) {
		if (cmb.getEditor().getItem().equals(""))
			return false;
		if (cmb.getSelectedIndex() < 0)
			return false;
		return true;
	}

	public static void finDateCheck(String valueInSaveFormat) throws Exception {
//		LocalDate value = LocalDate.parse(valueInSaveFormat);
//		LocalDate finFromDate = LocalDate.parse(PackingMaterialCommon.getFilemain().getFromDate());
//		LocalDate finToDate = LocalDate.parse(PackingMaterialCommon.getFilemain().getToDate());
//		if (finFromDate.isAfter(value))
//			throw new Exception("Date Can't be Less than Financial Date..!");
//		if (finToDate.isBefore(value))
//			throw new Exception("Date Can't be Greater than Financial Date..!");
	}

	public static void futureDateCheck(String valueInSaveFormat) throws Exception {
		LocalDate value = LocalDate.parse(valueInSaveFormat);
		if (value.isAfter(LocalDate.now()))
			throw new Exception("Future Date Not Allowed..!");
	}

	public static void BackDateCheck(String valueInSaveFormat) throws Exception {
		LocalDate value = LocalDate.parse(valueInSaveFormat);
		if (value.isBefore(LocalDate.now()))
			throw new Exception("Back Date Not Allowed..!");
	}

	public static void futureDateCheckForSessionClose(String valueInSaveFormat) throws Exception {
		LocalDate value = LocalDate.parse(valueInSaveFormat);
		if (value.isAfter(LocalDate.now().plusDays(1L)))
			throw new Exception("Future Date Not Allowed..!");
	}

	public static void fromDateToDateCheck(Spinner spnFromDate, Spinner spnToDate) throws Exception {
		Date spnFrmDate = (Date) spnFromDate.getValue();
		Date toDate = (Date) spnToDate.getValue();
		String fromDat = spnFromDate.getDateValue("yyyy-MM-dd");
		String toDat = spnToDate.getDateValue("yyyy-MM-dd");
		if (!toDat.equalsIgnoreCase(fromDat) && toDate.before(spnFrmDate))
			throw new Exception("To Date should not be Before From date...");
	}

	public static void centerScreen(JComponent parentComponent, JComponent childComponent, boolean tabPanAvailOrNot)
			throws Exception {
		try {
			if (tabPanAvailOrNot) {
				childComponent.setBounds(parentComponent.getWidth() / 2 - (childComponent.getSize()).width / 2,
						parentComponent.getHeight() / 2 - (childComponent.getSize()).height / 2, childComponent

								.getWidth(),
						childComponent.getHeight());
			} else {
				childComponent.setLocation(parentComponent.getWidth() / 2 - (childComponent.getSize()).width / 2,
						parentComponent.getHeight() / 2 - (childComponent.getSize()).height / 2);
			}
		} catch (ArithmeticException e) {
			throw new Exception("Future Date Not Allowed..!");
		}
	}

	public static ImageIcon getImage(String imagePath, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(classLoader.getResource(imagePath));
		Image image = imageIcon.getImage().getScaledInstance(width, height, 4);
		imageIcon.setImage(image);
		return imageIcon;
	}

	public static void checkPresenceOfLetter(String column, String value) throws Exception {
		if (!Pattern.matches("^([0-9]{" + value.length() + "})$", value))
			throw new Exception("Enter Valid " + column + " !");
	}

	public static JButton svgButtonCreation(char mnemonicKey, String text, String enableIcon, String disableIcon,
			String focusIcon) throws IOException {
		JButton jButton = new JButton();
		jButton.setMnemonic(mnemonicKey);
		jButton.setVisible(true);
		jButton.setName(text);
		if (classLoader.getResource(enableIcon) != null)
			jButton.setIcon(getSvgImage(enableIcon, jButton.getWidth(), jButton.getHeight()));
		if (classLoader.getResource(disableIcon) != null)
			jButton.setDisabledIcon(getSvgImage(disableIcon, jButton.getWidth(), jButton.getHeight()));
		if (classLoader.getResource(focusIcon) != null)
			jButton.setRolloverIcon(getSvgImage(focusIcon));
		jButton.setContentAreaFilled(false);
		jButton.setBorder((Border) null);
		jButton.setFocusPainted(false);
		jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jButton.setOpaque(false);
		return jButton;
	}

	public static JButton svgButtonCreationBasedOnCompany(char mnemonicKey, String text, String enableIcon,
			String disableIcon, String focusIcon, String loginCompany) throws IOException {
		JButton jButton = new JButton();
		jButton.setMnemonic(mnemonicKey);
		jButton.setVisible(true);
		jButton.setName(text);
		if (classLoader.getResource(enableIcon) != null)
			jButton.setIcon(getSvgImage(enableIcon, jButton.getWidth(), jButton.getHeight()));
		if (classLoader.getResource(disableIcon) != null)
			jButton.setDisabledIcon(getSvgImage(disableIcon, jButton.getWidth(), jButton.getHeight()));
		if (classLoader.getResource(focusIcon) != null)
			jButton.setRolloverIcon(getSvgImage(focusIcon));
		jButton.setContentAreaFilled(false);
		jButton.setBorder((Border) null);
		jButton.setFocusPainted(false);
		jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jButton.setOpaque(false);
		if ("HO".equalsIgnoreCase(loginCompany)) {
			jButton.setEnabled(true);
		} else {
			jButton.setEnabled(false);
		}
		return jButton;
	}

	public static Icon getSvgImage(String disableIcon) {
		return (Icon) new FlatSVGIcon(disableIcon);
	}

	public static Icon getSvgImage(String disableIcon, int width, int height) {
		return (Icon) new FlatSVGIcon(disableIcon, width, height);
	}

	public static Table createTable(JPanel panelParent, int x, int y, int width, int height, List<Column> lstColumns,
			Color headerBgColor, Font tableHeaderFont, Font tableFont) {
		Table table = null;
		if (lstColumns != null) {
			table = new Table(lstColumns);
		} else {
			table = new Table();
		}
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setFont(tableFont);
//		TableHeaderCellrender masterTableHeaderCellrender = new TableHeaderCellrender(headerBgColor,
//				Color.WHITE, tableHeaderFont);
		JScrollPane scrollPaneTag = new JScrollPane((Component) table);
		scrollPaneTag.setBounds(x, y, width, height);
		scrollPaneTag.setOpaque(true);
		scrollPaneTag.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		scrollPaneTag.setVisible(true);
		scrollPaneTag.setColumnHeader(new JViewport() {
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = 28;
				return d;
			}
		});
		panelParent.add(scrollPaneTag);
		table.resizeColumns(0.0D);
//		table.getTableHeader().setDefaultRenderer((TableCellRenderer) masterTableHeaderCellrender);
		table.getTableHeader().setResizingAllowed(true);
		table.setVisible(true);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(0);
		table.setRowHeight(25);
		table.setSize(new Dimension(width, height));
		table.setRowSelectionAllowed(true);
		setScrollPane(scrollPaneTag);
		return table;
	}

	public static JScrollPane getScrollPane() {
		return scrollPane;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		CustomControls.scrollPane = scrollPane;
	}
}
