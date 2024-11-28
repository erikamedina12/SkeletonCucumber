package config.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverProperties {

    private static Properties PROPERTIES = new Properties();

    private static final String GLOBAL_DATA_FILE_LOCATION = "/webDriverTest.properties";

    public String PLATFORM_NAME = null;

    public String ENVIRONMENT = null;

    public String CLIENT = null;

    public WebDriverProperties() {
        initConfig();
    }

    public void initConfig() {
        try {
            InputStream input = WebDriverProperties.class.getResourceAsStream(GLOBAL_DATA_FILE_LOCATION);
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PLATFORM_NAME = PROPERTIES.getProperty("webdriver.platformName");
        ENVIRONMENT = PROPERTIES.getProperty("webdriver.env");
        CLIENT = PROPERTIES.getProperty("webdriver.client");
    }

    public String getURL_BASE() {
        String rawUrlBase = String.format("webdriver.%s.urlBase.%s", getEnvironment(), getClient());
        return PROPERTIES.getProperty(rawUrlBase);
    }

    public String getPlatformName() {
        return PLATFORM_NAME;
    }

    public String getEnvironment() {
        return ENVIRONMENT;
    }

    public String getClient() {
        return CLIENT;
    }

    public String getMAIN_USERNAME() {
        String rawMainUserName = String.format("webdriver.%s.adminUser.%s", getEnvironment(), getClient());
        return PROPERTIES.getProperty(rawMainUserName);
    }

    public String getMAIN_PASSWORD() {
        String rawMainUserPass = String.format("webdriver.%s.adminUserPass.%s", getEnvironment(), getClient());
        return PROPERTIES.getProperty(rawMainUserPass);
    }


}
