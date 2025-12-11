package com.jora.billing.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Inet4Address;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.jora.billing.BillingApplication;
import com.jora.billing.common.CommonValues;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.common.ImageResource;
import com.jora.billing.common.ImagesMainScreen;
import com.jora.billing.common.SvgImageResources;
import com.jora.billing.config.ApplicationConfig;
import com.jora.billing.custom.CustomControls;
import com.jora.billing.custom.JMenuEnum;

@Component
@DependsOn("directoryDataSource")
public class FrmMDI extends JFrame implements ActionListener, KeyListener, Runnable, MouseListener {

	private static final long serialVersionUID = 1L;

	private final Logger log = LogManager.getLogger();

	private ClassLoader classLoader = FrmMDI.class.getClassLoader();

	private static JLabel lblTime;

	private static JLabel lblDate;

	private static JLabel lblGreetings;

	private static JLabel lblLogo;

	private JPanel panelMain;

	private JPanel panelCenter;

	private JPanel panelMasterRow1;

	private JLabel lblNavigator;

	private JButton btnLogout;

	private JButton btnMinimize;

	private JButton btnExit;

	private JButton btnLeftSlider;

	private JButton btnRightSlider;

	private JButton btnOperatorEntry;

	private JButton btnHsnBaics;

	private JButton btnProduct;

	private JButton btnCategory;

	private JButton btnMaster;

	private JButton btnInventory;

	private JButton btnView;

	private JButton btnReport;

	private JButton btnUtility;

	private JButton btnTransfer;

	private JLabel lblLeftIndicator;

	private JLabel lblRightIndicator;

