package Delivery.services;

/**
 * Created by igor on 22.05.17.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
