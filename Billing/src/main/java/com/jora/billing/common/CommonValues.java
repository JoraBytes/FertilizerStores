package com.jora.billing.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import com.jora.billing.custom.CustomMessage;
import com.jora.billing.custom.FormAction;

public class CommonValues {

	private static String applicationName;

	private static String version;

	private static Operator operator;

	private static int SCREENWIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	private static int SCREENHEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	private static Dimension panelContentSize;

	private static int INTERNALFRAMEWIDTH;

	private static int INTERNALFRAMEHEIGHT;

	private static Color HEADING_COLOR;

	private static Color MAIN_COLOR = Color.decode("#e9f1f7");

	private static Color BORDER_COLOR = Color.decode("#739edc");

	private static Color MENU_BORDER_COLOR = Color.decode("#4682B4");

	private static Color REPORT_TABLE_COLOR = Color.decode("#eed8f5");

	private static Color DASHED_LINE_COLOR = Color.decode("#d9e6ee");

	private static Color COLUMN_SELECTION_COLOR = Color.decode("#a0db8e");

	private static Color MAIN_BASE_COLOR = Color.decode("#f8faed");

	public static final String COMPYDBNAME = "compydb";

	public static final int IFRAMEXPOS = 0;

	public static final int IFRAMEYPOS = -23;

	public static final int IFRAMEWIDTH = 1024;

	public static final int IFRAMEHEIGHT = 653;

	private static String localIp = "";

	public static final DateTimeFormatter SAVEDATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static final DateTimeFormatter DISPLAYDATEFORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

	public static final DateTimeFormatter DISPLAYTIMEFORMAT = DateTimeFormatter.ofPattern("hh:mm:ss a");

	private static final DateTimeFormatter DISPLAYDATETIMEFORMAT = DateTimeFormatter
			.ofPattern("dd-MMM-yyyy hh:mm:ss a");

	public static final DateTimeFormatter SAVEDATETIMEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	public static final DecimalFormat AMTFORMAT = new DecimalFormat("#0.00");

	public static final Color TEXTFOCUS_COLOR = Color.decode("#f9dde7");

	public static final int ServerOrNodeCtrlCode = 15;

	public static final String OPENINGSTOCK = "106000000001";

	private static final int moduleId = 1;

	private static final Font lblSmallFont = new Font("Arial", 1, 10);

	private static final Font tableFont = new Font("Arial", 1, 12);

	private static final Font tableHeaderFont = new Font("Arial", 1, 13);

	private static final Color tableForeGroundColor = Color.BLACK;

	private static final Color tableGridColor = Color.BLACK;

	private static final int tableRowHeight = 30;

	private static final int borderThicknes = 2;

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

	private static final SimpleDateFormat dateTime = new SimpleDateFormat("hh:mm:ss a");

	private static final DateTimeFormatter sqlDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final DateTimeFormatter sqlDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static final String frmProduct = "Product Master";

	private static DecimalFormat threeDigitFormat = new DecimalFormat("000");

	private static FormAction currentform = null;

	public static DecimalFormat getThreeDigitFormat() {
		return threeDigitFormat;
	}

	public static String getLocalIp() {
		return localIp;
	}

	public static int getInt(Object value) {
		try {
			if (value == null)
				return 0;
			return Integer.parseInt(String.valueOf(value));
		} catch (NullPointerException | NumberFormatException e) {
			return 0;
		} catch (Exception e) {
			throw e;
		}
	}

	public static double getDouble(Object value) {
		try {
			if (value == null)
				return 0.0D;
			return Double.parseDouble(String.valueOf(value));
		} catch (NullPointerException | NumberFormatException e) {
			return 0.0D;
		} catch (Exception e) {
			throw e;
		}
	}

	public static String getApplicationName() {
		return applicationName;
	}

	public static void setApplicationName(String applicationName) {
		CommonValues.applicationName = applicationName;
	}

	public static int getSCREENWIDTH() {
		return SCREENWIDTH;
	}

	public static int getSCREENHEIGHT() {
		return SCREENHEIGHT;
	}

	public static int getINTERNALFRAMEWIDTH() {
		return INTERNALFRAMEWIDTH;
	}

	public static void setINTERNALFRAMEWIDTH(int iNTERNALFRAMEWIDTH) {
		INTERNALFRAMEWIDTH = iNTERNALFRAMEWIDTH;
	}

	public static int getINTERNALFRAMEHEIGHT() {
		return INTERNALFRAMEHEIGHT;
	}

	public static void setINTERNALFRAMEHEIGHT(int iNTERNALFRAMEHEIGHT) {
		INTERNALFRAMEHEIGHT = iNTERNALFRAMEHEIGHT;
	}

	public static Dimension getPanelContentSize() {
		return panelContentSize;
	}

	public static Color getHEADING_COLOR() {
		return HEADING_COLOR;
	}

	public static Color getMAIN_COLOR() {
		return MAIN_COLOR;
	}

	public static Color getBORDER_COLOR() {
		return BORDER_COLOR;
	}

	public static Color getMENU_BORDER_COLOR() {
		return MENU_BORDER_COLOR;
	}

	public static Color getREPORT_TABLE_COLOR() {
		return REPORT_TABLE_COLOR;
	}

	public static Color getDASHED_LINE_COLOR() {
		return DASHED_LINE_COLOR;
	}

	public static Color getCOLUMN_SELECTION_COLOR() {
		return COLUMN_SELECTION_COLOR;
	}

	public static Color getMAIN_BASE_COLOR() {
		return MAIN_BASE_COLOR;
	}

