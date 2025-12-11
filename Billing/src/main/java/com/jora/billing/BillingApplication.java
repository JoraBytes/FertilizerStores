package com.jora.billing;

import java.awt.Color;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.formdev.flatlaf.FlatLightLaf;
import com.jora.billing.common.CommonValues;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.config.ApplicationConfig;
import com.jora.billing.form.FrmMDI;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = { "com.jora.billing.*" })
@EnableScheduling
public class BillingApplication {

	private static ApplicationContext applicationContext;

	private static final Logger log = LogManager.getLogger(BillingApplication.class);

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			UIManager.put("Button.focusedBackground", Color.BLUE);
			UIManager.put("Button.focusedForeground", Color.WHITE);
			UIManager.put("TableHeader.hoverable", Boolean.FALSE);
			UIManager.put("TableHeader.hoverBackground", CommonValues.getBORDER_COLOR());
			UIManager.put("TableHeader.pressedBackground", CommonValues.getBORDER_COLOR());
			UIManager.put("Button.hoverBackground", Color.BLUE);
			UIManager.put("Button.hoverForeground", Color.WHITE);
			UIManager.put("FlatLaf.useSVGSaliency", false);

			CommonValues.setApplicationName(appName());
			ApplicationConfig.fileRead();
			applicationContext = new SpringApplicationBuilder(BillingApplication.class).web(WebApplicationType.NONE)
					.headless(false).run(args);
			FrmMDI mdi = applicationContext.getBean(FrmMDI.class);
			mdi.setVisible(true);
		} catch (Exception e) {
			log.error(ErrorHandler.errorTraceForLogger(e));
			JOptionPane.showMessageDialog(null, ErrorHandler.errorMessage(e), CommonValues.getApplicationName(),
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	private static String appName() {
		try {
			Properties props = new Properties();
			InputStream is = BillingApplication.class.getResourceAsStream("/META-INF/build-info.properties");
			if (is != null) {
				props.load(is);
				return props.getProperty("build.name", "BillingService");
			}
		} catch (Exception e) {
		}
		return "BillingService";
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
