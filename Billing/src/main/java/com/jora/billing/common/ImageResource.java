package com.jora.billing.common;

public class ImageResource {
	private static final String path = "images/";

	private static final String transferPath = "images/transfericons/";

	private static final String transferDisablePath = "images/transfericons/disable/";

	private static final String loginPath = "images/login/";

	public enum Images {
		IMAGE_JILABA_LOGO("images/jilabalogo.png"), IMAGE_STORE_SMALL_LOGO("images/logosmall.png"),
		IMAGE_BANNER("images/banner.png"), IMAGE_LOGIN_BG("images/login/logininnerbg.svg"),
		IMAGE_COMPANY_SELECTION("images/companychange.jpg"), IMAGE_TASK_BAR_LOGO("images/jilaba.png"),
		IMAGE_REFRESH("images/refresh.png"), IMAGE_FULL_BG("images/loginscreenbg.jpg"),
		COMPANY_LOGO("images/login/companyLogo.png"), IMAGE_USER_COMBOBOX("images/login/login.svg"),
		IMAGE_PASSWORD("images/login/password.svg"), IMAGE_LOGINN_SELECTED("images/login/login-fill.svg"),
		IMAGE_LOGGIN("images/login/login-borrder.svg"), IMAGE_EXXIT("images/login/exit-borrder.svg"),
		IMAGE_EXXIT_SELECTED("images/login/exit-fill.svg"), IMAGE_CAPSLOCK("images/login/capslock.svg"),
		IMAGE_BACK("images/back.png"), IMAGE_TRANSFER_SETTINGS("images/transfericons/transfersettings.png"),
		IMAGE_TRANSFER_SETTINGS_DISABLE("images/transfericons/disable/transfersettingsd.png"),
		IMAGE_MASTER_TRANSFER("images/transfericons/mastertransfer.png"),
		IMAGE_MASTER_TRANSFER_DISABLE("images/transfericons/disable/mastertransferd.png"),
		IMAGE_AUTO_TRANSFER("images/transfericons/autotransfer.png"),
		IMAGE_AUTO_TRANSFER_DISABLE("images/transfericons/disable/autotransferd.png"),
		IMAGE_BO_TO_HO("images/transfericons/botoho.png"),
		IMAGE_BO_TO_HO_DISABLE("images/transfericons/disable/botohod.png"),
		IMAGE_POSTING("images/transfericons/posting.png"),
		IMAGE_POSTING_DISABLE("images/transfericons/disable/postingd.png"), IMAGE_NSMT("images/transfericons/nsmt.png"),
		IMAGE_NSMT_DISABLE("images/transfericons/disable/nsmtd.png"), IMAGE_NST("images/transfericons/nst.png"),
		IMAGE_NST_DISABLE("images/transfericons/disable/nstd.png"), IMAGE_STU("images/transfericons/stu.png"),
		IMAGE_STU_DISABLE("images/transfericons/disable/stud.png"),
		IMAGE_STOCK_DOWNLOAD("images/transfericons/stockdownload.png"),
		IMAGE_STOCK_DOWNLOAD_DISABLE("images/transfericons/disable/stockdownloadd.png"),
		SVG_IMAGE("images/login/bg.svg"), SVG_INNERBG("images/fashion-contractor.webp");

		private String value;

		Images(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static String getPath() {
		return path;
	}

	public static String getTransferpath() {
		return transferPath;
	}

	public static String getTransferdisablepath() {
		return transferDisablePath;
	}

	public static String getLoginpath() {
		return loginPath;
	}
}
