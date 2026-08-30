package config;

public class BookingConfig {
    public static final String BASE_URI = AppConfig.getBaseUri();

    /** Учётные данные для POST /auth (см. doc/Restful-booker-API.md, Auth - CreateToken). */
    public static final String USERNAME = AppConfig.getUsername();
    public static final String PASSWORD = AppConfig.getPassword();
}
