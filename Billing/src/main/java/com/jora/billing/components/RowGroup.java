package com.jora.billing.components;

import java.awt.Color;
import java.awt.Font;
import org.springframework.util.StringUtils;

public class RowGroup {
	private String groupRowcolumnName;

	private Color foreColor = Color.BLACK;

	private Color backColor = null;

	private Font textFont = new Font("calibri", 1, 12);

	private String subRowTotalText = StringUtils.capitalize(this.groupRowcolumnName);

	private int grouporder = 1;

	public RowGroup(String columnname) {
	    this.groupRowcolumnName = columnname;
	  }

	public RowGroup(String groupRowcolumnName, String subRowTotalText) {
	    this.groupRowcolumnName = groupRowcolumnName;
	    this.subRowTotalText = subRowTotalText;
	  }

	public RowGroup(String m_JilabaColumnname, Font font) {
	    this.groupRowcolumnName = m_JilabaColumnname;
	    this.textFont = font;
	  }

	public RowGroup(String groupRowcolumnName, Color backColor, Font textFont) {
	    this.groupRowcolumnName = groupRowcolumnName;
	    this.backColor = backColor;
	    this.textFont = textFont;
	  }

	public RowGroup(String groupRowcolumnName, Color foreColor, Color backColor, Font textFont) {
	    this.groupRowcolumnName = groupRowcolumnName;
	    this.foreColor = foreColor;
	    this.backColor = backColor;
	    this.textFont = textFont;
	  }

	public RowGroup(String groupRowcolumnName, Color foreColor, Color backColor, Font textFont, String subRowTotalText) {
	    this.groupRowcolumnName = groupRowcolumnName;
	    this.foreColor = foreColor;
	    this.backColor = backColor;
	    this.textFont = textFont;
	    this.subRowTotalText = subRowTotalText;
	  }

	public String getGroupRowcolumnName() {
		return this.groupRowcolumnName;
	}

	public void setGroupRowcolumnName(String groupRowcolumnName) {
		this.groupRowcolumnName = groupRowcolumnName;
	}

	public Color getForeColor() {
		return this.foreColor;
	}

	public void setForeColor(Color foreColor) {
		this.foreColor = foreColor;
	}

	public Color getBackColor() {
		return this.backColor;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	public Font getTextFont() {
		return this.textFont;
	}

	public void setTextFont(Font textFont) {
		this.textFont = textFont;
	}

	public String getSubRowTotalText() {
		return this.subRowTotalText;
	}

	public void setSubRowTotalText(String subRowTotalText) {
		this.subRowTotalText = subRowTotalText;
	}

	public String GetgroupRowcolumnName() {
		return this.groupRowcolumnName;
	}

	public int getGrouporder() {
		return this.grouporder;
	}

	public void setGrouporder(int grouporder) {
		this.grouporder = grouporder;
	}
}
