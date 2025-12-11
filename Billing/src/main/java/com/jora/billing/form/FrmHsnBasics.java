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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.jora.billing.common.CommonValues;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.components.Column;
import com.jora.billing.components.ComboBox;
import com.jora.billing.components.Item;
import com.jora.billing.components.JTextFieldEnum.TextInputCase;
import com.jora.billing.components.JTextFieldEnum.TextInputType;
import com.jora.billing.components.Table;
import com.jora.billing.components.TextField;
import com.jora.billing.config.ApplicationConfig;
import com.jora.billing.custom.CustomControls;
import com.jora.billing.custom.FormAction;
import com.jora.billing.logic.CommonLogic;
import com.jora.billing.logic.HsnBasicsLogic;

@Component
public class FrmHsnBasics extends JInternalFrame
		implements FormAction, KeyListener, ActionListener, InternalFrameListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HsnBasicsLogic hsnBasicsLogic;

	private final CommonLogic commonLogic;

	private JPanel panelMain;

	private TextField txtHsnCode, txtHsnName, txtSgst, txtCgst, txtIgst;

	private ComboBox<Item> cmbActive;

	private JTabbedPane tabPan;

	private Table tblView;

	private JLabel lblRecordCount;

	private final Logger log = LogManager.getLogger(getClass());

	public FrmHsnBasics(HsnBasicsLogic hsnBasicsLogic, CommonLogic commonLogic) {
		this.hsnBasicsLogic = hsnBasicsLogic;
		this.commonLogic = commonLogic;
		try {
			setTitle("HSN BASICS");
			addInternalFrameListener(this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), "Problem occured while loading HSNBasics Entry");
		}
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
		JLabel lblHsnCode = new JLabel("HsnCode");
		lblHsnCode.setBounds(rectangle);
		lblHsnCode.setFont(font);
		panelSubEntry.add(lblHsnCode);
		rectangle = CustomControls.getRectangle(panelSubEntry.getWidth(), panelSubEntry.getHeight(), 32.0D, 5.6D, 58.0D,
				7.0D);
		txtHsnCode = new TextField();
		txtHsnCode.setBounds(rectangle);
		txtHsnCode.setFont(font);
		txtHsnCode.setEditable(true);
		txtHsnCode.setTextCase(TextInputCase.UPPER);
		txtHsnCode.setTextType(TextInputType.NUMBER);
//		txtHsnCode.setMaxLength(8);
		txtHsnCode.setVerifyInputWhenFocusTarget(true);
		panelSubEntry.add(txtHsnCode);

		int lblWidth = lblHsnCode.getWidth();
		int componentHeight = txtHsnCode.getHeight();
		int componentWidth = txtHsnCode.getWidth();
		double hGap = 7.0D;
		int y = CommonValues.verticalGap(panelSubEntry, txtHsnCode, hGap);
		JLabel lblHsnName = new JLabel("HSN Name");
		lblHsnName.setBounds(lblHsnCode.getX(), y, lblWidth, componentHeight);
		lblHsnName.setFont(font);
		panelSubEntry.add(lblHsnName);

		txtHsnName = new TextField();
		txtHsnName.setBounds(txtHsnCode.getX(), lblHsnName.getY(), componentWidth, componentHeight);
		txtHsnName.setFont(font);
		txtHsnName.setEditable(true);
//		txtHsnName.setMaxLength(500);
		txtHsnName.setTextCase(TextInputCase.UPPER);
		txtHsnName.setTextType(TextInputType.ALPHANUMERIC);
		txtHsnName.setVerifyInputWhenFocusTarget(true);
		txtHsnName.setVisible(true);
		panelSubEntry.add(txtHsnName);

		y = CommonValues.verticalGap(panelSubEntry, txtHsnName, hGap);

		JLabel lblSgst = new JLabel("SGST");
		lblSgst.setBounds(lblHsnCode.getX(), y, lblWidth, componentHeight);
		lblSgst.setFont(font);
		panelSubEntry.add(lblSgst);

		txtSgst = new TextField();
		txtSgst.setBounds(txtHsnCode.getX(), lblSgst.getY(), componentWidth, componentHeight);
		txtSgst.setFont(font);
		txtSgst.setEditable(true);
		txtSgst.setTextCase(TextInputCase.UPPER);
		txtSgst.setTextType(TextInputType.NUMERIC);
//		txtSgst.setMaxLength(6);
		txtSgst.setVerifyInputWhenFocusTarget(true);
		panelSubEntry.add(txtSgst);

		y = CommonValues.verticalGap(panelSubEntry, txtSgst, hGap);

		JLabel lblCgst = new JLabel("CGST");
		lblCgst.setBounds(lblHsnCode.getX(), y, lblWidth, componentHeight);
		lblCgst.setFont(font);
		panelSubEntry.add(lblCgst);

		txtCgst = new TextField();
		txtCgst.setBounds(txtHsnCode.getX(), lblCgst.getY(), componentWidth, componentHeight);
		txtCgst.setFont(font);
		txtCgst.setEditable(true);
		txtCgst.setTextCase(TextInputCase.UPPER);
		txtCgst.setTextType(TextInputType.NUMERIC);
