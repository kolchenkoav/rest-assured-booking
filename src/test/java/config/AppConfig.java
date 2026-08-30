package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties props = new Properties();
    private static String profile = getProfileFromSystemProperty();

    static {
        loadProperties();
    }

    private static String getProfileFromSystemProperty() {
        String profile = System.getProperty("profile", "local");
        String envProfile = System.getenv("PROFILE");
        return envProfile != null ? envProfile : profile;
    }

    private static void loadProperties() {
        try (InputStream input = AppConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("application.properties not found");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    public static String getBaseUri() {
        return props.getProperty(profile + ".BASE_URI");
    }

    public static String getUsername() {
        return props.getProperty(profile + ".USERNAME");
    }

    public static String getPassword() {
        return props.getProperty(profile + ".PASSWORD");
    }

    public static String getProfile() {
        return profile;
    }
}

