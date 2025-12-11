package com.jora.billing.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JDocumentFilter extends DocumentFilter {
	  private final TextField textField;
	  
	  JDocumentFilter(TextField textField) {
	    this.textField = textField;
	  }
	  
	  public void insertString(DocumentFilter.FilterBypass arg0, int arg1, String arg2, AttributeSet arg3) throws BadLocationException {
	    super.insertString(arg0, arg1, arg2, arg3);
	  }
	  
	  public void remove(DocumentFilter.FilterBypass arg0, int arg1, int arg2) throws BadLocationException {
	    super.remove(arg0, arg1, arg2);
	  }
	  
	  public void replace(DocumentFilter.FilterBypass arg0, int offs, int length, String text, AttributeSet arg4) throws BadLocationException {
	    String inputText = text;
	    if (textField.getMaxLength() > 0)
	      if (textField.isAssigned()) {
	        if (textField.getMaxLength() < inputText.length())
	          inputText = inputText.substring(0, textField.getMaxLength()); 
	        textField.setAssigned(false);
	      } else if (offs == 0 && length == 0) {
	        if (textField.getText().length() >= textField.getMaxLength()) {
	          inputText = "";
	        } else {
	          int requiredLength = textField.getMaxLength() - textField.getText().length();
	          if (requiredLength < inputText.length())
	            inputText = inputText.substring(0, requiredLength); 
	        } 
	      } else if (offs == 0 && length > 0) {
	        int requiredLength = textField.getMaxLength() - textField.getText().length() - length;
	        if ((((inputText != null) ? 1 : 0) & ((requiredLength < inputText.length()) ? 1 : 0)) != 0)
	          inputText = inputText.substring(0, requiredLength); 
	      } else if (offs > 0 && length == 0) {
	        if (textField.getText().length() >= textField.getMaxLength()) {
	          inputText = "";
	        } else {
	          int requiredLength = textField.getMaxLength() - textField.getText().length();
	          if (requiredLength < inputText.length())
	            inputText = inputText.substring(0, requiredLength); 
	        } 
	      } else if (offs > 0 && length > 0) {
	        int requiredLength = textField.getMaxLength() - textField.getText().length() - length;
	        if (requiredLength < inputText.length())
	          inputText = inputText.substring(0, requiredLength); 
	      }  
	    if (textField.getTextCase() == JTextFieldEnum.TextInputCase.LOWER) {
	      inputText = inputText.toLowerCase();
	    } else if (textField.getTextCase() == JTextFieldEnum.TextInputCase.UPPER) {
	      inputText = inputText.toUpperCase();
	    } 
	    super.replace(arg0, offs, length, inputText, arg4);
	  }
}
