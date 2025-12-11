package com.jora.billing.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableCellRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jora.billing.common.CommonValues;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.components.Column;
import com.jora.billing.components.ComboBox;
import com.jora.billing.components.Item;
import com.jora.billing.components.JTextFieldEnum.TextInputCase;
import com.jora.billing.components.JTextFieldEnum.TextInputType;
import com.jora.billing.components.Table;
import com.jora.billing.components.TableHeaderCellrender;
import com.jora.billing.components.TextField;
import com.jora.billing.custom.CustomControls;
import com.jora.billing.custom.FormAction;
import com.jora.billing.logic.OperatorLogic;
import com.jora.encodedecode.common.EncryptionDecryption;

@Component
public class FrmCategory extends JInternalFrame
		implements FormAction, KeyListener, ActionListener, InternalFrameListener {

	private static final long serialVersionUID = 1L;

	private JPanel panelMain;

	private JTabbedPane tabPan;

	private TextField txtCategoryGroup, txtCategoryName;

	private ComboBox<Item> cmbActive, cmbSaleType, cmbHSNCode;

	private Table tblView;

	private JLabel lblRecordCount;

	private final OperatorLogic operatorLogic;

	private final ObjectMapper objectMapper;

	private final Logger log = LogManager.getLogger(getClass());

	private int operCode = -1;

	public FrmCategory(OperatorLogic operatorLogic, FrmMDI frmMDI, ObjectMapper objectMapper) {
		this.operatorLogic = operatorLogic;
		this.objectMapper = objectMapper;
		try {
			setTitle("CATEGORY ENTRY");
			addInternalFrameListener(this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), "Problem occured while loading Operator Entry");
		}
	}

	private void componentListener() throws Exception {
		CustomControls.addListeners(this.panelMain, this);
		txtCategoryName.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				if (txtCategoryName.isEnabled()
						&& "".equalsIgnoreCase(String.valueOf(txtCategoryName.getText()).trim())) {
					JOptionPane.showMessageDialog(getContentPane(), "Enter the Category Name!.");
					return false;
				}
				return true;
			}
		});
	}

	private void componentCreation() throws Exception {
		Font font = new Font("", 1, 12);
		panelMain = new JPanel(null);
		panelMain.setBounds(0, 0, CommonValues.getINTERNALFRAMEWIDTH(), CommonValues.getINTERNALFRAMEHEIGHT());
		getContentPane().add(panelMain);
		JPanel panelEntry = new JPanel(null);
		panelEntry.setBounds(0, 0, CommonValues.getINTERNALFRAMEWIDTH(), CommonValues.getINTERNALFRAMEHEIGHT());
		panelEntry.setBackground(CommonValues.getMAIN_COLOR());
		JPanel panelView = new JPanel(null);
		panelView.setBounds(0, 0, CommonValues.getINTERNALFRAMEWIDTH(), CommonValues.getINTERNALFRAMEHEIGHT());
		panelView.setBackground(CommonValues.getMAIN_COLOR());
		Rectangle rectangle = CustomControls.getRectangle(panelEntry.getWidth(), panelEntry.getHeight(), 32.0D, 12.0D,
				35.0D, 80.0D);
		JPanel panelSubEntry = new JPanel(null);
		panelSubEntry.setBorder(BorderFactory.createEtchedBorder(1));
		panelSubEntry.setBounds(rectangle);
		panelSubEntry.setVisible(true);
		panelSubEntry.setBorder(
				BorderFactory.createLineBorder(CommonValues.getBORDER_COLOR(), CommonValues.getBorderthicknes()));
		panelSubEntry.setBackground(CommonValues.getMAIN_COLOR());
		panelEntry.add(panelSubEntry);
		tabPan = new JTabbedPane();
		tabPan.setBounds(0, -27, CommonValues.getINTERNALFRAMEWIDTH() + 30, CommonValues.getINTERNALFRAMEHEIGHT() + 60);
		tabPan.setVisible(true);
		tabPan.add("Entry", panelEntry);
		tabPan.add("View", panelView);
		tabPan.setEnabled(false);
		panelMain.add(tabPan);
		rectangle = CustomControls.getRectangle(panelSubEntry.getWidth(), panelSubEntry.getHeight(), 8.0D, 14.0D, 20.0D,
				8.0D);
		JLabel lblCategoryGroup = new JLabel("Category Group");
		lblCategoryGroup.setBounds(rectangle);
		lblCategoryGroup.setFont(font);
		panelSubEntry.add(lblCategoryGroup);
		rectangle = CustomControls.getRectangle(panelSubEntry.getWidth(), panelSubEntry.getHeight(), 32.0D, 14.0D,
				58.0D, 7.0D);
		txtCategoryGroup = new TextField();
		txtCategoryGroup.setBounds(rectangle);
		txtCategoryGroup.setFont(font);
		txtCategoryGroup.setEditable(true);
		txtCategoryGroup.setTextCase(TextInputCase.UPPER);
		txtCategoryGroup.setTextType(TextInputType.ALPHA);
		txtCategoryGroup.setVerifyInputWhenFocusTarget(true);
		panelSubEntry.add(txtCategoryGroup);
		int lblWidth = lblCategoryGroup.getWidth();
		int componentHeight = txtCategoryGroup.getHeight();
		int componentWidth = txtCategoryGroup.getWidth();
		double hGap = 7.0D;
		int y = CommonValues.verticalGap(panelSubEntry, txtCategoryGroup, hGap);
		JLabel lblCategoryName = new JLabel("Category Name");
		lblCategoryName.setBounds(lblCategoryGroup.getX(), y, lblWidth, componentHeight);
		lblCategoryName.setFont(font);
		panelSubEntry.add(lblCategoryName);
		txtCategoryName = new TextField();
		txtCategoryName.setBounds(txtCategoryGroup.getX(), lblCategoryName.getY(), componentWidth, componentHeight);
		txtCategoryName.setFont(font);
		txtCategoryName.setEditable(true);
		txtCategoryName.setTextCase(TextInputCase.UPPER);
		txtCategoryName.setTextType(TextInputType.ALPHANUMERIC);
		txtCategoryName.setMaxLength(10);
		txtCategoryName.setVerifyInputWhenFocusTarget(true);
		panelSubEntry.add(txtCategoryName);
		y = CommonValues.verticalGap(panelSubEntry, txtCategoryName, hGap);
		JLabel lblHSNCode = new JLabel("HSN Code");
		lblHSNCode.setBounds(lblCategoryName.getX(), y, lblWidth, componentHeight);
		lblHSNCode.setFont(font);
		panelSubEntry.add(lblHSNCode);
		cmbHSNCode = new ComboBox<>();
		cmbHSNCode.setBounds(txtCategoryName.getX(), lblHSNCode.getY(), componentWidth, componentHeight);
		cmbHSNCode.setFont(font);
		panelSubEntry.add(cmbHSNCode);
		y = CommonValues.verticalGap(panelSubEntry, cmbHSNCode, hGap);
		JLabel lblSaleType = new JLabel("Sale Type");
		lblSaleType.setBounds(lblCategoryName.getX(), y, lblWidth, componentHeight);
		lblSaleType.setFont(font);
		panelSubEntry.add(lblSaleType);
		cmbSaleType = new ComboBox<>();
		cmbSaleType.setBounds(cmbHSNCode.getX(), lblSaleType.getY(), componentWidth, componentHeight);
		cmbSaleType.setFont(font);
		panelSubEntry.add(cmbSaleType);
		y = CommonValues.verticalGap(panelSubEntry, cmbSaleType, hGap);
		JLabel lblActive = new JLabel("Active");
		lblActive.setBounds(lblCategoryName.getX(), y, lblWidth, componentHeight);
		lblActive.setFont(font);
		panelSubEntry.add(lblActive);
		cmbActive = new ComboBox<>();
		cmbActive.setBounds(cmbSaleType.getX(), lblActive.getY(), componentWidth, componentHeight);
		cmbActive.setFont(font);
		panelSubEntry.add(cmbActive);

		tblView = new Table();
		tblView.setRowHeight(CommonValues.getTablerowheight());
		tblView.setFont(CommonValues.getTablefont());
		tblView.setForeground(CommonValues.getTableforegroundcolor());
		tblView.setSelectionMode(JTable.AUTO_RESIZE_OFF);
		tblView.setBackground(CommonValues.getMAIN_COLOR());
		TableHeaderCellrender headerCellrender = new TableHeaderCellrender(CommonValues.getBORDER_COLOR(), Color.WHITE,
				CommonValues.getTableheaderfont());
		tblView.getTableHeader().setDefaultRenderer((TableCellRenderer) headerCellrender);
		tblView.disableFlatLafHeaderHover();
		tblView.getTableHeader().setReorderingAllowed(false);
		tblView.setShowHorizontalLines(true);
		tblView.setRowSelectionAllowed(true);
		tblView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblView.setShowVerticalLines(true);
		tblView.setAutoResizeMode(4);
		rectangle = CustomControls.getRectangle(panelView.getWidth(), panelView.getHeight(), 5.0D, 10.0D, 90.0D, 80.0D);
		JScrollPane scrPan = new JScrollPane(tblView);
		scrPan.setBounds(rectangle);
		scrPan.setBorder(BorderFactory.createLineBorder(CommonValues.getBORDER_COLOR(), 3));
		panelView.add(scrPan);
		tblView.setColumn(getColumns(scrPan.getWidth()));
		lblRecordCount = new JLabel("OPERATOR COUNT : ");
		lblRecordCount.setBounds(scrPan.getX(), scrPan.getY() + scrPan.getHeight(), componentWidth, componentHeight);
		lblRecordCount.setFont(new Font("", 1, 13));
		panelView.add(this.lblRecordCount);
		JLabel lblHint = new JLabel("Press E For Edit");
		lblHint.setBounds((int) (this.lblRecordCount.getX() * 17.5D), this.lblRecordCount.getY(), componentWidth,
				componentHeight);
		lblHint.setFont(new Font("", 1, 13));
		panelView.add(lblHint);
		CustomControls.controlEnableDisable(this.tabPan.getComponents(), false);
	}

	private List<Column> getColumns(int scrlwidth) {
		List<Column> lstCol = new ArrayList<>();
		lstCol.add(new Column("Operator Code", Integer.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D),
				4));
		lstCol.add(
				new Column("Operator Name", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 15.0D), 2));
		lstCol.add(
				new Column("Entry Rights", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(
				new Column("Edit Rights", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(
				new Column("View Rights", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(
				new Column("Delete Rights", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(
				new Column("Cancel Entry", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("Rate Entry", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("Pro User", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("Previous Day Entry", String.class,
				CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(
				new Column("Company Name", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 15.0D), 2));
		lstCol.add(new Column("CostCode", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 15.0D), 2));
		lstCol.add(new Column("Active", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("operator", Map.class, 0, 2));
		return lstCol;
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		try {
			componentCreation();
			componentListener();
		} catch (Exception e2) {
			log.error(ErrorHandler.errorTraceForLogger(e2));
			JOptionPane.showMessageDialog(this, "Problem occured while loading Operator");
		}
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (e.getSource() == txtCategoryGroup) {
					if (!validateFields(txtCategoryGroup))
						return;
					txtCategoryName.requestFocus();
				} else if (e.getSource() == txtCategoryName) {
					if (!validateFields(txtCategoryName))
						return;
					cmbActive.requestFocus();
				} else if (e.getSource() == cmbActive) {
					FormCommon.getBtnSave().requestFocus();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_E && e.getSource() == tblView) {
				FormCommon.getBtnEdit().doClick();
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(getContentPane(), ErrorHandler.errorMessage(e2));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public boolean formAdd() {
		try {
			loadData();
			txtCategoryName.requestFocus();
			tabPan.setSelectedIndex(0);
			operCode = -1;
			CustomControls.controlEnableDisable(((JPanel) tabPan.getComponent(0)).getComponents(), true);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	private void loadData() throws Exception {

		cmbSaleType.removeAllItems();
		cmbSaleType.addListItem(new Item("Both", "B"));
		cmbSaleType.addListItem(new Item("Rate", "R"));
		cmbSaleType.addListItem(new Item("Weight", "W"));

		cmbActive.removeAllItems();
		cmbActive.addListItem(new Item("Yes", "Y"));
		cmbActive.addListItem(new Item("No", "N"));

	}

	@Override
	public boolean formSave() {
		try {
			if (!validateFields(null))
				return false;

			Map<String, Object> saveMap = new HashMap<>();
			saveMap.put("catgroup", txtCategoryGroup.getText().trim());
			saveMap.put("catname", EncryptionDecryption.encrypt(txtCategoryName.getText()));
			saveMap.put("active", cmbActive.getSelectedItemValue());
			if (operCode == -1) {
				if (operatorLogic.save(saveMap) > 0) {
					JOptionPane.showMessageDialog(this, txtCategoryGroup.getText() + " saved Successfully!..");
				}
			} else {
				saveMap.put("operatorcode", operCode);
				saveMap.put("modifiedby", CommonValues.getOperator().getOperatorCode());
				if (operatorLogic.update(saveMap) > 0) {
					JOptionPane.showMessageDialog(this, txtCategoryGroup.getText() + " updated Successfully!..");
				}
			}
			FormCommon.getBtnCancel().doClick();
			return true;
		} catch (Exception e) {
			log.error(ErrorHandler.errorMessage(e));
			JOptionPane.showMessageDialog(this, ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	@Override
	public boolean formView() {
		try {
			CustomControls.controlEnableDisable(((JPanel) tabPan.getComponent(0)).getComponents(), false);
			int count = this.operatorLogic.viewOperators(tblView);
			lblRecordCount.setText("OPERATOR COUNT : " + count);
			tabPan.setSelectedIndex(1);
			CustomControls.controlEnableDisable(((JPanel) tabPan.getComponent(1)).getComponents(), true);
			tblView.changeSelection(0, 0, false, false);
			tblView.requestFocus();
			return true;
		} catch (Exception e) {
			log.error(ErrorHandler.errorMessage(e));
			JOptionPane.showMessageDialog(this, ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	@Override
	public boolean formEdit() {
		try {
			if (tblView.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(getContentPane(), "No row selected for edit...");
				tblView.changeSelection(0, 0, false, false);
				tblView.requestFocus();
				return false;
			}
			cmbActive.removeAllItems();
			cmbActive.addListItem(new Item("Yes", "Y"));
			cmbActive.addListItem(new Item("No", "N"));
			Map<String, Object> mapOperator = objectMapper.convertValue(
					tblView.getValueAt(tblView.getSelectedRow(), "operator"), new TypeReference<Map<String, Object>>() {
					});

			txtCategoryGroup.setText(mapOperator.get("operatorname").toString());
			txtCategoryName.setText(EncryptionDecryption.decrypt(mapOperator.get("password").toString()));
			cmbActive.setSelectedItemValue(mapOperator.get("active").toString());
			operCode = Integer.parseInt(mapOperator.get("operatorcode").toString());
			tabPan.setSelectedIndex(0);
			CustomControls.controlEnableDisable(((JPanel) tabPan.getComponent(0)).getComponents(), true);
			txtCategoryGroup.requestFocus();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	@Override
	public boolean formPosting() {
// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean formCancel() {
		try {
			CustomControls.clear((JPanel) tabPan.getComponent(0));
			CustomControls.clear((JPanel) tabPan.getComponent(1));
			tabPan.setSelectedIndex(0);
			operCode = -1;
			CustomControls.controlEnableDisable(tabPan.getComponents(), false);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	@Override
	public boolean formClose() {
		try {
			CustomControls.clear((JPanel) tabPan.getComponent(0));
			CustomControls.clear((JPanel) tabPan.getComponent(1));
			tabPan.setSelectedIndex(0);
			CustomControls.controlEnableDisable(tabPan.getComponents(), false);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	private boolean validateFields(java.awt.Component comp) {
		if (comp == null || comp == txtCategoryGroup) {
			if ("".equalsIgnoreCase(txtCategoryGroup.getText())) {
				txtCategoryGroup.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter the Operator Name");
				return false;
			}
		}
		if (comp == null || comp == txtCategoryName) {
			if ("".equalsIgnoreCase(txtCategoryName.getText())) {
				txtCategoryName.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter the Password..");
				return false;
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub

	}
}