	public static Rectangle getRectangle(int panelWidht, int panelHeight, double xposPer, double yposPer,
			double widthPer, double heightPer) {
		Rectangle rect = new Rectangle((int) (panelWidht * xposPer) / 100, (int) (panelHeight * yposPer) / 100,
				(int) (panelWidht * widthPer) / 100, (int) (panelHeight * heightPer) / 100);
		return rect;
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		CommonValues.version = version;
	}

	public static String getCompydbname() {
		return COMPYDBNAME;
	}

	public static int getIframexpos() {
		return IFRAMEXPOS;
	}

	public static int getIframeypos() {
		return IFRAMEYPOS;
	}

	public static int getIframewidth() {
		return IFRAMEWIDTH;
	}

	public static int getIframeheight() {
		return IFRAMEHEIGHT;
	}

	public static DateTimeFormatter getSavedateformat() {
		return SAVEDATEFORMAT;
	}

	public static DateTimeFormatter getDisplaydateformat() {
		return DISPLAYDATEFORMAT;
	}

	public static DateTimeFormatter getSavedatetimeformat() {
		return SAVEDATETIMEFORMAT;
	}

	public static DecimalFormat getAmtformat() {
		return AMTFORMAT;
	}

	public static Color getTextfocusColor() {
		return TEXTFOCUS_COLOR;
	}

	public static int getServerornodectrlcode() {
		return ServerOrNodeCtrlCode;
	}

	public static String getOpeningstock() {
		return OPENINGSTOCK;
	}

	public static int getModuleid() {
		return moduleId;
	}

	public static Font getLblsmallfont() {
		return lblSmallFont;
	}

	public static Font getTablefont() {
		return tableFont;
	}

	public static Font getTableheaderfont() {
		return tableHeaderFont;
	}

	public static Color getTableforegroundcolor() {
		return tableForeGroundColor;
	}

	public static Color getTablegridcolor() {
		return tableGridColor;
	}

	public static int getTablerowheight() {
		return tableRowHeight;
	}

	public static int getBorderthicknes() {
		return borderThicknes;
	}

	public static DateTimeFormatter getDatetimeformatter() {
		return dateTimeFormatter;
	}

	public static SimpleDateFormat getDatetime() {
		return dateTime;
	}

	public static DateTimeFormatter getSqldateformatter() {
		return sqlDateFormatter;
	}

	public static DateTimeFormatter getSqldatetimeformatter() {
		return sqlDateTimeFormatter;
	}

	public static String getFrmproduct() {
		return frmProduct;
	}

	public static void setSCREENWIDTH(int sCREENWIDTH) {
		SCREENWIDTH = sCREENWIDTH;
	}

	public static void setSCREENHEIGHT(int sCREENHEIGHT) {
		SCREENHEIGHT = sCREENHEIGHT;
	}

	public static void setPanelContentSize(Dimension panelContentSize) {
		CommonValues.panelContentSize = panelContentSize;
	}

	public static void setHEADING_COLOR(Color hEADING_COLOR) {
		HEADING_COLOR = hEADING_COLOR;
	}

	public static void setMAIN_COLOR(Color mAIN_COLOR) {
		MAIN_COLOR = mAIN_COLOR;
	}

	public static void setBORDER_COLOR(Color bORDER_COLOR) {
		BORDER_COLOR = bORDER_COLOR;
	}

	public static void setMENU_BORDER_COLOR(Color mENU_BORDER_COLOR) {
		MENU_BORDER_COLOR = mENU_BORDER_COLOR;
	}

	public static void setREPORT_TABLE_COLOR(Color rEPORT_TABLE_COLOR) {
		REPORT_TABLE_COLOR = rEPORT_TABLE_COLOR;
	}

	public static void setDASHED_LINE_COLOR(Color dASHED_LINE_COLOR) {
		DASHED_LINE_COLOR = dASHED_LINE_COLOR;
	}

	public static void setCOLUMN_SELECTION_COLOR(Color cOLUMN_SELECTION_COLOR) {
		COLUMN_SELECTION_COLOR = cOLUMN_SELECTION_COLOR;
	}

	public static void setMAIN_BASE_COLOR(Color mAIN_BASE_COLOR) {
		MAIN_BASE_COLOR = mAIN_BASE_COLOR;
	}

	public static void setLocalIp(String localIp) {
		CommonValues.localIp = localIp;
	}

	public static void setThreeDigitFormat(DecimalFormat threeDigitFormat) {
		CommonValues.threeDigitFormat = threeDigitFormat;
	}

	public static int verticalGap(Component parentPanel, Component cmp, double increaseYper) {
		return cmp.getY() + cmp.getHeight() + (int) (parentPanel.getHeight() * increaseYper / 100.0D);
	}

	public static int horizontalGap(Component parentPanel, Component cmp, double increaseXper) {
		return cmp.getX() + cmp.getWidth() + (int) (parentPanel.getWidth() * increaseXper / 100.0D);
	}

	public static FormAction getCurrentform() {
		return currentform;
	}

	public static void setCurrentform(FormAction currentform) {
		CommonValues.currentform = currentform;
	}

	public static void showErrorMessage(Component component, String message, String appName) {
		String msg = message;
		if (message != null && message.length() > 200)
			msg = "<html><body width='300px'><p>" + message;
		CustomMessage.showMessageDialog(component, msg, appName, 0);
	}

	public static DateTimeFormatter getDisplaydatetimeformat() {
		return DISPLAYDATETIMEFORMAT;
	}

	public static DateTimeFormatter getDisplaytimeformat() {
		return DISPLAYTIMEFORMAT;
	}

	public static String selectedYesOrNo(boolean isTrue) {
		return isTrue ? "YES" : "NO";
	}

	public static String selectedYOrN(boolean isTrue) {
		return isTrue ? "Y" : "N";
	}

	public static Operator getOperator() {
		return operator;
	}

	public static void setOperator(Operator operator) {
		CommonValues.operator = operator;
	}
}
