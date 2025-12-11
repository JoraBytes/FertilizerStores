package com.jora.billing.scheduled;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jora.billing.common.CommonValues;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.config.ApplicationConfig;
import com.jora.billing.form.FormCommon;
import com.jora.billing.form.FrmMDI;

@Component
public class SchedularTasks {

	@Scheduled(cron = "* * * * * ?")
	public void renderTime() {
		try {
			if (FormCommon.getLblDateTime() != null)
				FormCommon.getLblDateTime()
						.setText(CommonValues.getDisplaydatetimeformat().format(LocalDateTime.now()));
			if (FrmMDI.getLblDate() != null && FrmMDI.getLblTime() != null) {
				FrmMDI.getLblDate().setText(CommonValues.getDisplaydateformat().format(LocalDateTime.now()));
				FrmMDI.getLblTime().setText(CommonValues.getDisplaytimeformat().format(LocalDateTime.now()));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ErrorHandler.errorMessage(e));
		}
	}

	@Scheduled(cron = "*/5 * * * * ?")
	public void renderGreetings() {
		try {
			if (FrmMDI.getLblGreetings() != null) {
				FrmMDI.getLblGreetings().setText(ApplicationConfig.getLstGreetings().get(ApplicationConfig.getIndex()));
				if (ApplicationConfig.getIndex() == ApplicationConfig.getLstGreetings().size() - 1) {
					ApplicationConfig.setIndex(0);
				} else {
					ApplicationConfig.setIndex(ApplicationConfig.getIndex() + 1);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ErrorHandler.errorMessage(e));
		}
	}
}
