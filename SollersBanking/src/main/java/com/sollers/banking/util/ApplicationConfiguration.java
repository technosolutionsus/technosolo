package com.sollers.banking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfiguration {

	final static Logger logger = Logger.getLogger(ApplicationConfiguration.class);

	private final String resourceFile = null;
	private Properties properties = null;
	public static final String DEFAULT_RESOURCE_FILE = "appConf.properties";
	private String resourceDirectory = "/apps/sollers/config/properties/";
	private static String customDirectory = System.getProperty("com.sollers.banking.resources");

	/*
	public ApplicationConfiguration(String resourceFile) {
		this.resourceFile = resourceFile;
		try {
			loadConfiguration();
		} catch (Exception e) {

		}
	}
	*/
	
	protected void loadConfiguration() throws IOException {
		try {
			logger.info("Attempting to load " + resourceFile + " via file I/O.");
			properties = loadPropertiesFromStream(getResourceStream(resourceFile), resourceFile);
		} catch (Exception iae) {
			logger.info("Loading " + resourceFile + " via file I/O failed. Exception was: " + iae);
			logger.info("Attempting to load " + resourceFile + " via the classpath.");
			try {
				properties = loadConfigurationFromClasspath(resourceFile);
			} catch (Exception e) {
				logger.info(resourceFile + " could not be loaded by any means. Fail.", e);
			}
		}
	}

	private Properties loadPropertiesFromStream(InputStream is, String name) throws IOException {
		Properties config = new Properties();
		try {
			config.load(is);
			logger.info("Loaded '" + name + "' properties file", null);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (Exception e) {
				}
		}
		return config;
	}

	public InputStream getResourceStream(String filename) throws IOException {
		if (filename == null) {
			return null;
		}

		try {
			File f = getResourceFile(filename);
			if (f != null && f.exists()) {
				return new FileInputStream(f);
			}
		} catch (Exception e) {
		}

		throw new FileNotFoundException();
	}

	public File getResourceFile(String filename) {
		logger.info("Attempting to load " + filename + " as resource file via file I/O.");

		if (filename == null) {
			logger.info("Failed to load properties via FileIO. Filename is null.");
			return null;
		}

		File f = null;
		f = new File(customDirectory, filename);
		if (customDirectory != null && f.canRead()) {
			logger.info("Found in 'customDirectory' directory: " + f.getAbsolutePath());
			return f;
		} else {
			logger.info("Not found in 'customDirectory' directory or file not readable: " + f.getAbsolutePath());
		}

		URL fileUrl = ClassLoader.getSystemResource(resourceDirectory + "/" + filename);

		if (fileUrl != null) {
			String fileLocation = fileUrl.getFile();
			f = new File(fileLocation);
			if (f.exists()) {
				logger.info("Found in SystemResource Directory/resourceDirectory: " + f.getAbsolutePath());
				return f;
			} else {
				logger.info("Not found in SystemResource Directory/resourceDirectory (this should never happen): "
						+ f.getAbsolutePath());
			}
		} else {
			logger.info("Not found in SystemResource Directory/resourceDirectory: " + resourceDirectory + File.separator
					+ filename);
		}
		return null;
	}

	private Properties loadConfigurationFromClasspath(String fileName) throws IllegalArgumentException {
		Properties result = null;
		InputStream in = null;
		try {
			String currentClasspathSearchLocation = "config/";
			in = getClass().getClassLoader().getResourceAsStream(currentClasspathSearchLocation);
			if (in != null) {
				result = new Properties();
				result.load(in);
				logger.info("SUCCESSFULLY LOADED " + fileName + " via the CLASSPATH from '"
						+ currentClasspathSearchLocation + "' using current thread context class loader!");
			}
		} catch (Exception e) {
			result = null;

		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}

		if (result == null) {
			throw new IllegalArgumentException("Failed to load " + resourceFile + " as a classloader resource.");
		}
		return result;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}