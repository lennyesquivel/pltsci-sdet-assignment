package org.example.utils;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {

    private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

    public static final Properties props = new Properties();

    static {
        try {
            InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream("test.properties");
            props.load(input);
        } catch (Exception ex) {
            log.error("Caught exception while doing setup: " + ex.getMessage());
        }
    }

}
