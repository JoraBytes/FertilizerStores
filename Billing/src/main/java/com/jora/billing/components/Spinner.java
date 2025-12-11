package com.jora.billing.components;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

public class Spinner extends JSpinner {
	  private static final long serialVersionUID = 1L;
	  
	  private SpinnerDateModel spinnerDateModel;
	  
	  private final JSpinner.DateEditor jDateEdtitor;
	  
	  private String dateFormatString = "dd-MMM-yyyy";
	  
	  private final String timeFormatString = "hh:mm:ss a";
	  
	  private SimpleDateFormat simpleDateFormat;
	  
	  private SpinnerEnum spinnerEnum = SpinnerEnum.DATE;
	  
	  public Spinner(SpinnerEnum spinnerEnum) {
	    this.spinnerEnum = spinnerEnum;
	    this.spinnerDateModel = new SpinnerDateModel();
	    setModel(this.spinnerDateModel);
	    this.dateFormatString = (this.spinnerEnum == SpinnerEnum.DATE) ? this.dateFormatString : "hh:mm:ss a";
	    this.jDateEdtitor = new JSpinner.DateEditor(this, this.dateFormatString);
	    setEditor(this.jDateEdtitor);
	    setValue(new Date());
	  }
	  
	  public Spinner() {
	    this.spinnerDateModel = new SpinnerDateModel();
	    setModel(this.spinnerDateModel);
	    this.jDateEdtitor = new JSpinner.DateEditor(this, this.dateFormatString);
	    setEditor(this.jDateEdtitor);
	    setValue(new Date());
	  }
	  
	  public Spinner(String formatString) {
	    this.spinnerDateModel = new SpinnerDateModel();
	    setModel(this.spinnerDateModel);
	    this.dateFormatString = formatString;
	    this.jDateEdtitor = new JSpinner.DateEditor(this, this.dateFormatString);
	    setEditor(this.jDateEdtitor);
	    setValue(new Date());
	  }
	  
	  public Spinner(SpinnerModel arg0) {
	    super(arg0);
	    this.jDateEdtitor = new JSpinner.DateEditor(this, this.dateFormatString);
	    setEditor(this.jDateEdtitor);
	    setValue(new Date());
	  }
	  
	  public Spinner(SpinnerModel arg0, String formatString) {
	    super(arg0);
	    this.dateFormatString = formatString;
	    this.jDateEdtitor = new JSpinner.DateEditor(this, this.dateFormatString);
	    setEditor(this.jDateEdtitor);
	    setValue(new Date());
	  }
	  
	  public void setValue(Object value) {
	    super.setValue(value);
	  }
	  
	  public void setValue(String date) throws Exception  {
	    if (this.spinnerEnum == SpinnerEnum.DATE) {
	      this.simpleDateFormat = new SimpleDateFormat(this.dateFormatString);
	    } else {
	      this.simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
	    } 
	    setValue(this.simpleDateFormat.parse(date));
	  }
	  
	  public String getDateValue() {
	    this.simpleDateFormat = new SimpleDateFormat(this.dateFormatString);
	    return this.simpleDateFormat.format(getValue());
	  }
	  
	  public String getTimeValue() {
	    this.simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
	    return this.simpleDateFormat.format(getValue());
	  }
	  
	  public String getDateValue(String formatString) {
	    this.simpleDateFormat = new SimpleDateFormat(formatString);
	    return this.simpleDateFormat.format(getValue());
	  }
	  
	  public String getTimeValue(String formatString) {
	    this.simpleDateFormat = new SimpleDateFormat(formatString);
	    return this.simpleDateFormat.format(getValue());
	  }
	  
	  public String getDateFormatString() {
	    return this.dateFormatString;
	  }
	  
	  public String getTimeFormatString() {
	    return "hh:mm:ss a";
	  }
	  
	  public void setToolTipText(String text) {
	    super.setToolTipText(text);
	    ((JSpinner.DefaultEditor)getEditor()).getTextField().setToolTipText(text);
	  }
	  
	  public void requestFocus() {
	    super.requestFocus();
	    ((JSpinner.DefaultEditor)getEditor()).getTextField().requestFocus();
	  }
	  
	  public synchronized void addKeyListener(KeyListener l) {
	    super.addKeyListener(l);
	    ((JSpinner.DefaultEditor)getEditor()).getTextField().addKeyListener(l);
	  }
	  
	  public synchronized void addFocusListener(FocusListener arg0) {
	    super.addFocusListener(arg0);
	    ((JSpinner.DefaultEditor)getEditor()).getTextField().addFocusListener(arg0);
	  }
	  
	  public boolean requestFocusInWindow() {
	    super.requestFocus();
	    ((JSpinner.DefaultEditor)getEditor()).getTextField().requestFocus();
	    return true;
	  }

}
