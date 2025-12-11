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
import javax.swing.JCheckBox;
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
import com.jora.billing.components.PasswordField;
import com.jora.billing.components.Table;
import com.jora.billing.components.TableHeaderCellrender;
import com.jora.billing.components.TextField;
import com.jora.billing.config.ApplicationConfig;
import com.jora.billing.custom.CustomControls;
import com.jora.billing.custom.FormAction;
import com.jora.billing.logic.OperatorLogic;
import com.jora.encodedecode.common.EncryptionDecryption;

@Component
public class FrmOperatorEntry extends JInternalFrame
		implements FormAction, KeyListener, ActionListener, InternalFrameListener {

	private static final long serialVersionUID = 1L;

	private JPanel panelMain;

	private JTabbedPane tabPan;

	private TextField txtOperName;

	private ComboBox<Item> cmbActive;

	private PasswordField txtPassWord;

	private JCheckBox chkProUser;

	private JCheckBox chkEntry;

	private JCheckBox chkView;

	private JCheckBox chkEdit;

	private JCheckBox chkDelete;

	private JCheckBox chkCancelAccess;

	private JCheckBox chkPreviousEntry;

	private JCheckBox chkRateEntry;

	private Table tblView;

	private JLabel lblRecordCount;

	private final OperatorLogic operatorLogic;

	private final ObjectMapper objectMapper;

	private final Logger log = LogManager.getLogger(getClass());

	private int operCode = -1;

	public FrmOperatorEntry(OperatorLogic operatorLogic, FrmMDI frmMDI, ObjectMapper objectMapper) {
		this.operatorLogic = operatorLogic;
		this.objectMapper = objectMapper;
		try {
			setTitle("OPERATOR ENTRY");
			addInternalFrameListener(this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), "Problem occured while loading Operator Entry");
		}
	}

	private void componentListener() throws Exception {
		CustomControls.addListeners(this.panelMain, this);
		txtPassWord.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				if (txtPassWord.isEnabled() && "".equalsIgnoreCase(String.valueOf(txtPassWord.getPassword()).trim())) {
					JOptionPane.showMessageDialog(getContentPane(), "Enter Password...");
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
		rectangle = CustomControls.getRectangle(panelSubEntry.getWidth(), panelSubEntry.getHeight(), 8.0D, 5.6D, 20.0D,
				8.0D);
		JLabel lblOptName = new JLabel("Operator Name");
		lblOptName.setBounds(rectangle);
		lblOptName.setFont(font);
		panelSubEntry.add(lblOptName);
		rectangle = CustomControls.getRectangle(panelSubEntry.getWidth(), panelSubEntry.getHeight(), 32.0D, 5.6D, 58.0D,
				7.0D);
		txtOperName = new TextField();
		txtOperName.setBounds(rectangle);
		txtOperName.setFont(font);
		txtOperName.setEditable(true);
		txtOperName.setTextCase(TextInputCase.UPPER);
		txtOperName.setTextType(TextInputType.ALPHA);
		txtOperName.setVerifyInputWhenFocusTarget(true);
		panelSubEntry.add(txtOperName);
		int lblWidth = lblOptName.getWidth();
		int componentHeight = txtOperName.getHeight();
		int componentWidth = txtOperName.getWidth();
		double hGap = 7.0D;
		int y = CommonValues.verticalGap(panelSubEntry, txtOperName, hGap);
		JLabel lblPassWord = new JLabel("Password");
		lblPassWord.setBounds(lblOptName.getX(), y, lblWidth, componentHeight);
		lblPassWord.setFont(font);
		panelSubEntry.add(lblPassWord);
		txtPassWord = CustomControls.createPasswordField(txtPassWord, 10, "Enter Password");
		txtPassWord.setVerifyInputWhenFocusTarget(true);
		txtPassWord.setBounds(txtOperName.getX(), lblPassWord.getY(), componentWidth, componentHeight);
		txtPassWord.setFont(font);
		panelSubEntry.add(txtPassWord);
		y = CommonValues.verticalGap(panelSubEntry, txtPassWord, hGap);
		JLabel lblActive = new JLabel("Active");
		lblActive.setBounds(lblPassWord.getX(), y, lblWidth, componentHeight);
		lblActive.setFont(font);
		panelSubEntry.add(lblActive);
		cmbActive = new ComboBox<>();
		cmbActive.setBounds(txtPassWord.getX(), lblActive.getY(), componentWidth, componentHeight);
		cmbActive.setFont(font);
		panelSubEntry.add(cmbActive);
		JLabel lblOnlyMblApp = new JLabel("Only Mobile App");
		lblOnlyMblApp.setBounds(lblActive.getX(), txtOperName.getY() * 9, lblWidth, componentHeight);
		lblOnlyMblApp.setFont(font);
		lblOnlyMblApp.setVisible(false);
		panelSubEntry.add(lblOnlyMblApp);
		y = CommonValues.verticalGap(panelSubEntry, cmbActive, hGap);
		chkProUser = new JCheckBox("Pro User");
		chkProUser.setBounds(cmbActive.getX(), y, lblWidth, componentHeight);
		chkProUser.setFont(font);
		chkProUser.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkProUser);
		chkEntry = new JCheckBox("Entry");
		chkEntry.setBounds(cmbActive.getX() + cmbActive.getWidth() - lblWidth, chkProUser.getY(), lblWidth,
				componentHeight);
		chkEntry.setFont(font);
		chkEntry.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkEntry);
		y = CommonValues.verticalGap(panelSubEntry, chkEntry, hGap);
		chkView = new JCheckBox("View");
		chkView.setBounds(chkProUser.getX(), y, lblWidth, componentHeight);
		chkView.setFont(font);
		chkView.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkView);
		chkEdit = new JCheckBox("Edit");
		chkEdit.setBounds(chkEntry.getX(), chkView.getY(), lblWidth, componentHeight);
		chkEdit.setMnemonic(84);
		chkEdit.setFont(font);
		chkEdit.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkEdit);
		y = CommonValues.verticalGap(panelSubEntry, chkEdit, hGap);
		chkDelete = new JCheckBox("Delete");
		chkDelete.setBounds(chkView.getX(), y, lblWidth, componentHeight);
		chkDelete.setMnemonic(85);
		chkDelete.setFont(font);
		chkDelete.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkDelete);
		chkCancelAccess = new JCheckBox("Cancel Access");
		chkCancelAccess.setBounds(chkEdit.getX(), chkDelete.getY(), (int) (lblWidth * 1.3D), componentHeight);
		chkCancelAccess.setFont(font);
		chkCancelAccess.setMnemonic(79);
		chkCancelAccess.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkCancelAccess);
		y = CommonValues.verticalGap(panelSubEntry, chkCancelAccess, hGap);
		chkPreviousEntry = new JCheckBox("Previous Day Entry");
		chkPreviousEntry.setBounds(chkDelete.getX(), y, (int) (lblWidth * 1.3D), componentHeight);
		chkPreviousEntry.setFont(font);
		chkPreviousEntry.setMnemonic(82);
		chkPreviousEntry.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkPreviousEntry);
		chkRateEntry = new JCheckBox("Rate Entry");
		chkRateEntry.setBounds(this.chkCancelAccess.getX(), this.chkPreviousEntry.getY(), lblWidth, componentHeight);
		chkRateEntry.setFont(font);
		chkRateEntry.setMnemonic(78);
		chkRateEntry.setBackground(panelSubEntry.getBackground());
		panelSubEntry.add(chkRateEntry);
		JLabel lblAccess = new JLabel("Access");
		lblAccess.setBounds(lblActive.getX(), chkView.getY(), lblWidth, componentHeight);
		lblAccess.setFont(font);
		panelSubEntry.add(lblAccess);
		tblView = new Table(true, true, CommonValues.getBORDER_COLOR());
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
				if (e.getSource() == txtOperName) {
					if (!validateFields(txtOperName))
						return;
					txtPassWord.requestFocus();
				} else if (e.getSource() == txtPassWord) {
					if (!validateFields(txtPassWord))
						return;
					cmbActive.requestFocus();
				} else if (e.getSource() == cmbActive) {
					chkProUser.requestFocus();
				} else if (e.getSource() == chkProUser) {
					chkEntry.requestFocus();
				} else if (e.getSource() == chkEntry) {
					chkView.requestFocus();
				} else if (e.getSource() == chkView) {
					chkEdit.requestFocus();
				} else if (e.getSource() == chkEdit) {
					chkDelete.requestFocus();
				} else if (e.getSource() == chkDelete) {
					chkCancelAccess.requestFocus();
				} else if (e.getSource() == chkCancelAccess) {
					chkPreviousEntry.requestFocus();
				} else if (e.getSource() == chkPreviousEntry) {
					chkRateEntry.requestFocus();
				} else if (e.getSource() == chkRateEntry) {
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
			cmbActive.removeAllItems();
			cmbActive.addListItem(new Item("Yes", "Y"));
			cmbActive.addListItem(new Item("No", "N"));
			chkEntry.setSelected(true);
			txtOperName.requestFocus();
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

	@Override
	public boolean formSave() {
		try {
			if (!validateFields(null))
				return false;

			Map<String, Object> saveMap = new HashMap<>();
			saveMap.put("operatorname", txtOperName.getText().trim());
			saveMap.put("password", EncryptionDecryption.encrypt(txtPassWord.getPasswordAsString()));
			saveMap.put("active", cmbActive.getSelectedItemValue());
			saveMap.put("prouser", CommonValues.selectedYOrN(chkProUser.isSelected()));
			saveMap.put("entrymode", CommonValues.selectedYOrN(chkEntry.isSelected()));
			saveMap.put("viewmode", CommonValues.selectedYOrN(chkView.isSelected()));
			saveMap.put("editmode", CommonValues.selectedYOrN(chkEdit.isSelected()));
			saveMap.put("deletemode", CommonValues.selectedYOrN(chkDelete.isSelected()));
			saveMap.put("cancelaccess", CommonValues.selectedYOrN(chkCancelAccess.isSelected()));
			saveMap.put("rateentry", CommonValues.selectedYOrN(chkRateEntry.isSelected()));
			saveMap.put("previousdateentry", CommonValues.selectedYOrN(chkPreviousEntry.isSelected()));
			saveMap.put("companytag", ApplicationConfig.companyTag);
			saveMap.put("companyflag", ApplicationConfig.companyFlag);
			saveMap.put("autogen", "Y");
			if (operCode == -1) {
				if (operatorLogic.save(saveMap) > 0) {
					JOptionPane.showMessageDialog(this, txtOperName.getText() + " saved Successfully!..");
				}
			} else {
				saveMap.put("operatorcode", operCode);
				saveMap.put("modifiedby", CommonValues.getOperator().getOperatorCode());
				if (operatorLogic.update(saveMap) > 0) {
					JOptionPane.showMessageDialog(this, txtOperName.getText() + " updated Successfully!..");
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

			txtOperName.setText(mapOperator.get("operatorname").toString());
			txtPassWord.setText(EncryptionDecryption.decrypt(mapOperator.get("password").toString()));
			chkEntry.setSelected("Y".equals(mapOperator.get("entrymode").toString()));
			chkEdit.setSelected("Y".equals(mapOperator.get("editmode").toString()));
			chkView.setSelected("Y".equals(mapOperator.get("viewmode").toString()));
			chkDelete.setSelected("Y".equals(mapOperator.get("deletemode").toString()));
			chkCancelAccess.setSelected("Y".equals(mapOperator.get("cancelaccess").toString()));
			chkRateEntry.setSelected("Y".equals(mapOperator.get("rateentry").toString()));
			chkProUser.setSelected("Y".equals(mapOperator.get("prouser").toString()));
			chkPreviousEntry.setSelected("Y".equals(mapOperator.get("previousdateentry").toString()));
			cmbActive.setSelectedItemValue(mapOperator.get("active").toString());
			operCode = Integer.parseInt(mapOperator.get("operatorcode").toString());
			tabPan.setSelectedIndex(0);
			CustomControls.controlEnableDisable(((JPanel) tabPan.getComponent(0)).getComponents(), true);
			txtOperName.requestFocus();
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
		if (comp == null || comp == txtOperName) {
			if ("".equalsIgnoreCase(txtOperName.getText())) {
				txtOperName.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter the Operator Name");
				return false;
			}
		}
		if (comp == null || comp == txtPassWord) {
			if ("".equalsIgnoreCase(txtPassWord.getPasswordAsString())) {
				txtPassWord.requestFocus();
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
