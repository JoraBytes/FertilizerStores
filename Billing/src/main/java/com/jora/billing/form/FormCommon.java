package com.jora.billing.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Inet4Address;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicMenuBarUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.jora.billing.BillingApplication;
import com.jora.billing.common.CommonValues;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.common.ImageResources;
import com.jora.billing.common.SvgImageResources;
import com.jora.billing.components.Button;
import com.jora.billing.config.ApplicationConfig;
import com.jora.billing.custom.ButtonOption;
import com.jora.billing.custom.CustomControls;
import com.jora.billing.custom.FormAction;
import com.jora.billing.custom.FormMenuAction;
import com.jora.billing.custom.JMenuEnum;
import com.jora.billing.custom.JMenuEnum.FormType;

public class FormCommon extends JFrame
		implements FormMenuAction, KeyListener, ActionListener, WindowListener, FocusListener {

	private JMenuEnum.FormType formType;

	private static final long serialVersionUID = -6147732390065896481L;

	private JPanel panelMain;

	private JPanel panelTitle;

	private JLabel lblUserName;

	private JLabel lblDbName;

	private JLabel lblLocalIp;

	private JLabel lblProjectTitle;

	private JLabel lblVersion;

	private JLabel lblServerIp;

	private static JLabel lblDateTime;

	private JPanel panelMenu;

	private JPanel panelCompany;

	private JMenuBar menubar;

	private JLabel lblCompanyName;

	private JLabel lblHoBoName;

	private JLabel lblClientLogo;

	private JPanel panelHeading;

	private JLabel lblChildFrameTitle;

	private JLabel lblChildFrameDetails;

	private static JPanel panelContent;

	private JPanel panelButton;

	private static Button btnAdd;

	private static Button btnSave;

	private static Button btnView;

	private static Button btnEdit;

	private static Button btnPosting;

	private static Button btnCancel;

	private static Button btnClose;

	private JLabel lblMandatory;

	private JPanel panelHelp;

	private JLabel lblHelp;

	private JLabel lblLogo;

	private JLabel lblDesktop;

	public static JLabel lblHelpText;

	public static JFrame frmCommon;

	public static JLabel getLblDateTime() {
		return lblDateTime;
	}

	public static void setLblDateTime(JLabel lblDateTime) {
		FormCommon.lblDateTime = lblDateTime;
	}

	public FormCommon() throws Exception {
		setTitle("Common");
		setLayout(null);
		setDefaultCloseOperation(0);
		getContentPane().setPreferredSize(new Dimension(CommonValues.getSCREENWIDTH(), CommonValues.getSCREENHEIGHT()));
		setUndecorated(true);
		pack();
		componentCreation();
		componentListeners();
		titleBarValues();
		CommonValues.getPanelContentSize();
		CommonValues.setHEADING_COLOR(this.panelTitle.getBackground());
		addWindowListener(this);
//		if ("windows".equalsIgnoreCase(CommonValues.getOperatingSystemName())) {
		getGraphicsConfiguration().getDevice().setFullScreenWindow(getOwner());
//		} else {
//			getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
//		}
	}

	private void componentCreation() {
		try {
//			Font font = CommonValues.getFont(CommonValues.FontName.POPPINS, 12, CommonValues.FontTypes.MEDIUM);
			Font font = new Font("calibri", Font.PLAIN, 12);
			panelMain = new JPanel(null);
			panelMain.setBounds(0, 0, CommonValues.getSCREENWIDTH(), CommonValues.getSCREENHEIGHT());
			panelMain.setVisible(true);
			getContentPane().add(this.panelMain);
			panelTitle = new JPanel(new GridLayout());
			panelTitle.setBounds(0, 0, this.panelMain.getWidth(), this.panelMain.getHeight() * 3 / 100);
			panelTitle.setBackground(CommonValues.getBORDER_COLOR());
			panelTitle.setVisible(true);
			lblUserName = CustomControls.createLabel("", "", 2, font, Color.WHITE);
			lblUserName.setIcon((Icon) new FlatSVGIcon(ImageResources.Images.ICON_USER.getValue()));
			lblUserName.setVerticalAlignment(3);
			lblDbName = CustomControls.createLabel("", "", 2, font, Color.WHITE);
			lblDbName.setIcon((Icon) new FlatSVGIcon(ImageResources.Images.ICON_DB.getValue()));
			lblDbName.setVerticalAlignment(3);
			lblLocalIp = CustomControls.createLabel("", "", 2, font, Color.WHITE);
			lblLocalIp.setIcon((Icon) new FlatSVGIcon(ImageResources.Images.ICON_IP.getValue()));
			lblLocalIp.setVerticalAlignment(3);
			lblProjectTitle = CustomControls.createLabel(CommonValues.getApplicationName(), "", 0,
					new Font("Calibri", 1, 20), Color.WHITE);
			lblProjectTitle.setVerticalAlignment(1);
			lblVersion = CustomControls.createLabel(CommonValues.getVersion(), "", 0, font, Color.WHITE);
			lblVersion.setIcon((Icon) new FlatSVGIcon(ImageResources.Images.ICON_VERSION.getValue()));
			lblVersion.setVerticalAlignment(3);
			lblServerIp = CustomControls.createLabel("", "", 0, font, Color.WHITE);
			lblServerIp.setIcon((Icon) new FlatSVGIcon(ImageResources.Images.ICON_SERVER_IP.getValue()));
			lblServerIp.setVerticalAlignment(3);
			lblDateTime = CustomControls.createLabel("", "", 4, font, Color.WHITE);
			lblDateTime.setIcon((Icon) new FlatSVGIcon(ImageResources.Images.ICON_DATE.getValue()));
			lblDateTime.setVerticalAlignment(3);
			panelTitle.add(lblUserName);
			panelTitle.add(lblDbName);
			panelTitle.add(lblLocalIp);
			panelTitle.add(lblProjectTitle);
			panelTitle.add(lblVersion);
			panelTitle.add(lblServerIp);
			panelTitle.add(lblDateTime);
			panelMain.add(panelTitle);
			panelMenu = new JPanel(null);
			panelMenu.setBounds(0, panelTitle.getHeight(), panelMain.getWidth(), panelMain.getHeight() * 9 / 100);
			panelMenu.setBorder(BorderFactory.createLineBorder(CommonValues.getMENU_BORDER_COLOR(), 2));
			panelMenu.setVisible(true);
			panelMain.add(panelMenu);
			int x = (int) (0.2D * panelMenu.getWidth() / 100.0D);
			int y = 4 * panelMenu.getHeight() / 100;
			int width = 65 * panelMenu.getWidth() / 100;
			int height = 92 * panelMenu.getHeight() / 100;
			menubar = new JMenuBar();
			menubar.setBounds(x, y, width, height);
			menubar.setBackground(Color.WHITE);
			menubar.setUI(new BasicMenuBarUI());
			JPanel panelEmpty = new JPanel(null);
			panelEmpty.setBounds(x, y, 63 * this.panelMenu.getWidth() / 100, height);
			panelEmpty.setBackground(Color.WHITE);
			panelMenu.add(panelEmpty);
			x = (int) (60.2D * this.panelMenu.getWidth() / 100.0D);
			width = (int) (39.6D * this.panelMenu.getWidth() / 100.0D);
			panelCompany = new JPanel(null);
			panelCompany.setBounds(x, y, width, height);
			panelCompany.setBackground(Color.WHITE);
			panelCompany.setVisible(true);
			panelMenu.add(panelCompany);
			x = (int) (0.8D * panelCompany.getWidth() / 100.0D);
			y = 15 * panelCompany.getHeight() / 100;
			width = 40 * panelCompany.getWidth() / 100;
			height = 30 * panelCompany.getHeight() / 100;
			lblCompanyName = CustomControls.createLabel("", "", 4);
			lblCompanyName.setBounds(x, y, (int) (width * 1.4D), height);
			lblCompanyName.setVisible(true);
			panelCompany.add(lblCompanyName);
			y = 50 * panelCompany.getHeight() / 100;
			lblHoBoName = CustomControls.createLabel("", "", 4);
			lblHoBoName.setBounds(x, y, (int) (width * 1.4D), height);
			lblHoBoName.setForeground(Color.red);
			lblHoBoName.setVisible(true);
			panelCompany.add(lblHoBoName);
			x = lblCompanyName.getX() + lblCompanyName.getWidth() + (int) (2.5D * panelCompany.getWidth() / 100.0D);
			y = 15 * panelCompany.getHeight() / 100;
			width = (int) (0.5D * panelCompany.getWidth() / 100.0D);
			height = 99 * panelCompany.getHeight() / 100;
			JPanel panelLine = new JPanel(null);
			panelLine.setBounds(x, y, width, height);
			panelLine.setBackground(Color.BLACK);
			panelLine.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			panelLine.setVisible(true);
			panelCompany.add(panelLine);
			x = panelLine.getX() + panelLine.getWidth() + (int) (2.5D * panelCompany.getWidth() / 100.0D);
			width = 35 * this.panelCompany.getWidth() / 100;
			height = 85 * this.panelCompany.getHeight() / 100;
			lblClientLogo = CustomControls.createLabel("", "", 2);
			lblClientLogo.setBounds(x, y, width, height);
			lblClientLogo.setHorizontalAlignment(0);
			lblClientLogo.setVerticalAlignment(0);
			lblClientLogo.setVisible(true);
//			this.lblClientLogo.setIcon(CustomControls.getImage(ImageResources.Images.LOGO.getValue(), width, height));
			panelCompany.add(lblClientLogo);
			panelHeading = new JPanel(null);
			panelHeading.setBounds(menubar.getX(), menubar.getY(), menubar.getWidth(), menubar.getHeight());
			panelHeading.setBackground(Color.WHITE);
			panelHeading.setVisible(true);
			panelEmpty.add(panelHeading);
			x = 1 * panelHeading.getWidth() / 100;
			y = 25 * panelHeading.getHeight() / 100;
			width = 80 * panelHeading.getWidth() / 100;
			height = 35 * panelHeading.getHeight() / 100;
			lblChildFrameTitle = CustomControls.createLabel("HEY BUDDY", "", 2, new Font("Calibri", 1, 23),
					Color.BLACK);
			lblChildFrameTitle.setBounds(x, y, width, height);
			lblChildFrameTitle.setVisible(true);
			panelHeading.add(lblChildFrameTitle);
			y += 30 * panelHeading.getHeight() / 100;
			lblChildFrameDetails = CustomControls.createLabel("HELLO", "", 2, new Font("", 0, 14), Color.BLACK);
			lblChildFrameDetails.setBounds(x, y, width, height);
			lblChildFrameDetails.setVisible(true);
			panelHeading.add(lblChildFrameDetails);
			panelContent = new JPanel(null);
			panelContent.setBounds(0, panelMenu.getY() + panelMenu.getHeight(), panelMenu.getWidth(),
					(int) (panelMain.getHeight() * 78.5D / 100.0D));
			panelContent.setBackground(Color.decode("#00ace6"));
			panelContent.setVisible(false);
			panelMain.add(panelContent);
			CommonValues.setINTERNALFRAMEWIDTH(panelContent.getWidth());
			CommonValues.setINTERNALFRAMEHEIGHT(panelContent.getHeight() + 1 * panelContent.getHeight() / 100);
			panelButton = new JPanel(null);
			panelButton.setBounds(0, panelContent.getY() + panelContent.getHeight(), panelMenu.getWidth(),
					panelMain.getHeight() * 5 / 100);
			panelButton.setBackground(panelTitle.getBackground());
			Rectangle btnRectangle = CustomControls.getRectangle(panelButton.getWidth(), panelButton.getHeight(), 10.0D,
					5.0D, 7.0D, 90.0D);
			btnAdd = CustomControls.createButton(btnAdd, ImageResources.Images.ADDNORMAL.getValue(),
					ImageResources.Images.ADDDISABLE.getValue(), "ADD", CommonValues.getBORDER_COLOR(),
					CommonValues.getCOLUMN_SELECTION_COLOR());
			btnAdd.setBounds(btnRectangle);
			btnAdd.setVerifyInputWhenFocusTarget(false);
			btnAdd.setMnemonic(KeyEvent.VK_A);
			btnAdd.getInputMap(2).put(KeyStroke.getKeyStroke(65, 512), "addAction");
//			btnAdd.getActionMap().put("addAction", (Action) new Object(this));
			btnAdd.setVisible(true);
			int btnWidth = btnAdd.getWidth();
			int intHeight = btnAdd.getHeight();
			int intYPos = btnAdd.getY();
			int intXPos = 0;
			intXPos = CustomControls.horizontalGap(panelButton, (Component) btnAdd, 5.0D);
			btnSave = CustomControls.createButton(btnSave, ImageResources.Images.SAVENORMAL.getValue(),
					ImageResources.Images.SAVEDISABLE.getValue(), "SAVE", CommonValues.getBORDER_COLOR(),
					CommonValues.getCOLUMN_SELECTION_COLOR());
			btnSave.setBounds(intXPos, intYPos, btnWidth, intHeight);
			btnSave.setMnemonic(KeyEvent.VK_S);
			btnSave.getInputMap(2).put(KeyStroke.getKeyStroke(83, 512), "saveAction");
//			btnSave.getActionMap().put("saveAction", (Action) new Object(this));
			btnSave.setVisible(true);
			intXPos = CustomControls.horizontalGap(panelButton, (Component) btnSave, 5.0D);
			btnView = CustomControls.createButton(btnView, ImageResources.Images.VIEWNORMAL.getValue(),
					ImageResources.Images.VIEWDISABLE.getValue(), "VIEW", CommonValues.getBORDER_COLOR(),
					CommonValues.getCOLUMN_SELECTION_COLOR());
			btnView.setBounds(intXPos, intYPos, btnWidth, intHeight);
			btnView.setMnemonic(KeyEvent.VK_V);
			btnView.getInputMap(2).put(KeyStroke.getKeyStroke(86, 512), "viewAction");
//			btnView.getActionMap().put("viewAction", (Action) new Object(this));
			btnView.setVisible(true);
			intXPos = CustomControls.horizontalGap(panelButton, (Component) btnView, 5.0D);
			btnEdit = CustomControls.createButton(btnEdit, ImageResources.Images.EDITNORMAL.getValue(),
					ImageResources.Images.EDITDISABLE.getValue(), "EDIT", CommonValues.getBORDER_COLOR(),
					CommonValues.getCOLUMN_SELECTION_COLOR());
			btnEdit.setBounds(intXPos, intYPos, btnWidth, intHeight);
			btnEdit.setMnemonic(KeyEvent.VK_E);
			btnEdit.getInputMap(2).put(KeyStroke.getKeyStroke(69, 512), "editAction");
//			btnEdit.getActionMap().put("editAction", (Action) new Object(this));
			btnEdit.setVisible(true);
			intXPos = CustomControls.horizontalGap(panelButton, (Component) btnEdit, 5.0D);
			btnPosting = CustomControls.createButton(btnPosting, ImageResources.Images.POSTINGNORMAL.getValue(),
					ImageResources.Images.POSTINGDISABLE.getValue(), "POSTING", CommonValues.getBORDER_COLOR(),
					CommonValues.getCOLUMN_SELECTION_COLOR());
			btnPosting.setBounds(intXPos, intYPos, btnWidth, intHeight);
			btnPosting.setMnemonic(KeyEvent.VK_P);
			btnPosting.getInputMap(2).put(KeyStroke.getKeyStroke(80, 512), "postingAction");
//			btnPosting.getActionMap().put("postingAction", (Action) new Object(this));
			btnPosting.setVisible(true);
			intXPos = CustomControls.horizontalGap(panelButton, (Component) btnPosting, 5.0D);
			btnCancel = CustomControls.createButton(btnCancel, ImageResources.Images.CANCELNORMAL.getValue(),
					ImageResources.Images.CANCELDISABLE.getValue(), "CANCEL", CommonValues.getBORDER_COLOR(),
					CommonValues.getCOLUMN_SELECTION_COLOR());
			btnCancel.setBounds(intXPos, intYPos, btnWidth, intHeight);
			btnCancel.setMnemonic(KeyEvent.VK_C);
			btnCancel.getInputMap(2).put(KeyStroke.getKeyStroke(67, 512), "cancelAction");
//			btnCancel.getActionMap().put("cancelAction", (Action) new Object(this));
			btnCancel.setVisible(true);
			btnCancel.setVerifyInputWhenFocusTarget(false);
			intXPos = CustomControls.horizontalGap(panelButton, (Component) btnCancel, 5.0D);
			btnClose = CustomControls.createButton(btnClose, ImageResources.Images.CLOSENORMAL.getValue(),
					ImageResources.Images.CLOSEDISABLE.getValue(), "CLOSE", CommonValues.getBORDER_COLOR(),
					CommonValues.getCOLUMN_SELECTION_COLOR());
			btnClose.setBounds(intXPos, intYPos, btnWidth, intHeight);
			btnClose.setMnemonic(KeyEvent.VK_L);
			btnClose.getInputMap(2).put(KeyStroke.getKeyStroke(76, 512), "closeAction");
//			btnClose.getActionMap().put("closeAction", (Action) new Object(this));
			btnClose.setVisible(true);
			btnClose.setVerifyInputWhenFocusTarget(false);
			lblMandatory = new JLabel("* Mandatory Fields");
			lblMandatory.setBounds(panelMain.getX() + panelMain.getWidth() - 100, 10, 138, 20);
			lblMandatory.setForeground(Color.white);
			lblMandatory.setVisible(true);
			panelButton.add((Component) btnAdd);
			panelButton.add((Component) btnSave);
			panelButton.add((Component) btnView);
			panelButton.add((Component) btnEdit);
			panelButton.add((Component) btnPosting);
			panelButton.add((Component) btnCancel);
			panelButton.add((Component) btnClose);
			panelButton.add(lblMandatory);
			panelMain.add(panelButton);
			panelHelp = new JPanel(null);
			panelHelp.setBounds(0, panelButton.getY() + panelButton.getHeight(), panelButton.getWidth(),
					(int) (panelMain.getHeight() * 4.8D / 100.0D));
			panelHelp.setBackground(Color.WHITE);
			panelHelp.setVisible(true);
			panelMain.add(panelHelp);
			y = 10 * panelHelp.getHeight() / 100;
			JPanel panelHelpTopLine = new JPanel(null);
			panelHelpTopLine.setBounds(0, y, panelButton.getWidth(), panelHelp.getHeight() * 10 / 100);
			panelHelpTopLine.setBackground(CommonValues.getMENU_BORDER_COLOR());
			panelHelpTopLine.setVisible(true);
			panelHelp.add(panelHelpTopLine);
			x = 45 * panelHelp.getWidth() / 100;
			y = 35 * panelHelp.getHeight() / 100;
			width = 50 * panelHelp.getWidth() / 100;
			height = 50 * panelHelp.getHeight() / 100;
			lblHelp = CustomControls.createLabel("", ImageResources.Images.IMAGEHELP.getValue(), 2);
			lblHelp.setBounds(x, y, width, height);
			lblHelp.setVerticalAlignment(0);
			lblHelp.setVisible(true);
			x = 47 * panelHelp.getWidth() / 100;
			lblHelpText = CustomControls.createLabel("Help Text", "", 2);
			lblHelpText.setBounds(x, y, width, height);
			lblHelpText.setFont(new Font("", 1, 12));
			lblHelpText.setVisible(false);
			lblLogo = CustomControls.createLabel("", SvgImageResources.Images.svgDevelopedByLogo.getValue(), 4);
			lblLogo.setBounds(panelHelp.getWidth() - 250, 12, 200, 25);
			lblLogo.setHorizontalAlignment(4);
			lblLogo.setVisible(true);
			x = (int) (98.5D * panelHelp.getWidth() / 100.0D);
			y = panelHelp.getHeight() * 25 / 100;
			width = panelHelp.getWidth() * 7 / 1000;
			height = panelHelp.getHeight() * 70 / 100;
			lblDesktop = new JLabel("");
			lblDesktop.setBounds(x, y, width, height);
			lblDesktop.setBackground(Color.DARK_GRAY);
			lblDesktop.setToolTipText("Show Desktop");
			lblDesktop.setOpaque(true);
			lblDesktop.setVisible(true);
			panelHelp.add(lblHelpText);
			panelHelp.add(lblHelp);
			panelHelp.add(lblLogo);
			panelHelp.add(lblDesktop);
//			this.lblDesktop.addMouseListener(this);
			repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
		}

	}

	private void titleBarValues() throws Exception {
		lblLocalIp.setText(Inet4Address.getLocalHost().getHostAddress());
		lblServerIp.setText(ApplicationConfig.serverName);
		lblUserName.setText("Supervisor");
		lblCompanyName.setText(ApplicationConfig.companyFlag);
		lblHoBoName.setText("HO".equalsIgnoreCase("HO") ? "CORPORATE OFFICE" : "BRANCH OFFICE");
		lblVersion.setText("0.0.1");
	}

	private void componentListeners() {
		btnAdd.addActionListener(this);
		btnSave.addActionListener(this);
		btnView.addActionListener(this);
		btnEdit.addActionListener(this);
		btnPosting.addActionListener(this);
		btnCancel.addActionListener(this);
		btnClose.addActionListener(this);
		btnAdd.addFocusListener(this);
		btnSave.addFocusListener(this);
		btnView.addFocusListener(this);
		btnEdit.addFocusListener(this);
		btnPosting.addFocusListener(this);
		btnCancel.addFocusListener(this);
		btnClose.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				if (CommonValues.getCurrentform().formAdd()) {
					buttonEnable(ButtonOption.ADDCLICK);
				}
			} else if (e.getSource() == btnSave) {
				if (CommonValues.getCurrentform().formSave()) {
					buttonEnable(ButtonOption.SAVECLICK);
				}
			} else if (e.getSource() == btnView) {
				if (CommonValues.getCurrentform().formView()) {
					buttonEnable(ButtonOption.VIEWCLICK);
				}
			} else if (e.getSource() == btnEdit) {
				if (CommonValues.getCurrentform().formEdit()) {
					buttonEnable(ButtonOption.EDITCLICK);
				}
			} else if (e.getSource() == btnCancel) {
				if (CommonValues.getCurrentform().formCancel()) {
					buttonEnable(ButtonOption.CANCELCLICK);
				}
			} else if (e.getSource() == btnPosting) {
				if (CommonValues.getCurrentform().formPosting()) {
					buttonEnable(ButtonOption.POSTINGCLICK);
				}
			} else if (e.getSource() == btnClose) {
				CommonValues.getCurrentform().formClose();
				panelContent.setVisible(false);
				this.panelHeading.setVisible(false);
				this.panelButton.setVisible(false);
				formClose((JInternalFrame) CommonValues.getCurrentform());
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, ErrorHandler.errorMessage(e2));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void formLoad(JInternalFrame jInternalFrame, FormType formType, String dbName, String path) {
		try {
			panelContent.setVisible(true);
			CommonValues.setCurrentform((FormAction) jInternalFrame);
			panelButton.setVisible(true);
			panelMain.setVisible(true);
			panelContent.removeAll();
			panelContent.add(jInternalFrame);
			lblChildFrameTitle.setText(jInternalFrame.getTitle());
			lblChildFrameDetails.setText(path + " >> " + jInternalFrame.getTitle());
			jInternalFrame.setBounds(-10, -27, CommonValues.getINTERNALFRAMEWIDTH() + 20,
					CommonValues.getINTERNALFRAMEHEIGHT() + 27);
			CommonValues.setPanelContentSize(panelContent.getSize());
			this.formType = formType;
			setTitle(jInternalFrame.getTitle());
			jInternalFrame.setVisible(true);
			jInternalFrame.setSelected(true);
			buttonEnable(ButtonOption.FORMLOAD);
			this.lblDbName.setText(dbName.toUpperCase());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
		}
	}

	@Override
	public void formClose(JInternalFrame jInternalFrame) throws Exception {
		if (null != jInternalFrame) {
			jInternalFrame.dispose();
			jInternalFrame = null;
		}
		setVisible(false);
		BillingApplication.getApplicationContext().getBean(FrmMDI.class).setVisible(true);
	}

	private void buttonEnable(ButtonOption buttonOption) {
		try {
			if (buttonOption == ButtonOption.FORMLOAD) {
				formLoadEnable();
				return;
			}
			if (buttonOption == ButtonOption.ADDCLICK) {
				addClickEnable();
				return;
			}
			if (buttonOption == ButtonOption.SAVECLICK) {
				saveClickEnable();
				return;
			}
			if (buttonOption == ButtonOption.VIEWCLICK) {
				viewClickEnable();
				return;
			}
			if (buttonOption == ButtonOption.CANCELCLICK) {
				cancelClickEnable();
				return;
			}
			if (buttonOption == ButtonOption.EDITCLICK) {
				editClickEnable();
				return;
			}
		} catch (Exception e) {
			CommonValues.showErrorMessage(getContentPane(), e.getMessage(), getTitle());
		}
	}

	private void editClickEnable() {
		if (JMenuEnum.FormType.SAVE_VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(true);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnPosting.setEnabled(false);
			btnClose.setEnabled(true);
		} else if (JMenuEnum.FormType.VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(true);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnPosting.setEnabled(false);
			btnClose.setEnabled(true);
		}
	}

	private void cancelClickEnable() throws Exception {
		if (JMenuEnum.FormType.SAVE_VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnView.requestFocus();
		} else if (JMenuEnum.FormType.VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnView.requestFocus();
		} else if (JMenuEnum.FormType.VIEW_ONLY == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnView.requestFocus();
		} else if (JMenuEnum.FormType.SAVE_VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.SAVE == this.formType || JMenuEnum.FormType.SAVE_DISABLE == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.SAVE_VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.ADD_POST_ONLY == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(true);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.ADD_ONLY == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnPosting.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.ADD_VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnPosting.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnAdd.requestFocus();
		} else {
			throw new Exception("Invalid Form Type");
		}
	}

	private void viewClickEnable() throws Exception {
		if (JMenuEnum.FormType.SAVE_VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(true);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(true);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(true);
		} else if (JMenuEnum.FormType.VIEW == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(true);
		} else if (JMenuEnum.FormType.VIEW_ONLY == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.SAVE_VIEW == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(true);
		} else if (JMenuEnum.FormType.ADD_VIEW == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(true);
		} else {
			throw new Exception("Invalid Form Type");
		}
	}

	private void saveClickEnable() throws Exception {
		if (JMenuEnum.FormType.SAVE_VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnView.requestFocus();
		} else if (JMenuEnum.FormType.SAVE_VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.SAVE == this.formType || JMenuEnum.FormType.SAVE_DISABLE == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else {
			throw new Exception("Invalid Form Type");
		}
	}

	private void addClickEnable() throws Exception {
		if (JMenuEnum.FormType.SAVE_VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(true);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.SAVE_VIEW == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(true);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.SAVE == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(true);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.VIEW == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.ADD_ONLY == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.ADD_POST_ONLY == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(true);
		} else if (JMenuEnum.FormType.SAVE_DISABLE == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else if (JMenuEnum.FormType.ADD_VIEW == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(true);
		} else {
			throw new Exception("Invalid Form Type");
		}
	}

	private void formLoadEnable() throws Exception {
		if (JMenuEnum.FormType.SAVE_VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.SAVE == this.formType || JMenuEnum.FormType.SAVE_DISABLE == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.VIEW == this.formType) {
			btnSave.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.setEnabled(true);
			btnView.setEnabled(false);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.VIEW_ONLY == this.formType) {
			btnSave.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
			btnAdd.setEnabled(false);
			btnView.setEnabled(true);
			btnView.requestFocus();
		} else if (JMenuEnum.FormType.VIEW_EDIT == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnCancel.setEnabled(true);
			btnPosting.setEnabled(false);
			btnView.requestFocus();
		} else if (JMenuEnum.FormType.SAVE_VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(true);
			btnEdit.setEnabled(false);
			btnPosting.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.ADD_ONLY == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnPosting.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.ADD_POST_ONLY == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnPosting.setEnabled(true);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.ADD_VIEW == this.formType) {
			btnAdd.setEnabled(true);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnPosting.setEnabled(false);
			btnCancel.setEnabled(false);
			btnClose.setEnabled(true);
			btnAdd.requestFocus();
		} else if (JMenuEnum.FormType.CANCEL_CLOSE_ONLY == this.formType) {
			btnAdd.setEnabled(false);
			btnSave.setEnabled(false);
			btnView.setEnabled(false);
			btnEdit.setEnabled(false);
			btnCancel.setEnabled(true);
			btnClose.setEnabled(true);
			btnPosting.setEnabled(false);
		} else {
			throw new Exception("Invalid Form Type");
		}
	}

	public static Button getBtnCancel() {
		return btnCancel;
	}

	public static Button getBtnClose() {
		return btnClose;
	}

	public static Button getBtnSave() {
		return btnSave;
	}

	public static Button getBtnAdd() {
		return btnAdd;
	}

	public static Button getBtnView() {
		return btnView;
	}

	public static Button getBtnEdit() {
		return btnEdit;
	}

	public static Button getBtnPosting() {
		return btnPosting;
	}
}
