package com.jora.billing.common;

public class SvgImageResources {

	private static final String path = "images/";

	private static final String mainScreen = "images/mainscreensvgs/";

	private static final String icon = "images/icons/";

	public static String getPath() {
		return path;
	}

	public static String getMainscreen() {
		return mainScreen;
	}

	public static String getIcon() {
		return icon;
	}

	public enum Images {
		svgTransferFocus("images/mainscreensvgs/Transfer-Focus.svg"),
		svgTransferNonFocus("images/mainscreensvgs/Transfer-Non-Focus.svg"),
		SvgNavigateBackground("images/mainscreensvgs/background.svg"),
		SvgLeftPanelJILABA_LOGO("images/mainscreensvgs/Menu-Background-Gradient.svg"),
		SvgRightPanel("images/mainscreensvgs/rightpanelcard.svg"), SvgStoresPanel("images/mainscreensvgs/hosiery.svg"),
		SvgLine("images/mainscreensvgs/line.svg"), SvgDetails("images/mainscreensvgs/Details.svg"),
		SvgDateTime("images/mainscreensvgs/date-and-time.svg"),
		SvgMasterFocus("images/mainscreensvgs/Master-Focus1.svg"),
		SvgInventoryFocus("images/mainscreensvgs/Inventory-Focus1.svg"),
		SvgReportsFocus("images/mainscreensvgs/Report-Focus1.svg"),
		SvgUtilityFocus("images/mainscreensvgs/Utility-Focus1.svg"),
		SvgUtilityNonFocus("images/mainscreensvgs/Utility-Non-Focus1.svg"),
		SvgLogoutFocus("images/mainscreensvgs/logout-focus.svg"), SvgExitFocus("images/mainscreensvgs/Exit-Focus.svg"),
		SvgAccountsMainFocus("images/mainscreensvgs/Accounts-Focus.svg"),
		SvgMinimize("images/mainscreensvgs/minimize-focus.svg"),
		SvgAccountsMainNonFocus("images/mainscreensvgs/Accounts-Non-Focus.svg"),
		svgLeftSliderFocus("images/mainscreensvgs/left-said-navigator-focus.svg"),
		svgLeftSliderNonFocus("images/mainscreensvgs/left-said-navigator.svg"),
		svgRightSliderFocus("images/mainscreensvgs/right-said-navigator-focus.svg"),
		svgRightSliderNonFocus("images/mainscreensvgs/right-said-navigator.svg"),
		svgDevelopedByLogo("images/mainscreensvgs/developedBylogo.png"),
		SvgRightIndicate("images/mainscreensvgs/left-indicate.svg"),
		SvgLeftIndicate("images/mainscreensvgs/right-indicate.svg"),
		SvgIpAddress("images/mainscreensvgs/ipAddress1.svg"),
		SvgServerAddress("images/mainscreensvgs/serverAddress1.svg"), SvgCompDb("images/mainscreensvgs/compDb1.svg"),
		SvgAppVersion("images/mainscreensvgs/appVersion1.svg"),
		SvgMasterNonFocus("images/mainscreensvgs/Master-Non-Focus1.svg"),
		SvgInventoryNonFocus("images/mainscreensvgs/Inventory-Non-Focus1.svg"),
		SvgReportsnonFocus("images/mainscreensvgs/Report-Non-Focus1.svg"),
		SvgLogoutnonFocus("images/mainscreensvgs/logout-non-focus.svg"),
		SvgExitnonFocus("images/mainscreensvgs/Exit-Non-Focus.svg"), SVG_AMOUNT_RECTANGLE("images/amountrectangle.svg"),
		SvgAddFocus("images/icons/add.svg"), SvgAddNonfocus("images/icons/add-d.svg"),
		SvgClearFocus("images/icons/clear.svg"), SvgLoadFocus("images/icons/load.svg"),
		SvgLoadNonfocus("images/icons/load-d.svg"), SvgClearNonFocus("images/icons/clear-d.svg"),
		SvgWhiteCard("images/inner-bg.svg"), SvgViewFocus("images/mainscreensvgs/view-Focus.svg"),
		SvgViewNonFocus("images/mainscreensvgs/view-Non-Focus.svg");

		private String value;

		Images(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
}