	public FrmMDI() {
		try {
			setTitle(CommonValues.getApplicationName());
			setUndecorated(true);
			getContentPane().setSize(new Dimension(CommonValues.getSCREENWIDTH(), CommonValues.getSCREENHEIGHT()));
			getContentPane()
					.setPreferredSize(new Dimension(CommonValues.getSCREENWIDTH(), CommonValues.getSCREENHEIGHT()));
			pack();
			setLayout(null);
			setResizable(false);
			componentCreation();
			componentListener();
		} catch (Exception e) {
			log.error(ErrorHandler.errorTraceForLogger(e));
			JOptionPane.showMessageDialog(getContentPane(), "Something went wrong...");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

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
	public void actionPerformed(ActionEvent e) {
		try {
			String masterPath = "MASTER";
			if (e.getSource() == btnOperatorEntry) {
				FrmOperatorEntry frmOperatorEntry = BillingApplication.getApplicationContext()
						.getBean(FrmOperatorEntry.class);
				openForm(frmOperatorEntry, JMenuEnum.FormType.SAVE_VIEW_EDIT, "DENA2526", masterPath);
				setVisible(false);
				return;
			} else if (e.getSource() == btnHsnBaics) {
				FrmHsnBasics frmHsnBasics = BillingApplication.getApplicationContext()
						.getBean(FrmHsnBasics.class);
				openForm(frmHsnBasics, JMenuEnum.FormType.SAVE_VIEW_EDIT, "DENA2526", masterPath);
				setVisible(false);
				return;
			} else if (e.getSource() == btnCategory) {
				FrmCategory frmCategory = BillingApplication.getApplicationContext().getBean(FrmCategory.class);
				openForm(frmCategory, JMenuEnum.FormType.SAVE_VIEW_EDIT, "DENA2526", masterPath);
				setVisible(false);
				return;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, ErrorHandler.errorMessage(e2));
		}
	}

	private void componentListener() throws Exception {
		CustomControls.addListeners(panelMain, this);
	}

	private void componentCreation() throws Exception {
		panelMain = new JPanel(null);
		panelMain.setBounds(0, 0, CommonValues.getSCREENWIDTH(), CommonValues.getSCREENHEIGHT());
		getContentPane().add(panelMain);
		JLabel lblBackground = new JLabel();
		lblBackground.setBounds(0, 0, panelMain.getWidth(), panelMain.getHeight());
		lblBackground.setIcon((Icon) new FlatSVGIcon(ImageResource.Images.SVG_IMAGE.getValue(), panelMain.getWidth(),
				panelMain.getHeight()));
		panelMain.add(lblBackground);
		Rectangle panelRect = CommonValues.getRectangle(panelMain.getWidth(), panelMain.getHeight(), 96.0D, 38.0D, 3.0D,
				5.0D);
		btnMinimize = new JButton();
		btnMinimize.setBounds(panelRect);
		btnMinimize.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgMinimize.getValue(),
				btnMinimize.getWidth(), btnMinimize.getHeight()));
		btnMinimize.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgMinimize.getValue(),
				btnMinimize.getWidth(), btnMinimize.getHeight()));
		btnMinimize.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgMinimize.getValue(),
				btnMinimize.getWidth(), btnMinimize.getHeight()));
		btnMinimize.setName("Minimize");
		btnMinimize.addActionListener(this);
		btnMinimize.setContentAreaFilled(false);
		btnMinimize.setBorder((Border) null);
		btnMinimize.setFocusPainted(false);
		btnMinimize.setMnemonic(77);
		btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMinimize.setOpaque(false);
		lblBackground.add(btnMinimize);
		panelRect = CommonValues.getRectangle(panelMain.getWidth(), panelMain.getHeight(), 96.0D, 47.0D, 3.0D, 5.0D);
		btnLogout = new JButton();
		btnLogout.setBounds(panelRect);
		btnLogout.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLogoutFocus.getValue(),
				btnLogout.getWidth(), btnLogout.getHeight()));
		btnLogout.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLogoutFocus.getValue(),
				btnLogout.getWidth(), btnLogout.getHeight()));
		btnLogout.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLogoutFocus.getValue(),
				btnLogout.getWidth(), btnLogout.getHeight()));
		btnLogout.setName("Logout");
		btnLogout.addActionListener(this);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorder((Border) null);
		btnLogout.setFocusPainted(false);
		btnLogout.setMnemonic(76);
		btnLogout.setCursor(new Cursor(12));
		btnLogout.setOpaque(false);
		lblBackground.add(btnLogout);
		panelRect = CommonValues.getRectangle(panelMain.getWidth(), panelMain.getHeight(), 96.0D, 56.0D, 3.0D, 5.0D);
		btnExit = new JButton();
		btnExit.setBounds(panelRect);
		btnExit.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgExitFocus.getValue(),
				btnExit.getWidth(), btnExit.getHeight()));
		btnExit.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgExitFocus.getValue(),
				btnExit.getWidth(), btnExit.getHeight()));
		btnExit.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgExitFocus.getValue(), btnExit.getWidth(),
				btnExit.getHeight()));
		btnExit.setName("Exit");
		btnExit.addActionListener(this);
		btnExit.setContentAreaFilled(false);
		btnExit.setBorder((Border) null);
		btnExit.setMnemonic(69);
		btnExit.setFocusPainted(false);
		btnExit.setCursor(new Cursor(12));
		btnExit.setOpaque(false);
		lblBackground.add(btnExit);
		panelRect = CommonValues.getRectangle(panelMain.getWidth(), panelMain.getHeight(), 4.0D, 5.0D, 92.0D, 90.0D);
		JLabel lblWhiteCard = new JLabel();
		lblWhiteCard.setBounds(panelRect);
		lblWhiteCard.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgWhiteCard.getValue(),
				lblWhiteCard.getWidth(), lblWhiteCard.getHeight()));
		lblWhiteCard.setName("wc");
		lblBackground.add(lblWhiteCard);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 1.3D, 17.0D, 97.5D,
				0.5D);
		JLabel lblLine = new JLabel();
		lblLine.setBounds(panelRect);
		lblLine.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLine.getValue(), lblLine.getWidth(),
				lblLine.getHeight()));
		lblLine.setDisabledIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLine.getValue(), lblLine.getWidth(),
				lblLine.getHeight()));
		lblWhiteCard.add(lblLine);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 4.0D, 24.0D, 5.5D,
				50.0D);
		btnLeftSlider = new JButton();
		btnLeftSlider.setBounds(panelRect);
		btnLeftSlider.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.svgLeftSliderNonFocus.getValue(),
				btnLeftSlider.getWidth(), btnLeftSlider.getHeight()));
		btnLeftSlider.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.svgLeftSliderFocus.getValue(),
				btnLeftSlider.getWidth(), btnLeftSlider.getHeight()));
		btnLeftSlider.setContentAreaFilled(false);
		btnLeftSlider.setBorder((Border) null);
		btnLeftSlider.setCursor(new Cursor(12));
		btnLeftSlider.setOpaque(false);
		lblWhiteCard.add(btnLeftSlider);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 90.0D, 24.0D, 5.5D,
				50.0D);
		btnRightSlider = new JButton();
		btnRightSlider.setBounds(panelRect);
		btnRightSlider.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.svgRightSliderNonFocus.getValue(),
				btnRightSlider.getWidth(), btnRightSlider.getHeight()));
		btnRightSlider.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.svgRightSliderFocus.getValue(),
				btnRightSlider.getWidth(), btnRightSlider.getHeight()));
		btnRightSlider.setContentAreaFilled(false);
		btnRightSlider.setBorder((Border) null);
		btnRightSlider.setCursor(new Cursor(12));
		btnRightSlider.setOpaque(false);
		lblWhiteCard.add(btnRightSlider);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 21.0D, 21.0D, 62.0D,
				6.1D);
		lblNavigator = new JLabel();
		lblNavigator.setBounds(panelRect);
		lblNavigator.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgNavigateBackground.getValue(),
				lblNavigator.getWidth(), lblNavigator.getHeight()));
		lblNavigator.setLayout(new GridLayout(1, 5, 1, 1));
		lblNavigator.setName("Nav");
		lblWhiteCard.add(lblNavigator);
		btnMaster = new JButton();
		btnMaster.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgMasterFocus.getValue(),
				btnMaster.getWidth(), btnMaster.getHeight()));
		btnMaster.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgMasterFocus.getValue(),
				btnMaster.getWidth(), btnMaster.getHeight()));
		btnMaster.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgMasterNonFocus.getValue(),
				btnMaster.getWidth(), btnMaster.getHeight()));
		btnMaster.setName("Master");
		btnMaster.addActionListener(this);
		btnMaster.addKeyListener(this);
		btnMaster.setContentAreaFilled(false);
		btnMaster.setBorder((Border) null);
		btnMaster.setMnemonic(77);
		btnMaster.setFocusPainted(false);
		btnMaster.setCursor(new Cursor(12));
		btnMaster.setOpaque(false);
		btnMaster.setSelected(true);
		lblNavigator.add(btnMaster);
		btnInventory = new JButton();
		btnInventory.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgInventoryFocus.getValue(),
				btnInventory.getWidth(), btnInventory.getHeight()));
		btnInventory.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgInventoryFocus.getValue(),
				btnInventory.getWidth(), btnInventory.getHeight()));
		btnInventory.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgInventoryNonFocus.getValue(),
				btnInventory.getWidth(), btnInventory.getHeight()));
		btnInventory.setName("Inventory");
		btnInventory.setContentAreaFilled(false);
		btnInventory.setBorder((Border) null);
		btnInventory.addActionListener(this);
		btnInventory.setMnemonic(73);
		btnInventory.setFocusPainted(false);
		btnInventory.setCursor(new Cursor(12));
		btnInventory.setOpaque(false);
		lblNavigator.add(btnInventory);
		btnView = new JButton();
		btnView.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgViewFocus.getValue(),
				btnView.getWidth(), btnView.getHeight()));
		btnView.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgViewFocus.getValue(),
				btnView.getWidth(), btnView.getHeight()));
		btnView.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgViewNonFocus.getValue(), btnView.getWidth(),
				btnView.getHeight()));
		btnView.setName("View");
		btnView.setContentAreaFilled(false);
		btnView.setBorder((Border) null);
		btnView.addActionListener(this);
		btnView.setMnemonic(86);
		btnView.setFocusPainted(false);
		btnView.setCursor(new Cursor(12));
		btnView.setOpaque(false);
		lblNavigator.add(btnView);
		btnReport = new JButton();
		btnReport.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgReportsFocus.getValue(),
				btnReport.getWidth(), btnReport.getHeight()));
		btnReport.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgReportsFocus.getValue(),
				btnReport.getWidth(), btnReport.getHeight()));
		btnReport.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgReportsnonFocus.getValue(),
				btnReport.getWidth(), btnReport.getHeight()));
		btnReport.setName("Report");
		btnReport.setContentAreaFilled(false);
		btnReport.setBorder((Border) null);
		btnReport.addActionListener(this);
		btnReport.setMnemonic(82);
		btnReport.setFocusPainted(false);
		btnReport.setCursor(new Cursor(12));
		btnReport.setOpaque(false);
		lblNavigator.add(btnReport);
		btnUtility = new JButton();
		btnUtility.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgUtilityFocus.getValue(),
				btnUtility.getWidth(), btnUtility.getHeight()));
		btnUtility.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgUtilityFocus.getValue(),
				btnUtility.getWidth(), btnUtility.getHeight()));
		btnUtility.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgUtilityNonFocus.getValue(),
				btnUtility.getWidth(), btnUtility.getHeight()));
		btnUtility.setName("Utility");
		btnUtility.setContentAreaFilled(false);
		btnUtility.setBorder((Border) null);
		btnUtility.addActionListener(this);
		btnUtility.setMnemonic(85);
		btnUtility.setFocusPainted(false);
		btnUtility.setCursor(new Cursor(12));
		btnUtility.setOpaque(false);
		lblNavigator.add(btnUtility);
		btnTransfer = new JButton();
		btnTransfer.setRolloverIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.svgTransferFocus.getValue(),
				btnTransfer.getWidth(), btnTransfer.getHeight()));
		btnTransfer.setSelectedIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.svgTransferFocus.getValue(),
				btnTransfer.getWidth(), btnTransfer.getHeight()));
		btnTransfer.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.svgTransferNonFocus.getValue(),
				btnTransfer.getWidth(), btnTransfer.getHeight()));
		btnTransfer.setName("Transfer");
		btnTransfer.addActionListener(this);
		btnTransfer.setContentAreaFilled(false);
		btnTransfer.setBorder((Border) null);
		btnTransfer.setMnemonic(84);
		btnTransfer.setFocusPainted(false);
		btnTransfer.setCursor(new Cursor(12));
		btnTransfer.setOpaque(false);
		lblNavigator.add(btnTransfer);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 0.0D, 2.5D, 100.0D,
				13.0D);
		JLabel lblStores = new JLabel();
		lblStores.setBounds(panelRect);
		lblWhiteCard.add(lblStores);
		panelRect = CommonValues.getRectangle(lblStores.getWidth(), lblStores.getHeight(), 3.5D, 20.0D, 40.0D, 99.0D);
		lblGreetings = new JLabel();
		lblGreetings.setBounds(panelRect);
		lblGreetings.setForeground(Color.decode("#0F63AC"));
		lblGreetings.setVerticalAlignment(1);
		lblGreetings.setHorizontalAlignment(10);
		lblGreetings.setFont(new Font("Palatino", Font.BOLD, 20));
		lblStores.add(lblGreetings);
		panelRect = CommonValues.getRectangle(lblStores.getWidth(), lblStores.getHeight(), 85.0D, 6.0D, 12.0D, 85.0D);
		JLabel lblDateTime = new JLabel();
		lblDateTime.setBounds(panelRect);
		lblDateTime.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgDateTime.getValue(),
				lblDateTime.getWidth(), lblDateTime.getHeight()));
		lblDateTime.setDisabledIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgDateTime.getValue(),
				lblDateTime.getWidth(), lblDateTime.getHeight()));
		lblStores.add(lblDateTime);
		panelRect = CommonValues.getRectangle(lblDateTime.getWidth(), lblDateTime.getHeight(), 36.0D, 22.0D, 54.0D,
				16.0D);
		lblDate = new JLabel();
		lblDate.setBounds(panelRect);
		lblDate.setForeground(Color.decode("#0F63AC"));
		lblDate.setFont(new Font("calibri", 0, 12));
		lblDateTime.add(lblDate);
		panelRect = CommonValues.getRectangle(lblDateTime.getWidth(), lblDateTime.getHeight(), 36.0D, 62.0D, 54.0D,
				16.0D);
		lblTime = new JLabel();
		lblTime.setBounds(panelRect);
		lblTime.setFont(new Font("calibri", 0, 12));
		lblTime.setForeground(Color.decode("#0F63AC"));
		lblDateTime.add(lblTime);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 48.0D, 83.0D, 5.0D,
				1.5D);
		lblLeftIndicator = new JLabel();
		lblLeftIndicator.setBounds(panelRect);
		lblLeftIndicator.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgRightIndicate.getValue(),
				lblLeftIndicator.getWidth(), lblLeftIndicator.getHeight()));
		lblLeftIndicator.setDisabledIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgRightIndicate.getValue(),
				lblLeftIndicator.getWidth(), lblLeftIndicator.getHeight()));
		lblWhiteCard.add(lblLeftIndicator);
		lblRightIndicator = new JLabel();
		lblRightIndicator.setBounds(panelRect);
		lblRightIndicator.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLeftIndicate.getValue(),
				lblRightIndicator.getWidth(), lblRightIndicator.getHeight()));
		lblRightIndicator.setDisabledIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLeftIndicate.getValue(),
				lblRightIndicator.getWidth(), lblRightIndicator.getHeight()));
		lblRightIndicator.setVisible(false);
		lblWhiteCard.add(lblRightIndicator);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 1.3D, 88.0D, 97.5D,
				0.5D);
		lblLine = new JLabel();
		lblLine.setBounds(panelRect);
		lblLine.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLine.getValue(), lblLine.getWidth(),
				lblLine.getHeight()));
		lblLine.setDisabledIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgLine.getValue(), lblLine.getWidth(),
				lblLine.getHeight()));
		lblWhiteCard.add(lblLine);
		panelRect = CommonValues.getRectangle(panelMain.getWidth(), panelMain.getHeight(), 42.0D, 0.4D, 19.5D, 13.0D);
		lblLogo = new JLabel();
		lblLogo.setBounds(panelRect);
		lblWhiteCard.add(lblLogo);
		panelRect = CommonValues.getRectangle(lblWhiteCard.getWidth(), lblWhiteCard.getHeight(), 4.0D, 87.0D, 92.0D,
				10.0D);
		JPanel panelBottom = new JPanel(null);
		panelBottom.setBounds(panelRect);
		panelBottom.setBackground(Color.decode("#fbfbfb"));
		lblWhiteCard.add(panelBottom);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 3.0D, 48.0D, 2.0D,
				30.0D);
		JLabel lblServer = new JLabel();
		lblServer.setBounds(panelRect);
		lblServer.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgServerAddress.getValue(),
				lblServer.getWidth(), lblServer.getHeight()));
		panelBottom.add(lblServer);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 5.0D, 48.5D, 15.0D,
				30.0D);
		JLabel lblServerIp = new JLabel("   " + ApplicationConfig.serverName);
		lblServerIp.setForeground(Color.decode("#0F63AC"));
