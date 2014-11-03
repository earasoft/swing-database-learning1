package com.earasoft.learning1;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigLearning {

	public static void main(String[] args) {
		try {
			PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
