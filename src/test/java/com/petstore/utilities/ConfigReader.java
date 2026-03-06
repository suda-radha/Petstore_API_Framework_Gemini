package com.petstore.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties;

	static {
		try {
			String path = "src/test/resources/config.properties";
			FileInputStream input = new FileInputStream(path);
			properties = new Properties();
			properties.load(input);
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String keyName) {
		return properties.getProperty(keyName);
	}

	// New method to get the Base URL based on the environment flag
	public static String getBaseUrl() {
		// Priority 1: Command line (-Denv=uat)
		// Priority 2: config.properties (env=int)
		String environment = System.getProperty("env") != null ? System.getProperty("env") : getProperty("env");

		return getProperty(environment + "_url");
	}

}