//		txtCgst.setMaxLength(6);
		txtCgst.setVerifyInputWhenFocusTarget(true);
		panelSubEntry.add(txtCgst);

		y = CommonValues.verticalGap(panelSubEntry, txtCgst, hGap);

		JLabel lblIgst = new JLabel("IGST");
		lblIgst.setBounds(lblHsnCode.getX(), y, lblWidth, componentHeight);
		lblIgst.setFont(font);
		panelSubEntry.add(lblIgst);

		txtIgst = new TextField();
		txtIgst.setBounds(txtHsnCode.getX(), lblIgst.getY(), componentWidth, componentHeight);
		txtIgst.setFont(font);
		txtIgst.setEditable(true);
		txtIgst.setTextCase(TextInputCase.UPPER);
		txtIgst.setTextType(TextInputType.NUMERIC);
//		txtIgst.setMaxLength(6);
		txtIgst.setVerifyInputWhenFocusTarget(true);
		panelSubEntry.add(txtIgst);

		y = CommonValues.verticalGap(panelSubEntry, txtIgst, hGap);

		JLabel lblActive = new JLabel("Active");
		lblActive.setBounds(lblHsnName.getX(), y, lblWidth, componentHeight);
		lblActive.setFont(font);
		panelSubEntry.add(lblActive);
		cmbActive = new ComboBox<>();
		cmbActive.setBounds(txtHsnName.getX(), lblActive.getY(), componentWidth, componentHeight);
		cmbActive.setFont(font);
		panelSubEntry.add(cmbActive);

		tblView = new Table();
		tblView.setRowHeight(CommonValues.getTablerowheight());
		tblView.setFont(CommonValues.getTablefont());
		tblView.setForeground(CommonValues.getTableforegroundcolor());
		tblView.setSelectionMode(0);
		tblView.setBackground(CommonValues.getMAIN_COLOR());
//		TableHeaderCellrender headerCellrender = new TableHeaderCellrender(
//				CommonValues.getBORDER_COLOR().brighter(), Color.WHITE.brighter(),
//				CommonValues.getTableheaderfont());
//		this.tblView.getTableHeader().setDefaultRenderer((TableCellRenderer) headerCellrender);
		tblView.setColumnSelectionAllowed(true);
		tblView.setShowHorizontalLines(true);
