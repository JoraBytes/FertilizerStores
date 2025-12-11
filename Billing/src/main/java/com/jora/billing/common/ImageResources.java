package com.jora.billing.common;

public class ImageResources {

	public enum Images {
		LOADINGSCREEN("master/common/loadingscreen.gif"), DATE("master/common/date.png"),
		LOCALIP("master/common/localip.png"), SERVERIP("master/common/serverip.png"), USER("master/common/user.png"),
		VERSION("master/common/version.png"), DBNAME("master/common/dbname.png"), LOGO("master/common/smalllogo.png"),
		ADDNORMAL("master/mdibuttons/add_btn.png"), ADDDISABLE("master/mdibuttons/d_add_btn.png"),
		ADDFOCUS("master/mdibuttons/add_focus.png"), ADD("master/button/frm_add.png"),
		CLEAR("master/button/frm_clear.png"), SAVENORMAL("master/mdibuttons/save_btn.png"),
		SAVEFOCUS("master/mdibuttons/save_focus.png"), SAVEDISABLE("master/mdibuttons/d_save_btn.png"),
		VIEWNORMAL("master/mdibuttons/view_btn.png"), VIEWFOCUS("master/mdibuttons/view_focus.png"),
		VIEWDISABLE("master/mdibuttons/d_view_btn.png"), VIEWPNG("master/button/view.png"),
		VIEWDISABLEPNG("master/button/view-disable.png"), VIEWFOCUSPNG("master/button/view-focus.png"),
		EDITNORMAL("master/mdibuttons/edit_btn.png"), EDITFOCUS("master/mdibuttons/edit_focus.png"),
		EDITDISABLE("master/mdibuttons/d_edit_btn.png"), CANCELNORMAL("master/mdibuttons/cancel_btn.png"),
		CANCELFOCUS("master/mdibuttons/cancel_focus.png"), CANCELDISABLE("master/mdibuttons/d_cancel_btn.png"),
		CLOSENORMAL("master/mdibuttons/close_btn.png"), CLOSEFOCUS("master/mdibuttons/close_focus.png"),
		CLOSEDISABLE("master/mdibuttons/d_close_btn.png"), PRINTPNG("master/button/print.png"),
		PRINTDISABLEPNG("master/button/print-disable.png"), PRINTFOCUSPNG("master/button/print-focus.png"),
		POSTPNG("master/button/post.png"), POSTDISABLEPNG("master/button/post-disable.png"),
		POSTFOCUSPNG("master/button/post-focus.png"), POSTINGNORMAL("master/mdibuttons/posting_btn.png"),
		POSTINGFOCUS("master/mdibuttons/posting_focus.png"), POSTINGDISABLE("master/mdibuttons/d_posting_btn.png"),
		IMAGEJILABALOGO("master/common/jilabalogo.png"), IMAGEHELP("master/common/help.png"),
		ICON_USER("master/common/user.svg"), ICON_DB("master/common/db.svg"), ICON_IP("master/common/localip.svg"),
		ICON_VERSION("master/common/version.svg"), ICON_SERVER_IP("master/common/ip.svg"),
		ICON_DATE("master/common/date.svg");

		private String value;

		public String getValue() {
			return this.value;
		}

		Images(String string) {
			this.value = string;
		}
	}
}
