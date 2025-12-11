package com.jora.billing.config;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.jora.encodedecode.common.EncryptionDecryption;

public class ApplicationConfig {
	public static String serverName, companyTag, portNo, userName, password, companyFlag;
	public static int dbYear;
	private static List<String> lstGreetings = new ArrayList<String>();
	private static int index = 0;

	public static void fileRead() throws Exception {
		File file = new File(System.getProperty("user.dir") + "\\Appconfig.ini");
		if (!file.exists()) {

			throw new Exception("Appconfig.ini File not found!...");
		}

		List<String> lstServerDetails = new ArrayList<String>();
		lstServerDetails = Files.readAllLines(file.toPath());
		int lineCount = 1;
		for (String s : lstServerDetails) {
			switch (lineCount) {
			case 1:
				companyTag = EncryptionDecryption.decrypt(s.trim());
				break;
			case 2:
				companyFlag = EncryptionDecryption.decrypt(s.trim());
				break;
			case 3:
				serverName = EncryptionDecryption.decrypt(s.trim());
				break;
			case 4:
				portNo = EncryptionDecryption.decrypt(s.trim());
				break;
			case 5:
				userName = EncryptionDecryption.decrypt(s.trim());
				break;
			case 6:
				password = EncryptionDecryption.decrypt(s.trim());
				break;
			default:
				break;
			}
			lineCount += 1;
		}
		loadGreetings();
	}

	private static void loadGreetings() {
		lstGreetings.add("Good to see you! Let’s begin today’s billing.");
		lstGreetings.add("Welcome! Growing your business, one bill at a time.");
		lstGreetings.add("Wishing you a good and steady workday ahead.");
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		ApplicationConfig.index = index;
	}

	public static List<String> getLstGreetings() {
		return lstGreetings;
	}
}