//		lblServerIp.setFont(CommonValues.getFont(CommonValues.FontName.RUBIK, 13, CommonValues.FontTypes.BOLD));
		lblServerIp.setBounds(panelRect);
		panelBottom.add(lblServerIp);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 22.0D, 48.0D, 2.0D,
				30.0D);
		JLabel lblImgVersion = new JLabel();
		lblImgVersion.setBounds(panelRect);
		lblImgVersion.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgAppVersion.getValue(),
				lblImgVersion.getWidth(), lblImgVersion.getHeight()));
		panelBottom.add(lblImgVersion);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 24.0D, 48.5D, 15.0D,
				30.0D);
		JLabel lblVersion = new JLabel("   " + CommonValues.getVersion());
		lblVersion.setForeground(Color.decode("#0F63AC"));
//		lblVersion.setFont(CommonValues.getFont(CommonValues.FontName.RUBIK, 13, CommonValues.FontTypes.BOLD));
		lblVersion.setBounds(panelRect);
		panelBottom.add(lblVersion);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 42.0D, 49.5D, 1.5D,
				25.0D);
		JLabel lblIp = new JLabel();
		lblIp.setBounds(panelRect);
		lblIp.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgIpAddress.getValue(), lblIp.getWidth(),
				lblIp.getHeight()));
		panelBottom.add(lblIp);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 44.0D, 48.5D, 15.0D,
				30.0D);
		JLabel lblIpAddress = new JLabel("   " + Inet4Address.getLocalHost().getHostAddress());
		lblIpAddress.setForeground(Color.decode("#0F63AC"));
