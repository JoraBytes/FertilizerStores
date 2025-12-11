package com.jora.billing.custom;

import javax.swing.JInternalFrame;

public interface FormMenuAction {
	void formLoad(JInternalFrame paramJInternalFrame, JMenuEnum.FormType paramFormType, String paramString1,
			String paramString2);

	void formClose(JInternalFrame paramJInternalFrame) throws Exception;
}