//		tblView.addKeyListener(this);
		tblView.setShowVerticalLines(true);
		tblView.setAutoResizeMode(4);
		rectangle = CustomControls.getRectangle(panelView.getWidth(), panelView.getHeight(), 5.0D, 10.0D, 90.0D, 80.0D);
		JScrollPane scrPan = new JScrollPane(tblView);
		scrPan.setBounds(rectangle);
		scrPan.setBorder(BorderFactory.createLineBorder(CommonValues.getBORDER_COLOR(), 3));
		panelView.add(scrPan);
		tblView.setColumn(lstCol(scrPan.getWidth()));
		lblRecordCount = new JLabel("OPERATOR COUNT : ");
		lblRecordCount.setBounds(scrPan.getX(), scrPan.getY() + scrPan.getHeight(), componentWidth, componentHeight);
		lblRecordCount.setFont(new Font("", 1, 13));
		lblRecordCount.setForeground(Color.red);
		panelView.add(this.lblRecordCount);
		JLabel lblHint = new JLabel("<<< Press E For Edit >>>");
		lblHint.setBounds((int) (this.lblRecordCount.getX() * 17.5D), this.lblRecordCount.getY(), componentWidth,
				componentHeight);
		lblHint.setFont(new Font("", 1, 13));
		lblHint.setForeground(Color.red);
		panelView.add(lblHint);

		CustomControls.controlEnableDisable(this.tabPan.getComponents(), false);
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

	private void componentListener() {
		CustomControls.addListeners(this.panelMain, this);

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (e.getSource() == txtHsnCode) {
					txtHsnName.requestFocus();
				} else if (e.getSource() == txtHsnName) {
					txtSgst.requestFocus();
				} else if (e.getSource() == txtSgst) {
					txtCgst.requestFocus();
				} else if (e.getSource() == txtCgst) {
					txtIgst.requestFocus();
				} else if (e.getSource() == txtIgst) {
					cmbActive.requestFocus();
				} else if (e.getSource() == cmbActive) {
					FormCommon.getBtnSave().requestFocus();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				if (e.getSource() == tblView) {
					if (tblView.getRowCount() != 0) {
//						String.valueOf(tblLotStoneDetails.getValueAt(tblLotSelectedRow, "BalPieces"))
					}
				}
			}
		} catch (Exception e2) {

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public boolean formAdd() {
		try {
			loadIntials();
			tabPan.setSelectedIndex(0);
			CustomControls.controlEnableDisable(((JPanel) tabPan.getComponent(0)).getComponents(), true);
			txtHsnCode.requestFocus();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	private void loadIntials() {
		try {
			commonLogic.loadActive(cmbActive);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
		}

	}

	@Override
	public boolean formSave() {
		try {
			if (!validateFields(null))
				return false;

			Map<String, Object> saveMap = new HashMap<>();
			saveMap.put("hsncode", txtHsnCode.getText().trim());
			saveMap.put("hsnname", txtHsnName.getText().trim());
			saveMap.put("sgst", txtSgst.getText().trim());
			saveMap.put("cgst", txtCgst.getText().trim());
			saveMap.put("igst", txtIgst.getText().trim());
			saveMap.put("active", cmbActive.getSelectedItemValue());
			saveMap.put("companytag", ApplicationConfig.companyTag);
			saveMap.put("companyflag", ApplicationConfig.companyFlag);
			saveMap.put("createdby", 1);

			if (hsnBasicsLogic.save(saveMap) > 0) {
				JOptionPane.showMessageDialog(this, txtHsnName.getText() + " saved Successfully!..");
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

	private boolean validateFields(java.awt.Component comp) throws Exception {
		if (comp == null || comp == txtHsnCode) {
			if ("".equalsIgnoreCase(txtHsnCode.getText())) {
				txtHsnCode.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter the HSN Code");
				return false;
			}

			if (commonLogic.chkExisting("hsnbasics", "hsncode", txtHsnCode.getText(), "String")) {
				throw new Exception("Hsncode " + txtHsnCode.getText() + " Already Exists");
			}

		}

		if (comp == null || comp == txtHsnName) {
			if ("".equalsIgnoreCase(txtHsnName.getText())) {
				txtHsnName.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter the HSN Name");
				return false;
			}
		}

		if (comp == null || comp == txtSgst) {
			txtSgst.setText(txtSgst.getText().trim().isEmpty() ? "0.00" : txtSgst.getText());
			if (Double.parseDouble(txtSgst.getText()) > 100) {
				txtSgst.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter Valid SGST");
				return false;
			}
		}

		if (comp == null || comp == txtCgst) {
			txtCgst.setText(txtCgst.getText().trim().isEmpty() ? "0.00" : txtCgst.getText());
			if (Double.parseDouble(txtCgst.getText()) > 100) {
				txtCgst.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter Valid CGST");
				return false;
			}
		}

		if (comp == null || comp == txtIgst) {
			txtIgst.setText(txtIgst.getText().trim().isEmpty() ? "0.00" : txtIgst.getText());
			if (Double.parseDouble(txtIgst.getText()) > 100) {
				txtIgst.requestFocus();
				JOptionPane.showMessageDialog(this, "Enter Valid IGST");
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean formView() {
		try {
			CustomControls.controlEnableDisable(((JPanel) tabPan.getComponent(0)).getComponents(), false);
			int count = hsnBasicsLogic.viewHsnBasics(tblView);
			lblRecordCount.setText("RECORD COUNT : " + count);
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

		return false;
	}

	@Override
	public boolean formPosting() {

		return false;
	}

	@Override
	public boolean formCancel() {
		try {
			CustomControls.clear((JPanel) this.tabPan.getComponent(0));
			CustomControls.clear((JPanel) this.tabPan.getComponent(1));
			this.tabPan.setSelectedIndex(0);
			CustomControls.controlEnableDisable(this.tabPan.getComponents(), false);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, ErrorHandler.errorMessage(e));
//			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	@Override
	public boolean formClose() {
		try {
			CustomControls.clear((JPanel) this.tabPan.getComponent(0));
			CustomControls.clear((JPanel) this.tabPan.getComponent(1));
			this.tabPan.setSelectedIndex(0);
			CustomControls.controlEnableDisable(this.tabPan.getComponents(), false);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), ErrorHandler.errorMessage(e));
			FormCommon.getBtnCancel().doClick();
			return false;
		}
	}

	private List<Column> lstCol(int scrlwidth) {
		List<Column> lstCol = new ArrayList<>();
		lstCol.add(new Column("HSNCODE", Integer.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 4));
		lstCol.add(new Column("HSN Name", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 15.0D), 2));
		lstCol.add(new Column("SGST Per", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("CGST Per", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("IGST Per", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("Active", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 5.0D), 2));
		lstCol.add(new Column("CreatedBy", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(
				new Column("Created Date", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));
		lstCol.add(new Column("Last ModifiedBy", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D),
				2));
		lstCol.add(new Column("Last Modified Date", String.class,
				CustomControls.getWidthOrHeightFromPer(scrlwidth, 10.0D), 2));

		lstCol.add(
				new Column("Company Name", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 15.0D), 2));
		lstCol.add(new Column("CostCode", String.class, CustomControls.getWidthOrHeightFromPer(scrlwidth, 15.0D), 2));
		lstCol.add(new Column("hsnbasics", Map.class, 0, 2));
		return lstCol;
	}

}