//		lblIpAddress.setFont(CommonValues.getFont(CommonValues.FontName.RUBIK, 13, CommonValues.FontTypes.BOLD));
		lblIpAddress.setBounds(panelRect);
		panelBottom.add(lblIpAddress);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 60.0D, 48.0D, 2.0D,
				30.0D);
		JLabel lblComp = new JLabel();
		lblComp.setBounds(panelRect);
		lblComp.setIcon((Icon) new FlatSVGIcon(SvgImageResources.Images.SvgCompDb.getValue(), lblComp.getWidth(),
				lblComp.getHeight()));
		panelBottom.add(lblComp);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 63.0D, 48.5D, 15.0D,
				30.0D);
		JLabel lblCompCode = new JLabel("   " + ApplicationConfig.companyTag);
		lblCompCode.setForeground(Color.decode("#0F63AC"));
//		lblCompCode.setFont(CommonValues.getFont(CommonValues.FontName.RUBIK, 11, CommonValues.FontTypes.BOLD));
		lblCompCode.setBounds(panelRect);
		panelBottom.add(lblCompCode);
		panelRect = CommonValues.getRectangle(panelBottom.getWidth(), panelBottom.getHeight(), 79.0D, 35.0D, 20.0D,
				60.0D);
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(panelRect);
		lblLogo.setIcon(getImage(SvgImageResources.Images.svgDevelopedByLogo.getValue(), lblLogo.getWidth(),
				lblLogo.getHeight()));
		panelBottom.add(lblLogo);
		panelCenterCreation(lblWhiteCard);
		repaint();
	}

	private void panelCenterCreation(JLabel panelInside) throws Exception {
		Rectangle rectangle = CommonValues.getRectangle(panelInside.getWidth(), panelInside.getHeight(), 1.3D, 25.0D,
				97.5D, 70.0D);
		this.panelCenter = new JPanel(null);
		this.panelCenter.setBounds(rectangle);
		this.panelCenter.setOpaque(false);
		panelInside.add(this.panelCenter);
		panelMasterButtonsCreation();
//		panelInventoryButtonsCreation();
//		panelViewButtonsCreation();
//		panelReportButtonsCreation();
//		panelUtilityButtonsCreation();
//		panelTransferButtonsCreation();
	}

	private void panelMasterButtonsCreation() throws Exception {
		Rectangle rectangle = CommonValues.getRectangle(this.panelCenter.getWidth(), this.panelCenter.getHeight(),
				16.0D, 10.0D, 70.0D, 30.0D);
		panelMasterRow1 = new JPanel();
		panelMasterRow1.setBounds(rectangle);
		panelMasterRow1.setOpaque(false);
		panelMasterRow1.setLayout(new GridLayout(1, 5));
		panelCenter.add(this.panelMasterRow1);
		btnOperatorEntry = CustomControls.svgButtonCreation(' ', "OperatorEntry",
				ImagesMainScreen.Images.IMAGE_OPERATOR.getValue(),
				ImagesMainScreen.Images.IMAGE_OPERATOR_DISABLE.getValue(),
				ImagesMainScreen.Images.IMAGE_OPERATOR_HOVER.getValue());

		btnHsnBaics = CustomControls.svgButtonCreation(' ', "HsnBasics", ImagesMainScreen.Images.IMAGE_HSN.getValue(),
				ImagesMainScreen.Images.IMAGE_HSN_DISABLE.getValue(),
				ImagesMainScreen.Images.IMAGE_HSN_HOVER.getValue());

		btnProduct = CustomControls.svgButtonCreation(' ', "Product", ImagesMainScreen.Images.IMAGE_PRODUCT.getValue(),
				ImagesMainScreen.Images.IMAGE_PRODUCT_DISABLE.getValue(),
				ImagesMainScreen.Images.IMAGE_PRODUCT_HOVER.getValue());

		btnCategory = CustomControls.svgButtonCreation(' ', "Category",
				ImagesMainScreen.Images.IMAGE_CATEGORY_MASTER.getValue(),
				ImagesMainScreen.Images.IMAGE_CATEGORY_MASTER_DISABLE.getValue(),
				ImagesMainScreen.Images.IMAGE_CATEGORY_MASTER_HOVER.getValue());

		panelMasterRow1.add(btnOperatorEntry);
		panelMasterRow1.add(btnHsnBaics);
		panelMasterRow1.add(btnProduct);
		panelMasterRow1.add(btnCategory);

	}

	private ImageIcon getImage(String imagePath, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(this.classLoader.getResource(imagePath));
		Image image = imageIcon.getImage().getScaledInstance(width, height, 4);
		imageIcon.setImage(image);
		return imageIcon;
	}

	public void openForm(JInternalFrame jFrame, JMenuEnum.FormType formType, String dbName, String path)
			throws Exception {
		FormCommon formCommon = new FormCommon();
		((BasicInternalFrameUI) jFrame.getUI()).setNorthPane(null);
		formCommon.formLoad(jFrame, formType, dbName, path);
		formCommon.setVisible(true);
	}

	public static JLabel getLblTime() {
		return lblTime;
	}

	public static JLabel getLblDate() {
		return lblDate;
	}

	public static JLabel getLblGreetings() {
		return lblGreetings;
	}
}
